
public class BoardDriver {

	public static void main(String[] args) {
		
		int numOfPlayers = 5;
		int numOfDiscardPiles = 2;
		int numOfDecks = 1;
		
		Board testBoard = new Board(numOfPlayers, numOfDiscardPiles, numOfDecks);
		
		System.out.println("*** PRINTING GAME BOARD IMMEDIATLEY AFTER CREATION ***");
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER SHUFFLING DECK ***");
		testBoard.getDeck().shuffleStack();
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER DEALING CARDS TO ALL PLAYERS EVENLY ONE AT A TIME ***");
		testBoard.getDeck().dealEvenlyTo(testBoard.getPlayers());
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER EACH PLAYER DISCARDS ONE CARD FROM TOP TO DISCARD PILE 1 ***");
		for (CardStack players: testBoard.getPlayers()) {
			testBoard.getDiscardPiles().get(0).addToTop(players.dealTopCard());
		}
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER EACH PLAYER DISCARDS TWO CARDS FROM TOP TO DISCARD PILE 2 ***");
		for (CardStack players: testBoard.getPlayers()) {
			testBoard.getDiscardPiles().get(1).addToTop(players.dealTopCard(2));
		}
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER PLAYER 1 RECIEVES ALL CARDS FROM DISCARD PILE 2 TO THE BOTTOM OF PLAYER 1 STACK ***");
		testBoard.getPlayers().get(0).addToBottom(testBoard.getDiscardPiles().get(1).getStack());
		testBoard.printBoard();
	
	}

}
