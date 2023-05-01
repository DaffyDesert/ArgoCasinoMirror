import javax.swing.JPanel;

public class SolitaireBoard {
	static int NUM_COLUMNS = 7;
	JPanel mainPanel;
	SolitaireColumn columns[];
	CardStack deck;
	CardStack discardPile;
    
    CardStack heartFoundation;
    CardStack spadeFoundation;
    CardStack diamondFoundation;
    CardStack clubFoundation;

    public SolitaireColumn getColumnAt(int index) {return columns[index];}
	public CardStack getHeartFoundation() {return heartFoundation;};
	public CardStack getSpadeFoundation() {return spadeFoundation;}
	public CardStack getDiamondFoundation() {return diamondFoundation;}
	public CardStack getClubFoundation() {return clubFoundation;}
	public CardStack getDeck() {return deck;}
	
	public SolitaireBoard() {
		heartFoundation = new CardStack();
		spadeFoundation = new CardStack();
		diamondFoundation = new CardStack();
		clubFoundation = new CardStack();
		columns = new SolitaireColumn[NUM_COLUMNS];
		deck = new CardStack();
		discardPile = new CardStack();
		deck.createStandardDeck();
		deck.shuffleStack();
	}

	public void dealToColumns() {
		for(int i = 0; i < NUM_COLUMNS; ++i) {
			columns[i] = new SolitaireColumn();
			for(int j = 0; j < i + 1; ++j) {
				Card currCard = deck.dealTopCard();
				currCard.setFaceDown(true);
				columns[i].addCard(currCard);
			}
			columns[i].getColumn().peekCard(i).setFaceDown(false);
		}
	}
	
	public boolean addToHeartFoundation(Card card) {
		if(heartFoundation.getStack().isEmpty()) {
			if(card.getSuit().compareTo("H") == 0 && card.getRank() == 1) {
				heartFoundation.getStack().add(card);
				card.setFaceDown(true);
	            return true;
			}
			else
				return false;
		}
		Card topCard = heartFoundation.getStack().get(heartFoundation.getStackSize() - 1);
		int rankDifference = card.getRank() - topCard.getRank();
		if(card.getSuit() == "H") {
            if(heartFoundation.getStack().isEmpty() || card.getRank() == 1){
                heartFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
            else if(rankDifference == 1){
                heartFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
                return false;
        }
        return false;
	}
	
	public boolean addToSpadeFoundation(Card card) {
		if(spadeFoundation.getStack().isEmpty()) {
			if(card.getSuit().compareTo("S") == 0 && card.getRank() == 1) {
				spadeFoundation.getStack().add(card);
				card.setFaceDown(true);
	            return true;
			}
			else
				return false;
		}
		Card topCard = spadeFoundation.getStack().get(spadeFoundation.getStackSize() - 1);
		int rankDifference = card.getRank() - topCard.getRank();
		if(card.getSuit() == "S") {
            if(spadeFoundation.getStack().isEmpty() || card.getRank() == 1){
                spadeFoundation.getStack().add(card);
				card.setFaceDown(true);
                return true;
            }
            else if(rankDifference == 1){
                spadeFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
                return false;
        }
        return false;
	}
	
	public boolean addToClubFoundation(Card card) {
		if(clubFoundation.getStack().isEmpty()) {
			if(card.getSuit().compareTo("C") == 0 && card.getRank() == 1) {
				clubFoundation.getStack().add(card);
				card.setFaceDown(true);
	            return true;
			}
			else
				return false;
		}
		Card topCard = clubFoundation.getStack().get(clubFoundation.getStackSize() - 1);
		int rankDifference = card.getRank() - topCard.getRank();
		if(card.getSuit() == "C") {
            if(clubFoundation.getStack().isEmpty() || card.getRank() == 1){
                clubFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
            else if(rankDifference == 1){
                clubFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
                return false;
        }
        return false;
	}
	
	public boolean addToDiamondFoundation(Card card) {
		if(diamondFoundation.getStack().isEmpty()) {
			if(card.getSuit().compareTo("D") == 0 && card.getRank() == 1) {
				diamondFoundation.getStack().add(card);
				card.setFaceDown(true);
	            return true;
			}
			else
				return false;
		}
		Card topCard = diamondFoundation.getStack().get(diamondFoundation.getStackSize() - 1);
		int rankDifference = card.getRank() - topCard.getRank();
		if(card.getSuit() == "C") {
            if(diamondFoundation.getStack().isEmpty() || card.getRank() == 1){
                diamondFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
            else if(rankDifference == 1){
                diamondFoundation.getStack().add(card);
                card.setFaceDown(true);
                return true;
            }
                return false;
        }
        return false;
	}
	
    public void addToDiscardPile(Card card) {
        discardPile.getStack().add(card);
    }

    public CardStack getDiscardPile() {
        return discardPile;
    }
    
    public void resetDeck() {
    		int discardStackSize = discardPile.getStackSize();
    		for (int i = 0; i < discardStackSize; ++i) {
    			Card currCard = discardPile.dealTopCard();
    			currCard.setFaceDown(true);
    			deck.addToTop(currCard);
    		}
    }
    
    public Card drawFromDeck() {
    	return deck.dealTopCard();
    }
}