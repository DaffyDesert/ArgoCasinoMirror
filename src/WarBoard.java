import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

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
	CardStack deck;
	String turnResult;
	String spoilsOutcome;

	public WarBoard() {
		deck = new CardStack("Deck");
		playerStack = new CardStack("Player Stack");
		enemyStack = new CardStack("Enemy Stack");
		winnerSpoils = new CardStack("Winner Spoils");
		playerZone = new CardStack();
		enemyZone = new CardStack();
		
		deck.createStandardDeck();
		setPointValue();
		deck.shuffleStack();
		deck.dealEvenlyTo(playerStack, enemyStack);
		mainPanel = new JPanel();
	}
	
	private void setPointValue() {
		for(int i  = 0; i < deck.getStackSize(); i++) {
			if (deck.peekCard(i).getRank() == 1)//if rank == 1 == ace; then set pointVal to 14
				deck.peekCard(i).setPointValue(14);
			else
				deck.peekCard(i).setPointValue(deck.peekCard(i).getRank());//else PointVal = rank				
		}
	}
	
	public String getResultMessage() {
		return turnResult;
	}

	public void setResultMessage(String msg) {
		this.turnResult = msg;
	}

	public String getOutcometMessage() {
		return spoilsOutcome;
	}

	public void setOutcomeMessage(String msg) {
		this.spoilsOutcome = msg;
	}

	public boolean turn() {
		if(checkWinStatus() != 2)
			return false;

		playerZone.addToTop(playerStack.dealTopCard());
		enemyZone.addToTop(enemyStack.dealTopCard());
		
		return compare(playerZone.peekCard(playerZone.getStackSize()-1), enemyZone.peekCard(enemyZone.getStackSize()-1));
	}
  
	public boolean compare(Card playerCard, Card enemyCard) {
		
		if(playerCard.getPointValue() == enemyCard.getPointValue()) {
			turnResult = "Your card " + playerCard.toString() + " is equal to the enemy card " + enemyCard.toString();
			spoilsOutcome = " This... means... WAR!!!! ";
			goToWar();
		}
		else if(playerCard.getPointValue() > enemyCard.getPointValue()) {
			turnResult = "Your card " + playerCard.toString() + " beats the enemy card " + enemyCard.toString() + ". ";
			spoilsOutcome = "Winner spoils of " + (winnerSpoils.getStackSize() + 2) + " cards will be moved to your stack.";
			
			playerZone.flipTopCard();
			enemyZone.flipTopCard();
			
			drawBoard();

			playerZone.flipTopCard();
			enemyZone.flipTopCard();


			winnerSpoils.addToTop(playerZone.dealTopCard());
			winnerSpoils.addToTop(enemyZone.dealTopCard());

			while(!(winnerSpoils.getStackSize() == 0))
				playerStack.addToBottom(winnerSpoils.dealTopCard());
		}
		else if(playerCard.getPointValue() < enemyCard.getPointValue()) {
			turnResult = "Your card " + playerCard.toString() + " loses to the enemy card " + enemyCard.toString() + ". ";
			spoilsOutcome = "Winner spoils of " + (winnerSpoils.getStackSize() + 2) + " cards will be moved to the enemy stack.";
			
			playerZone.flipTopCard();
			enemyZone.flipTopCard();
			
			drawBoard();

			playerZone.flipTopCard();
			enemyZone.flipTopCard();


			winnerSpoils.addToTop(playerZone.dealTopCard());
			winnerSpoils.addToTop(enemyZone.dealTopCard());

			while(!(winnerSpoils.getStackSize() == 0))
				enemyStack.addToBottom(winnerSpoils.dealTopCard());
		}
		return true;
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
		Color felt = new Color(0,122,51);
		
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout());
		mainPanel.setPreferredSize(new Dimension(1270,720));
		mainPanel.setBackground(felt); 
		
		JLabel turnResultMessage = new JLabel(getResultMessage());
		JPanel turnResultArea = new JPanel();
		turnResultArea.setLayout(new BoxLayout(turnResultArea, BoxLayout.Y_AXIS));
		turnResultArea.setBackground(felt);
		turnResultArea.add(turnResultMessage);
		turnResultArea.setPreferredSize(new Dimension(1270,20));
		
		JLabel turnOutcomeMessage = new JLabel(getOutcometMessage());
		JPanel turnOutcomeArea = new JPanel();
		turnOutcomeArea.setLayout(new BoxLayout(turnOutcomeArea, BoxLayout.Y_AXIS));
		turnOutcomeArea.setBackground(felt);
		turnOutcomeArea.add(turnOutcomeMessage);
		turnOutcomeArea.setPreferredSize(new Dimension(1270,20));
		
		JPanel playerSide = new JPanel(new GridLayout());
		playerSide.setLayout(new GridBagLayout());
		playerSide.setPreferredSize(new Dimension(423,720));
		playerSide.setBackground(felt);
		playerStack.draw().setToolTipText("These are your cards on this side of the board");
		playerSide.add(playerStack.draw());

		JPanel playZone = new JPanel();
		playZone.setLayout(new GridBagLayout());
		playZone.setPreferredSize(new Dimension(423,720));
		playZone.setBackground(felt);
		playZone.add(playerZone.draw());
		playZone.add(enemyZone.draw());
		
		JPanel middle = new JPanel();
		middle.setBackground(felt);
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		middle.add(playZone);
		turnResultArea.setToolTipText("This is a text representation of the results of the turn");
		middle.add(turnResultArea);
		turnOutcomeMessage.setToolTipText("This is a text representation of the results of the turn");
		middle.add(turnOutcomeMessage);
		
		JPanel enemySide = new JPanel();
		enemySide.setLayout(new GridBagLayout());
		enemySide.setPreferredSize(new Dimension(423,720));
		enemySide.setBackground(felt);
		enemyStack.draw().setToolTipText("These are the Enemy cards on this side of the board");
		enemySide.add(enemyStack.draw());
		
		mainPanel.add(playerSide);
		mainPanel.add(middle);
		mainPanel.add(enemySide);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		return mainPanel;
	}


}