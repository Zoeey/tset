package model.gameInfo.utils;

import model.gameInfo.Location;
import model.gameInfo.utils.FoodGenerator;
import model.gameObjects.Food;
import model.gameObjects.GameField;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author apomosov
 */
public class UniformFoodGenerator implements FoodGenerator {
    /*@NotNull
    private final GameField field;
    private final int threshold;
    private final double foodPerSecond;

    public UniformFoodGenerator(@NotNull GameField field, double foodPerSecond, int threshold) {
        this.field = field;
        this.threshold = threshold;
        this.foodPerSecond = foodPerSecond;
    }

    @Override
    public void tick(long elapsedNanos) {
        if (field.getFoods().size() <= threshold) {
        int toGenerate = (int) Math.ceil(foodPerSecond * elapsedNanos / 1_000_000_000.);
        }
    }*/

    @NotNull
    private final GameField field;
    private final int threshold;
    private final double foodPerSecond=0.1;
    Random random = new Random();



    @Override
    public void tick(long elapsedNanos) {

    }
    public UniformFoodGenerator(@NotNull GameField field,int threshold) {
        this.field = field;
        this.threshold = threshold;
    }
    @Override
    public Food generate(){
        return new Food(new Location(10+random.nextInt(field.getWidth()-20),10+random.nextInt(field.getHeight()-20)));
    }

}
