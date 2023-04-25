import javax.swing.JFrame;

public class SolitaireColumnGUITest {

	public static void main(String[] args) {
		SolitaireColumn column = new SolitaireColumn();
		Card card1 = new Card(13, "S");
		card1.setFaceDown(false);
		Card card2 = new Card(12, "H");
		card2.setFaceDown(false);
		Card card3 = new Card(11, "C");
		card3.setFaceDown(false);
		column.addCard(card1);
		column.addCard(card2);
		column.addCard(card3);
		
		JFrame frame = new JFrame();
		frame.setSize(1270, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(column.display());
		
		frame.setVisible(true);
	}

}
