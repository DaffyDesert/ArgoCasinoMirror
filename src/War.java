import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class War extends JPanel{
	/*Variables go here*/
	JLabel l; 
	Component table;
	
	War() {
		// INITIALIZE WAR ENEMY AND WAR PLAYER MEMBERS HERE
		this.setLayout(new BorderLayout(20,15));
		l = new JLabel("Let the game begin!", SwingConstants.CENTER);
		this.add(l,BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	//Paints card table
	public void paint(Graphics g) {
		g.setColor(new Color(42, 137, 32));
		g.fillRoundRect(100, 100, 500, 500, 50, 50);
		
		g.setColor(Color.black);
		g.drawRect(300, 464, 100, 136); // Where Player's cards will go
		
		g.drawRect(300, 100, 100, 136); // Where opponent's cards will go
	}
	
}
