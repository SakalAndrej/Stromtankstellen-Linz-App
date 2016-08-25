package sakal_andrej.stromtankstellenlinz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import sakal_andrej.stromtankstellenlinz.model.Model;

public class ShowSelectedStation extends AppCompatActivity {

    EditText showName;
    HomeActivity homeActivity;
    String selected_item_id;
    int selected_item_nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_station);
        homeActivity = new HomeActivity();
        showName = (EditText) findViewById(R.id.teInformation);
        if (this.getIntent().hasExtra("ITEM_ID") && this.getIntent().hasExtra("ITEM_NR")) {
            selected_item_id = getIntent().getStringExtra("ITEM_ID");
            selected_item_nr = getIntent().getIntExtra("ITEM_NR",0);
            if (selected_item_nr >= Model.getInstance().getStationsList().size() || selected_item_nr < 0)
            {

            }
            else {
                showName.setText(Model.getInstance().getStationsList().get(selected_item_nr).getName());
            }
        }

    }
}
