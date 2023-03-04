import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
	private JPanel boardPanel = new JPanel();
	JPanel mainPanel;
	
	public WarBoard() {
		super(2, 1, 1);
		getPlayers().get(0).setStackName("Enemy Stack");
		getPlayers().get(1).setStackName("Player Stack");
		getDiscardPiles().get(0).setStackName("Winner Spoils");
		
		System.out.println("Shuffling and Dealing...");
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
		
		getPlayers().get(0).printStack();
		getPlayers().get(1).printStack();
		getDiscardPiles().get(0).printStack();
		
		playerZone = new CardStack();
		enemyZone = new CardStack();
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
		
		getWinnerSpoils().addToTop(enemyCard);
		getWinnerSpoils().addToTop(playerCard);
		
		enemyCard.flipCard();
		playerCard.flipCard();
		
		playerZone.addToTop(playerCard);
		enemyZone.addToTop(enemyCard);
		
		drawBoard();
		enemyCard.flipCard(); 		//gotta flip the cards back 
		playerCard.flipCard();		//before u give them to the winner

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
		JLabel empty = new JLabel();
		
		
		/*boardPanel.removeAll(); 
		JPanel area1 = new JPanel();
		JPanel area2 = new JPanel();
		JPanel spacer = new JPanel();
		Color felt = new Color(10, 108, 3);
		BoxLayout spacerLayout = new BoxLayout(spacer, BoxLayout.X_AXIS);
		
		boardPanel.setBounds(0, 0, 1270, 576);
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		boardPanel.setBackground(felt);
		
		area1.setLayout(new BorderLayout());
		area1.setBackground(felt);
		area2.setLayout(new BorderLayout());
		area2.setBackground(felt);
		
		spacer.setLayout(spacerLayout);
		spacer.setBackground(felt);
		spacer.add(Box.createHorizontalStrut(300));
		spacer.add(Box.createVerticalStrut(100));
		
		area1.add(getPlayers().get(0).draw(), BorderLayout.WEST);
		area1.add(spacer, BorderLayout.CENTER);
		area1.add(enemyZone.draw(), BorderLayout.SOUTH);
		
		area2.add(getPlayers().get(1).draw(), BorderLayout.EAST);
		area2.add(spacer, BorderLayout.CENTER);
		area2.add(playerZone.draw(), BorderLayout.NORTH);
		
		boardPanel.add(area1,BorderLayout.NORTH);
		boardPanel.add(spacer);
		boardPanel.add(area2,BorderLayout.SOUTH); */
		
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
		middle.add(playerZone.draw());
		middle.add(enemyZone.draw());
		
		JPanel enemySide = new JPanel();
		enemySide.setLayout(new GridBagLayout());
		enemySide.setPreferredSize(new Dimension(423,720));
		enemySide.setBackground(felt);
		enemySide.add(getPlayers().get(0).draw());
		
		mainPanel.add(playerSide);
		mainPanel.add(middle);
		mainPanel.add(enemySide);
		
		//boardPanel.revalidate();
		//boardPanel.repaint();
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		return mainPanel;
	}

}