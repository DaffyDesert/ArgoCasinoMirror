import java.util.ArrayList;

/**
 * Handles the turns and moves in a game of war
 * @author zw_ch
 *
 */

//Added extends Board because before it was not doing that.
public class WarBoard extends Board{
	private ArrayList<Card> victoryDeck;
	
	//Added parameters and super-constructor call
	public WarBoard(int numOfPlayers, int numOfDiscardPiles, int numOfDecks) {
		super(numOfPlayers, numOfDiscardPiles, numOfDecks);
		victoryDeck = new ArrayList<> ();
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
		
		if((enemyCard.getRank() == 0) && (playerCard.getRank() != 0)) {
			enemyStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			return false;
		}
		else if(playerCard.getRank() == 0 && (enemyCard.getRank() != 0)) {
			playerStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			return true;
		}
		else if(playerCard.getRank() > enemyCard.getRank()) {
			playerStack.addToBottom(victoryDeck);
			victoryDeck.clear();
			return true;
		}
		else if(enemyCard.getRank() > playerCard.getRank()) {
			enemyStack.addToBottom(victoryDeck);
			victoryDeck.clear();
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
		victoryDeck.add(enemyStack.dealTopCard());
		victoryDeck.add(playerStack.dealTopCard());
		
		Card enemyCard = enemyStack.dealTopCard();
		Card playerCard = playerStack.dealTopCard();
		
		return compare(enemyCard,playerCard,enemyStack,playerStack);
	}


public void turn(CardStack enemy, CardStack player) {
	Card enemyCard = enemy.dealTopCard();
	Card playerCard = player.dealTopCard();
	
	compare(enemyCard,playerCard,enemy,player);
	if(!victoryDeck.isEmpty()) {
		victoryDeck.clear();
	}
	
}	
}
