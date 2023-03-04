import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * War class is gui based that will show the player what is happening in the game in real time.
 */
@SuppressWarnings("serial")
public class War extends JPanel implements Game, MouseListener{
	JPanel statusBar;
	JPanel gameOverScreen;
	WarBoard board;
	Stopwatch stopwatch;
	Thread thread1;
	java.util.Timer updateGUI;

	War() {
		this.setBounds(0, 0, 1270, 720);
		statusBar = new JPanel();
		board = new WarBoard();
		
		stopwatch = new Stopwatch();
		stopwatch.display().setBounds(0, 0, 400, 100);
		thread1 = new Thread(stopwatch);
		
		this.addMouseListener(this);
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
	public JPanel showGameOverScreen() {
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