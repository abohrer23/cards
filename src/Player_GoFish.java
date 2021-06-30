import Utils.Card;
import Utils.Player;

import java.util.Arrays;
import java.util.LinkedList;

public class Player_GoFish extends Player {

    private int numBooks;
    private int gamePoints;
    private final int[] numValues;

    public Player_GoFish(){
        this.hand = new LinkedList<Card>();
        this.name = "";
        this.numBooks = 0;
        this.gamePoints = 0;
        this.numValues = new int[14];
    }

    public Player_GoFish(String name){
        this.hand = new LinkedList<Card>();
        this.name = name;
        this.numBooks = 0;
        this.gamePoints = 0;
        this.numValues = new int[14];
    }


    public int getNumBooks(){
        return numBooks;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public void changeBooks(int num){
        numBooks += num;
    }

    public void changeGamePoints(int points){
        gamePoints += points;
    }

    public void zeroOutBooks(){
        numBooks = 0;
    }

    public void zeroOutGamePoints(){
        gamePoints = 0;
    }

    public void addCard(Card c){
        hand.add(c);
        numValues[c.getValue()]++;
        if (numValues[c.getValue()]==4){
            numBooks++;
        }

    }

    public void removeCard(Card c){
        hand.remove(c);
        numValues[c.getValue()]--;
    }

    /**
     * Selects a card value to ask for
     * @return value to request
     */
    public int pickCardToRequest(){
        //select one of cards you have
        return valueMode();
    }

    /**
     * @return most common value in hand
     */
    private int valueMode(){
        int mode = 0;
        int index = 0;
        for (int i = 0; i < numValues.length; i++){
            if (numValues[i]>mode && numValues[i]!=4){
                index = i;
                mode = numValues[i];
            }
        }
        //if any duplicates
        if (mode > 1) {
            return index;
        } else {
            //pick random card from hand and ask for that value
            return hand.get((int)(Math.random()*hand.size())).getValue();
        }
    }

    public boolean done(){
        for (int numValue : numValues) {
            if (numValue != 0 && numValue != 4) {
                return false;
            }
        }
        return true;
    }

    /**
     * Respond to request for a card value
     * @param value value requested
     * @return a card with that value if player has one, or null
     */
    public Card respondToRequest(int value){
        shuffleHand();
        for (Card c : hand){
            if (c.getValue() == value){
                sortHand();
                return c;
            }
        }
        sortHand();
        return null;
    }

    public void clearNumVals(){
        Arrays.fill(numValues, 0);
    }
}
