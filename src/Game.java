import javax.swing.JPanel;


public interface Game {
	/*
	 * Member variables!!
	 */
	//private Stopwatch watch = new Stopwatch(); //The stop watch
	//private Thread watchThread = new Thread(watch); //The thread that allows the watch to run alongside the program.
	//public Board gameBoard;
	
	/*
	 * Methods that should be implemented in each game. This list may grow as time goes on
	 */
	public void startWatch(); //Starts the stop watch
	public void startGame(); //Starts the game. Calls startWatch()
	public void stopWatch(); //Stops the watch
	public void stopGame(); //Stops the game. Calls stopWatch();
	public JPanel display(); //Returns JPanel to main GUI which displays the game.
	public int isOver(); //Checks if the game is over.
	//public void run(); //Runs the game's logic
	public boolean turn(); //Handles the logic for each of the game's turns
	public JPanel showGameOverScreen(); //Returns the Game Over Screen JPanel.
}
