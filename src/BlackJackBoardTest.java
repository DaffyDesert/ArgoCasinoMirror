import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BlackJackBoardTest {
	BlackJackBoard testBoard;
	Card ace;
	Card two;
	Card five;
	Card king;

	@BeforeAll
	public void testableObjects() {
		testBoard = new BlackJackBoard();
	}	
	
	@BeforeEach
	public  void setUpBoard() {
		testBoard = new BlackJackBoard();
	}
	
	@AfterEach
	public void clearBoard() {
		testBoard.getDeck().emptyStack();
		testBoard.playerHand().emptyStack();
		testBoard.dealerHand().emptyStack();
	}
		
	@Test
	void constructorMakeDeckDealPlayer() {
		assertTrue(testBoard.getDeck().getStackSize() == 312);
		assertTrue(testBoard.playerHand().getStackSize() == 0);
		assertTrue(testBoard.dealerHand().getStackSize() == 0);
	}

	@Test
	void testSetBoard() {
		
		testBoard.setBoard();
		
		assertTrue(testBoard.playerHand().getStackSize() == 2);
		assertFalse(testBoard.playerHand().peekCard(0).isFaceDown());
		assertFalse(testBoard.playerHand().peekCard(1).isFaceDown());
	
		assertTrue(testBoard.dealerHand().getStackSize() == 2);
		assertFalse(testBoard.dealerHand().peekCard(0).isFaceDown());
		assertTrue(testBoard.dealerHand().peekCard(1).isFaceDown());
		
		assertTrue(testBoard.getDeck().getStackSize() == 308);

	}

	@Test
	void testSetValue() {
		
		testBoard.setCardValues();
		
		assertTrue(testBoard.getDeck().peekCard(0).getRank() == 2);
		assertTrue(testBoard.getDeck().peekCard(1).getRank() == 3);
		assertTrue(testBoard.getDeck().peekCard(2).getRank() == 4);
		assertTrue(testBoard.getDeck().peekCard(3).getRank() == 5);
		assertTrue(testBoard.getDeck().peekCard(4).getRank() == 6);
		assertTrue(testBoard.getDeck().peekCard(5).getRank() == 7);
		assertTrue(testBoard.getDeck().peekCard(6).getRank() == 8);
		assertTrue(testBoard.getDeck().peekCard(7).getRank() == 9);
		assertTrue(testBoard.getDeck().peekCard(8).getRank() == 10);
		assertTrue(testBoard.getDeck().peekCard(9).getRank() == 10);
		assertTrue(testBoard.getDeck().peekCard(10).getRank() == 10);
		assertTrue(testBoard.getDeck().peekCard(11).getRank() == 10);
		assertTrue(testBoard.getDeck().peekCard(12).getRank() == 11);
		
	}
	
	@Test
	void testAcesInHand() {
		
		assertTrue(testBoard.acesInHand(testBoard.getDeck()) == 24);
		assertTrue(testBoard.acesInHand(testBoard.playerHand()) == 0);
		assertTrue(testBoard.acesInHand(testBoard.dealerHand()) == 0);

		testBoard.playerHand().addToTop(ace);
		testBoard.dealerHand().addToTop(ace);
	
		assertTrue(testBoard.acesInHand(testBoard.getDeck()) == 24);
		assertTrue(testBoard.acesInHand(testBoard.playerHand()) == 1);
		assertTrue(testBoard.acesInHand(testBoard.dealerHand()) == 1);

		testBoard.playerHand().addToTop(ace);
		testBoard.playerHand().addToTop(ace);
	
		assertTrue(testBoard.acesInHand(testBoard.getDeck()) == 24);
		assertTrue(testBoard.acesInHand(testBoard.playerHand()) == 3);
		assertTrue(testBoard.acesInHand(testBoard.dealerHand()) == 1);
}
	
	@Test
	void testHandTotalBust21() {
		testBoard.setCardValues();
		ace = testBoard.getDeck().peekCard(12);
		two = testBoard.getDeck().peekCard(0);
		five = testBoard.getDeck().peekCard(3);
		king = testBoard.getDeck().peekCard(11);
		
		testBoard.playerHand().addToTop(ace);
		testBoard.playerHand().addToTop(two);
		
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 13);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(king);
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 13);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(five);
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 18);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(ace);
		testBoard.playerHand().addToTop(ace);
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 20);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(ace);

		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 21);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertTrue(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(ace);

		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 22);
		assertTrue(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));
	}	
	
	@Test
	void testHitDealsCardandFlipsCard() {
		assertTrue(testBoard.getDeck().getStackSize() == 312);
		assertTrue(testBoard.playerHand().getStackSize() == 0);
		assertTrue(testBoard.dealerHand().getStackSize() == 0);

		testBoard.hit(testBoard.playerHand());

		assertTrue(testBoard.getDeck().getStackSize() == 311);
		assertTrue(testBoard.playerHand().getStackSize() == 1);
		assertTrue(testBoard.dealerHand().getStackSize() == 0);

		testBoard.hit(testBoard.playerHand());
		testBoard.hit(testBoard.dealerHand());
		testBoard.hit(testBoard.dealerHand());

		assertTrue(testBoard.getDeck().getStackSize() == 308);
		assertTrue(testBoard.playerHand().getStackSize() == 2);
		assertTrue(testBoard.dealerHand().getStackSize() == 2);

		assertFalse(testBoard.playerHand().peekCard(0).isFaceDown());
		assertFalse(testBoard.playerHand().peekCard(1).isFaceDown());
		assertFalse(testBoard.dealerHand().peekCard(0).isFaceDown());
		assertFalse(testBoard.dealerHand().peekCard(1).isFaceDown());
	}
	
	@Test
	void testHitReturnsFalseIfBustOr21() {
		//Deck unshuffled - dealing from top results in ace, king, queen, jack, ten, etc.......
		testBoard.setCardValues();
		
		testBoard.hit(testBoard.playerHand()); //ace = 11
		assertFalse(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		
		testBoard.hit(testBoard.playerHand()); //ace + king = 21
		assertTrue(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));

		testBoard.hit(testBoard.playerHand()); //ace + king + Queen = 21
		assertTrue(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));

		testBoard.hit(testBoard.playerHand()); //ace + king + Queen + Jack = 31
		assertFalse(testBoard.is21(testBoard.playerHand()));
		assertTrue(testBoard.isBust(testBoard.playerHand()));
	}
	
	@Test
	void testStayAndDealerTurn() {
		testBoard.setCardValues();
		ace = testBoard.getDeck().peekCard(12);
		two = testBoard.getDeck().peekCard(0);
		five = testBoard.getDeck().peekCard(3);
		king = testBoard.getDeck().peekCard(11);
		
		testBoard.getDeck().emptyStack();
		
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(king);
		
		testBoard.stay();

		assertTrue(testBoard.handTotal(testBoard.dealerHand()) == 20);
		
		testBoard.getDeck().emptyStack();
		testBoard.dealerHand().emptyStack();
		
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(two);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(ace);
		
		testBoard.stay();

		assertTrue(testBoard.handTotal(testBoard.dealerHand()) == 18);
	}
}
