package prog2.tp3;

/**
 * Created by Martin Gutierrez.
 * User: Martin
 * Date: 26/08/11
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public class Dice {
    public static final int SIDES = 6;
    private int sides;
    private int value;

    Dice(int sides, int value) {
        this.sides = sides;
        this.value = value;
    }

    Dice(int sides) {
        this.sides = sides;
        this.value = assignRandomValue(sides);

    }

    public Dice() {
        this(SIDES);
    }

    private int assignRandomValue(int sides) {
        return (int) (Math.random() * sides) + 1;
    }

    public int getValue() {
        return value;
    }

    public void roll() {
        value = assignRandomValue(sides);
    }
}