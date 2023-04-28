import javax.swing.JPanel;

public class SolitaireBoard {
	static int NUM_COLUMNS = 7;
	JPanel mainPanel;
	SolitaireColumn columns[];
	CardStack deck;
	CardStack discardPile;
    
    HeartFoundation heartfoundation;
	SpadeFoundation Spadefoundation;
    DiamondFoundation Diamondfoundation;
    ClubFoundation Clubfoundation;


    public SolitaireColumn getColumnAt(int index) {
		return columns[index];
	}
	public CardStack getDeck() {return deck;}


	public FoundationStack getFoundations(int index ) {

		return FoundationStack [index];
	}
	
	public SolitaireBoard() {
		/* FDStack = new FoundationStack[NUM_FOUNDATIONS];*/
		columns = new SolitaireColumn[NUM_COLUMNS];
		deck = new CardStack();
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
				System.out.println(currCard);
			}
			columns[i].getColumn().peekCard(0).setFaceDown(false);
		}
	}

    private class HeartFoundation extends CardStack{
        @override
        public boolean addToTop(Card card) {
            Card topCard = stack.get(stack.length()-1);
            int rankDifference =card.getRank() - topcard.getRank();
            if(card.getSuit() =='H') {
                if(stack.isEmpty() || card.getRank() == 1){
                    stack.add(card);
                    return true;
                }
                else if(rankDifference == 1){
                    stack.add(card);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }
    private class SpadeFoundation extends CardStack{
        @override
        public boolean addToTop(Card card) {
            Card topCard = stack.get(stack.length()-1);
            int rankDifference =card.getRank() - topcard.getRank();
            if(card.getSuit() =='S') {
                if(stack.isEmpty() || card.getRank() == 1){
                    stack.add(card);
                    return true;
                }
                else if(rankDifference == 1){
                    stack.add(card);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }

    private class ClubFoundation extends CardStack{
        @override
        public boolean addToTop(Card card) {
            Card topCard = stack.get(stack.length()-1);
            int rankDifference =card.getRank() - topcard.getRank();
            if(card.getSuit() =='C') {
                if(stack.isEmpty() || card.getRank() == 1){
                    stack.add(card);
                    return true;
                }
                else if(rankDifference == 1){
                    stack.add(card);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }
    private class DiamondFoundation extends CardStack{
        @override
        public boolean addToTop(Card card) {
            Card topCard = stack.get(stack.length()-1);
            int rankDifference =card.getRank() - topcard.getRank();
            if(card.getSuit() =='D') {
                if(stack.isEmpty() || card.getRank() == 1){
                    stack.add(card);
                    return true;
                }
                else if(rankDifference == 1){
                    stack.add(card);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }

    public void addToDiscardPile(Card card) {
        discardPile.addCard(card);
    }

    public CardStack getDiscardPile() {
        return discardPile;
    }

    /*Card card = columns[columnIndex].removeCard();
solitaireBoard.addToDiscardPile(card); */

}