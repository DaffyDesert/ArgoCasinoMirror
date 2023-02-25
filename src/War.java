import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * War class will be a JPanel that will contain 2 deck objects and 1 table. Class 
 * will also take have all methods that it takes to play a game of War
 * @author Daniel Padgett
 *
 */
@SuppressWarnings("serial")
public class War extends JPanel implements ActionListener, MouseListener {

	/* Variables go here */
	DeckComponent enemyDeck;
	DeckComponent playerDeck;
	JLabel tableLabel;
	JLabel text;
	ImageIcon table;
	Timer timer;
	
	/*
	 * War constructor creates a gui that will show two decks and a table.
	 * The player can start each turn by simply clicking the table.
	 */
	War() {
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

	/**
	 * Takes in two cards and returns the result from the player's 
	 * perspective. 1 = loss, 0 = win
	 * @param the player's card 
	 * @param the enemy's card
	 * @return result from the player's perspective
	 */
	public int compare(Card playerCard, Card enemyCard) {
		int playerRank = turnRankToInt(playerCard.getRank());
		int enemyRank = turnRankToInt(enemyCard.getRank());

		if (playerRank == enemyRank) { // if cards are of the same suit then a "war" will occur
			
		}
		else if(playerRank == 0) { // "if player has an Ace, player wins"
			return 0;
		}
		else if(enemyRank == 0) { // "if enemy has an Ace, player loses"
			return 1;
		}
		else if (playerRank < enemyRank) { // "if player's card is less than the enemy's, then return 1(defeat)"
			return 1;
		}
		return 0;
	}
	
	

	/**
	 * Helper function to assist the compare function to turn the
	 * rank of a card in the form of a string into an integer.
	 * @param the suit of a card
	 * @return the integer it is supposed to represent
	 */
	private int turnRankToInt(String cardSuit) {
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

	/**
	 * Mouse pressed will activate when the player clicks the table
	 * object in the gui. This will then move the top card from the 
	 * player's deck onto the table. Then, 1000ms later(1 second) the
	 * enemy's top card will be moved.
	 * @param The mouse event
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getComponent() == getComponent(2)) {
			text.setVisible(false);
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

	@Override
	public void actionPerformed(ActionEvent e) {		
	}

}
