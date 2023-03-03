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
 * @author danielM @date 3/1/23
 *
 */

public class WarBoard extends Board{
	
	//Added to Danny's stuff
	private CardStack playerZone;
	private CardStack enemyZone;
	
	public WarBoard() {
		super(2, 1, 1);
		getPlayers().get(0).setStackName("Enemy Stack");
		getPlayers().get(1).setStackName("Player Stack");
		getDiscardPiles().get(0).setStackName("Winner Spoils");
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
		
		playerZone = new CardStack();
		enemyZone = new CardStack();
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
		return playerZone;
	}

	public CardStack getEnemyZone() {
		return enemyZone;
	}
	
	/**
	 * each player moves their card to the winnerSpoil stack for comparison by compare()
	 * !!! ENEMY MUST DEAL TO SPOILS STACK FIRST !!!
	 * 
	 */
	public boolean turn() {
		if(checkWinStatus() != 2)
			return false;
		Card enemyCard = getEnemyStack().dealTopCard();
		Card playerCard = getPlayerStack().dealTopCard();
		
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		getWinnerSpoils().addToTop(enemyCard);
		getWinnerSpoils().addToTop(playerCard);
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

			playerZone.getStack().clear(); //New
			enemyZone.getStack().clear();
      
			return true;
		}
		else if(comparisonValue > 0) { //enemy win
			getEnemyStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();

			playerZone.getStack().clear(); //New
			enemyZone.getStack().clear();

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
	 * then calls turn() to initiate a draw and comparision to see who wins the current spoils stack
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
		
		if(checkWinStatus() != 2)
			return false;
		
		Card enemyCard = getEnemyStack().dealTopCard();
		Card playerCard = getPlayerStack().dealTopCard();
		
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		getWinnerSpoils().addToTop(playerCard);
		getWinnerSpoils().addToTop(enemyCard);
		
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
		else
			statusReturnCode = 1;
		
		return statusReturnCode;
	}
	
	//Added to danny's stuff
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
