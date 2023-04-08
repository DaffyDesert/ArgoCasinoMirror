import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 	War handles the GUI and displays aspects of a game of war.
 *  It passes the display of the game board to the MainMenu 
 *  for proper display and calls WarBoard to perform game logic 
 *  functions.
 */

@SuppressWarnings("serial")
public class War extends JPanel implements Game, MouseListener{
	JPanel statusBar;
	JPanel gameOverScreen;
	WarBoard board;
	Stopwatch stopwatch;
	Thread thread;

	War() {
		this.setBounds(0, 0, 1270, 720);
		statusBar = new JPanel();
		board = new WarBoard();
		
		stopwatch = new Stopwatch();
		stopwatch.display().setBounds(0, 0, 400, 100);
		thread = new Thread(stopwatch);
		
		this.addMouseListener(this);
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
		stopwatch.stopThread();
	}
	
	@Override
	public void stopGame() {
		stopWatch();
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
	public boolean turn() {
		return board.turn();
	}

	@Override
	public JPanel showGameOverScreen() {
		JPanel gameOver = new JPanel();
		gameOver.setLayout(new FlowLayout());
		
		gameOver.setBounds(0, 0, 1270, 720);
		stopwatch.finalLabel();
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
		
		
		Timer timer = new Timer(8000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		timer.start();
		
		return gameOver;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.turn() == false) {
			stopGame();
			stopWatch();
			showGameOverScreen();
		}
	}

	//Functions below are required by MouseListener interface but do not have functionality
	
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