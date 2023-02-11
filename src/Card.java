/**
 * Card class that holds a:
 * rank: ace, 2-10, jack, queen, king (13 possible values)
 * suit: heart, club, diamond, spade
 * value: combination of the rank and suit of a card (5H, KD)
 * @author danielMiller
 *
 */ 
public class Card {

	private String rank;
	private String suit;
	private String value;
	
	/**
	 * Constructor
	 * @param rank - ace, 2-10, jack, queen, king (13 possible values)
	 * @param suit - heart, club, diamond, spade
	 */
	public Card(int rank, int suit) {
		setRank(rank);
		setSuit(suit);
		value = getRank() + getSuit().charAt(0); // e.g rank = 7 suit = Hearts -> value ="7H"
	}

	/**
	 * return rank of the card
	 * @return value
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * Set the of the cards - primarily used to parse Ace, jack, queen, king rank values 
	 * @param rank
	 */
	public void setRank(int rank) {
		if(rank == 0)
			this.rank = "A";
		else if(rank == 10)
			this.rank = "J";
		else if(rank == 11)
			this.rank = "Q";
		else if(rank == 12)
			this.rank = "K";
		else
			this.rank = Integer.toString(rank);		
	}

	/**
	 * Set the of the cards - primarily used to parse Ace, jack, queen, king rank values 
	 * @param rank
	 */
	public void setSuit(int suit) {
		if(suit == 0)
			this.suit = "Hearts";
		else if(suit == 1)
			this.suit = "Diamonds";
		else if(suit == 2)
			this.suit = "Spades";
		else if(suit == 3)
			this.suit = "Clubs";
		else
			this.suit = "error";		
	}
/**
	 * return suit of the card
	 * @return suit
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * return the value of the card
	 */
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return getRank() + " of " + getSuit();
	}
	
}
