import java.util.ArrayList;

/**
 * Board class that will create a playing area for cards and stack of cards to be created and manipulated
 * 
 * Class should eventually be abstract class to more defined GameBoard classes such as WarBoard, BlackJackBoard, etc..
 *
 * @author daniel
 * @date 2/18/23
 *
 */
public class Board {

	private ArrayList<CardStack> players = new ArrayList<>();
	private ArrayList<CardStack> discardPiles = new ArrayList<>();
	private CardStack deck = new Deck("52 Card Deck");
	
	public Board(int numOfPlayers, int numOfDiscardPiles, int numOfDecks) {
		CardStack playerToAdd;
		CardStack discardPileToAdd;
		CardStack deckToAdd;
		for(int i = 0; i < numOfPlayers; i++) {
			playerToAdd = new CardStack("player_  " + (i + 1));
			getPlayers().add(playerToAdd);
		}
		for(int i = 0; i < numOfDiscardPiles; i++) {
			discardPileToAdd = new CardStack("discard_pile_" + (i + 1));
			getDiscardPiles().add(discardPileToAdd);
		}
		if (numOfDecks > 1) {
			for(int i = 1; i < numOfDecks; i++) {
				deckToAdd = new Deck("");
				getDeck().addToTop(deckToAdd.getStack());;
			}
		getDeck().setStackName("x" + numOfDecks + " 52 Card Deck");
		}
	}
	
	/**
	 * @return the players
	 */
	public ArrayList<CardStack> getPlayers() {
		return players;
	}

	/**
	 * @return the discardPiles
	 */
	public ArrayList<CardStack> getDiscardPiles() {
		return discardPiles;
	}

	/**
	 * @return the deck
	 */
	public CardStack getDeck() {
		return deck;
	}
	
	
	//STUBS
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
