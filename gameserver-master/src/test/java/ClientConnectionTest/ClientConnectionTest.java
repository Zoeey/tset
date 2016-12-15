package ClientConnectionTest;

import main.MainServer;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by User on 26.11.2016.
 */
public class ClientConnectionTest {
    MainServer ms;
    @Test
    public void handlePacketsTest(){
        ms = new MainServer("handlePacketsTestConfig.properties");
        ms.start();
    }

    @Test
    public void FileAndLeaderTest(){
        ms = new MainServer("FileAndLeader.properties");
        ms.start();
    }

    @Test
    public void Fileest(){
        ms = new MainServer("File.properties");
        ms.start();
    }

    @Test
    public void LeaderTest(){
        ms = new MainServer("Leader.properties");
        ms.start();
    }

}
