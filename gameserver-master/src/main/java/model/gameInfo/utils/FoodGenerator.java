package model.gameInfo.utils;

import model.gameObjects.Food;
import ticker.Tickable;

/**
 * @author apomosov
 */
public interface FoodGenerator extends Tickable {
    Food generate();
}
