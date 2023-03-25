import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Card class represents a playing card typical to most card games. It uses the Bicycle card deck as a guide for how it is designed.
 */
public class Card {
	
	private static final int CARD_WIDTH = 100;	//in pixels
	private static final int CARD_HEIGHT = 145; //in pixels
	
	private int rank;
	private String suit;
	private String value;
	private boolean faceDown;
	private Image cardImage;
	
	public Card(int rank, int suit) { 
		this.rank = rank;
		setSuit(suit);
		setValue();
		
		faceDown = true;
	}

	public int getCardWidth() {
		return CARD_WIDTH;
	}

	public int getCardHeight() {
		return CARD_HEIGHT;
	}

	public int getRank() {
		return rank;
	}

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
	
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}
	

	public boolean isFaceDown() {
		return faceDown;
	}

	public void setFaceDown(boolean isFaceDown) {
		this.faceDown = isFaceDown;
	}

	public void flipCard() {
		if(faceDown == true)
			setFaceDown(false);
		else
			setFaceDown(true);
	}

	public String toString() {
		return getRank() + " of " + getSuit();
	}
	
	public  void setCardImage() {
		try {
			File imageFile;
			if(isFaceDown() == false)
				imageFile = new File("imgs/" + getValue() + ".png");
			else
				imageFile = new File("imgs/FD.png");
			cardImage = ImageIO.read(imageFile);
			cardImage = cardImage.getScaledInstance(getCardWidth(), getCardHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Image getCardImage() {
		return cardImage;
	}
	
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