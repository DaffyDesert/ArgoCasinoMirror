
public class BoardDriver {

	public static void main(String[] args) {
		
		int numOfPlayers = 5;
		int numOfDiscardPiles = 2;
		int numOfDecks = 2;
		
		Board testBoard = new Board(numOfPlayers, numOfDiscardPiles, numOfDecks);
		
		testBoard.printBoard();
		testBoard.getDeck().dealEvenlyTo(testBoard.getPlayers());
		testBoard.printBoard();
		}

}
