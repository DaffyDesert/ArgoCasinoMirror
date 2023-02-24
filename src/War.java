import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class War extends JPanel implements ActionListener, MouseListener {

	/* Variables go here */
	DeckComponent enemyDeck;
	DeckComponent playerDeck;
	JLabel tableLabel;
	JLabel text;
	ImageIcon table;
	Timer timer;
	boolean gameOver;

	War() {
		gameOver = false;
		this.setLayout(null);

		table = new ImageIcon("tabletop.png");

		tableLabel = new JLabel(table);
		tableLabel.setBounds(0, 150, 800, 400);
		playerDeck = new DeckComponent();
		playerDeck.setBounds(350, 600, 100, 132);
		enemyDeck = new DeckComponent();
		enemyDeck.setBounds(350, 10, 100, 132);
		text = new JLabel("Click on the table to begin");
		text.setBounds(500, 500, 200, 200);

		this.add(playerDeck);
		this.add(enemyDeck);
		this.add(tableLabel);
		this.add(text);
		this.setVisible(true);

		playerDeck.addMouseListener(this);
		tableLabel.addMouseListener(this);
	}

	/*
	 * compare function will be given two cards to check, then will determine which
	 * one is of a higher rank returns 1 if enemy wins returns 0 if player wins
	 */
	public int compare(Card playerCard, Card enemyCard) {
		int playerSuit = turnSuitToInt(playerCard.getSuit());
		int enemySuit = turnSuitToInt(enemyCard.getSuit());

		if (playerSuit == enemySuit) {
			// if cards are of the same suit then a "battle" will occur
		} else if (playerSuit < enemySuit) {
			return 1;
		}
		return 0;
	}

	/*
	 * turnSuitToInt function is a helper to the compare function so it can easily
	 * identify the suit through integers
	 */
	private int turnSuitToInt(String cardSuit) {
		if (cardSuit.compareTo("A") == 0)
			return 0;
		else if (cardSuit.compareTo("J") == 0)
			return 10;
		else if (cardSuit.compareTo("Q") == 0)
			return 11;
		else if (cardSuit.compareTo("K") == 0)
			return 12;
		else
			return Integer.parseInt(cardSuit);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getComponent() == getComponent(2)) {
			System.out.println("2");
			playerDeck.setLocation(300, 350);
			Timer timer = new Timer(1000, new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        // code to execute on each tick of the timer
			    	enemyDeck.setLocation(400,350);
			    }
			});
			timer.start();
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
