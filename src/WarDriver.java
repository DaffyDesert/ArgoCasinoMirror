
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WarDriver {

	public static void main(String[] args) {
		JFrame f = new JFrame("War");
		War game = new War();
				
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(800,800));
		f.setResizable(false);
		
		f.add(game);
		
		f.pack();
		f.setVisible(true);
	}
}
