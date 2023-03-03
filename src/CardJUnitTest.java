
import static org.junit.jupiter.api.Assertions.*;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

public class CardJUnitTest {
	
	@Test
	public void TestConstructor() {
		ArrayList<Card> testDeck = new ArrayList<Card>();
		Card currCard;
				
		for(int s = 0; s < 4; s++)				
			for(int r = 0; r < 13; r++) {		
				currCard = new Card(r, s);	
				testDeck.add(currCard);
				System.out.println(currCard.getValue());
			}
	}
	
	@Test
	public void TestSetCardImage() {
		JFrame frame = new JFrame();
		
		/*Card AoS = new Card(12,2);
		AoS.setCardImage();
		JPanel card = new JPanel();
		JLabel cardImg = new JLabel(new ImageIcon(AoS.getCardImage()));
		card.add(cardImg);
		frame.add(card);*/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1260,720);
		frame.setVisible(true);
	}
	
	
}
