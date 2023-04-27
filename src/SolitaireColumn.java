import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SolitaireColumn {
	private CardStack column;

	SolitaireColumn() {
		column = new CardStack();
	}

	CardStack getColumn() {
		return column;
	}

	boolean addCard(Card card) {	
		if(card.isFaceDown()) {
			column.getStack().add(card);
			return true;
		}
		
		else if(isEmpty()) {
			if(isKing(card)) {
				column.getStack().add(card);
				return true;
			}
			else
				return false;
		}
		else {
			Card topCard = column.peekCard(column.getStackSize() - 1);
	        if ((isAdjacentAndDifferentColor(topCard, card)) || (topCard.isFaceDown())) {
	            column.getStack().add(card);
	            return true;
	        } else {
	            return false;
	        }
		}
	}
	
	boolean isKing(Card card) {
		if(card.getRank() == 13) 
			return true;
		return false;
	}
	
	
	boolean isEmpty() {
		return column.getStack().isEmpty();
	}
	
	boolean isAdjacentAndDifferentColor(Card card1, Card card2) {
		int rankDifference = card1.getRank() - card2.getRank();
	    boolean isAdjacent = rankDifference == 1;
	    boolean isDifferentColor = (isRed(card1) && isBlack(card2)) || (isBlack(card1) && isRed(card2));
	    return isAdjacent && isDifferentColor;
	}
	
	boolean isRed(Card card) {
	    String suit = card.getSuit();
	    return suit.equals("H") || suit.equals("D");
	}
	
	boolean isBlack(Card card) {
	    String suit = card.getSuit();
	    return suit.equals("S") || suit.equals("C");
	}
	

	JPanel display() {

		int numCards = column.getStack().size();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(null);
		panel.setBorder(null);
		JLayeredPane lp = new JLayeredPane();
		lp.setPreferredSize(new Dimension(135,750));
		Point origin = new Point(10,0);
		int offset = 50;
	
		for(int i = numCards - 1; i >= 0; i--) {
			JPanel currCard = column.getStack().get(i).draw();
			currCard.setBounds(origin.x,(origin.y + (offset*i)),150,150);
			lp.add(currCard);
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
