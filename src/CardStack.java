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
	
	public String getStackName() {return stackName;}
	public void setStackName(String stackName) {this.stackName = stackName;}
	public ArrayList<Card> getStack() {return stack;}
	public void setStack(ArrayList<Card> stack) {this.stack = stack;}
	public int getStackSize() {return stack.size();}
	
	public CardStack() {
		setStackName("");
	}
	public CardStack(String stackName) {
		setStackName(stackName);
	}
	public void createStandardDeck() {
		for(int i = 1; i <= 13; ++i) {
			stack.add(new Card(i, "H"));
			stack.add(new Card(i, "D"));
			stack.add(new Card(i, "S"));
			stack.add(new Card(i, "C"));
		}
	}
	
	public void emptyStack() {this.stack.clear();}

	public void addToTop(Card card) {
		stack.add(card);
	}
	
	public void addToBottom(Card card) {
		stack.add(0,card);
	}
	
	public Card dealTopCard() {
		return stack.remove(getStackSize() - 1);
	}
 
	public void flipTopCard() {
		if(peekCard(getStackSize() - 1).isFaceDown())
			peekCard(getStackSize() - 1).setFaceDown(false);
		else
			peekCard(getStackSize() - 1).setFaceDown(true);
	}
	
	public Card peekCard(int index) {
		return stack.get(index);
	}

	public void cutStack() {
		
	}
	
	public void shuffleStack() {
		Random randomIndex = new Random();
		ArrayList<Card> tempStack = new ArrayList<>();
		tempStack.addAll(0, getStack());
		emptyStack();
		
		while(!tempStack.isEmpty()) {
			addToTop(tempStack.remove(randomIndex.nextInt(tempStack.size())));
		}
		
	}
	
	public void dealEvenlyTo(CardStack cs1, CardStack cs2) {
		
		while(!getStack().isEmpty()) {
			cs1.addToTop(dealTopCard());
			cs2.addToTop(dealTopCard());
		}
	}
	public void printStack() {
		for(Card stack: getStack()) {
			System.out.printf(stack.toString() + " ");
		}
		System.out.println();
		System.out.println(getStackName() + " Stack Size: " + getStack().size());
		System.out.println();
	}
	
	public JPanel draw() {
		Color feltGreen = new Color(0,122,51);
		JPanel cardStack = new JPanel();
		JPanel cardStackArea = new JPanel(new BorderLayout());
		JPanel cardStackNameArea = new JPanel();
		if(!(getStackName() == ""))
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
		if(!(getStackName() == ""))
			cardStack.setToolTipText("This is the " + getStackName());
		else
			cardStack.setToolTipText("These are the cards in play. Yours on the left, enemy on the right.");

		return cardStack;
	}
	

}


