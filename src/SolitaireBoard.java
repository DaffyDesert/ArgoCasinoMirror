import javax.swing.JPanel;

public class SolitaireBoard {
	static int NUM_COLUMNS = 7;
	static int NUM_FOUNDATIONS = 4;
	JPanel mainPanel;
	SolitaireColumn columns[];
	CardStack deck;
	FoundationStack[] FDStack;
   // HeartStack[] foundations;
    //SpadeStack[] foundations;
   // DiamondsStack[] foundations;
    // ClubStack[] foundations;
	
    public SolitaireColumn getColumnAt(int index) {
		return columns[index];
	}
	public CardStack getDeck() {return deck;}


	public FoundationStack getFoundations(int index ) {

		return FoundationStack [index];
	}
	
	public SolitaireBoard() {
		FDStack = new FoundationStack[NUM_FOUNDATIONS];
		columns = new SolitaireColumn[NUM_COLUMNS];
		deck = new CardStack();
		deck.createStandardDeck();
		deck.shuffleStack();
	}

	
 /* HeartPile = new HeartStack[NUM_FOUNDATIONS];
for (int i = 0; i < NUM_FOUNDATIONS; i++) {
    HeartPile[i] = new HeartStack();
}  */

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

}