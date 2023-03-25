import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The CardStack class handles stacks of cards and all features that pertain to them.
 */
@SuppressWarnings("serial")
public class CardStack extends JComponent{
	
	private String stackName;
	private ArrayList<Card> stack = new ArrayList<>();
	private int stackSize;
	
	public CardStack() {
		setStackName("Stack");
		stack.clear();
		stackSize = stack.size();
	}

	public CardStack(String stackName) {
		stack.clear();
		setStackName(stackName);
		stackSize = stack.size();
	}
	
	public CardStack(String stackName, Card card) {
		setStack(card);
		setStackName(stackName);
		stackSize = stack.size();
	}
	
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

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public ArrayList<Card> getStack() {
		return stack;
	}

	public int getStackSize() {
		return stack.size();
	}

	private void setStack(Card card) {
		emptyStack();
		getStack().add(card);
	}


	private void setStack(ArrayList<Card> stack) {
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
		getStack().addAll(0, cardsToAdd) ;;// stack is received in the order as if it were dealt dealTopCard()
	}
	
	public Card dealTopCard() {
		Card topCard = getStack().get(getStack().size()-1);
		getStack().remove(getStack().size()-1);
		return topCard;
	}
	
	public ArrayList<Card> dealTopCard(int numOfCards) {
		ArrayList<Card> topCards = new ArrayList<>();
		for(int i = 0; i < numOfCards; i++)
			topCards.add(dealTopCard());
		return topCards;
	}
	
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
	
	public ArrayList<CardStack> dealEvenlyTo(ArrayList<CardStack> stacksToBeDealtTo) {
		int numRecievingStacks = stacksToBeDealtTo.size();
		int cardsEach = stack.size()/numRecievingStacks;

		for(int x = 0; x < cardsEach; x++)
			for(int y = 0; y < numRecievingStacks; y++)
				stacksToBeDealtTo.get(y).addToTop(dealTopCard());
		return stacksToBeDealtTo;
	}
	
	private void emptyStack() {
		getStack().clear();
	}

	public JPanel draw() {
		Color feltGreen = new Color(10, 108, 3);
		JPanel cardStack = new JPanel();
		JPanel cardStackArea = new JPanel(new BorderLayout());
		JPanel cardStackNameArea = new JPanel();
		cardStackNameArea.add(new JLabel( getStackName() + " Cards: " + getStackSize() ));
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
	
	public void printStack() {
		for(Card stack: getStack()) {
			System.out.printf(stack.getValue() + " ");
		}
		System.out.println();
		System.out.println(getStackName() + " Stack Size: " + getStack().size());
		System.out.println();
	}
	
}


