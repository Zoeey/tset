package model.gameInfo;

/**
 * Created by User on 10.10.2016.
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y){
        setLocation(x,y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //TODO: check whether x and y are set correctly
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x + " " +
                "y=" + y +
                '}';
    }

    public static Location getRandomLocation(){
        return new Location(0,0);
    }


}
