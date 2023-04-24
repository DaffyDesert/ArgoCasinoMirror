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
		if(isEmpty()) {
			if(isKing(card)) {
				column.getStack().add(card);
				return true;
			}
			else
				return false;
		}
		else {
			Card topCard = column.peekCard(column.getStackSize() - 1);
	        if (isAdjacentAndDifferentColor(topCard, card)) {
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

		return null;
	}
	
	void printColumn() {
		column.printStack();
	}
}
