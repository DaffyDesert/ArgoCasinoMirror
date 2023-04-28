import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolitaireBoardJUnit {
	@Test
	public void SolitaireBoardTest() {
		SolitaireBoard board = new SolitaireBoard();
		
		//does board create an entire deck
		assertTrue(board.getDeck().getStackSize() == 52);
		
		//does each column have the correct amount of cards
		board.dealToColumns();
		assertTrue(board.getColumnAt(0).getColumn().getStackSize() == 1);
		assertTrue(board.getColumnAt(1).getColumn().getStackSize() == 2);
		assertTrue(board.getColumnAt(2).getColumn().getStackSize() == 3);
		assertTrue(board.getColumnAt(3).getColumn().getStackSize() == 4);
		assertTrue(board.getColumnAt(4).getColumn().getStackSize() == 5);
		assertTrue(board.getColumnAt(5).getColumn().getStackSize() == 6);
		assertTrue(board.getColumnAt(6).getColumn().getStackSize() == 7);
		
		//is the last card in the column facing up?
		assertTrue(!board.getColumnAt(0).getColumn().peekCard(0).isFaceDown());
		assertTrue(!board.getColumnAt(1).getColumn().peekCard(0).isFaceDown());

	}
}
