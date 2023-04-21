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

		return null;
	}
	
	void printColumn() {
		column.printStack();
	}
}
