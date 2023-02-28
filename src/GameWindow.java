import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	MainMenu mainmenu;
	
	public GameWindow() {
		super("Argo Casino!");
		setSize(1270, 720); //Minimized resolution
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainmenu = new MainMenu(); //Creates all the menus
		add(mainmenu.display()); //Displays all the menus
		
		setVisible(true); 
	}
}
