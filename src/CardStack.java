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
	public void addToTop(Card card) {
		stack.add(card);
	}
	public void addToBottom(Card card) {
		stack.add(0,card);
	}
	public Card dealTopCard() {
		Card temp = stack.get(stack.size() - 1);
		getStack().remove(stack.size() - 1);
		return temp;
	}
	public Card peekCard(int index) {
		return stack.get(index);
	}
	public void shuffleStack() {
		int size = stack.size();
		Random r = new Random();
		
		for(int i = 0; i < size; ++i) {
			Card currCard = stack.get(i);
			int randVal = i + r.nextInt(size - i);
			Card randCard = stack.get(randVal);
			stack.set(randVal, currCard);
			stack.set(i, randCard);
		}
	}
	
	public void dealEvenlyTo(CardStack mainDeck, CardStack cs1, CardStack cs2) {
		
		while(!mainDeck.getStack().isEmpty()) {
			Card card1 = mainDeck.dealTopCard();
			Card card2 = mainDeck.dealTopCard();
			
			cs1.addToTop(card1);
			cs2.addToTop(card2);
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
	

}


