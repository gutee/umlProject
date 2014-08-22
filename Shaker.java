package prog2.tp3;

import java.util.ArrayList;
import algoritmos.tpa3.palindromo.AnalizadorPalindromo;

/**
 * Created by Martin Gutierrez.
 * User: Martin
 * Date: 31/08/11
 * Time: 02:33
 * To change this template use File | Settings | File Templates.
 */
public class Shaker extends AnalizadorPalindromo{
    private ArrayList shaker;

    Shaker(ArrayList shaker) {
        this.shaker = shaker;
    }

    public static Shaker createShaker(int sides, int dices) {
        ArrayList shaker = new ArrayList();
        for (int i = 0; i < dices; i++) {
            shaker.add(new Dice(sides));
        }
        return new Shaker(shaker);
    }

    public void rollDices() {
        for (int i = 0; i < shaker.size(); i++) {
            Dice dice = (Dice) (shaker.get(i));
            dice.roll();
        }
    }
}