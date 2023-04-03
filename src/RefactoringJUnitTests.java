import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;


public class RefactoringJUnitTests {
	@Test
	public void CardConstructorTest() {
		Card c1 = new Card(1, "S");
		Card c2 = new Card(2, "S");
		Card c3 = new Card(11, "S");
		Card c4 = new Card(12, "S");
		Card c5 = new Card(13, "S");
		
		assertTrue(c1.toString().compareTo("AS") == 0);
		assertTrue(c2.toString().compareTo("2S") == 0);
		assertTrue(c3.toString().compareTo("JS") == 0);
		assertTrue(c4.toString().compareTo("QS") == 0);
		assertTrue(c5.toString().compareTo("KS") == 0);		
	}
	
	@Test
	public void CardStackConstructorTest() {
		CardStack cardStack1 = new CardStack();
		CardStack cardStack2 = new CardStack("Stackname");		
		
		assertTrue(cardStack1.getStackName().compareTo("") == 0);
		assertTrue(cardStack2.getStackName().compareTo("Stackname") == 0);
	}
	
	@Test
	public void TestCreateStandardDeck() {
		CardStack cardstack = new CardStack("standard deck");
		
		cardstack.createStandardDeck();
		
		// Cant exactly test this through JUnit so outputing to screen feels like the next best thing
		for(int i = 0; i < cardstack.getStackSize(); ++i) {
			//System.out.println(cardstack.getStack().get(i).toString());
		}
	}
	
	@Test
	public void TestAddingToStack() {
		CardStack cs1 = new CardStack();
		
		assertTrue(cs1.getStack().isEmpty());
		
		cs1.addToTop(new Card(1, "S"));
		cs1.addToTop(new Card(5, "H"));
		cs1.addToTop(new Card(7, "D"));
		
		assertTrue(cs1.dealTopCard().toString().compareTo("7D") == 0);
		assertTrue(cs1.dealTopCard().toString().compareTo("5H") == 0);
		assertTrue(cs1.dealTopCard().toString().compareTo("AS") == 0);

		cs1.getStack().clear();
		
		cs1.addToBottom(new Card(13, "H"));
		cs1.addToBottom(new Card(4, "C"));
		cs1.addToBottom(new Card(9, "S"));
		
		assertTrue(cs1.dealTopCard().toString().compareTo("KH") == 0);
		assertTrue(cs1.dealTopCard().toString().compareTo("4C") == 0);
		assertTrue(cs1.dealTopCard().toString().compareTo("9S") == 0);
	}
	
	@Test
	public void TestShuffleStack() {
		CardStack cs1 = new CardStack();
		
		cs1.addToTop(new Card(1, "S"));
		cs1.addToTop(new Card(3, "S"));
		cs1.addToTop(new Card(13, "D"));
		cs1.addToTop(new Card(11, "H"));
		
		cs1.printStack();
		
		cs1.shuffleStack();
		
		cs1.printStack();

	}
}
