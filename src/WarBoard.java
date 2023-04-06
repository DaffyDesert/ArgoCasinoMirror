import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * WarBoard handles the logic for a game of War. It holds player 
 * card data and manipulates it on the game board. It also 
 * passes a JPanel display object to the War class for display.
 */

public class WarBoard{
	
	JPanel mainPanel;
	CardStack playerStack;	
	CardStack enemyStack;
	CardStack winnerSpoils;
	CardStack playerZone;
	CardStack enemyZone;
	
	public WarBoard() {
		CardStack standDeck = new CardStack();
		playerStack = new CardStack("Player Stack");
		enemyStack = new CardStack("Enemy Stack");
		winnerSpoils = new CardStack("Winner Spoils");
		playerZone = new CardStack();
		enemyZone = new CardStack();
		
		standDeck.createStandardDeck();
		standDeck.shuffleStack();
		standDeck.dealEvenlyTo(standDeck, playerStack, enemyStack);
		playerStack.printStack();
		enemyStack.printStack();
		
		mainPanel = new JPanel();
	}
	
	public boolean turn() {
		if(checkWinStatus() != 2)
			return false;
		Card enemyCard = enemyStack.dealTopCard();
		Card playerCard = playerStack.dealTopCard();
		
		winnerSpoils.addToTop(enemyCard);
		winnerSpoils.addToTop(playerCard);
		
		enemyCard.setFaceDown(false);
		playerCard.setFaceDown(false);
		
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		drawBoard();
		enemyCard.setFaceDown(false);
		playerCard.setFaceDown(false);		
		
		return compare(playerCard, enemyCard);
	}
  
	public boolean compare(Card playerCard, Card enemyCard) {
		
		System.out.println("Comparing cards: Player - " + playerCard.toString() + " Enemy Card - " + enemyCard.toString());
		if(playerCard.getRank() == 1) {
			while(!winnerSpoils.getStack().isEmpty()) 
				playerStack.addToBottom(winnerSpoils.dealTopCard());
			
			return true;
		}
		else if(enemyCard.getRank() == 1) {
			while(!winnerSpoils.getStack().isEmpty()) 
				enemyStack.addToBottom(winnerSpoils.dealTopCard());

			return true;
		}
		else if(playerCard.getRank() > enemyCard.getRank()) {
			while(!winnerSpoils.getStack().isEmpty()) 
				playerStack.addToBottom(winnerSpoils.dealTopCard());
			
			return true;
		}
		else {
			while(!winnerSpoils.getStack().isEmpty()) 
				enemyStack.addToBottom(winnerSpoils.dealTopCard());
			
			return true;
		}
	}

	public boolean goToWar() {
		if(checkWinStatus() != 2)
			return false;

		winnerSpoils.addToTop(enemyStack.dealTopCard());
		winnerSpoils.addToTop(playerStack.dealTopCard());
		
		return turn();
	}

	public int checkWinStatus() {
		int statusReturnCode = 2;
		if (enemyStack.getStackSize() == 0 && playerStack.getStackSize() == 0) //TIE - EXTREMELY UNLIKLEY BUT POSSIBLE
			statusReturnCode = 0;
		else if(playerStack.getStackSize() == 0)
			statusReturnCode = -1;
		else if(enemyStack.getStackSize() == 0)
			statusReturnCode = 1;
		
		return statusReturnCode;
	}
	
	public JPanel drawBoard() {
		Color felt = new Color(10, 108, 3);
		
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout());
		mainPanel.setPreferredSize(new Dimension(1270,720));
		mainPanel.setBackground(felt); 
		
		JPanel playerSide = new JPanel();
		playerSide.setLayout(new GridBagLayout());
		playerSide.setPreferredSize(new Dimension(423,720));
		playerSide.setBackground(felt);
		playerSide.add(playerStack.draw());
		
		JPanel middle = new JPanel();
		middle.setLayout(new GridBagLayout());
		middle.setPreferredSize(new Dimension(423,720));
		middle.setBackground(felt);
		middle.add(playerZone.draw());
		middle.add(enemyZone.draw());
		
		JPanel enemySide = new JPanel();
		enemySide.setLayout(new GridBagLayout());
		enemySide.setPreferredSize(new Dimension(423,720));
		enemySide.setBackground(felt);
		enemySide.add(enemyStack.draw());
		
		mainPanel.add(playerSide);
		mainPanel.add(middle);
		mainPanel.add(enemySide);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		return mainPanel;
	}

}