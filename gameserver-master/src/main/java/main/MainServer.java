package main;

import accountServer.AccountServer;
import clientConnection.ClientConnectionServer;
import clientConnection.ClientConnections;
import leaderboardReplicator.LeaderboardReplicator;
import matchmaker.MatchMaker;
import matchmaker.MatchMakerImpl;
import mechanics.Mechanics;
import messageSystem.MessageSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import replication.Replicator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by User on 26.11.2016.
 */
public class MainServer  {

    private final static Logger log = LogManager.getLogger(MainServer.class);
    private Properties properties = new Properties();

    public static void main(String[] args) {
        new MainServer("handlePacketsTestConfig.properties").start();
    }

    public MainServer(String configFileName){
        try {
            properties.load(new FileInputStream(getClass().getClassLoader().getResource(configFileName).getFile()));
        } catch(IOException e){
            log.info("Error opening properties file");
            e.printStackTrace();
        }
    }

    public void start(){
        log.info("main server start");

        try {
            int accountServerPort = Integer.parseInt(properties.getProperty("accountServerPort"));
            int clientConnectionPort = Integer.parseInt(properties.getProperty("clientConnectionPort"));
            Class matchMakerClass = Class.forName(properties.getProperty("matchMaker"));
            Class replicatorClass = Class.forName(properties.getProperty("replicator"));
            Class leaderboardReplicatorClass = Class.forName(properties.getProperty("leaderboardReplicator"));
            List<String> servicesNames = Arrays.asList(properties.getProperty("services").split(", "));

            MessageSystem messageSystem = new MessageSystem();
            ApplicationContext.put(MessageSystem.class, messageSystem);
            ApplicationContext.put(MatchMaker.class, matchMakerClass.newInstance());
            ApplicationContext.put(ClientConnections.class, new ClientConnections());
            ApplicationContext.put(Replicator.class, replicatorClass.newInstance());
            ApplicationContext.put(LeaderboardReplicator.class, leaderboardReplicatorClass.newInstance());

            if(servicesNames.contains("accountServer")) {
                messageSystem.registerService(AccountServer.class, new AccountServer(accountServerPort));
            }
            if(servicesNames.contains("clientConnection")) {
                messageSystem.registerService(ClientConnectionServer.class, new ClientConnectionServer(clientConnectionPort));
            }
            if(servicesNames.contains("mechanics")) {
                messageSystem.registerService(Mechanics.class, new Mechanics());
            }

            messageSystem.getServices().forEach(Service::start);
            for (Service service : messageSystem.getServices()) {
                service.join();
            }
        } catch (Throwable e){
            log.info("Error getting properties instance");
            e.printStackTrace();
        }

    }
}
