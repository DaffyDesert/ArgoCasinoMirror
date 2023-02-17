import java.util.ArrayList;

/**
 * Interface used that implement what a pile of cards is expected to do
 * 
 * Allows for better abstraction between a deck, player hand, dealer hand, 
 * stacks of cards, discard pile, etc..
 * 
 * @author danie
 *
 */
public class CardStack {
	
	private ArrayList<Card> stack = new ArrayList<>();
	
	public CardStack(Card cardToAdd) {
		addCard(cardToAdd);
	}
	
	public CardStack(ArrayList<Card> cardsToAdd) {
		addStack(cardsToAdd);
	}
	
	public void addCard(Card cardToAdd) {
		stack.add(cardToAdd);
	}
	
	public void addStack(ArrayList<Card> cardsToAdd) {
		stack.addAll(cardsToAdd);
	}	
	/**
	 * Removes the card from the top of the deck and returns it as a card object to be given 
	 * given to a different deck object (player hand, discard pile, etc...)
	 * @param numOfCards
	 * @return the top card 
	 */
	public Card dealTopCard() {
		Card topCard = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
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
		for(Card decks: stack) {
			System.out.printf(decks.getValue() + " ");
		}
		System.out.println();
		System.out.println("Deck Size: " + stack.size());
	}


	
	public void emptyDeck() {
		stack.clear();
	}
}


