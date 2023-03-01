
public class demo {

	public static void main(String[] args) {
		WarBoard war = new WarBoard();
		Card c1 = new Card(0,1);
		Card c2 = new Card(4,2);
		Card c3 = new Card(5,1);
		Card c4 = new Card(10,1);
		Card c5 = new Card(13,2);
		CardStack enemy = new CardStack();
		CardStack player = new CardStack();
		
		

		enemy.addToBottom(c2);
		player.addToBottom(c1);
		System.out.println("Player winning: ");
		war.turn(enemy, player);
		player.printStack();
		player.dealTopCard(2);
		
		
		enemy.addToBottom(c1);
		player.addToBottom(c2);
		System.out.println("Enemy winning: ");
		war.turn(enemy, player);
		enemy.printStack();
		enemy.dealTopCard(2);

		
		
		System.out.println("Test goToWar no recusrion");
		player.addToBottom(c3);
		player.addToBottom(c4);
		player.addToBottom(c5);
		
		enemy.addToBottom(c3);
		enemy.addToBottom(c2);
		enemy.addToBottom(c1);
		war.turn(enemy, player);
		enemy.printStack();
		enemy.dealTopCard(6);
		
		System.out.println("Test goToWar with recursion");
		player.addToBottom(c3);
		player.addToBottom(c4);
		player.addToBottom(c5);
		player.addToBottom(c5);
		player.addToBottom(c3);
		
		enemy.addToBottom(c3);
		enemy.addToBottom(c2);
		enemy.addToBottom(c5);
		enemy.addToBottom(c4);
		enemy.addToBottom(c1);
		
		war.turn(enemy, player);
		enemy.printStack();
		
	}

}
