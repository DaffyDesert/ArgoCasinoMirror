import javax.swing.JFrame;

public class SolitaireTester {

	public static void main(String[] args) {
		Solitaire sol = new Solitaire();
		JFrame frame = new JFrame();
		frame.setSize(1270,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(sol.display());
		frame.setVisible(true);
	}

}
