package Utils;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final int value;
    private final boolean utf16 = true; //false for command line games, true for console games

    public Card (Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    /**
     * @param other The card to compare against
     * @return whether this card's suit is the same as the other
     */
    public boolean sameSuit(Card other){
        return this.suit.equals(other.suit);
    }

    /**
     * @param other The card to compare against
     * @param acesHigh Consider Aces as 14 (default is true)
     * @return whether this card's value is greater than the other
     */
    public boolean greaterThan(Card other, boolean acesHigh){
        if (acesHigh) {
            if (this.value==1 && other.value !=1){
                return true;
            }
            if (this.value!=1 && other.value ==1){
                return false;
            }
        }
        return this.value > other.value;
    }

    /**
     * @param other The card to compare against
     * @return whether this card's value is greater than the other, defaults to aces high
     */
    public boolean greaterThan(Card other) {
       return greaterThan(other, true);
    }

    /**
     *
     * @param other the card to compare to
     * @return whether this card's suit is greater than the other (uses H>D>C>S). Equal returns false.
     */
    public boolean suitGreaterThan(Card other){
        if (sameSuit(other)){
            return false;
        }
        if (this.suit.equals(Suit.HEARTS)){
            return true;
        }
        if (other.suit.equals(Suit.HEARTS)){
            return false;
        }
        if (this.suit.equals(Suit.DIAMONDS)){
            return true;
        }
        if (other.suit.equals(Suit.DIAMONDS)){
            return false;
        }
        return this.suit.equals(Suit.CLUBS);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public String toString() {
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

        if (utf16) {
            if (suit.equals(Suit.CLUBS)) {
                out += '\u2663';
            } else if (suit.equals(Suit.SPADES)) {
                out += '\u2660';
            } else if (suit.equals(Suit.DIAMONDS)) {
                out += '\u2666';
            } else if (suit.equals(Suit.HEARTS)) {
                out += '\u2665';
            }
        } else {
            if (suit.equals(Suit.CLUBS)) {
                out += 'C';
            } else if (suit.equals(Suit.SPADES)) {
                out += 'S';
            } else if (suit.equals(Suit.DIAMONDS)) {
                out += 'D';
            } else if (suit.equals(Suit.HEARTS)) {
                out += 'H';
            }
        }

        return out;
    }

    @Override
    public int compareTo(Card other) {
        //same card
        if (this.equals(other)){
            return 0;
        }

        //different suit (returns +-2 for distinction)
        if (this.suitGreaterThan(other)){
            return 2;
        }
        if (other.suitGreaterThan(this)){
            return -2;
        }

        //same suit, compare value
        if (this.greaterThan(other)){
            return 1;
        }
        return -1;
    }
}
