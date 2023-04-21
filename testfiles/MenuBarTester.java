import javax.swing.JFrame;

public class MenuBarTester {

	public static void main(String[] args) {
		MainMenu mainmenu = new MainMenu();
		JFrame test = new JFrame("Test Window");
		GameMenuBar menuBar = new GameMenuBar(mainmenu);
		test.setSize(1270, 720);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setExtendedState(JFrame.MAXIMIZED_BOTH);
		test.setJMenuBar(menuBar);
		test.setVisible(true);
		test.add(mainmenu.display());
	}

}
