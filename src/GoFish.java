import Utils.Card;
import Utils.Deck;
import Utils.Player;

import java.util.LinkedList;
import java.util.List;

public class GoFish extends Game {
    private final Deck deck;
    public final int numPlayers;
    public final List<Player> players;
    public final String name = "Go Fish";
    public Player dealer;
    public Player current;
    public Player leading;

    public void dealCards() {
        deck.shuffle();
        int i = 0;
        for (Card c : deck.getCards()) {
            players.get(i % players.size()).addCard(c);
            i++;
        }
        for (Player p : players){
            p.sortHand();
        }
    }

    public GoFish(){
        this.deck = new Deck("standard");
        this.numPlayers = 4;
        this.players = new LinkedList<Player>();
        for (int i = 0; i < numPlayers; ++i){
            players.add(new Player("Player "+(i+1)));
        }
    }

    public void playGame(){
        System.out.println("Welcome to "+name);
        dealCards();

        for (Player p : players){
            p.printHand();
            System.out.println();
        }

//        while (!gameOver()){
//            round();
//        }
    }

    boolean gameOver() {
       //TODO
        return false;
    }

    public void round() {


    }

    public void turn() {

    }

    public static void main (String[] args){
       GoFish gf = new GoFish();
       gf.playGame();
    }
}
