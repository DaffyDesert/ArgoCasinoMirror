import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackJack  extends JPanel implements Game {
	private Stopwatch stopWatch;
	private Thread thread;
	private JPanel board;
	private JPanel statusBar;
	private JPanel gameOverScreenPanel;
	private JPanel deckPanel;
	private JPanel playerHandPanel;
	private JPanel dealerHandPanel;
	private JPanel buttonPanel;
	private JPanel deckAndHandsPanel;
	private JButton hit;
	private JButton hold;
	
	//These Cardstacks are for testing purposes and will be remove once I implement the blackjack board class
	
	//Player hand
	private CardStack testHand;
	
	//Dealer hand
	private CardStack testHand2;
	
	//Adds cards to the Player Hand
	private CardStack testHandHit;
	
	public BlackJack() {
		stopWatch = new Stopwatch();
		thread = new Thread(stopWatch);
		
		//Remove this section when the blackjack board class is implemented 
		Card testCard1 = new Card(0,0);
		Card testCard2 = new Card(4,0);
		Card testCard3 = new Card(5,0);
		
		Card testCard4 = new Card(1,0);
		Card testCard5 = new Card(2,0);
		Card testCard6 = new Card(3,0);
		
		
		
		testHand = new CardStack();
		testHand2 = new CardStack();
		
		testHandHit = new CardStack();

		testHand.addToBottom(testCard1);
		testHand.addToBottom(testCard2);
		testHand.addToBottom(testCard3);
		
		testHand2.addToBottom(testCard1);
		testHand2.addToBottom(testCard2);
		testHand2.addToBottom(testCard3);
		
		testHandHit.addToBottom(testCard4);
		testHandHit.addToBottom(testCard5);
		testHandHit.addToBottom(testCard6);
		
		
	}
	
	@Override
	public void startWatch() {
		thread.start();
		
	}

	@Override
	public void startGame() {
		startWatch();
		
	}

	@Override
	public void stopWatch() {
		stopWatch.stopThread();
		
	}

	@Override
	public void stopGame() {
		stopWatch();
		
	}

	@Override
	public JPanel display() {
		board = new JPanel();
		board.removeAll();
		board.setBackground(new java.awt.Color(0, 122, 51));
		board.setLayout(new BorderLayout());
		
		statusBarPanelBuilder();
		buttonPanelBuilder();
		deckAndHandsPanelBuilder();
		
		board.add(statusBar,BorderLayout.NORTH);
		board.add(deckAndHandsPanel,BorderLayout.CENTER);
		board.add(buttonPanel,BorderLayout.SOUTH);
		board.revalidate();
		board.repaint();
		return board;
	}
	
	public void deckAndHandsPanelBuilder() {
		deckAndHandsPanel = new JPanel();
		deckAndHandsPanel.setLayout(new BorderLayout());
		deckAndHandsPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		deckPanelBuilder();
		playerHandPanelBuilder();
		dealerHandPanelBuilder();
		
		deckAndHandsPanel.add(dealerHandPanel,BorderLayout.NORTH);
		deckAndHandsPanel.add(deckPanel,BorderLayout.CENTER);
		deckAndHandsPanel.add(playerHandPanel,BorderLayout.SOUTH);
		
	}
	
	
	public void deckPanelBuilder() {
		Card backOfCard = new Card(0,0);
		backOfCard.setFaceDown(true);
		backOfCard.setCardImage();
		JLabel back = new JLabel(new ImageIcon(backOfCard.getCardImage()));
			
		deckPanel = new JPanel();
		deckPanel.setLayout(new BorderLayout());
		deckPanel.setBackground(new java.awt.Color(0, 122, 51));
		deckPanel.setLayout(new BorderLayout());
		deckPanel.add(back,BorderLayout.CENTER);
	}
	
	public void buttonPanelBuilder() {
		buttonPanel = new JPanel();
		hit = new JButton("Hit");
		hold = new JButton("Hold");
		
		buttonPanel.setLayout(new GridLayout());
		buttonPanel.setPreferredSize(new Dimension(100,100));
		buttonPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		hit.setForeground(new java.awt.Color(0, 156, 222));
		hit.setBackground(new java.awt.Color( 224, 60, 49));
		hit.setFont(new Font("Arial", Font.PLAIN, 40));
		hit.setBorder(null);
		hit.addActionListener(new hitButtonListner());
		
		hold.setForeground(new java.awt.Color(0, 156, 222));
		hold.setBackground(new java.awt.Color(255, 184, 28));
		hold.setFont(new Font("Arial", Font.PLAIN, 40));
		hold.setBorder(null);
		hold.addActionListener(new holdButtonListner());
		
		buttonPanel.add(hit,BorderLayout.WEST);
		buttonPanel.add(hold,BorderLayout.EAST);
		
	}
	
	public void statusBarPanelBuilder() {
		statusBar = new JPanel();
		statusBar.setBounds(0,0,1270,144);
		statusBar.setLayout(new BorderLayout());
		statusBar.setBackground(new java.awt.Color(0, 122, 51));
		statusBar.add(stopWatch.display(), BorderLayout.SOUTH);
	}
	
	public void playerHandPanelBuilder() {
		playerHandPanel = new JPanel();	
		playerHandPanel.setLayout(new FlowLayout());
		playerHandPanel.setBackground(new java.awt.Color(0, 122, 51));
		showCards(playerHandPanel,testHand,false);
		playerHandPanel.revalidate();			
	}
	
	public void dealerHandPanelBuilder() {
		dealerHandPanel = new JPanel();
		dealerHandPanel.setLayout(new FlowLayout());
		dealerHandPanel.setBackground(new java.awt.Color(0,122,51));		
		showCards(dealerHandPanel,testHand2,true);
		dealerHandPanel.revalidate();
	}
	
	public void showCards(JPanel handPanel,CardStack hand, boolean faceDown) {
		JLabel cards;
		for(int i = 0; i < hand.getStackSize(); i++) {
			
			//sets the first card to be face down
			if(i == 0) {
				hand.getStack().get(i).setFaceDown(true);
				hand.getStack().get(i).setCardImage();
				cards = new JLabel(new ImageIcon(hand.getStack().get(i).getCardImage()));
				handPanel.add(cards);
			}
			else {
				hand.getStack().get(i).setFaceDown(faceDown);
				hand.getStack().get(i).setCardImage();
				cards = new JLabel(new ImageIcon(hand.getStack().get(i).getCardImage()));
				handPanel.add(cards);
			}
		}
	}
	
	public void showCardsFaceUp(JPanel handPanel, CardStack hand) {
		handPanel.removeAll();
		JLabel cards;
		for(int i = 0; i < hand.getStackSize(); i++) {
			hand.getStack().get(i).setFaceDown(false);
			hand.getStack().get(i).setCardImage();
			cards = new JLabel(new ImageIcon(hand.getStack().get(i).getCardImage()));
			handPanel.add(cards);
		}
	}
	private class hitButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updatePlayerHand();
		}
	}
	
	private class holdButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			board.remove(deckAndHandsPanel);
			board.remove(buttonPanel);
			board.add(showGameOverScreen(),BorderLayout.CENTER);
		}
	}
	
	public void updatePlayerHand() {
		Card newCard = testHandHit.dealTopCard();
		testHand.addToTop(newCard);
		playerHandPanelBuilder();
		deckAndHandsPanel.add(playerHandPanel,BorderLayout.SOUTH);
	}
	
	public void updateDealerHand() {
		Card newCard = new Card(0,2);
		testHand2.addToTop(newCard);
		dealerHandPanelBuilder();
		deckAndHandsPanel.add(dealerHandPanel,BorderLayout.NORTH);
	}
	
	public void gameOverScreenPanelBuilder(int gameOverCondition) {
		gameOverScreenPanel = new JPanel();
		gameOverScreenPanel.setLayout(new BorderLayout());
		gameOverScreenPanel.setBackground(new java.awt.Color(0,122,51));
		
		
		showCardsFaceUp(dealerHandPanel,testHand2);
		showCardsFaceUp(playerHandPanel,testHand);
		
		gameOverScreenPanel.add(dealerHandPanel,BorderLayout.NORTH);
		gameOverScreenPanel.add(playerHandPanel,BorderLayout.SOUTH);
		
		
		// added the spaces in the JLabel because I could not figure out how to center the text 
		if(gameOverCondition == 1) {
			JLabel message = new JLabel("                           You Win");;
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			gameOverScreenPanel.add(message, BorderLayout.CENTER);
			
		}
		if(gameOverCondition == 2) {
			JLabel message = new JLabel("                         You Busted");
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			gameOverScreenPanel.add(message, BorderLayout.CENTER);
		}
		
		if(gameOverCondition == 3) {
			JLabel message = new JLabel("                         You Loose");
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			gameOverScreenPanel.add(message, BorderLayout.CENTER);
		}

	}

	@Override
	public int isOver() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean turn() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public JPanel showGameOverScreen() {
		// going to use the isOver method as the parameter for the gameOverScreenBuilder
		gameOverScreenPanelBuilder(2);
		return gameOverScreenPanel;
	}

	
	

}
