import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SolitaireColumn{
	private CardStack column;

	SolitaireColumn() {
		column = new CardStack();
	}

	CardStack getColumn() {
		return column;
	}

	boolean addCard(Card card) {	
		if(column.getStack().isEmpty()) {
			if(card.getRank() == 13) {
				column.getStack().add(card);
				return true;
			}
			else
				return false;
		}
		else {
			Card topCard = column.peekCard(column.getStackSize() - 1);
			if(topCard.getRank() - card.getRank() == 1) {
				if(topCard.getSuit().compareTo("C") == 0 || topCard.getSuit().compareTo("S") == 0) {
					if(card.getSuit().compareTo("D") == 0 || card.getSuit().compareTo("H") == 0) {
						column.getStack().add(card);
						return true;
					}
					else 
						return false;
				}
				else {
					if(card.getSuit().compareTo("C") == 0 || card.getSuit().compareTo("S") == 0) {
						column.getStack().add(card);
						return true;
					}
					else
						return false;
				}
				
			}
			else
				return false;
		}
	}

	JPanel display() {
		int numCards = column.getStack().size();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLayeredPane lp = new JLayeredPane();
		lp.setPreferredSize(new Dimension(300,310));
		Point origin = new Point(10,100);
		int offset = 50;
		for(int i = numCards - 1; i >= 0; --i) {
			JPanel currCard = column.getStack().get(i).draw();
			
			currCard.setBounds(origin.x, origin.y,150,150);
			
			lp.add(currCard);
			origin.y -= offset;
		}
		lp.setVisible(true);
		panel.add(lp);
		panel.setVisible(true);
		return panel;
	}
	
	void printColumn() {
		column.printStack();
	}
}
