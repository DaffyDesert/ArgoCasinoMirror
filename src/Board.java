import java.util.ArrayList;

/**
 *	Board is an abstract class that is extended by the individual games’ Board classes. This handles any card logic that is shared between games.
 */
public abstract class Board {

	private ArrayList<CardStack> players = new ArrayList<>();
	private ArrayList<CardStack> discardPiles = new ArrayList<>();
	private CardStack deck = new CardStack("52 Card Deck");
	
	public Board(int numOfPlayers, int numOfDiscardPiles, int numOfDecks) {
		CardStack playerToAdd;
		CardStack discardPileToAdd;

		for(int i = 0; i < numOfPlayers; i++) {
			playerToAdd = new CardStack("player_" + (i + 1));
			getPlayers().add(playerToAdd);
		}
		for(int i = 0; i < numOfDiscardPiles; i++) {
			discardPileToAdd = new CardStack("discard_pile_" + (i + 1));
			getDiscardPiles().add(discardPileToAdd);
		}
		for(int i = 0; i < numOfDecks; i++)
			deck.createStandardDeck();;
			
		if (numOfDecks > 1) 
			getDeck().setStackName("x" + numOfDecks + " 52 Card Deck");
		
	}
	
	public ArrayList<CardStack> getPlayers() {
		return players;
	}

	public ArrayList<CardStack> getDiscardPiles() {
		return discardPiles;
	}

	public CardStack getDeck() {
		return deck;
	}
	
	
	public void printBoard() {
		for(CardStack playerStack: getPlayers()) {
			playerStack.printStack();
		}
		for(CardStack discardStack: getDiscardPiles()) {
			discardStack.printStack();
		}
		deck.printStack();
	}
}
