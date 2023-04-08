
import javax.swing.*;

/**
 * The GameWindow class handles the creation and display
 * of the game’s window. It does this by use of a JFrame object 
 * and a displayed JPanel obtained from its included MainMenu class.
 */

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	MainMenu mainmenu;
	
	public GameWindow() {
		super("Argo Casino!");
		setSize(1270, 720); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainmenu = new MainMenu(); 
		add(mainmenu.display()); 

		setVisible(true); 
	}
}
