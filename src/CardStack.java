import java.util.ArrayList;
import java.util.Random;

/**
 * Class used that implement what a pile of cards is expected to do
 * 
 * Allows for better abstraction between a deck, player hand, dealer hand, 
 * stacks of cards, discard pile, etc..
 * 
 * @author danielMiller
 *
 */
public class CardStack {
	
	private String stackName;
	private ArrayList<Card> stack = new ArrayList<>();
	
	public CardStack() {
		stack.clear();
	}

	public CardStack(String stackName) {
		stack.clear();
		setStackName(stackName);
	}
	
	public CardStack(String stackName, Card card) {
		setStack(card);
		setStackName(stackName);
	}
	
	public CardStack(String stackName, ArrayList<Card> stack) {
		setStack(stack);
		setStackName(stackName);
	}
	
	/**
	 * @return the stackName
	 */
	public String getStackName() {
		return stackName;
	}

	/**
	 * @param stackName the stackName to set
	 */
	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public ArrayList<Card> getStack() {
		return stack;
	}

	public void setStack(Card card) {
		emptyStack();
		getStack().add(card);
	}

	public void setStack(ArrayList<Card> stack) {
		emptyStack();
		getStack().addAll(stack);
	}

	public void addToTop(Card cardToAdd) {
		getStack().add(cardToAdd);
	}
	
	public void addToTop(ArrayList<Card> cardsToAdd) {
		getStack().addAll(cardsToAdd);
	}
	
	public void addToBottom(Card cardToAdd) {
		getStack().add(0, cardToAdd);
	}
	
	public void addToBottom(ArrayList<Card> cardsToAdd) {
		getStack().addAll(0, cardsToAdd) ; 	// stack is recieved in the order as if it were dealt dealTopCard()
	}
	
	/**
	 * Removes the card from the top of the deck and returns it as a card object to be given 
	 * given to a different deck object (player hand, discard pile, etc...)
	 * @param numOfCards
	 * @return the top card 
	 */
	public Card dealTopCard() {
		Card topCard = getStack().get(getStack().size()-1);
		getStack().remove(getStack().size()-1);
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

	/**
	 * Shuffles the deck (the size of the deck does not matter) 
	 */
	public void shuffleStack() {
		ArrayList<Card> shuffeledDeck = new ArrayList<>();
		Random r = new Random();
		
		while(!getStack().isEmpty()) {
			int cardToMove = r.nextInt(getStack().size());
			shuffeledDeck.add(getStack().get(cardToMove));
			getStack().remove(cardToMove);
		}
		
		setStack(shuffeledDeck);
	}
	
	/**
	 * deals calling stack (deck for example) cards from the top one card at a time and evenly distributes to each card stack in the 
	 * takes in a returns the stacks to be dealt to
	 * any remaining cards in calling stack (remainder of even distribution) will stay in the calling stack
	 * @param stacksToBeDealtTo
	 */
	public ArrayList<CardStack> dealEvenlyTo(ArrayList<CardStack> stacksToBeDealtTo) {
		int numRecievingStacks = stacksToBeDealtTo.size();
		int cardsEach = stack.size()/numRecievingStacks;

		for(int x = 0; x < cardsEach; x++)
			for(int y = 0; y < numRecievingStacks; y++)
				stacksToBeDealtTo.get(y).addToTop(dealTopCard());
		return stacksToBeDealtTo;
	}
	/**
	 * function to clear the stack
	 * only used in constructor atm - could be removed?
	 */
	private void emptyStack() {
		getStack().clear();
	}
	
	//STUBBs created to show how deck can be used as discard pile or hand - deck, discard pile, player hand, may all be sub classes to CardPile class???
	public void printStack() {
		for(Card stack: getStack()) {
			System.out.printf(stack.getValue() + " ");
		}
		System.out.println();
		System.out.println(getStackName() + " Stack Size: " + getStack().size());
	}

}


