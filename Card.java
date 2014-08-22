package prog2.tp2;

/**
 * Created by Martin Gutierrez.
 * User: Martin
 * Date: 19/08/11
 * Time: 23:01
 */
public class Card {
    private int suit;
    private int number;
    private int value;

    Card(int suit, int number, int value) {
        this.suit = suit;
        this.number = number;
        this.value = value;
    }

    Card(int suit, int number) {
        this.suit = suit;
        this.number = number;
        this.value = 0;
    }

    public int getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return getNumber() + "de" + getSuit();
    }
}