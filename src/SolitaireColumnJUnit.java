import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolitaireColumnJUnit {
	SolitaireColumn column;
	
	@Test
	public void addCardTest() {
		column = new SolitaireColumn();
		
		
		//Test 1: only kings can be added to an empty stack
		assertTrue(!column.addCard(new Card(12, "S")));
		assertTrue(column.addCard(new Card(13, "C")));
		
		//Test 2: order of cards can only be in seqential order
		column.addCard(new Card(12, "H"));
		column.addCard(new Card(11, "S"));
		
		// Should fail because the next card is not a 10
		assertTrue(!column.addCard(new Card(1, "S")));
	}
}
