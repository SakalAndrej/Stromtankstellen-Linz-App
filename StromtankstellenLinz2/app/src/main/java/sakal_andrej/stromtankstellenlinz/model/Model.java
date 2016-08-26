package sakal_andrej.stromtankstellenlinz.model;

import android.location.Location;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by sakal_andrej on 25.08.16.
 */
public class Model {
    private ArrayList<Station> stationsList;
    private ArrayList<Station> lastSearched;
    private static Model instance = null;

    public static Model getInstance() {
        if (instance==null)
            instance = new Model();
        return instance;
    }

    private Model() {
        stationsList = new ArrayList<Station>();
    }

    public ArrayList<Station> getStationsList() {
        return stationsList;
    }

    public void setStationsList(ArrayList<Station> stationsList) {
        this.stationsList = stationsList;
    }

    public ArrayList<Station> SearchByString(String s) {
        lastSearched = new ArrayList<>();
        if (lastSearched.size() <= 0) {
            for (int i = 0; i < stationsList.size(); i++) {
                if (stationsList.get(i).getName().contains(s)) {
                    lastSearched.add(stationsList.get(i));
                }
            }
        }
        else {
            int length = lastSearched.size();
            for (int i = 0; i < length; i++) {
                if (lastSearched.get(i).getName().contains(s)) {
                    lastSearched.add(lastSearched.get(i));
                }
            }
        }
        return lastSearched;
    }

    public void calculatingDistance(double longi, double lati) {
        Location currLocation = new Location("Me");
        currLocation.setLongitude(longi);
        currLocation.setLatitude(lati);

        for (int i=0; i < stationsList.size();i++) {
            Location stationLocation = new Location("Station");
            stationLocation.setLongitude(stationsList.get(i).getxPos());
            stationLocation.setLatitude(stationsList.get(i).getyPos());
            stationsList.get(i).setDistance(currLocation.distanceTo(stationLocation));
        }
    }
}
