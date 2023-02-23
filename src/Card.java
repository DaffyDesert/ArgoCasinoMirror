import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Card class that holds a:
 * rank: ace, 2-10, jack, queen, king (13 possible values)
 * suit: heart, club, diamond, spade
 * value: combination of the rank and suit of a card (5H, KD)
 * faceDown: boolean if card value is showing or the back of the card is showing
 * 
 * @author danielMiller
 * @date 2/21/23
 * @version 1.0
 */
public class Card {

	private static final int CARD_WIDTH = 100;	//in pixels
	private static final int CARD_HEIGHT = 145; //in pixels
	
	private String rank;
	private String suit;
	private String value;
	private boolean faceDown;
	private Image cardImage;
	
	/**
	 * Constructor
	 * @param rank - ace, 2-10, jack, queen, king (13 possible values)
	 * @param suit - heart, club, diamond, spade
	 */
	public Card(int rank, int suit) {
		setRank(rank);
		setSuit(suit);
		value = getRank() + getSuit().charAt(0); // e.g rank = 7 suit = Hearts -> value ="7H"
		faceDown = true;
		try {
			File imageFile = new File("imgs/" + getValue() + ".png");
			cardImage = ImageIO.read(imageFile);
			cardImage = cardImage.getScaledInstance(getCardWidth(), getCardHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	/**
	 * 
	 * @return  CARD_WIDTH
	 */
	public int getCardWidth() {
		return CARD_WIDTH;
	}

	/**
	 * 
	 * @return CARD_HEIGHT
	 */
	public int getCardHeight() {
		return CARD_HEIGHT;
	}

	/**
	 * return rank of the card
	 * @return value
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * Set the of rank the cards
	 * 
	 * @param rank - rank of card (ace, 1-9, jack, queen, king)
	 */
	public void setRank(int rank) {
		if(rank == 0)
			this.rank = "A";
		else if(rank == 10)
			this.rank = "J";
		else if(rank == 11)
			this.rank = "Q";
		else if(rank == 12)
			this.rank = "K";
		else
			this.rank = Integer.toString(rank+1);		
	}

	/**
	 * Set the suit of the cards 
	 * @param suit - suit to set (heart, diamonds, spades, clubs)
	 */
	public void setSuit(int suit) {
		if(suit == 0)
			this.suit = "Hearts";
		else if(suit == 1)
			this.suit = "Diamonds";
		else if(suit == 2)
			this.suit = "Spades";
		else if(suit == 3)
			this.suit = "Clubs";
		else
			this.suit = "error";		
	}
	/**
	 * 
	 * @return the suit of the card
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * @return the value of the card
	 */
	public String getValue() {
//		if (isFaceDown() == true)
//			return "FD";
		return value;
	}
	
	/**
	 * 
	 * @return if card is face down 
	 */
	public boolean isFaceDown() {
		return faceDown;
	}

	/**
	 * set faceDown value to the provided boolean parameter
	 * 
	 * @param isFaceDown - value to set faceDown equal to
	 */
	public void setFaceDown(boolean isFaceDown) {
		this.faceDown = isFaceDown;
	}

	/**
	 * changes the faceDown boolean value to it's opposite (turns true to false and false to true)
	 */
	public void flipCard() {
		if(faceDown == true)
			setFaceDown(false);
		else
			setFaceDown(true);
	}

	/**
	 * returns a String representing the verbose value of a card
	 * format:  "<rank> of <suit>"
	 */
	public String toString() {
		return getRank() + " of " + getSuit();
	}
	public JPanel draw(Graphics2D g2D)
	{
		Color feltGreen = new Color(10, 108, 3);
		JPanel card = new JPanel();
		JLabel cardImg = new JLabel(new ImageIcon(cardImage));
		card.setBackground(feltGreen);
		
		card.add(cardImg);
		
		return card;
	}
}
