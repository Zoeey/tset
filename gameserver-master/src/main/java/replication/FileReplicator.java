package replication;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.ApplicationContext;
import matchmaker.MatchMaker;
import model.gameInfo.GameConstants;
import model.gameInfo.GameSession;
import model.gameInfo.Player;
import clientConnection.ClientConnections;
import clientConnection.packets.PacketReplicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import protocol.model.Cell;
import protocol.model.pFood;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Max on 28.11.2016.
 */
public class FileReplicator implements Replicator{
    private static final Logger log = LogManager.getLogger(FileReplicator.class);

    @Override
    public void replicate() {
        /*String SJSON;
        try{
            FileReader FR = new FileReader("replica.json");
            BufferedReader BFR = new BufferedReader(FR);
            SJSON = BFR.readLine();
        } catch (FileNotFoundException e) {
            SJSON = "{ \"Food\": [],\"Cells\":[] }";
            log.info("FNFE");
        } catch (IOException e) {
            SJSON = "{ \"Food\": [],\"Cells\":[] }";
            log.info("readtrouble");
        }
        log.info(SJSON);
        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(SJSON).getAsJsonObject();
        JsonArray jsonFood = mainObject.getAsJsonArray("food");
        JsonArray jsonCells = mainObject.getAsJsonArray("cells");
        Food[] food = new Food[jsonFood.size()];
        Cell[] cell = new Cell[jsonCells.size()];
        int i = 0;
        for(JsonElement f:jsonFood){
            JsonObject jsonf=f.getAsJsonObject();
            Food f1= new Food(jsonf.get("cellId").getAsInt(), GameConstants.FOOD_MASS,jsonf.get("x").getAsInt(),jsonf.get("y").getAsInt());
            food[i]=f1;
            i++;
        }
        i=0;
        for(JsonElement c:jsonCells){
            JsonObject jsonc=c.getAsJsonObject();
            Cell c1= new Cell(jsonc.get("cellId").getAsInt(),jsonc.get("playerId").getAsInt(),jsonc.get("isVirus").getAsBoolean(),jsonc.get("size").getAsFloat(),jsonc.get("x").getAsInt(),jsonc.get("y").getAsInt());
            cell[i]=c1;
            i++;
        }
        for (GameSession gameSession : ApplicationContext.get(MatchMaker.class).getActiveGameSessions()) {
            for (Map.Entry<Player, Session> connection : ApplicationContext.get(ClientConnections.class).getConnections()) {
                if (gameSession.getPlayers().contains(connection.getKey())) {
                    try {
                        new PacketReplicate(cell, food).write(connection.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/


    }
}
