package zagar.network.handlers;

import java.nio.ByteBuffer;
import java.util.Collections;
import protocol.model.pFood;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import protocol.CommandLeaderBoard;
import protocol.CommandReplicate;
import protocol.model.pVirus;
import zagar.GameConstants;
import zagar.util.JSONDeserializationException;
import zagar.util.JSONHelper;
import zagar.view.Cell;
import zagar.Game;
import org.jetbrains.annotations.NotNull;
import zagar.view.Food;
import zagar.view.Virus;

public class PacketHandlerReplicate {
  @NotNull
  private static final Logger log = LogManager.getLogger(PacketHandlerReplicate.class);

  public PacketHandlerReplicate(@NotNull String json) {
    CommandReplicate commandReplicate;
    try {
      commandReplicate = JSONHelper.fromJSON(json, CommandReplicate.class);
    } catch (JSONDeserializationException e) {
      e.printStackTrace();
      return;
    }
    int iSize = commandReplicate.getCells().length+commandReplicate.getFood().length;
    Cell[] gameCells = new Cell[commandReplicate.getCells().length];
    Food[] gameFood = new Food[commandReplicate.getFood().length];
    Virus[] gameVirus = new Virus[commandReplicate.getVirus().length];
    for (int i = 0; i < commandReplicate.getCells().length; i++) {
      protocol.model.Cell c = commandReplicate.getCells()[i];
      gameCells[i] = new Cell(c.getX(), c.getY(), c.getSize(), c.getCellId());
    }
    for (int i = 0; i < commandReplicate.getFood().length; i++) {
      pFood c = commandReplicate.getFood()[i];
      gameFood[i] = new Food(c.getX(), c.getY());
    }
    for (int i = 0; i < commandReplicate.getVirus().length; i++) {
      pVirus c = commandReplicate.getVirus()[i];
      gameVirus[i] = new Virus(c.getX(), c.getY());
    }
    Game.player.clear();
    Collections.addAll(Game.player, gameCells);
    Game.cells = gameCells;
    Game.food = gameFood;
    Game.virus = gameVirus;
  }
}
