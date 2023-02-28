import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame{
	//MainMenu mainmenu;
	
	public GameWindow() {
		super("Argo Casino!");
		setSize(1270, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //May have to change to do nothing on close in order to kill the thread.
		setLayout(new FlowLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
