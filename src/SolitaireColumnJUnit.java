import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolitaireColumnJUnit {
	SolitaireColumn column;
	SolitaireColumn column2;
	
	@Test
	public void addCardTest() {
		column = new SolitaireColumn();
		column2 = new SolitaireColumn();
		
		//Test 1: only kings can be added to an empty stack
		assertTrue(!column.addCard(new Card(12, "S")));
		assertTrue(column.addCard(new Card(13, "C")));
		
		//Test 2: order of cards can only be in seqential order
		assertTrue(column.addCard(new Card(12, "H")));
		assertTrue(column.addCard(new Card(11, "S")));
		
		// Should fail because the next card is not a 10
		assertTrue(!column.addCard(new Card(1, "S")));
		
		//Class should only allow alternating colors when adding cards. ie: hearts(red), spade(black), diamond(red), club(black)
		//order right now is 13C, 12H, 11S. so it should only be a 10H or 10D
		assertTrue(!column.addCard(new Card(10, "C")));
		assertTrue(!column.addCard(new Card(10, "S")));
		
		assertTrue(column.addCard(new Card(10, "H")));
		
		/***** Testing another Column to simulate a full column *****/
		assertTrue(column2.addCard(new Card(13, "H")));
		assertTrue(column2.addCard(new Card(12, "S")));
		assertTrue(column2.addCard(new Card(11, "D")));
		assertTrue(column2.addCard(new Card(10, "C")));
		assertTrue(column2.addCard(new Card(9, "H")));
		assertTrue(column2.addCard(new Card(8, "S")));
		assertTrue(column2.addCard(new Card(7, "D")));
		assertTrue(column2.addCard(new Card(6, "C")));
		assertTrue(column2.addCard(new Card(5, "H")));
		assertTrue(column2.addCard(new Card(4, "S")));
		assertTrue(column2.addCard(new Card(3, "D")));
		assertTrue(column2.addCard(new Card(2, "C")));
		assertTrue(column2.addCard(new Card(1, "H")));











	}
}
