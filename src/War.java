import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class War extends JPanel implements MouseListener{

	/*Variables go here*/
	DeckComponent enemyDeck;
	DeckComponent playerDeck;
	JLabel tableLabel;
	ImageIcon table;
	
	War() {
		this.setLayout(null);

		table = new ImageIcon("tabletop.png");
		
		playerDeck = new DeckComponent();
		playerDeck.setBounds(350, 600, 100, 132);
		enemyDeck = new DeckComponent();
		enemyDeck.setBounds(350, 10, 100, 132);
		tableLabel = new JLabel(table);
		tableLabel.setBounds(0, 150, 800, 400);
	
		this.add(enemyDeck);
		this.add(tableLabel);
		this.add(playerDeck);
		this.setVisible(true);
		
		playerDeck.addMouseListener(this);
		tableLabel.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getComponent() == getComponent(1)) {
			System.out.println("Table was clicked");
		}
		if(e.getComponent() == getComponent(2)) {
			System.out.println("Player deck was clicked");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
