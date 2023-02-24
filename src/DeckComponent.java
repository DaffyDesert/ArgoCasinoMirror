import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DeckComponent extends JLabel{
	
	JLabel opponentDeckLabel;
	ImageIcon card;
	
	DeckComponent() {
		setLayout(new BorderLayout(0,0));
		
		this.setPreferredSize(new Dimension(100,132));
		card = new ImageIcon("customcard.png"); /* TODO: customcard is just a placeholder so i know where to put everything */
		opponentDeckLabel = new JLabel(card);
		this.add(opponentDeckLabel);
		
		this.setVisible(true);
	}
}
