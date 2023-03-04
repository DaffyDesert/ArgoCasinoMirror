import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class used to implement what a stack of cards should expected to do /n
 * 
 * Allows for better abstraction between a deck, player hand, dealer hand, 
 * discard piles, etc..
 * 
 * stackName - name of the stack object
 * stack - list of card objects to handle
 * 
 * @author danielMiller
 *
 */
@SuppressWarnings("serial")
public class CardStack extends JComponent{
	
	private String stackName;
	private ArrayList<Card> stack = new ArrayList<>();
	private int stackSize;
	
	/**
	 * default constructor that makes an empty stack of cards (a holding place for card objects)
	 * with a default stackName of "Stack"
	 */
	public CardStack() {
		setStackName("Stack");
		stack.clear();
		stackSize = stack.size();
	}

	/**
	 * Constructor that makes an empty stack of cards (a holding place for card objects)
	 * with a parameterized stackName value
	 */
	public CardStack(String stackName) {
		stack.clear();
		setStackName(stackName);
		stackSize = stack.size();
	}
	
	/**
	 * Constructor that makes a stack of cards using a parameterized Card object
	 * and a parameterized stackName value
	 */
	public CardStack(String stackName, Card card) {
		setStack(card);
		setStackName(stackName);
		stackSize = stack.size();
	}
	
	/**
	 * Constructor that makes a stack of cards using a parameterized list of Card objects
	 * and a parameterized stackName value
	 */
	public CardStack(String stackName, ArrayList<Card> stack) {
		setStack(stack);
		setStackName(stackName);
		stackSize = stack.size();
	}
	
	public void addDeckToStack() {
		Card workingCard;
		
		for(int s = 0; s < 4; s++)				// int s 0, 1, 2, 3 == suit hearts, diamonds, spades, clubs
			for(int r = 0; r < 13; r++) {		// int r 0-12 == ace, 2-10, jack, queen, king
				workingCard = new Card(r, s);	// Card constructor follows above structure
				getStack().add(workingCard);
			}
	}

	/**
	 * @return stackName
	 */
	public String getStackName() {
		return stackName;
	}

	/**
	 * sets stackName with parameterized value 
	 * 
	 * @param stackName the stackName to set
	 */
	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	/**
	 * 
	 * @return stack
	 */
	public ArrayList<Card> getStack() {
		return stack;
	}

	public int getStackSize() {
		return stack.size();
	}

	/**
	 * Private method to set stack with parameterized value 
	 * 
	 * @param card - card object to set the stack to
	 */
	private void setStack(Card card) {
		emptyStack();
		getStack().add(card);
	}


	/**
	 * Private method to set stack with parameterized list  
	 * 
	 * @param stack
	 */
	private void setStack(ArrayList<Card> stack) {
		emptyStack();
		getStack().addAll(stack);
	}

	/**
	 * adds parameterized card object to the top of the card stack
	 * 
	 * @param cardToAdd
	 */
	public void addToTop(Card cardToAdd) {
		getStack().add(cardToAdd);
	}
	
	/**
	 * adds parameterized list of card object to the top of the card stack
	 * 
	 * @param cardsToAdd
	 */
	public void addToTop(ArrayList<Card> cardsToAdd) {
		getStack().addAll(cardsToAdd);
	}
	
	/**
	 * adds parameterized card object to the bottom of the card stack
	 * 
	 * @param cardToAdd
	 */
	public void addToBottom(Card cardToAdd) {
		getStack().add(0, cardToAdd);
	}
	
	/**
	 * adds parameterized list of card object to the bottom of the card stack
	 * 
	 * @param cardsToAdd
	 */
	public void addToBottom(ArrayList<Card> cardsToAdd) {
		getStack().addAll(0, cardsToAdd) ;;// stack is received in the order as if it were dealt dealTopCard()
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
	 * Flips the top card of the stack
	 */
	public void flipTopCard() {
		getStack().get(stackSize-1).flipCard();
	}

	public void flipStack() {
		for(Card stackToFlip: getStack())
			stackToFlip.flipCard();
	}

	public Card peekCard(int index) {
		return stack.get(index);		
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

	/**
	 * Returns the card stack in the form of a JPanel
	 * 
	 * @param g
	 * @return CardStack in the form of a JPanel
	 */
	public JPanel draw() {
		Color feltGreen = new Color(10, 108, 3);
		JPanel cardStack = new JPanel();
		JPanel cardStackArea = new JPanel(new BorderLayout());
		JPanel cardStackNameArea = new JPanel();
		cardStackNameArea.add(new JLabel(getStackName()));
		cardStackNameArea.setBackground(feltGreen);
		cardStackArea.setBackground(feltGreen);
				
		for(Card cardToDraw: getStack()) {
			cardStackArea.add(cardToDraw.draw(), BorderLayout.CENTER); 
		}
		
		cardStack.setBackground(feltGreen);
		cardStack.setLayout(new BoxLayout(cardStack, BoxLayout.Y_AXIS));
		cardStack.add(cardStackArea);
		cardStack.add(cardStackNameArea);

		return cardStack;
	}
	
	/**
	 * STUBB 
	 * 
	 * prints the stack of cards to console by iterating from bottom to top
	 */
	public void printStack() {
		for(Card stack: getStack()) {
			System.out.printf(stack.getValue() + " ");
		}
		System.out.println();
		System.out.println(getStackName() + " Stack Size: " + getStack().size());
		System.out.println();
	}
	
}


