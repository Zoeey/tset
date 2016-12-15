package replication;

import clientConnection.ClientConnections;
import clientConnection.packets.PacketReplicate;
import main.ApplicationContext;
import matchmaker.MatchMaker;
import model.gameInfo.GameSession;
import model.gameInfo.Player;
import model.gameObjects.Food;
import model.gameObjects.PlayerCell;
import model.gameObjects.Virus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import protocol.model.Cell;
import protocol.model.pFood;
import protocol.model.pVirus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Max on 29.11.2016.
 */
public class DefaultReplication implements Replicator {
    private final static Logger log = LogManager.getLogger(DefaultReplication.class);
    @Override
    public void replicate() {
        for (GameSession gameSession : ApplicationContext.get(MatchMaker.class).getActiveGameSessions()) {
            int i =0;
            List<Virus> virus1 = gameSession.getField().getVirus();
            pVirus[] virus = new pVirus[virus1.size()];
            log.info("###########3"+gameSession.getField().getVirus().size());
            for(Virus v: virus1){
                virus[i] = new pVirus(v.getLocation().getX(),v.getLocation().getY());
                i++;
            }
            List<Food> food1 = gameSession.getField().getFoods();
            pFood[] food = new pFood[food1.size()];
            i=0;
            for(Food f: food1){
                food[i]= new pFood(f.getLocation().getX(),f.getLocation().getY());
                i++;
            }
            int numberOfCellsInSession = 0;
            for (Player player : gameSession.getPlayers()) {
                numberOfCellsInSession += player.getCells().size();
            }
            Cell[] cells = new Cell[numberOfCellsInSession];
            i = 0;
            for (Player player : gameSession.getPlayers()) {
                for (PlayerCell playerCell : player.getCells()) {
                    cells[i] = new Cell(playerCell.getId(), player.getId(), playerCell.getRadius(), playerCell.getLocation().getX(), playerCell.getLocation().getY());
                    i++;
                }
            }
            for (Map.Entry<Player, Session> connection : ApplicationContext.get(ClientConnections.class).getConnections()) {
                if (gameSession.getPlayers().contains(connection.getKey()) && connection.getValue().isOpen()) {
                    try {
                        new PacketReplicate(cells, food,virus).write(connection.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
