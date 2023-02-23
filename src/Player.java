import java.util.ArrayList;

//Implementation may vary.
public abstract class Player {
	/*
	 * Object that should be standard across player classes.
	 */
	private Deck playerDeck;
	
	/*
	 * Objects that may be different across different subclasses.
	 */
	//private Card activeCard; //May be used to represent an active card in War
	//private ArrayList<Card> hand; //May be used to represent your hand in Blackjack.
	
	/*
	 * Methods
	 */
	public Player(Deck startingDeck) {
		playerDeck = startingDeck;
	}
	
}
