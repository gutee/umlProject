package prog2.tp2;

import java.util.ArrayList;

/**
 * Created by Martin Gutierrez.
 * User: Martin
 * Date: 26/08/11
 * Time: 07:49
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    public Card hand;

    public Player() {
        this.hand = new ArrayList();
    }

    public ArrayList getHand() {
        return hand;
    }

    public void add(Card card) {
        hand.add(card);
    }
}
