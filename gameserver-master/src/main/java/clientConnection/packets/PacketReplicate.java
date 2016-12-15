package clientConnection.packets;

import clientConnection.JSONHelper.JSONHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandReplicate;
import protocol.model.Cell;
import protocol.model.pFood;
import protocol.model.pVirus;

import java.io.IOException;

public class PacketReplicate {
    @NotNull
    private static final Logger log = LogManager.getLogger(PacketReplicate.class);
    @NotNull
    private final Cell[] cells;
    @NotNull
    private final pFood[] food;
    @NotNull
    private final pVirus[] virus;

    public PacketReplicate(@NotNull Cell[] cells, @NotNull pFood[] food,@NotNull pVirus[] virus) {
        this.cells = cells;
        this.food = food;
        this.virus = virus;
    }

    public void write(@NotNull Session session) throws IOException {
        String msg = JSONHelper.toJSON(new CommandReplicate(food, cells,virus));
        log.info("Sending [" + msg + "]");
        session.getRemote().sendString(msg);
    }
}
