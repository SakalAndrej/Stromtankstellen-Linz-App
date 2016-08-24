package sakal_andrej.stromtankstellenlinz.model;

/**
 * Created by sakal_andrej on 24.08.16.
 */
public class Station {

    private int xPos;
    private int yPos;
    private String Name;
    private String type;


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
