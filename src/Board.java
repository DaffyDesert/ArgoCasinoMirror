import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
			playerToAdd = new CardStack("player_" + (i + 1));
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
	
	
	/**
	 * STUBB
	 * prints the board 
	 */
	public void printBoard() {
		for(CardStack playerStack: getPlayers()) {
			playerStack.printStack();
		}
		for(CardStack discardStack: getDiscardPiles()) {
			discardStack.printStack();
		}
		deck.printStack();
	}
	
	/**
	 * STUBB
	 * 
	 * How a board is drawn should be dictated by the type of Board it is - this should be implemented in the WarBoard, etc .. classes
	 * 
	 * paint the stack (gui version of printStack() STUBB)
	 */
	public JPanel drawBoard(Graphics2D g) {
		Color feltGreen = new Color(10, 108, 3);
		JPanel board = new JPanel(new BorderLayout());
		JPanel playerArea = new JPanel();
		JPanel discardArea = new JPanel();
		JPanel deckArea = new JPanel();

		board.setBounds(0, 0, 500, 900);
		board.setBackground(feltGreen);
		
		playerArea.setLayout(new BoxLayout(playerArea, BoxLayout.X_AXIS));
		playerArea.setBackground(feltGreen);

		discardArea.setLayout(new BoxLayout(discardArea, BoxLayout.X_AXIS));
		discardArea.setBackground(feltGreen);
		
		deckArea.setLayout(new BorderLayout());
		deckArea.setBackground(feltGreen);
		
		for(CardStack playerStacks: getPlayers()) {
			playerArea.add(playerStacks.draw(g));
		}

		for(CardStack discardStacks: getDiscardPiles()) {
			discardArea.add(discardStacks.draw(g));
		}

		deckArea.add(getDeck().draw(g), BorderLayout.CENTER);
		
		board.add(playerArea, BorderLayout.SOUTH);
		board.add(discardArea, BorderLayout.NORTH);
		board.add(deckArea);
		
		return board;
	}
}
