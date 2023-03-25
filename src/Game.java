import javax.swing.JPanel;

/**
 *  Game interface lays out the common methods needed by all card games
 */

public interface Game {
	public void startWatch(); 
	public void startGame(); 
	public void stopWatch(); 
	public void stopGame();
	public JPanel display(); 
	public int isOver(); 
	public boolean turn(); 
	public JPanel showGameOverScreen(); 
}
