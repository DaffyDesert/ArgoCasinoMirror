import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Handles the turns and moves in a game of war
 * @author zw_ch
 * @author danielM @date 3/1/23
 *
 */

public class WarBoard extends Board{
	
	//Added to Danny's stuff
	//private CardStack playerZone;
	//private CardStack enemyZone;
	JPanel mainPanel;
	
	public WarBoard() {
		super(2, 3, 1);
		getPlayers().get(0).setStackName("Enemy Stack");
		getPlayers().get(1).setStackName("Player Stack");
		getDiscardPiles().get(0).setStackName("Winner Spoils");
		getDiscardPiles().get(1).setStackName("Player Zone");
		getDiscardPiles().get(2).setStackName("Enemy Zone");
		
		System.out.println("Shuffling and Dealing...");
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
		
		//getPlayers().get(0).printStack();
		//getPlayers().get(1).printStack();
		//getDiscardPiles().get(0).printStack();
		printBoard();
		
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

		getPlayerStack().printStack();
		getEnemyStack().printStack();
		getWinnerSpoils().printStack();
		
		return compare();
	}
  
  
	/**
	 * Compares the top two cars of the winnerSpoils stack
	 * Assumes enemy added card to stack first
	 * 
	 * cards will be added to the bottom of the winning players stack (based on greatest rank value)
	 * ties will call goToWar()
	 * 
	 */
	private boolean compare() {
		int comparisonValue;
		
		comparisonValue = getWinnerSpoils().peekCard(getWinnerSpoils().getStackSize()-2).getRank()		//enemyCard Rank
							-																			// subtracted by
							getWinnerSpoils().peekCard(getWinnerSpoils().getStackSize()-1).getRank();	//playerCard Rank
		
		if(comparisonValue < 0) { //player win
			getPlayerStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();
			getPlayerZone().getStack().clear(); //New
			getEnemyZone().getStack().clear();
      
			return true;
		}
		else if(comparisonValue > 0) { //enemy win
			getEnemyStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();
			getPlayerZone().getStack().clear(); //New
			getEnemyZone().getStack().clear();

			return true;
		}
		else if(comparisonValue == 0) { //tie
			return goToWar();
		}
		else {
			System.out.print("ERROR AT compare()");
			return false;
		}
	}

	/**
	 * deals the two treasure cards(one from each player) to the winnerSpoils stack
	 * 
	 * then calls turn() to initiate a draw and comparison to see who wins the current spoils stack
	 * 
	 * @param enemyStack
	 * @param playerStack
	 * @return True-player won False-enemy won
	 */
	public boolean goToWar() {
		if(checkWinStatus() != 2)
			return false;

		getWinnerSpoils().addToTop(getEnemyStack().dealTopCard());
		getWinnerSpoils().addToTop(getPlayerStack().dealTopCard());
		
		
		return turn();
	}

	/**
	 * statusReturnCode:
	 * 2 = continue
	 * 1 = player win
	 * 0 = tie
	 * -1 = enemy win
	 * @return
	 */
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
	
	//Added to danny's stuff
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