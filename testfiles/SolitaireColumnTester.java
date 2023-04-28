import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class SolitaireColumnTester {

	public static void main(String[] args) {
		SolitaireColumn c1 = new SolitaireColumn();
		c1.addCard(new Card(13, "C"));
		c1.addCard(new Card(12, "H"));
		c1.addCard(new Card(11, "S"));
		SolitaireColumn c2 = new SolitaireColumn();
		c2.addCard(new Card(13, "H"));
		
		JFrame frame = new JFrame();
		frame.setSize(1270,750);
		frame.setLayout(new BoxLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(c1.display());
		frame.add(c2.display());
		frame.setVisible(true);
	}

}
