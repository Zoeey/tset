package messageSystem.messages;

import clientConnection.ClientConnectionServer;
import main.ApplicationContext;
import main.Service;
import messageSystem.Address;
import messageSystem.Message;
import messageSystem.MessageSystem;
import leaderboardReplicator.LeaderboardReplicator;
import replication.Replicator;

/**
 * Created by User on 28.11.2016.
 */
public class ReplicateLeaderBoardMsg extends Message {
    public ReplicateLeaderBoardMsg(Address from) {
        super(from, ApplicationContext.get(MessageSystem.class).getService(ClientConnectionServer.class).getAddress());
    }

    @Override
    public void execute(Service service) {
        ApplicationContext.get(LeaderboardReplicator.class).replicate();
    }
}
