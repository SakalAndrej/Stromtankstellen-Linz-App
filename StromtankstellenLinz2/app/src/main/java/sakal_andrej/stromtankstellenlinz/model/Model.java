package sakal_andrej.stromtankstellenlinz.model;

import java.util.ArrayList;

/**
 * Created by sakal_andrej on 25.08.16.
 */
public class Model {
    private ArrayList<Station> stationsList;
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
}
