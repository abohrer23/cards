package Utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    protected List<Card> hand;
    protected String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public void removeCard(Card c){
        hand.remove(c);
    }

    public void printHand(){
        System.out.print(name+": ");
        for (Card c : hand){
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Sorts hand (Uses H>D>C>S by default)
     */
    public void sortHand(){
        Collections.sort(hand);
        //Collections.reverse(hand);
    }

    public void shuffleHand(){
        Collections.shuffle(hand);
    }

    public void clearHand(){
        hand.clear();
    }
}
