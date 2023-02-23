import javax.swing.JPanel;


public interface Game {
	/*
	 * Objects to be constant and unchanging across each game
	 */
	public Stopwatch watch = new Stopwatch(); //The stop watch
	public Thread watchThread = new Thread(watch); //The thread that allows the watch to run alongside the program.
	
	/*
	 * Objects that will be different between each game.
	 */
	//public Board gameBoard;
	//public Player player; //May be replaced with game's respective player class, which should extend Player class.
	//public Player enemy; //Where applicable
	
	/*
	 * Methods that should be implemented in each game. This list may grow as time goes on
	 */
	public void startWatch(); //Starts the stop watch
	public void startGame(); //Starts the game. Calls startWatch()
	public void stopWatch(); //Stops the watch
	public void stopGame(); //Stops the game. Calls stopWatch();
	public JPanel display(); //Returns JPanel to main GUI which displays the game.
	public boolean isOver(); //Checks if the game is over.
}
