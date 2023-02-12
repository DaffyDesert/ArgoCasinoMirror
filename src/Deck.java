import java.util.ArrayList;
import java.util.Random;

/**
 * Class to build a standard deck of playing cards utilizing the Card class
 * @author danielMiller
 *
 * cards: array list of cards representing the deck object
 */
public class Deck {

	private ArrayList<Card> deck = new ArrayList<>();
	
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
	
	/**
	 * Removes the card from the top of the deck and returns it as a card object to be given 
	 * given to a different deck object (player hand, discard pile, etc...)
	 * @param numOfCards
	 * @return the top card 
	 */
	public Card dealTopCard() {
		Card topCard = deck.get(deck.size()-1);
		deck.remove(deck.size()-1);
		return topCard;
	}
	
	/**
	 * Takes in an integer and then removes that many cards from the top of the deck and returns them as an ArrayList to be 
	 * given to a different deck object (player hand, discard pile, etc...)
	 * @param numOfCards - number of cards to be dealt
	 */
	public ArrayList<Card> dealTopCard(int numOfCards) {
		ArrayList<Card> topCards = new ArrayList<>();
		for(int i = 0; i < numOfCards; i++)
			topCards.add(dealTopCard());
		return topCards;
	}
	

	//STUBBs created to show how deck can be used as discard pile or hand - deck, discard pile, player hand, may all be sub classes to CardPile class???
	public void printDeck() {
		for(Card decks: deck) {
			System.out.printf(decks.getValue() + " ");
		}
		System.out.println();
		System.out.println("Deck Size: " + deck.size());
	}

	public void addCard(Card cardToAdd) {
		deck.add(cardToAdd);
	}
	
	public void addCard(ArrayList<Card> cardsToAdd) {
		deck.addAll(cardsToAdd);
	}
	
	public void emptyDeck() {
		deck.clear();
	}
}
