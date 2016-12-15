package accountServer;

import accountServer.authentification.Authentification;
import main.ApplicationContext;
import main.Service;
import messageSystem.MessageSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import accountServer.authentification.AutorizationFilter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by User on 20.10.2016.
 */
public class AccountServer extends Service {

    private final int port;
    private static final Logger log = LogManager.getLogger(AccountServer.class);

    public AccountServer(int port){
        super("account_server_on_port_8081");
        log.info("account server start");
        this.port = port;
    }

    public void startServer(){

        Server gameServer = new Server(port);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");

        gameServer.setHandler(contextHandler);

        ServletHolder jerseyServlet = contextHandler.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "accountServer"
        );

        jerseyServlet.setInitParameter(
                "com.sun.jersey.spi.container.ContainerRequestFilters",
                AutorizationFilter.class.getCanonicalName()
        );

        try {
            gameServer.start();
            Authentification.userDAO.getAll();
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.info("Error creating server:\n" + sw.toString());
        }
    }

    @Override
    public void run(){
        startServer();
        while(true) {
            ApplicationContext.get(MessageSystem.class).executeForService(this);
        }
    }

    public static void main(String[] args) {
        new AccountServer(8081).run();
    }

}
