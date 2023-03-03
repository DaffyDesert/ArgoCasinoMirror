import javax.swing.JFrame;

public class CardImageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Card c = new Card(7, 2);
		JFrame window = new JFrame();
		
		window.add(c.draw());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}
