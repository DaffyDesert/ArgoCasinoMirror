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
public class War extends JPanel implements Game, ActionListener, MouseListener{
	Graphics2D g;
	JLabel startText;
	JLabel endText;
	JPanel statusBar;
	JPanel gamePanel;
	JPanel gameOverScreen;
	WarBoard board;
	Stopwatch stopwatch;
	Thread thread;

	/**
	 * War default constructor will set up the Panel. No layout manager is used.
	 * It will initialize the startText label and set the position, font, and size.
	 * It will do the same with endText.
	 * Also, it will initialize the stopwatch and thread. As well as set the position 
	 * for the Stopwatch.
	 */
	War() {//Removed setLayout(null);
		
		this.setBounds(0, 0, 1270, 720);
		
		board = new WarBoard(2, 2, 2);
		gamePanel = board.drawBoard(g);

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
		thread = new Thread(stopwatch);
		//Removed startGame() from constructor, will be handled in run()

		//Added statusBar panel to be displayed at the top of the War game panel.
		statusBar = new JPanel();
		statusBar.setBounds(0, 0, 1270, 144);
		statusBar.setLayout(new FlowLayout());
		statusBar.add(startText);
		statusBar.add(stopwatch.display());
		
		//Creates layout manager for War();
		this.setLayout(new GridBagLayout());
		GridBagConstraints sbCon = setConstraint(0, 0, 10, 1, 635, 72, 0.0, 0.1);
		GridBagConstraints gameCon = setConstraint(0, 1, 30, 30, 635, 288, 0.0, 9.0);
		sbCon.anchor = GridBagConstraints.NORTH;
		gameCon.anchor = GridBagConstraints.NORTH;
		gameCon.fill = GridBagConstraints.BOTH;
		
		this.add(statusBar, sbCon);
		this.add(gamePanel, gameCon);
		this.revalidate();
		this.repaint();
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
		thread.start();
	}

	/**
	 * Function will start the watch. And after 3.5 seconds, 
	 * the displayed "Game Start" will be set to invisible.
	 */
	@Override
	public void startGame() {
		startWatch();
		Timer timer = new Timer(3500, new ActionListener() {
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
		stopwatch.stopTimer();
	}
	
	/**
	 * Function will stop the watch and show the "Game Over" text
	 */
	@Override
	public void stopGame() {
		stopwatch.stopTimer();
		endText.setVisible(true);
	}

	@Override
	public JPanel display() {
		return this;
	}
	
	/**
	* TODO: When warboard is functional, add functionality to this method
	*/
	@Override
	public int isOver() {
		return 0;
	}

	@Override
	public void run() {

	}

	@Override
	public void turn() {
		
	}

	@Override
	public JPanel GameOverScreen() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
