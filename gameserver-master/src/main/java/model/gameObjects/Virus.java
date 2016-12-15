package model.gameObjects;

import model.gameInfo.GameConstants;
import model.gameInfo.Location;

/**
 * @author apomosov
 */
public class Virus extends Cell {
    public Virus(Location location) {
        super(location, GameConstants.VIRUS_MASS);
    }
}
