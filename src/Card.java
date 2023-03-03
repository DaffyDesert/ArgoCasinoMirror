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
	
	private int rank;
	private String suit;
	private String value;
	private boolean faceDown;
	private Image cardImage;
	
	/**
	 * Constructor
	 *  sets card ranking Ace High
	 * @param rank - ace, 2-10, jack, queen, king (13 possible integer values 0 to 12)
	 * @param suit - heart, club, diamond, spade
	 */
	public Card(int rank, int suit) { 
		this.rank = rank;
		setSuit(suit);
		setValue();
		
		faceDown = true;
	}
	
	public Card(int rank, int suit, String filename) {
		this.rank = rank;
		setSuit(suit);
		setValue();
		
		faceDown = true;
		
		
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
	public int getRank() {
		return rank;
	}

	/**
	 * set the value of the suit
	 * 
	 * e.g rank = 7 and suit = Hearts -> value ="7H"
	 */
	public void setValue() {
		if(getRank() == 9)
			this.value = "J" + getSuit().charAt(0); 
		else if(getRank() == 10)
			this.value = "Q" + getSuit().charAt(0);
		else if(getRank() == 11)
			this.value = "K" + getSuit().charAt(0);
		else if(getRank() == 12)
			this.value = "A" + getSuit().charAt(0);
		else
			this.value = Integer.toString(rank+2) + getSuit().charAt(0);		

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
	
	/**
	 * PRIVATE function used to set the image of the card based on its faceDown boolean value (true = backOfCard)
	 */
	private void setCardImage() {
		try {
			File imageFile;
			if(isFaceDown() == false)
				imageFile = new File("imgs/" + getValue() + ".png");
			else
				imageFile = new File("imgs/FD.png");
			cardImage = ImageIO.read(imageFile);
			cardImage = cardImage.getScaledInstance(getCardWidth(), getCardHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * returns the card in the form a of a JPanel
	 * 
	 * @param g2D
	 * @return
	 */
	public JPanel draw()
	{
		setCardImage();
		Color feltGreen = new Color(10, 108, 3);
		JPanel card = new JPanel();
		JLabel cardImg = new JLabel(new ImageIcon(cardImage));
		card.setBackground(feltGreen);
		
		card.add(cardImg);
		
		return card;
	}
}
