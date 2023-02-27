import java.util.ArrayList;

/**
 * Handles the turns and moves in a game of war
 * @author zw_ch
 *
 */

public class WarBoard {
	private ArrayList<Card> victoryDeck;
	
	public WarBoard() {
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
	 * For the war move draws two cards from each deck and adds it to the victory pile
	 * uses the compare method as recursive function 
	 * 
	 * @param enemyStack
	 * @param playerStack
	 * @return True-player won False-enemy won
	 */
	public boolean goToWar(CardStack enemyStack, CardStack playerStack) {
		CardStack enemy = new CardStack();
		enemy.addToBottom(enemyStack.dealTopCard(2));
		
		CardStack player = new CardStack();
		player.addToBottom(playerStack.dealTopCard(2));
		
		Card enemyCard = enemy.getStack().get(1);
		Card playerCard = player.getStack().get(1);
		
		victoryDeck.add(enemy.getStack().get(1));
		victoryDeck.add(player.getStack().get(1));
	

		
		if(!compare(enemyCard,playerCard, enemyStack,playerStack)) {
			return false;
		}
		else {
			return true;
		}
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
