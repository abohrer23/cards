import Utils.Player;

import java.util.List;

public abstract class Game {
    //variables
    public List<Player> players;
    public String name;

    //methods
    public abstract void dealCards();
    public abstract void playGame();
    public abstract void round();
    public abstract void turn();
    abstract boolean gameOver();
}
