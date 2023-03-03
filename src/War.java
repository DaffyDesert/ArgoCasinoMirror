import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * War class is gui based that will show the player what is happening in the game in real time.
 * 
 * @author Daniel Padgett, 
 *
 */
@SuppressWarnings("serial")
public class War extends JPanel implements Game, ActionListener, MouseListener {
	JLabel testText;
	JLabel startText;
	JLabel endText;
	Stopwatch stopwatch;
	Thread thread;
	/**
	 * War default constructor will set up the Panel. No layout manager is used.
	 * It will initialize the startText label and set the position, font, and size.
	 * It will do the same with endText.
	 * Also, it will ititialize the stopwatch and thread. As well as set the position 
	 * for the Stopwatch.
	 */
	War() {
		this.setLayout(null);
		startText = new JLabel("GAME START");
		startText.setBounds(50, 100, 400, 100);
		startText.setVerticalAlignment(SwingConstants.CENTER);
		startText.setFont(new Font("Sans Serif", Font.BOLD, 60));

		endText = new JLabel("GAME OVER");
		endText.setBounds(50, 100, 400, 100);
		endText.setVerticalAlignment(SwingConstants.CENTER);
		endText.setFont(new Font("Sans Serif", Font.BOLD, 60));

		stopwatch = new Stopwatch();
		stopwatch.display().setBounds(325, -25, 200, 100);
		startGame();
		thread = new Thread(stopwatch);
	}

	/**
	 * Function will simply start the stopwatch's timer.
	 */
	@Override
	public void startWatch() {
		stopwatch.startTimer();
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
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(startText);
		panel.add(endText);
		endText.setVisible(false);
		panel.add(stopwatch.display());
		panel.addMouseListener(this);
		
		return panel;
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
*/
}
