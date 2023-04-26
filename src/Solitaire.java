import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Solitaire extends JPanel implements Game{
	private Stopwatch stopwatch;
	private Thread thread;
	JPanel board;
	JPanel tableau;
	JPanel foundations;
	JPanel handAndWaste;
	
	Solitaire() {
		stopwatch = new Stopwatch();
		thread = new Thread(stopwatch);
		//solitaire = new SolitaireBoard();
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
		stopwatch.stopTimer();
		
		
	}

	@Override
	public void stopGame() {
		stopWatch();
		
	}

	@Override
	public JPanel display() {
		board = new JPanel();
		board.removeAll();
		board.setBackground(new java.awt.Color(0,122,40));
		board.setLayout(new BorderLayout());
		board.setPreferredSize(new Dimension(1270,650));
		
		tableauBuilder();
		foundationsBuilder();
		handAndWasteBuilder();
		
		board.add(tableau,BorderLayout.CENTER);
		board.add(foundations,BorderLayout.EAST);
		board.add(handAndWaste, BorderLayout.WEST);
		
		board.revalidate();
		board.repaint();
		
		return board;
		
	}

	@Override
	public int isOver() {
		return 0;
	}

	@Override
	public boolean turn() {
		return false;
	}

	@Override
	public JPanel showGameOverScreen() {
		return null;
	}
	
	public void tableauBuilder() {
		tableau = new JPanel();
		tableau.setLayout(new BorderLayout());
		tableau.setBackground(new java.awt.Color(0,122,51));
		tableau.setPreferredSize(new Dimension(50,50));
		tableau.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	
	public void foundationsBuilder() {
		foundations = new JPanel();
		foundations.setLayout(new BorderLayout());
		foundations.setBackground(new java.awt.Color(0,122,51));
		foundations.setPreferredSize(new Dimension(200,250));
		foundations.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	
	public void handAndWasteBuilder() {
		handAndWaste = new JPanel();
		handAndWaste.setLayout(new BorderLayout());
		handAndWaste.setBackground(new java.awt.Color(0,122,51));
		handAndWaste.setPreferredSize(new Dimension(200,250));
		handAndWaste.setBorder(BorderFactory.createLineBorder(Color.black,2));
	}

}
