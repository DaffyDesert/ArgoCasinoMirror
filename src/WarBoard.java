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

public class WarBoard extends Board{
	
	JPanel mainPanel;
	
	public WarBoard() {
		super(2, 3, 1);
		getPlayers().get(0).setStackName("Enemy Stack");
		getPlayers().get(1).setStackName("Player Stack");
		getDiscardPiles().get(0).setStackName("Winner Spoils");
		getDiscardPiles().get(1).setStackName("Player Zone");
		getDiscardPiles().get(2).setStackName("Enemy Zone");
		
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
		
		mainPanel = new JPanel();
	}
	
	public CardStack getPlayerStack() {
		return getPlayers().get(1);
	}
	
	public CardStack getEnemyStack() {
		return getPlayers().get(0);

	}
	
	public CardStack getWinnerSpoils() {
		return getDiscardPiles().get(0);
	}
	
	
	public CardStack getPlayerZone() {
		return getDiscardPiles().get(1);
	}

	public CardStack getEnemyZone() {
		return getDiscardPiles().get(2);
	}
	
	public boolean turn() {
		if(checkWinStatus() != 2)
			return false;
		Card enemyCard = getEnemyStack().dealTopCard();
		Card playerCard = getPlayerStack().dealTopCard();
		
		getWinnerSpoils().addToTop(enemyCard);
		getWinnerSpoils().addToTop(playerCard);
		
		enemyCard.flipCard();
		playerCard.flipCard();
		
		getPlayerZone().addToTop(playerCard);
		getEnemyZone().addToTop(enemyCard);
		
		drawBoard();
		enemyCard.flipCard(); 
		playerCard.flipCard();		
		
		return compare();
	}
  
	private boolean compare() {
		int comparisonValue;
		
		comparisonValue = getWinnerSpoils().peekCard(getWinnerSpoils().getStackSize()-2).getRank()		
							-																			
							getWinnerSpoils().peekCard(getWinnerSpoils().getStackSize()-1).getRank();	
		
		if(comparisonValue < 0) { 
			getPlayerStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();
			getPlayerZone().getStack().clear(); 
			getEnemyZone().getStack().clear();
      
			return true;
		}
		else if(comparisonValue > 0) { 
			getEnemyStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();
			getPlayerZone().getStack().clear(); 
			getEnemyZone().getStack().clear();

			return true;
		}
		else if(comparisonValue == 0) {
			return goToWar();
		}
		else {
			System.out.print("ERROR AT compare()");
			return false;
		}
	}

	public boolean goToWar() {
		if(checkWinStatus() != 2)
			return false;

		getWinnerSpoils().addToTop(getEnemyStack().dealTopCard());
		getWinnerSpoils().addToTop(getPlayerStack().dealTopCard());
		
		return turn();
	}

	public int checkWinStatus() {
		int statusReturnCode = 2;
		if (getEnemyStack().getStackSize() == 0 && getPlayerStack().getStackSize() == 0) //TIE - EXTREMELY UNLIKLEY BUT POSSIBLE
			statusReturnCode = 0;
		else if(getPlayerStack().getStackSize() == 0)
			statusReturnCode = -1;
		else if(getEnemyStack().getStackSize() == 0)
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
		playerSide.add(getPlayers().get(1).draw());
		
		JPanel middle = new JPanel();
		middle.setLayout(new GridBagLayout());
		middle.setPreferredSize(new Dimension(423,720));
		middle.setBackground(felt);
		middle.add(getPlayerZone().draw());
		middle.add(getEnemyZone().draw());
		
		JPanel enemySide = new JPanel();
		enemySide.setLayout(new GridBagLayout());
		enemySide.setPreferredSize(new Dimension(423,720));
		enemySide.setBackground(felt);
		enemySide.add(getPlayers().get(0).draw());
		
		mainPanel.add(playerSide);
		mainPanel.add(middle);
		mainPanel.add(enemySide);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		return mainPanel;
	}

}