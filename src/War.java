import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * War class is gui based that will show the player what is happening in the game in real time.
 * 
 * @author Daniel Padgett, 
 *
 */
@SuppressWarnings("serial")
public class War extends JPanel implements Game, MouseListener{
	JLabel startText;
	JLabel endText;
	JPanel statusBar;
	JPanel gamePanel;
	JPanel gameOverScreen;
	WarBoard board;
	Stopwatch stopwatch;
	Thread thread1;
	java.util.Timer updateGUI;

	/**
	 * War default constructor will set up the Panel. No layout manager is used.
	 * It will initialize the startText label and set the position, font, and size.
	 * It will do the same with endText.
	 * Also, it will initialize the stopwatch and thread. As well as set the position 
	 * for the Stopwatch.
	 */
	War() {
		
		this.setBounds(0, 0, 1270, 720);
		
		board = new WarBoard();
		
		startText = new JLabel("GAME START");
		startText.setBounds(0, 0, 400, 100);
		startText.setVerticalAlignment(SwingConstants.NORTH);
		startText.setFont(new Font("Sans Serif", Font.BOLD, 60));

		endText = new JLabel("GAME OVER");
		endText.setBounds(0, 0, 400, 100);
		endText.setVerticalAlignment(SwingConstants.NORTH);
		endText.setFont(new Font("Sans Serif", Font.BOLD, 60));

		stopwatch = new Stopwatch();
		stopwatch.display().setBounds(0, 0, 400, 100);
		thread1 = new Thread(stopwatch);
		
		this.addMouseListener(this);
	}
	
	private GridBagConstraints setConstraint(int x, int y, int width, int height, int xpad, int ypad, double xweight, double yweight) {
		GridBagConstraints newConstraint = new GridBagConstraints();
		newConstraint.gridx = x;
		newConstraint.gridy = y;
		newConstraint.gridwidth = width;
		newConstraint.gridheight = height;
		newConstraint.ipadx = xpad;
		newConstraint.ipady = ypad;
		newConstraint.weightx = xweight;
		newConstraint.weighty = yweight;
		return newConstraint;
	}

	/**
	 * Function will simply start the stopwatch's timer.
	 */
	@Override
	public void startWatch() {
		thread1.start();
	}

	/**
	 * Function will start the watch. And after 3.5 seconds, 
	 * the displayed "Game Start" will be set to invisible.
	 */
	@Override
	public void startGame() {
		startWatch();
		javax.swing.Timer timer = new javax.swing.Timer(3500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startText.setVisible(false);
			}
		});
		timer.start();
	}
	
	/**
	 * Function will simply stop the stopwatch's timer.
	 */
	@Override
	public void stopWatch() {
		stopwatch.stopThread();
	}
	
	/**
	 * Function will stop the watch and show the "Game Over" text
	 */
	@Override
	public void stopGame() {
		stopWatch();
		updateGUI.cancel();
	}

	@Override
	public JPanel display() {
		/*
		//Added statusBar panel to be displayed at the top of the War game panel.
		statusBar = new JPanel();
		statusBar.setBounds(0, 0, 1270, 144);
		statusBar.setLayout(new BorderLayout());
		//statusBar.add(startText);
		statusBar.add(stopwatch.display(), BorderLayout.SOUTH);
		statusBar.setBackground(Color.black);
				
		//Creates layout manager for War();
		this.setLayout(new GridBagLayout());
		GridBagConstraints sbCon = setConstraint(0, 0, 10, 1, 635, 72, 0.0, 0.1);
		GridBagConstraints gameCon = setConstraint(0, 1, 30, 30, 635, 288, 0.0, 9.0);
		sbCon.anchor = GridBagConstraints.NORTH;
		gameCon.anchor = GridBagConstraints.NORTH;
		gameCon.fill = GridBagConstraints.BOTH;
				
		this.add(statusBar, sbCon);
		this.add(board.drawBoard(), gameCon);
		this.revalidate();
		this.repaint();
		
		return this;
		*/
		
		statusBar = new JPanel();
		statusBar.setBounds(0,0,1270,144);
		statusBar.setLayout(new BorderLayout());
		statusBar.add(stopwatch.display(), BorderLayout.SOUTH);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints sbCon = new GridBagConstraints();
		sbCon.fill = GridBagConstraints.HORIZONTAL;
		sbCon.gridy = 0;		
		sbCon.ipadx = 1270;
		sbCon.ipady = 40;
		
		GridBagConstraints gameCon = new GridBagConstraints();
		gameCon.fill = GridBagConstraints.HORIZONTAL;
		gameCon.gridy = 1;
		
		this.add(statusBar,sbCon);
		this.add(board.drawBoard(), gameCon);
		
		this.revalidate();
		this.repaint();
		return this;
	}
	
	@Override
	public int isOver() { 
		return board.checkWinStatus();
	}

	@Override
	public void turn() {
		board.turn();
	}

	@Override
	public JPanel GameOverScreen() {
		JPanel gameOver = new JPanel();
		gameOver.setLayout(new FlowLayout());
		
		gameOver.setBounds(0, 0, 1270, 720);
		stopwatch.updateLabel();
		gameOver.add(stopwatch.display());
		
		JLabel winLabel = new JLabel();
		if (isOver() == 1) {
			winLabel.setText("You win!!!");
		}
		else if (isOver() == -1){
			winLabel.setText("You lose!!!");
		}
		else if (isOver() == 0) {
			winLabel.setText("A Tie!?!?!?!");
		}
		
		gameOver.add(winLabel);
		
		this.removeAll();
		this.add(gameOver);
		this.revalidate();
		this.repaint();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gameOver;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.turn();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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