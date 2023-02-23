import javax.swing.JPanel;

public interface Game {
	public Stopwatch watch; //The stop watch
	public Thread watchThread; //The thread that allows the watch to run alongside the program.
	public Board gameBoard; //Danny's board object. Implementation may need work
	public Player player; //Object of player abstract class. May be subclass
	void startWatch(); //Starts the stop watch
	void startGame(); //Starts the game. Calls startWatch()
	void stopWatch(); //Stops the watch
	void stopGame(); //Stops the game. Calls stopWatch();
	JPanel display(); //Returns JPanel to main GUI which displays the game.
	boolean isOver(); //Checks if the game is over.
}
