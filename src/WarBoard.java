

/**
 * Handles the turns and moves in a game of war
 * @author zw_ch
 * @author danielM @date 3/1/23
 *
 */

public class WarBoard extends Board{
	
	public WarBoard() {
		super(2, 1, 1);
		getPlayers().get(0).setStackName("Enemy Stack");
		getPlayers().get(1).setStackName("Player Stack");
		getDiscardPiles().get(0).setStackName("Winner Spoils");
		getDeck().shuffleStack();
		getDeck().dealEvenlyTo(getPlayers());
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
	
	/**
	 * each player moves their card to the winnerSpoil stack for comparison by compare()
	 * !!! ENEMY MUST DEAL TO SPOILS STACK FIRST !!!
	 * 
	 */
	public boolean turn() {
		getWinnerSpoils().addToTop(getEnemyStack().dealTopCard());
		getWinnerSpoils().addToTop(getPlayerStack().dealTopCard());
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
			return true;
		}
		else if(comparisonValue > 0) { //enemy win
			getEnemyStack().addToBottom(getWinnerSpoils().getStack());
			getWinnerSpoils().getStack().clear();
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
		else
			statusReturnCode = 1;
		
		return statusReturnCode;
	}

		
}
