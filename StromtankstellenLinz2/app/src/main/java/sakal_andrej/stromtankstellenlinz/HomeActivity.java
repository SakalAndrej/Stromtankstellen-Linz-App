package sakal_andrej.stromtankstellenlinz;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sakal_andrej.stromtankstellenlinz.model.Model;
import sakal_andrej.stromtankstellenlinz.model.Station;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG = "";

    //Names of the Energy Stations
    ArrayList<String> stationsNames;
    //Shows the Energy Station names
    ListView lv;


    private GoogleApiClient mGoogleApiClient;

    private LocationRequest mLocationRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lv = (ListView) findViewById(R.id.lvShowStations);

        //ReadCSV ----------------------
        final String FILENAME = this.getFilesDir().getAbsolutePath()+"/convertcsv.csv";
        ReadCsv(FILENAME, ",");
        ArrayAdapter<String> la = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_2,android.R.id.text1,stationsNames);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    //.addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                    .addApi(LocationServices.API)
                    .build();
        }

        lv.setAdapter(la);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HomeActivity.this,ShowSelectedStation.class);
                it.putExtra("ITEM_ID", Model.getInstance().getStationsList().get(i).getId());
                it.putExtra("ITEM_NR",i);
                startActivity(it);
            }
        });
    }

    /**
     * Read the CSV files with the Energy Stations
     * @param filename
     * @param s
     */
    private void ReadCsv(String filename, String s) {
        String line = "";
        String[] col;
        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));
            //stationsList = new ArrayList<Station>();
            stationsNames = new ArrayList<String>();
            //Overwrite headline
            br.readLine();
            while((line = br.readLine()) != null) {
                col = line.split(s);
                    Station station = new Station(col[1], col[3],col[8]);
                    stationsNames.add(col[1]);
                    Model.getInstance().getStationsList().add(station);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            TextView t = (TextView) findViewById(R.id.tvName);
            t.setText( String.valueOf(mLastLocation.getLatitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient connection has been suspend");
    }
}
