import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
*    Handles the GUI and display aspects of a game of blackjack. 
*    It passes the display of the game to the main menu class. Uses 
*    the blackjack board to handle card data and all functions needed in a game of blackjack. 
*/


public class BlackJack  extends JPanel implements Game {
	private Stopwatch stopwatch;
	private Thread thread;
	private JPanel board;
	private JPanel statusBar;
	private JPanel gameOverScreenPanel;
	private JPanel deckPanel;
	private JPanel playerHandPanel;
	private JPanel dealerHandPanel;
	private JPanel buttonPanel;
	private JPanel deckAndHandsPanel;
	private JPanel handsFlippedPanel;
	private JButton hit;
	private JButton hold;
	private JButton newHand;
	private JRadioButton help;
	private JToolTip hitToolTip;
	private JToolTip holdToolTip;
	
	private BlackJackBoard blackJack;
	private int winCondition;
	private boolean turnIsOver;
	public Component gameOverScreen;
	
	
	public BlackJack() {
		stopwatch = new Stopwatch();
		thread = new Thread(stopwatch);
		blackJack = new BlackJackBoard();
	}
	
	@Override
	public void startWatch() {
		thread.start();
	}

	@Override
	public void startGame() {
		startWatch();
		blackJack.setBoard();
		if(blackJack.is21(blackJack.playerHand())) {
			winCondition = 1;
			board.add(showGameOverScreen(),BorderLayout.CENTER);
		}
	}

	@Override
	public void stopWatch() {
		stopwatch.stopTimer();
		board.revalidate();	
	}

	@Override
	public void stopGame() {
		stopWatch();
		
	}

	@Override
	public JPanel display() {
 		board = new JPanel();
		board.removeAll();
		board.setBackground(new java.awt.Color(0, 122, 40));
		board.setLayout(new BorderLayout());
		board.setPreferredSize(new Dimension(1270,650));
		
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
		deckAndHandsPanel.setBackground(new java.awt.Color(0, 122, 40));
		deckAndHandsPanel.setPreferredSize(new Dimension(50,50));
		deckPanelBuilder();
		playerHandPanelBuilder();
		dealerHandPanelBuilder();
		
		deckAndHandsPanel.add(dealerHandPanel,BorderLayout.NORTH);
		deckAndHandsPanel.add(deckPanel,BorderLayout.CENTER);
		deckAndHandsPanel.add(playerHandPanel,BorderLayout.SOUTH);
		deckAndHandsPanel.revalidate();
		deckAndHandsPanel.repaint();
	}
	
	
	public void deckPanelBuilder() {
		Card backOfCard = new Card(0,"H");
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
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(700,50));
	
		buttonPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		
		
		hit.setForeground(new java.awt.Color(0, 156, 222));
		hit.setBackground(new java.awt.Color( 224, 60, 49));
		hit.setFont(new Font("Arial", Font.PLAIN, 30));
		hit.setBorder(null);
	
		hit.addActionListener(new hitButtonListner());
		hold.setForeground(new java.awt.Color(0, 156, 222));
		hold.setBackground(new java.awt.Color(255, 184, 28));
		hold.setFont(new Font("Arial", Font.PLAIN, 30));
		hold.setBorder(null);
		hold.addActionListener(new holdButtonListner());
		

		buttonPanel.add(hit);
		buttonPanel.add(hold);
		
	}
	
	public void statusBarPanelBuilder() {
		statusBar = new JPanel();
		help = new JRadioButton("Help");
		help.setBackground(new java.awt.Color(0, 122, 51));
		help.addActionListener(new helpButtinListner());
		help.setBorder(null);
		
		statusBar.setLayout(new BorderLayout());
		statusBar.setPreferredSize(new Dimension(1270,50));
		statusBar.setBackground(new java.awt.Color(0, 122, 51));
		statusBar.add(stopwatch.display(), BorderLayout.NORTH);
		statusBar.add(help,BorderLayout.EAST);
	}
	
	public void playerHandPanelBuilder() {
		playerHandPanel = new JPanel();	
		playerHandPanel.setLayout(new FlowLayout());
		playerHandPanel.setBackground(new java.awt.Color(0, 122, 51));
		showCards(playerHandPanel,blackJack.playerHand(),false);
		playerHandPanel.revalidate();			
	}
	
	public void dealerHandPanelBuilder() {
		dealerHandPanel = new JPanel();
		dealerHandPanel.setLayout(new FlowLayout());
		dealerHandPanel.setBackground(new java.awt.Color(0,122,51));		
		showCards(dealerHandPanel,blackJack.dealerHand(),true);
		dealerHandPanel.revalidate();
	}
	
	public void showCards(JPanel handPanel,CardStack hand, boolean faceDown) {
		JLabel cards;
		for(int i = 0; i < hand.getStackSize(); i++) {
			hand.getStack().get(i).setFaceDown(faceDown);
			hand.getStack().get(i).setCardImage();
			cards = new JLabel(new ImageIcon(hand.getStack().get(i).getCardImage()));
			handPanel.add(cards);
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
	
	private class helpButtinListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(help.isSelected()) {
				hitToolTip = new JToolTip();
				holdToolTip = new JToolTip();
				hitToolTip.setTipText("Adds cards to your hand");
				holdToolTip.setTipText("Ends your turn");
								
				buttonPanel.add(hitToolTip);
				buttonPanel.add(hit);
				buttonPanel.add(hold);
				buttonPanel.add(holdToolTip);
			}
			if(!(help.isSelected())){
				buttonPanel.remove(hitToolTip);
				buttonPanel.remove(holdToolTip);
				buttonPanelBuilder();
				
				board.add(buttonPanel,BorderLayout.SOUTH);
			}
		}
		
	}
	private class hitButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updatePlayerHand();
			turn();
			if(turnIsOver) {
				board.add(showGameOverScreen(),BorderLayout.CENTER);
			}
			
		}
	}
	
	private class holdButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			blackJack.playerStay();
			winCondition = isOver();
			board.add(showGameOverScreen(),BorderLayout.CENTER);
		}
	}
	
	private class newHandButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			board.removeAll();
			board.revalidate();
			blackJack.dealerHand.emptyStack();
			blackJack.playerHand.emptyStack();
		
			stopwatch = new Stopwatch();
			thread = new Thread(stopwatch);
			thread.start();
			
			blackJack.setBoard();
			if(blackJack.is21(blackJack.playerHand())) {
				winCondition = 1;
				board.add(showGameOverScreen(),BorderLayout.CENTER);
			}
			
			board.setPreferredSize(new Dimension(1270,650));
			
			statusBarPanelBuilder();
			buttonPanelBuilder();
			deckAndHandsPanelBuilder();
			
			board.add(statusBar,BorderLayout.NORTH);
			board.add(deckAndHandsPanel,BorderLayout.CENTER);
			board.add(buttonPanel,BorderLayout.SOUTH);
		}
		
	}
	
	public void updatePlayerHand() {
		blackJack.hit(blackJack.playerHand());
		playerHandPanelBuilder();
		deckAndHandsPanel.add(playerHandPanel,BorderLayout.SOUTH);
	}
	
	public void updateDealerHand() {
		dealerHandPanelBuilder();
		deckAndHandsPanel.add(dealerHandPanel,BorderLayout.NORTH);
	}
	
	
	
	public void handsFlippedPanelBuilder(int gameOverCondition) {
		handsFlippedPanel = new JPanel();
		handsFlippedPanel.setLayout(new BorderLayout());
		handsFlippedPanel.setBackground(new java.awt.Color(0,122,51));
		
		showCardsFaceUp(dealerHandPanel,blackJack.dealerHand());
		showCardsFaceUp(playerHandPanel,blackJack.playerHand());
		
		handsFlippedPanel.add(dealerHandPanel,BorderLayout.NORTH);
		handsFlippedPanel.add(playerHandPanel,BorderLayout.SOUTH);
		// added the spaces in the JLabel because I could not figure out how to center the text 
		if(gameOverCondition == 1) {
			JLabel message = new JLabel("                           You Win");;
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			handsFlippedPanel.add(message, BorderLayout.CENTER);
			
		}
		if(gameOverCondition == 2) {
			JLabel message = new JLabel("                         You Busted");
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			handsFlippedPanel.add(message, BorderLayout.CENTER);
		}
		
		if(gameOverCondition == 3) {
			JLabel message = new JLabel("                         You Lose");
			message.setFont(new Font("Serif", Font.PLAIN, 72));
			handsFlippedPanel.add(message, BorderLayout.CENTER);
		}
		
	}
	
	
	
	public void gameOverScreenPanelBuilder() {
		
		board.remove(deckAndHandsPanel);
		board.remove(buttonPanel);
		board.remove(statusBar);
		statusBar.remove(help);
		statusBar.setPreferredSize(new Dimension(1270,15));
		board.add(statusBar,BorderLayout.NORTH);

		gameOverScreenPanel = new JPanel();
		gameOverScreenPanel.setLayout(new BorderLayout());
		gameOverScreenPanel.setBackground(new java.awt.Color(0,122,51));
		gameOverScreenPanel.setPreferredSize(new Dimension(50,50));
		
		handsFlippedPanelBuilder(winCondition);
		
		gameOverScreenPanel.add(handsFlippedPanel,BorderLayout.CENTER);
		
		newHand = new JButton("newHand");
		newHand.setForeground(new java.awt.Color(0, 156, 222));
		newHand.setBackground(new java.awt.Color( 224, 60, 49));
		newHand.setFont(new Font("Arial", Font.PLAIN,50));
		newHand.setBorder(null);
		newHand.addActionListener( new newHandButtonListner());
		
		JPanel newHandPanel = new JPanel();
		newHandPanel.setLayout(new FlowLayout());
		newHandPanel.setBackground(new java.awt.Color(0,122,51));
		newHandPanel.add(newHand);
		
		
		
		
		
		gameOverScreenPanel.add(newHandPanel,BorderLayout.EAST);
	}

	@Override
	public int isOver() {
		int playerHandTotal = blackJack.handTotal(blackJack.playerHand());
		int dealerHandTotal = blackJack.handTotal(blackJack.dealerHand());
		CardStack playerHand = blackJack.playerHand();
		CardStack dealerHand = blackJack.dealerHand();
		
		if(blackJack.isBust(playerHand)) {
			return 2;
		}
		else if(blackJack.isBust(dealerHand)) {
			return 1;
		}
		
		else if((blackJack.is21(playerHand)) || (playerHandTotal > dealerHandTotal)) {
			return 1;
		}
		
		else if((blackJack.is21(dealerHand)) || (dealerHandTotal > playerHandTotal)){
			return 3;
		}
		
		else {
			return 3;
		}
		
	}

	@Override
	public boolean turn() {
		if(blackJack.isBust(blackJack.playerHand())) {
			winCondition = 2;
			turnIsOver = true;
		}
		
		else if(blackJack.is21(blackJack.playerHand())) {
			winCondition = 1;
			turnIsOver = true;
		}
		else {
			turnIsOver = false;
		}
		return turnIsOver;
	}

	
	
	
	public JPanel showGameOverScreen() {
		stopGame();
		gameOverScreenPanelBuilder();
		return gameOverScreenPanel;
	}

	
	

}
