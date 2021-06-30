import Utils.Card;
import Utils.Deck;
import Utils.Player;
import Utils.Suit;

import java.util.LinkedList;
import java.util.List;

public class GoFish extends Game {
    private final Deck deck;
    public final int numPlayers;
    public final int numStartingCards;
    public final List<Player_GoFish> players;
    public final String name = "Go Fish";
    public int round;
    public int turn;


    public GoFish(){
       this.deck = new Deck("standard");
        this.numPlayers = 4;
        if (numPlayers == 2 || numPlayers == 3){
            numStartingCards = 7;
        } else {
            numStartingCards = 5;
        }
        this.players = new LinkedList<Player_GoFish>();

        players.add(new Player_GoFish("West"));
        players.add(new Player_GoFish("North"));
        players.add(new Player_GoFish("East"));
        players.add(new Player_GoFish("South")); //TODO allow a user to play


        this.round = 0;
        this.turn = 0;
    }

    public void dealCards() {
        deck.shuffle();

        int j = 0;
        for (int i = 0; i < numStartingCards*numPlayers; i++) {
            players.get(j%4).addCard(deck.pop());
            j++;
        }
        for (Player_GoFish p : players){
            p.sortHand();
        }
    }


    public void playGame(){
        System.out.println("Welcome to "+name);
        dealCards();

        for (Player_GoFish p : players){
            p.printHand();
        }
        System.out.println("Deck: " + deck);
        System.out.println();

        while (!gameOver()){
            turn();
        }

        int maxBooks = 0;
        Player_GoFish winner = null;
        for (Player_GoFish p : players){
            if (p.getNumBooks() > maxBooks){
                maxBooks = p.getNumBooks();
                winner = p;
            }
        }
        winner.changeGamePoints(1);
        System.out.println("Game ended! "+winner.getName() + " won with " + maxBooks + " books.");

        for (Player_GoFish p : players) {
            p.zeroOutBooks();
            p.clearHand();
            p.clearNumVals();
        }
    }

    boolean gameOver() {
        if (deck.isEmpty()) {
            for (Player_GoFish p : players) {
                if (!p.done()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void round() {
//        while (!gameOver()){
//            turn();
//        }
//        round++;
    }

    public void turn() {
        Player_GoFish active = players.get(turn%numPlayers);

        //TODO intelligent subject choice
        //ask the player to your left
        int subjectNum = (int)(Math.random()*numPlayers);
        while (subjectNum == (turn%numPlayers)) {
            subjectNum = (int)(Math.random()*numPlayers);
        }
        Player_GoFish subject = players.get(subjectNum);

//        active.printHand();
//        subject.printHand();
        if (!active.getHand().isEmpty()) {
            int req = active.pickCardToRequest();
            System.out.println(active.getName() + " asks " + subject.getName() + " for a " + convertValueToPrint(req));
            Card resp = subject.respondToRequest(req);
            if (resp != null) {
                System.out.println(subject.getName() + " gives " + active.getName() + " a " + resp);
                active.addCard(resp);
                subject.removeCard(resp);
            } else {
                System.out.print(subject.getName() + " says: \"Go Fish!\"");
                if (!deck.isEmpty()) {
                    Card drawn = cardFromDeck();
                    active.addCard(drawn);
                    System.out.println(". "+active.getName() + " picked up " + drawn);
                } else {
                    System.out.println(". There are no cards left");
                }
            }
        }
//        for (Player p : players){
//            p.printHand();
//        }
//        System.out.println("Deck: "+deck);
        System.out.println();
        turn++;
    }

    private Card cardFromDeck(){
//        if (deck.isEmpty()){
//            return null;
//        }
        return deck.pop();
    }

    public static void main (String[] args){
       GoFish gf = new GoFish();
       gf.playGame();
    }

    private String convertValueToPrint(int value){
        String out = "";
        if (value == 1){
            out += "A";
        } else if (value == 11){
            out+="J";
        } else if (value == 12){
            out+="Q";
        } else if (value == 13){
            out+="K";
        } else {
            out+=value;
        }
        return out;
    }
}
