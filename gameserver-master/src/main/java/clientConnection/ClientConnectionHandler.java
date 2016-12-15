package clientConnection;

import clientConnection.JSONHelper.JSONHelper;
import clientConnection.handlers.PacketHandlerAuth;
import clientConnection.handlers.PacketHandlerEjectMass;
import clientConnection.handlers.PacketHandlerMove;
import clientConnection.handlers.PacketHandlerSplit;
import com.google.gson.JsonObject;
import main.ApplicationContext;
import model.gameInfo.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.jetbrains.annotations.NotNull;
import protocol.*;

import java.util.Map;

public class ClientConnectionHandler extends WebSocketAdapter {
    private final static @NotNull
    Logger log = LogManager.getLogger(ClientConnectionHandler.class);

    @Override
    public void onWebSocketConnect(@NotNull Session sess) {
        super.onWebSocketConnect(sess);
        log.info("Socket connected: " + sess);
    }

    @Override
    public void onWebSocketText(@NotNull String message) {
        super.onWebSocketText(message);
        log.info("Received packet: " + message);
        if (getSession().isOpen()) {
        handlePacket(message);
        }
    }

    @Override
    public void onWebSocketClose(int statusCode, @NotNull String reason) {
        super.onWebSocketClose(statusCode, reason);
        log.info("Socket closed: [" + statusCode + "] " + reason);
        ClientConnections clientConnections = ApplicationContext.get(ClientConnections.class);
        clientConnections.removeConnection(clientConnections.getConnectedPlayer(getSession()));
    }

        @Override
        public void onWebSocketError(@NotNull Throwable cause) {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }

    public void handlePacket(@NotNull String msg) {
        JsonObject json = JSONHelper.getJSONObject(msg);
        String name = json.get("command").getAsString();
        switch (name) {
            case CommandAuth.NAME:
                new PacketHandlerAuth(getSession(), msg);
                break;
            case CommandEjectMass.NAME:
                new PacketHandlerEjectMass(getSession(), msg);
                break;
            case CommandSplit.NAME:
                new PacketHandlerSplit(getSession(), msg);
                break;
            case CommandMove.NAME:
                new PacketHandlerMove(getSession(), msg);
                break;
        }
    }
}
