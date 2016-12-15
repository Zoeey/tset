package model.gameInfo.utils;

import model.gameInfo.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author apomosov
 */
public interface PlayerPlacer {
    void place(@NotNull Player player);
}
