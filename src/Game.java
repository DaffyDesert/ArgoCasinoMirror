import javax.swing.JPanel;


public interface Game {
	public void startWatch(); //Starts the stop watch
	public void startGame(); //Starts the game. Calls startWatch()
	public void stopWatch(); //Stops the watch
	public void stopGame(); //Stops the game. Calls stopWatch();
	public JPanel display(); //Returns JPanel to main GUI which displays the game.
	public int isOver(); //Checks if the game is over.
	public boolean turn(); //Handles the logic for each of the game's turns
	public JPanel showGameOverScreen(); //Returns the Game Over Screen JPanel.
}
