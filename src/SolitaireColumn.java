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
			if(topCard.getRank() - card.getRank() != 1) {
				column.getStack().add(card);
				return true;
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
