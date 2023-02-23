import javax.swing.JFrame;

public class BoardDriver {

	public static void main(String[] args) {
		
		int numOfPlayers = 2;
		int numOfDiscardPiles = 2;
		int numOfDecks = 1;
		
		Board testBoard = new Board(numOfPlayers, numOfDiscardPiles, numOfDecks);
		System.out.println("*** IF CARD VALUES ARE SHOWING AS \"FD\" then comment \"//\" out the first two lines of the getValue() method in the card class ***");
		
		System.out.println("*** PRINTING GAME BOARD IMMEDIATLEY AFTER CREATION ***");
		System.out.println();
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER SHUFFLING DECK ***");
		System.out.println();
		testBoard.getDeck().shuffleStack();
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER DEALING CARDS TO ALL PLAYERS EVENLY ONE AT A TIME ***");
		System.out.println();
		testBoard.getDeck().dealEvenlyTo(testBoard.getPlayers());
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER EACH PLAYER DISCARDS ONE CARD FROM TOP TO DISCARD PILE 1 ***");
		System.out.println();
		for (CardStack players: testBoard.getPlayers()) {
			testBoard.getDiscardPiles().get(0).addToTop(players.dealTopCard());
		}
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER EACH PLAYER DISCARDS TWO CARDS FROM TOP TO DISCARD PILE 2 ***");
		System.out.println();
		for (CardStack players: testBoard.getPlayers()) {
			testBoard.getDiscardPiles().get(1).addToTop(players.dealTopCard(2));
		}
		testBoard.printBoard();
		System.out.println("*** PRINTING GAME BOARD AFTER PLAYER 1 RECIEVES ALL CARDS FROM DISCARD PILE 2 TO THE BOTTOM OF PLAYER 1 STACK ***");
		System.out.println();
		testBoard.getDiscardPiles().get(1).dealTopCard(testBoard.getDiscardPiles().get(1).getStack().size());
		testBoard.printBoard();
	
		JFrame gui = new JFrame("Test Board");
		
		gui.add(testBoard.drawBoard(null));
		gui.setVisible(true);
		gui.setSize(750, 750);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		
}
