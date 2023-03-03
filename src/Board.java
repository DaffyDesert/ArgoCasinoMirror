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
public abstract class Board {//Made abstract for... reasons

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
			deck.addDeckToStack();
			
		if (numOfDecks > 1) 
			getDeck().setStackName("x" + numOfDecks + " 52 Card Deck");
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 13; ++j) {
				Card newCard = new Card(j, i);
				deck.addToTop(newCard);
			}
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
	
}
