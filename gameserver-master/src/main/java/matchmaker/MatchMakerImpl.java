package matchmaker;

import model.gameInfo.*;
import model.gameInfo.utils.RandomPlayerPlacer;
import model.gameInfo.utils.UniformVirusGenerator;
import model.gameObjects.GameField;
import model.gameInfo.utils.UniformFoodGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static model.gameInfo.GameConstants.MAX_PLAYERS_IN_SESSION;

/**
 * Creates {@link GameSession} for single player
 *
 * @author Alpi
 */
public class MatchMakerImpl implements MatchMaker {
    @NotNull
    private final Logger log = LogManager.getLogger(MatchMakerImpl.class);
    @NotNull
    private final List<GameSession> activeGameSessions = new ArrayList<>();

  /**
   * Creates new GameSession for single player
   *
   * @param player single player
   */
    @Override
    public void joinGame(@NotNull Player player) {
        if(activeGameSessions.size() == 0) {
            GameSession newGameSession = createNewGame();
            activeGameSessions.add(newGameSession);
            newGameSession.genFood();
            newGameSession.genVirus();
            newGameSession.join(player);
            log.info(player + " joined " + newGameSession);
        }
        else {
            if(activeGameSessions.get(activeGameSessions.size() - 1).getPlayers().size() < MAX_PLAYERS_IN_SESSION ) {
                activeGameSessions.get(activeGameSessions.size() - 1).join(player);
                log.info(player + " joined " + activeGameSessions.get(0));
            }
            else{
                GameSession newGameSession = createNewGame();
                activeGameSessions.add(newGameSession);
                newGameSession.genFood();
                newGameSession.genVirus();
                newGameSession.join(player);
                log.info(player + " joined " + newGameSession);
            }
        }
    }

    @NotNull
    public List<GameSession> getActiveGameSessions() {
    return new ArrayList<>(activeGameSessions);
  }

  /**
   * @return new GameSession
   */
    private
    @NotNull
    GameSession createNewGame() {
        GameField field = new GameField();
        //TODO
        //Ticker ticker = ApplicationContext.instance().get(Ticker.class);
        UniformFoodGenerator foodGenerator = new UniformFoodGenerator(field, GameConstants.MAX_FOOD_ON_FIELD);
        //ticker.registerTickable(foodGenerator);
        return new GameSessionImpl(foodGenerator, new RandomPlayerPlacer(field), new UniformVirusGenerator(field, GameConstants.NUMBER_OF_VIRUSES));
    }
}
