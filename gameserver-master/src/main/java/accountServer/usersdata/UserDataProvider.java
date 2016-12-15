package accountServer.usersdata;

import accountServer.authentification.Authentification;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.authInfo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.10.2016.
 */
@Path("/data")
public class UserDataProvider {
    private static final Logger log = LogManager.getLogger(UserDataProvider.class);

    @GET
    @Path("/users")
    @Produces("application/json")
    public Response getLoggedInUsersList(){
        try{
            log.info("Users JSON requested");
            List<Token> tokens = Authentification.tokenDAO.getAll();
            ArrayList<User> loggedInUsers = new ArrayList<>();
            for(Token token: tokens){
                loggedInUsers.add(Authentification.userDAO.getUserById(token.getUserId()));
            }
            return Response.ok((new UsersJSON(loggedInUsers)).writeJson()).build();
        } catch (Exception e){
            log.info("Error sending users info");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("leaderboard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response getLeaders1(@QueryParam("N") int N){
        List<Leader> n1;
        n1= Authentification.LB.getAll(N);
        String S="{ ";
        if(N>n1.size()) N=n1.size();
        for(int i=0;i<n1.size();i++) {
            S += n1.get(i).toJSON();
            if (i<n1.size()-1) S+=", ";
        }
        S+=" }";
        log.info("S");
        return Response.ok(S).build();
    }

    /*static public String[] getLeaders() {
        String SJSON;
        try{
            FileReader FR = new FileReader("Leader.json");
            BufferedReader BFR = new BufferedReader(FR);
            SJSON = BFR.readLine();
        } catch (FileNotFoundException e) {
            log.info("LeaderJSON file not found");
            SJSON = "{ \"Leaders\": }";
        } catch (IOException e) {
            log.info("Cannot read JSON");
            SJSON = "{ \"Leaders\": }";
        }
        log.info(SJSON);
        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(SJSON).getAsJsonObject();
        JsonArray Leaders = mainObject.getAsJsonArray("Leaders");
        String[] S1 = new String[Leaders.size()];
        int i=0;
        for(JsonElement S: Leaders){

            S1[i]= S.getAsString();
            i++;
        }
        return S1;
    }*/
}
