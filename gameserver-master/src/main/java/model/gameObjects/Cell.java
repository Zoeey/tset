package model.gameObjects;

import model.gameInfo.Location;
import model.gameInfo.utils.SequentialIDGenerator;

/**
 * @author apomosov
 */
public abstract class Cell {
    public static final SequentialIDGenerator idGenerator = new SequentialIDGenerator();

    private Location location;
    private int radius;
    private int mass;

    public Cell(Location location, int mass) {
        this.location = location;
        this.mass = mass;
        updateRadius();
    }

    public Location getLocation(){return location;}

    public void setLocation(Location location){this.location = location;}

    public int getRadius() {
        return radius;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
        updateRadius();
    }

    private void updateRadius(){
    this.radius = (int) Math.sqrt(this.mass/Math.PI);
  }
}
