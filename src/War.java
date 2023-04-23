import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.Timer;

/**
 * 	War handles the GUI and displays aspects of a game of war.
 *  It passes the display of the game board to the MainMenu 
 *  for proper display and calls WarBoard to perform game logic 
 *  functions.
 */

@SuppressWarnings("serial")
public class War extends JPanel implements Game {
	JPanel statusBar;
	JButton drawCardButton = new JButton("Play Turn");
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

		drawCardButton.setForeground(new java.awt.Color(0, 156, 222));
		drawCardButton.setBackground(new java.awt.Color( 224, 60, 49));
		drawCardButton.setFont(new Font("Arial", Font.PLAIN, 40));
		drawCardButton.setBorder(null);
		drawCardButton.setToolTipText("Reveal the top cards to see who wins or if its a War!!!!!");
		drawCardButton.addActionListener(new turnButtonListner());

		this.setLayout(new GridBagLayout());

		GridBagConstraints sbCon = new GridBagConstraints();
		sbCon.fill = GridBagConstraints.HORIZONTAL;
		sbCon.gridy = 0;		
		sbCon.ipadx = 1270;
		sbCon.ipady = 20;
		
		GridBagConstraints gameCon = new GridBagConstraints();
		gameCon.fill = GridBagConstraints.HORIZONTAL;
		gameCon.gridy = 1;
		
		GridBagConstraints hitBttn = new GridBagConstraints();
		hitBttn.fill = GridBagConstraints.HORIZONTAL;
		hitBttn.gridy = 3;		
		
		this.add(statusBar,sbCon);
		this.add(board.drawBoard(), gameCon);
		this.add(drawCardButton,hitBttn);
		
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
		timer.stop();
		
		return gameOver;
	}

		private class turnButtonListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (turn() == false) {
				stopGame();
				stopWatch();
				showGameOverScreen();
			}
		}
	}

}