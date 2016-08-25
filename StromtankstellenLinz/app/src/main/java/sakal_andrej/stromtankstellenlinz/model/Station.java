package sakal_andrej.stromtankstellenlinz.model;

/**
 * Created by sakal_andrej on 24.08.16.
 */
public class Station {

    private double xPos;
    private double yPos;
    private double distance;
    private String id;
    private String name;
    private String type;
    private String position;

    public Station(String _name, String s, String _id) {
        this.name = _name;
        this.position = s;
        this.id = _id;
        ConvertPositionInXY(s);
    }

    private void ConvertPositionInXY(String s) {
        String[] splitted = s.split(" ");
        this.setxPos(Double.parseDouble(splitted[0]));
        this.setyPos(Double.parseDouble(splitted[1]));
        if (!splitted[2].equals("0")) {
            System.out.println("!!!! ERROR IN POSITON 3RD POINT IS NOT 0 !!!!");
        }
    }


    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
