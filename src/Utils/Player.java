package Utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final List<Card> hand;
    private int tricks;
    private int roundPoints;
    private int gamePoints;
    private String name;

    public Player(){
        this.hand = new LinkedList<Card>();
        this.roundPoints = 0;
        this.gamePoints = 0;
        this.tricks = 0;
        this.name = "";
    }

    public Player(String name){
        this.hand = new LinkedList<Card>();
        this.roundPoints = 0;
        this.gamePoints = 0;
        this.tricks = 0;
        this.name = name;
    }

    public int getTricks(){
        return tricks;
    }

    public int getGamePoints() {
        return gamePoints;
    }

    public int getRoundPoints(){
        return roundPoints;
    }

    public void changeTricks(int num){
        tricks += num;
    }

    public void changeRoundPoints(int points){
        roundPoints += points;
    }

    public void changeGamePoints(int points){
        gamePoints += points;
    }

    public void zeroOutTricks(){
        tricks = 0;
    }

    public void zeroOutRoundPoints(){
        roundPoints = 0;
    }

    public void zeroOutGamePoints(){
        gamePoints = 0;
    }

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
    }

    /**
     * Sorts hand (Uses H>D>C>S by default)
     */
    public void sortHand(){
        Collections.sort(hand);
        //Collections.reverse(hand);
    }
}
