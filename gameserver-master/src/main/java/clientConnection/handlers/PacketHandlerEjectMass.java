package clientConnection.handlers;

import clientConnection.ClientConnectionServer;
import clientConnection.ClientConnections;
import clientConnection.JSONHelper.JSONDeserializationException;
import clientConnection.JSONHelper.JSONHelper;
import main.ApplicationContext;
import mechanics.Mechanics;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import messageSystem.messages.EjectMassMsg;
import model.gameInfo.Player;
import org.eclipse.jetty.websocket.api.Session;
import org.jetbrains.annotations.NotNull;
import protocol.CommandEjectMass;

public class PacketHandlerEjectMass {
    public PacketHandlerEjectMass(@NotNull Session session, @NotNull String json) {
        CommandEjectMass commandEjectMass;
        try {
            commandEjectMass = JSONHelper.fromJSON(json, CommandEjectMass.class);
        } catch (JSONDeserializationException e) {
            e.printStackTrace();
            return;
        }
        MessageSystem messageSystem = ApplicationContext.get(MessageSystem.class);
        Address from = messageSystem.getService(ClientConnectionServer.class).getAddress();
        Address to = messageSystem.getService(Mechanics.class).getAddress();
        Player currentPlayer = ApplicationContext.get(ClientConnections.class).getConnectedPlayer(session);
        Message message = new EjectMassMsg(from, to, currentPlayer );
        messageSystem.sendMessage(message);
    }
}
