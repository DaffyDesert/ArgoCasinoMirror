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
		Card queenofSpades = new Card (12, "S");
		queenofSpades.setFaceDown(false);
		assertTrue(!column.addCard(queenofSpades));
		Card kingofClubs = new Card (13, "C");
		kingofClubs.setFaceDown(false);
		assertTrue(column.addCard(kingofClubs));
		
		//Test 2: order of cards can only be in sequential order
		Card queenofHearts = new Card(12, "H");
		queenofHearts.setFaceDown(false);
		assertTrue(column.addCard(queenofHearts));
		Card jackofSpades = new Card(11, "S");
		jackofSpades.setFaceDown(false);
		assertTrue(column.addCard(jackofSpades));
		
		// Should fail because the next card is not a 10
		Card aceofHearts = new Card(1, "H");
		aceofHearts.setFaceDown(false);
		assertTrue(!column.addCard(aceofHearts));
		
		//Class should only allow alternating colors when adding cards. ie: hearts(red), spade(black), diamond(red), club(black)
		//order right now is 13C, 12H, 11S. so it should only be a 10H or 10D
		Card tenofClubs = new Card(10,"C");
		tenofClubs.setFaceDown(false);
		assertTrue(!column.addCard(tenofClubs));
		Card tenofSpades = new Card(10, "S");
		tenofSpades.setFaceDown(false);
		assertTrue(!column.addCard(tenofSpades));
		
		Card tenofHearts = new Card(10, "H");
		tenofHearts.setFaceDown(false);
		assertTrue(column.addCard(tenofHearts));
		
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
	
	@Test
	public void TestIsKing() {
		column = new SolitaireColumn();
		assertTrue(column.isKing(new Card(13,"C")));
		assertTrue(column.isKing(new Card(13,"H")));
		assertTrue(column.isKing(new Card(13,"S")));
		assertTrue(column.isKing(new Card(13,"D")));
		
		assertTrue(!column.isKing(new Card(12,"C")));
	}
	
	@Test
	public void TestIsEmpty() {
		column = new SolitaireColumn();
		
		assertTrue(column.isEmpty());
		
		column.addCard(new Card(13,"H"));
		assertTrue(!column.isEmpty());
	}
	
	@Test
	public void testAdjacentAndDifferentColor() {
		column = new SolitaireColumn();
		
		//Different color and adjacent cards (should pass)
		Card FiveofHearts = new Card(5,"H");
		Card SixofSpades = new Card(6,"S");
		assertTrue(column.isAdjacentAndDifferentColor(SixofSpades, FiveofHearts));
		
		//Same color and adjacent cards (should fail)
		Card SevenofDiamonds = new Card(7, "D");
		Card EightofDiamonds = new Card(7, "D");
		assertTrue(!column.isAdjacentAndDifferentColor(EightofDiamonds, SevenofDiamonds));
		
		//Different color but non-adjacent cards (should fail)
		Card NineofClubs = new Card(9,"C");
		Card JackofHearts = new Card(11, "H");
		assertTrue(!column.isAdjacentAndDifferentColor(JackofHearts, NineofClubs));
		
		//Same color and non-adjacent cards (should fail)
		Card AceofSpades = new Card(1, "S");
		Card KingofClubs = new Card(13, "C");
		assertTrue(!column.isAdjacentAndDifferentColor(KingofClubs, AceofSpades));

	}
	
	@Test
	public void testColors() {
		SolitaireColumn column = new SolitaireColumn();
		Card FiveofHearts = new Card(5,"H");
		Card KingofClubs = new Card(13, "C");
		Card SevenofDiamonds = new Card(7, "D");
		Card AceofSpades = new Card(1, "S");
		
		assertTrue(column.isBlack(AceofSpades));
		assertTrue(column.isBlack(KingofClubs));
		assertTrue(column.isRed(FiveofHearts));
		assertTrue(column.isRed(SevenofDiamonds));

		assertTrue(!column.isRed(AceofSpades));
		assertTrue(!column.isBlack(FiveofHearts));

	}
}
