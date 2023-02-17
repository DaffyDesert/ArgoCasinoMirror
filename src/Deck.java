import java.util.ArrayList;
import java.util.Random;

/**
 * Class to build a standard deck of playing cards utilizing the Card class
 * @author danielMiller
 *
 * cards: array list of cards representing the deck object
 */
public class Deck extends CardStack{

	/**
	 * Default constructor that builds standard 52 card deck into 
	 * the deck ArrayList of Cards
	 */
	public Deck(){
		Card workingCard;
		
		for(int s = 0; s < 4; s++)				// int s 0, 1, 2, 3 == suit hearts, diamonds, spades, clubs
			for(int r = 0; r < 13; r++) {		// int r 0-12 == ace, 2-10, jack, queen, king
				workingCard = new Card(r, s);	// Card constructor follows above structure
				deck.add(workingCard);
			}
	}
	
	/**
	 * Shuffles the deck (the size of the deck does not matter) 
	 */
	public void shuffleDeck() {
		ArrayList<Card> shuffeledDeck = new ArrayList<>();
		Random r = new Random();
		
		while(!deck.isEmpty()) {
			int cardToMove = r.nextInt(deck.size());
			shuffeledDeck.add(deck.get(cardToMove));
			deck.remove(cardToMove);
		}
		
		deck = shuffeledDeck;
	}
	
}