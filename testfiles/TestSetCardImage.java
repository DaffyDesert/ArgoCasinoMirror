import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestSetCardImage {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		ArrayList<Card> testDeck = new ArrayList<Card>();
		ArrayList<JPanel> panelList = new ArrayList<JPanel>();
		ArrayList<JLabel> cardImages = new ArrayList<JLabel>();
		ImageIcon temp;
		Card currCard;
				
		for(int s = 0; s < 4; s++)	{			
			for(int r = 0; r < 13; r++) {		
				currCard = new Card(r, s);	
				currCard.flipCard();
				testDeck.add(currCard);
			}
		}
		
		for(int i = 0; i < testDeck.size(); ++i) {
			testDeck.get(i).setCardImage();
			temp = new ImageIcon(testDeck.get(i).getCardImage());
			cardImages.add(new JLabel(temp));
			panelList.add(new JPanel());
			panelList.get(i).add(cardImages.get(i));
			frame.add(panelList.get(i));
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1260,720);
		frame.setVisible(true);
	}

}
