package model.gameInfo;

import model.gameInfo.utils.PlayerPlacer;
import model.gameInfo.utils.VirusGenerator;
import model.gameInfo.utils.FoodGenerator;
import model.gameObjects.Food;
import model.gameObjects.GameField;
import model.gameInfo.utils.SequentialIDGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static model.gameInfo.GameConstants.MAX_FOOD_ON_FIELD;

/**
 * @author apomosov
 */
public class GameSessionImpl implements GameSession {
    private final static Logger log = LogManager.getLogger(GameSessionImpl.class);
    private static final SequentialIDGenerator idGenerator = new SequentialIDGenerator();
    private final long id = idGenerator.next();
    @NotNull
    private final GameField field = new GameField();
    @NotNull
    private final List<Player> players = new ArrayList<>();
    @NotNull
    private final FoodGenerator foodGenerator;
    @NotNull
    private final PlayerPlacer playerPlacer;
    @NotNull
    private final VirusGenerator virusGenerator;

    public GameSessionImpl(@NotNull FoodGenerator foodGenerator, @NotNull PlayerPlacer playerPlacer, @NotNull VirusGenerator virusGenerator) {
        this.foodGenerator = foodGenerator;
        this.playerPlacer = playerPlacer;
        this.virusGenerator = virusGenerator;
        virusGenerator.generate();
    }

    @Override
    public void join(@NotNull Player player) {
        players.add(player);
        this.playerPlacer.place(player);
    }

    @Override
        public void leave(@NotNull Player player) {
        players.remove(player);
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public GameField getField() {
        return field;
    }

    @Override
    public String toString() {
        return "GameSessionImpl{" +
            "id=" + id +
            '}';
    }
    @Override
    public void genFood(){
        while(field.getFoods().size() < MAX_FOOD_ON_FIELD) {
            field.addFood(foodGenerator.generate());
        }
    }

    @Override
    public void genVirus(){
        while(field.getVirus().size() < 5){
            field.addVirus(virusGenerator.generate());
        }
    }
    public void Update(){
        log.info("Updating begin");
        List<Integer> DeleteIndex = new ArrayList<>();
        for(Player p: players) {
            for(Food f: field.getFoods()){
                float dist = (p.getCells().get(0).getLocation().getX()-f.getLocation().getX())*(p.getCells().get(0).getLocation().getX()-f.getLocation().getX()) + (p.getCells().get(0).getLocation().getY()-f.getLocation().getY())*(p.getCells().get(0).getLocation().getY()-f.getLocation().getY());
                if( Math.sqrt(dist) < p.getCells().get(0).getRadius()+f.getRadius()){
                    DeleteIndex.add(field.getFoods().indexOf(f));
                    p.addMass(f.getMass());
                    log.info("########################################################food updated");
                }
            }
        }
        for(Integer i : DeleteIndex){
            field.removeFood(i);
        }
        genFood();
    }
}
