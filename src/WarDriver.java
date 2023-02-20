import java.awt.Dimension;

import javax.swing.JFrame;

public class WarDriver {

	public static void main(String[] args) {
		JFrame f = new JFrame("War");
		War game = new War();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setSize(700,700);
		f.add(game);
		f.setVisible(true);
	}

}
