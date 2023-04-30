import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Solitaire extends JPanel implements Game{
	private Stopwatch stopwatch;
	private Thread thread;
	JPanel board;
	JPanel tableau;
	JPanel foundations;
	JPanel handAndWaste;
	ArrayList<SolitaireColumn> tableauColumns = new ArrayList<SolitaireColumn>();
	SolitaireBoard solitaire;
	JPanel heartFoundation = new JPanel();
	JPanel spadeFoundation = new JPanel();
	JPanel diamondFoundation = new JPanel();
	JPanel clubFoundation = new JPanel();

	Solitaire() {
		stopwatch = new Stopwatch();
		thread = new Thread(stopwatch);
		solitaire = new SolitaireBoard();
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
		tableau.setLayout(new FlowLayout());
		
		tabeauColumnsAdder();

		tableau.setBackground(new java.awt.Color(0,122,51));
		tableau.setPreferredSize(new Dimension(6000,310));
		tableau.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	}
	
	public void tabeauColumnsAdder() {	
		for(int i= 0; i < 7; i++) {
			SolitaireColumn newColoumn = new SolitaireColumn();
			tableauColumns.add(newColoumn);
			for(int j = 0; j < (i + 1); j++) {
				tableauColumns.get(i).addCard(solitaire.getDeck().dealTopCard());
			}
		
		
			tableauColumns.get(i).getColumn().flipTopCard();
			tableau.add(tableauColumns.get(i).display());
		}

	}
	
	public void foundationsBuilder() {
		heartFoundation = new JPanel();
		heartFoundation.setBackground(Color.red);
		heartFoundation.setPreferredSize(new Dimension(100,145));
		heartFoundation.addMouseListener(new FoundationListener());
		
		spadeFoundation = new JPanel();
		spadeFoundation.setBackground(Color.black);
		spadeFoundation.setPreferredSize(new Dimension(100,145));
		spadeFoundation.addMouseListener(new FoundationListener());
		
		diamondFoundation = new JPanel();
		diamondFoundation.setBackground(Color.blue);
		diamondFoundation.setPreferredSize(new Dimension(100,145));
		diamondFoundation.addMouseListener(new FoundationListener());
		
		clubFoundation = new JPanel();
		clubFoundation.setBackground(Color.green);
		clubFoundation.setPreferredSize(new Dimension(100,145));
		clubFoundation.addMouseListener(new FoundationListener());
		
		foundations = new JPanel();
		foundations.setLayout(new FlowLayout(0, 10, 20));
		foundations.setBackground(new java.awt.Color(0,122,51));
		foundations.setPreferredSize(new Dimension(130,250));
		foundations.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		foundations.add(heartFoundation);
		foundations.add(spadeFoundation);
		foundations.add(diamondFoundation);
		foundations.add(clubFoundation);
		
	}
	
	public void handAndWasteBuilder() {
//		JPanel hand = new JPanel();
//		hand.setBackground(Color.CYAN);
//		hand.setPreferredSize(new Dimension(100,145));
//		hand.add(solitaire.getDeck().draw());
//		
//		JPanel waste = new JPanel();
//		waste.setBackground(Color.pink);
//		waste.setPreferredSize(new Dimension(100,145));
//		waste.add(solitaire.getDeck().draw());
		
		handAndWaste = new JPanel();
		handAndWaste.setLayout(new FlowLayout(0, 10, 20));
		handAndWaste.setBackground(new java.awt.Color(0,122,51));
		handAndWaste.setPreferredSize(new Dimension(130,250));
		handAndWaste.setBorder(BorderFactory.createLineBorder(Color.black,2));
		
		handAndWaste.add(solitaire.getDeck().draw());
		handAndWaste.add(solitaire.getDiscardPile().draw());
	}
	
	private class FoundationListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent() == heartFoundation) {
				System.out.println("IT HURTS");
			}
			else if(e.getComponent() == spadeFoundation) {
				System.out.println("IT BURNS");
			}
			else if(e.getComponent() == diamondFoundation) {
				System.out.println("IM IN PAIN");
			}
			else if(e.getComponent() == clubFoundation) {
				System.out.println("MY EARS BURN");
			}
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

	@Override
	public void promptPlayerBet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerBank() {
		// TODO Auto-generated method stub
		
	}
	

}
