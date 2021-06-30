package Utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private final LinkedList<Card> cards;

    /**
     * @param type what kind of game (standard, euchre, etc)
     */
    public Deck(String type){
        this.cards = new LinkedList<Card>();
        if (type.contains("standard") ||type.contains("normal")||type.contains("regular")){
            //52 card deck
            for (Suit s: Suit.values()){
                if (!s.equals(Suit.NONE)) {
                    for (int i = 1; i <= 13; ++i) {
                        cards.add(new Card(s, i));
                    }
                }
            }
       // } else if (type.contains("500")){ off until joker support added
            //43 card deck
        } else if (type.contains("euchre")){
            //24 card deck
            for (Suit s: Suit.values()){
                if (!s.equals(Suit.NONE)) {
                    for (int i = 9; i <= 13; ++i) {
                        cards.add(new Card(s, i));
                    }
                }
            }
        } else {
            //52 card for default case
             for (Suit s: Suit.values()){
                 if (!s.equals(Suit.NONE)) {
                     for (int i = 1; i <= 13; ++i) {
                         cards.add(new Card(s, i));
                     }
                 }
             }
        }
    }

    public String toString(){
        String out = "";
        for (Card c : cards){
            out += c.toString();
            out += " ";
        }
        return out;
    }

    public List<Card> getCards(){
        return cards;
    }

    public Card pop(){
        return cards.pop();
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
}
