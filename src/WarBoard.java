import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Handles the turns and moves in a game of war
 * @author zw_ch
 *
 */

//Added extends Board because before it was not doing that.
public class WarBoard extends Board{
	private ArrayList<Card> victoryDeck;
	private CardStack playerZone; //Added these for GUI reasons
	private CardStack enemyZone;
	
	//Added parameters and super-constructor call
	public WarBoard(int numOfPlayers, int numOfDiscardPiles, int numOfDecks) {
		super(numOfPlayers, numOfDiscardPiles, numOfDecks);
		victoryDeck = new ArrayList<> ();
		playerZone = new CardStack("Player's Zone");
		enemyZone = new CardStack("Enemy's Zone");
		
		fillDeck();
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
	}
	
	public void fillDeck() {
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 13; ++j) {
				getDeck().addToTop(new Card(j, i));
			}
		}
	}
	
	/**
	 * compares the top cards of an enemy and card deck two see who has the higher card rank
	 * calls the goToWar method if there is a tie
	 * gives the cards in the victorydDeck to the winner
	 * 
	 * 
	 * @param enemyCard
	 * @param playerCard
	 * @param enemyStack
	 * @param playerStack
	 * @return True-player won False-enemy won
	 *
	 */
	public boolean compare(Card enemyCard, Card playerCard, CardStack enemyStack, CardStack playerStack) {
		
		victoryDeck.add(playerCard);
		victoryDeck.add(enemyCard);
		//New stuff added for gui reasons
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		if((enemyCard.getRank() == 0) && (playerCard.getRank() != 0)) {
			enemyStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			playerZone.emptyStack();
			enemyZone.emptyStack();
			return false;
		}
		else if(playerCard.getRank() == 0 && (enemyCard.getRank() != 0)) {
			playerStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			playerZone.emptyStack();
			enemyZone.emptyStack();
			return true;
		}
		else if(playerCard.getRank() > enemyCard.getRank()) {
			playerStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			playerZone.emptyStack();
			enemyZone.emptyStack();
			return true;
		}
		else if(enemyCard.getRank() > playerCard.getRank()) {
			enemyStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			playerZone.emptyStack();
			enemyZone.emptyStack();
			return false;
		}
		else {
			return goToWar(enemyStack,playerStack);
		}
	}
	
	
	
	/**
	 * 
	 *
	 * uses the compare method as recursive function 
	 * 
	 * @param enemyStack
	 * @param playerStack
	 * @return True-player won False-enemy won
	 */
	public boolean goToWar(CardStack enemyStack, CardStack playerStack) {	
		//Added these for gui reasons
		Card enemyFaceDown = enemyStack.dealTopCard();
		Card playerFaceDown = playerStack.dealTopCard();
		
		victoryDeck.add(enemyFaceDown);
		victoryDeck.add(playerFaceDown);
		
		playerZone.addToTop(playerFaceDown);
		enemyZone.addToTop(enemyFaceDown);
		
		Card enemyCard = enemyStack.dealTopCard();
		Card playerCard = playerStack.dealTopCard();
		
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		return compare(enemyCard,playerCard,enemyStack,playerStack);
	}


	public void turn(CardStack enemy, CardStack player) {
		Card enemyCard = enemy.dealTopCard();
		Card playerCard = player.dealTopCard();
	
		compare(enemyCard,playerCard,enemy,player);
		if(!victoryDeck.isEmpty()) {
			victoryDeck.clear();
		}
		if(!playerZone.getStack().isEmpty()) {
			playerZone.emptyStack();
		}
		if(!enemyZone.getStack().isEmpty()) {
			enemyZone.emptyStack();
		}
	
	}
	
	public JPanel drawBoard() {
		JPanel outerPanel = new JPanel();
		JPanel panel = new JPanel();
		JPanel area1 = new JPanel();
		JPanel area2 = new JPanel();
		JPanel spacer = new JPanel();
		Color felt = new Color(10, 108, 3);
		BoxLayout spacerLayout = new BoxLayout(spacer, BoxLayout.X_AXIS);
		
		outerPanel.setBounds(0, 0, 1270, 576);
		outerPanel.setLayout(new FlowLayout());
		outerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		outerPanel.setBackground(felt);
		
		panel.setBounds(0, 0, 1270, 576);
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		panel.setBackground(felt);
		
		area1.setLayout(new BorderLayout());
		area1.setBackground(felt);
		area2.setLayout(new BorderLayout());
		area2.setBackground(felt);
		
		spacer.setLayout(spacerLayout);
		spacer.setBackground(felt);
		spacer.add(Box.createHorizontalStrut(500));
		spacer.add(Box.createVerticalStrut(300));
		
		area1.add(getPlayers().get(1).draw(), BorderLayout.WEST);
		area1.add(spacer, BorderLayout.CENTER);
		area1.add(enemyZone.draw(), BorderLayout.SOUTH);
		
		area2.add(playerZone.draw(), BorderLayout.NORTH);
		area2.add(spacer, BorderLayout.CENTER);
		area2.add(getPlayers().get(0).draw(), BorderLayout.EAST);
		
		panel.add(area1, BorderLayout.NORTH);
		panel.add(area2, BorderLayout.SOUTH);
		
		outerPanel.add(panel);
		
		return outerPanel;
	}
}
