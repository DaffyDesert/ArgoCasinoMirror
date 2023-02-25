import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WarTester {
	War game1;
	Card card1;
	Card card2;
	
	
	@Test
	public void testCompareMethod() {
		game1 = new War();
		card1 = new Card(2, 0); 
		card2 = new Card(8, 3); 

		int result = game1.compare(card1, card2);

		//Test basic function of the compare method
		assertEquals(1, result);
		
		//Test if one card is an ace 
		card1.setRank(0);
		card2.setRank(12);
		
		result = game1.compare(card1, card2);
		assertEquals(0,result);
	}
}
