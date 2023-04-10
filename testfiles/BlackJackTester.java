import javax.swing.*;

@SuppressWarnings("serial")
public class BlackJackTester extends JFrame{

	public static void main(String[] args) {
		BlackJack b = new BlackJack();
		b.startGame();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(b.display());
		frame.setSize(1270,720);
		frame.setVisible(true);

	}

}
