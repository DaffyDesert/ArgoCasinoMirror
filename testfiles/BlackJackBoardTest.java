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
	Card three;
	Card four;
	Card five;
	Card six;
	Card seven;
	Card eight;
	Card nine;
	Card ten;
	Card jack;
	Card queen;
	Card king;

	@BeforeAll
	public void testableObjects() {
		testBoard = new BlackJackBoard();
		ace = new Card(1, "H");
		two = new Card(2, "H");
		three = new Card(3, "H");
		four = new Card(4, "H");
		five = new Card(5, "H");
		six = new Card(6, "H");
		seven = new Card(7, "H");
		eight = new Card(8, "H");
		nine = new Card(9, "H");
		ten = new Card(10, "H");
		jack = new Card(11, "H");
		queen = new Card(1, "H");
		king = new Card(13, "H");

}	
	
	@BeforeEach
	public  void setUpBoard() {
		testBoard = new BlackJackBoard();
		ace = new Card(1, "H");
		two = new Card(2, "H");
		five = new Card(5, "H");
		king = new Card(13, "H");

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
	void testSetCardValues() {
		testBoard.getDeck().emptyStack();
		testBoard.getDeck().addToTop(two);
		testBoard.getDeck().addToTop(three);
		testBoard.getDeck().addToTop(four);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(six);
		testBoard.getDeck().addToTop(seven);
		testBoard.getDeck().addToTop(eight);
		testBoard.getDeck().addToTop(nine);
		testBoard.getDeck().addToTop(ten);
		testBoard.getDeck().addToTop(jack);
		testBoard.getDeck().addToTop(queen);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(ace);
		testBoard.setCardPointValues();

		for(int i = 0; i < testBoard.getDeck().getStackSize(); i++)
			if(testBoard.getDeck().peekCard(i).getPointValue() == 11)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == 'A');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 10)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == 'K' 
						|| testBoard.getDeck().peekCard(i).toString().charAt(0) == 'Q'
						|| testBoard.getDeck().peekCard(i).toString().charAt(0) == 'J'
						|| testBoard.getDeck().peekCard(i).toString().charAt(0) == '1');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 9)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '9');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 8)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '8');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 7)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '7');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 6)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '6');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 5)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '5');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 4)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '4');
			else if(testBoard.getDeck().peekCard(i).getPointValue() == 3)
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '3');
			else
				assertTrue(testBoard.getDeck().peekCard(i).toString().charAt(0) == '2');
				
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
		testBoard.getDeck().emptyStack();
		
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(two);
		testBoard.getDeck().addToTop(ace);
		
		//System.out.println(ace.getRank());
		testBoard.setCardPointValues();
		//System.out.println(ace.getRank());

		
		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //A
		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //2
		
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 13);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //K
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 13);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //5
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 18);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //A
		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //A
		
		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 20);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertFalse(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //A

		assertTrue(testBoard.handTotal(testBoard.playerHand()) == 21);
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		assertTrue(testBoard.is21(testBoard.playerHand()));

		testBoard.playerHand().addToTop(testBoard.getDeck().dealTopCard()); //A

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
		testBoard.getDeck().emptyStack();
		
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(ace);
		testBoard.setCardPointValues();
		
		testBoard.hit(testBoard.playerHand()); //ace = 11
		assertFalse(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));
		
		testBoard.hit(testBoard.playerHand()); //ace + king = 21
		assertTrue(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));

		testBoard.hit(testBoard.playerHand()); //ace + king + king = 21
		assertTrue(testBoard.is21(testBoard.playerHand()));
		assertFalse(testBoard.isBust(testBoard.playerHand()));

		testBoard.hit(testBoard.playerHand()); //ace + king + king + king = 31
		assertFalse(testBoard.is21(testBoard.playerHand()));
		assertTrue(testBoard.isBust(testBoard.playerHand()));
	}
	
	@Test
	void testStayAndDealerTurn() {
		testBoard.getDeck().emptyStack();
		
		testBoard.getDeck().addToTop(ace);
		testBoard.getDeck().addToTop(king);
		testBoard.getDeck().addToTop(king);
		testBoard.setCardPointValues();
		
		testBoard.playerStay();

		assertTrue(testBoard.handTotal(testBoard.dealerHand()) == 20);
		
		clearBoard();
		setUpBoard();
		testBoard.getDeck().emptyStack();

		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(two);
		testBoard.getDeck().addToTop(five);
		testBoard.getDeck().addToTop(ace);
		testBoard.setCardPointValues();
		
		testBoard.playerStay();

		assertTrue(testBoard.handTotal(testBoard.dealerHand()) == 18);
	}

	@Test
	void testPlaceBetGetBet() {
		
		testBoard.setBoard();
		
		assertTrue(testBoard.getPlayerBet() == 0);
		
		testBoard.setPlayerBet(200);
		
		assertTrue(testBoard.getPlayerBet() == 200);

		testBoard.setPlayerBet(250);
		
		assertTrue(testBoard.getPlayerBet() == 250);
}

}
