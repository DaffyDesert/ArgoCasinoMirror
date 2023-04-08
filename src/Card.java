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
	private boolean faceDown;
	private Image cardImage;
	
	public int getRank() {return rank;}
	public void setRank(int rank) {this.rank = rank;}
	public String getSuit() {return suit;}
	public void setSuit(String suit) {this.suit = suit;}
	public boolean isFaceDown() {return faceDown;}
	public void setFaceDown(boolean faceDown) {this.faceDown = faceDown;}
	
	public Card(int rank, String suit) {
		setRank(rank);
		setSuit(suit);
		setFaceDown(true);
	}
	
	private String rankToString() {
		if(getRank() == 1)
			return "A";
		else if(getRank() == 11)
			return "J";
		else if(getRank() == 12)
			return "Q";
		else if(getRank() == 13)
			return "K";
	}
  
	public String toString() {
		return rankToString() + getSuit();
	}
	
	public  void setCardImage() {
		try {
			File imageFile;
			if(isFaceDown() == false)
				imageFile = new File("imgs/" + toString() + ".png");
			else
				imageFile = new File("imgs/FD.png");
			cardImage = ImageIO.read(imageFile);
			cardImage = cardImage.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT);
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