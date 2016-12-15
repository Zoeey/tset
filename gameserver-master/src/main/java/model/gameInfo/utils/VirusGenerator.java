package model.gameInfo.utils;

import model.gameObjects.Virus;
import ticker.Tickable;

/**
 * @author apomosov
 */
public interface VirusGenerator extends Tickable {
    Virus generate();
}
