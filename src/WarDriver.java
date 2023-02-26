
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WarDriver {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		War game = new War();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(500,600);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
	}
}
