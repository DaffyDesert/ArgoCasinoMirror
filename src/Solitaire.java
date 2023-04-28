import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Solitaire extends JPanel implements Game{
	private Stopwatch stopwatch;
	private Thread thread;
	
	JPanel board;
	JPanel tableau;
	JPanel foundations;
	JPanel handAndWaste;
	
	ArrayList<SolitaireColumn> tableauColumns = new ArrayList<SolitaireColumn>();
	
	SolitaireBoard solitaire;
	
	JPanel heartFoundation;
	JPanel spadeFoundation;
	JPanel diamondFoundation;
	JPanel clubFoundation;
	
	JPanel column1;
	JPanel column2;
	JPanel column3;
	JPanel column4;
	JPanel column5;
	JPanel column6;
	JPanel column7;
	
	JPanel hand;
	JPanel waste;

	Solitaire() {
		stopwatch = new Stopwatch();
		thread = new Thread(stopwatch);
		solitaire = new SolitaireBoard();
		solitaire.dealToColumns();
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
		column1 = new JPanel();
		column1.setPreferredSize(new Dimension(135, 730));
		column1.setLayout(new FlowLayout());
		column1.add(solitaire.getColumnAt(0).display());
		column1.addMouseListener(new ColumnListener());
		column1.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column1);
		
		column2 = new JPanel();
		column2.setPreferredSize(new Dimension(135, 730));
		column2.setLayout(new FlowLayout());
		column2.add(solitaire.getColumnAt(1).display());
		column2.addMouseListener(new ColumnListener());
		column2.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column2);
		
		column3 = new JPanel();
		column3.setPreferredSize(new Dimension(135, 730));
		column3.setLayout(new FlowLayout());
		column3.add(solitaire.getColumnAt(2).display());
		column3.addMouseListener(new ColumnListener());
		column3.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column3);
		
		column4 = new JPanel();
		column4.setPreferredSize(new Dimension(135, 730));
		column4.setLayout(new FlowLayout());
		column4.add(solitaire.getColumnAt(3).display());
		column4.addMouseListener(new ColumnListener());
		column4.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column4);
		
		column5 = new JPanel();
		column5.setPreferredSize(new Dimension(135, 730));
		column5.setLayout(new FlowLayout());
		column5.add(solitaire.getColumnAt(4).display());
		column5.addMouseListener(new ColumnListener());
		column5.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column5);
		
		column6 = new JPanel();
		column6.setPreferredSize(new Dimension(135, 730));
		column6.setLayout(new FlowLayout());
		column6.add(solitaire.getColumnAt(5).display());
		column6.addMouseListener(new ColumnListener());
		column6.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column6);
		
		column7 = new JPanel();
		column7.setPreferredSize(new Dimension(135, 730));
		column7.setLayout(new FlowLayout());
		column7.add(solitaire.getColumnAt(6).display());
		column7.addMouseListener(new ColumnListener());
		column7.setBackground(new java.awt.Color(0,122,51));
		tableau.add(column7);
	}
	
	public void foundationsBuilder() {
		heartFoundation = new JPanel();
		heartFoundation.setBackground(new java.awt.Color(0,122,51));
		heartFoundation.setPreferredSize(new Dimension(100,145));
		heartFoundation.addMouseListener(new FoundationListener());
		heartFoundation.setBorder(BorderFactory.createTitledBorder("Hearts"));
		
		spadeFoundation = new JPanel();
		spadeFoundation.setBackground(new java.awt.Color(0,122,51));
		spadeFoundation.setPreferredSize(new Dimension(100,145));
		spadeFoundation.addMouseListener(new FoundationListener());
		spadeFoundation.setBorder(BorderFactory.createTitledBorder("Spades"));
		
		diamondFoundation = new JPanel();
		diamondFoundation.setBackground(new java.awt.Color(0,122,51));
		diamondFoundation.setPreferredSize(new Dimension(100,145));
		diamondFoundation.addMouseListener(new FoundationListener());
		diamondFoundation.setBorder(BorderFactory.createTitledBorder("Diamonds"));

		clubFoundation = new JPanel();
		clubFoundation.setBackground(new java.awt.Color(0,122,51));
		clubFoundation.setPreferredSize(new Dimension(100,145));
		clubFoundation.addMouseListener(new FoundationListener());
		clubFoundation.setBorder(BorderFactory.createTitledBorder("Club"));

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
		hand = new JPanel();
		hand = solitaire.getDeck().draw();
		hand.addMouseListener(new DeckAndWasteListener());
		
		waste = new JPanel();
		waste.setBackground(Color.black);
		waste = solitaire.getDiscardPile().draw();
		waste.addMouseListener(new DeckAndWasteListener());
		
		handAndWaste = new JPanel();
		handAndWaste.setLayout(new FlowLayout(0, 10, 20));
		handAndWaste.setBackground(new java.awt.Color(0,122,51));
		handAndWaste.setPreferredSize(new Dimension(130,250));
		handAndWaste.setBorder(BorderFactory.createLineBorder(Color.black,2));
		
		handAndWaste.add(hand);
		handAndWaste.add(waste);
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
	
	private class ColumnListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent() == column1) {
				System.out.println("column1");
			}
			else if(e.getComponent() == column2) {
				System.out.println("column2");
			}
			else if(e.getComponent() == column3) {
				System.out.println("column3");
			}
			else if(e.getComponent() == column4) {
				System.out.println("column4");
			}
			else if(e.getComponent() == column5) {
				System.out.println("column5");
			}
			else if(e.getComponent() == column6) {
				System.out.println("column6");
			}
			else if(e.getComponent() == column7) {
				System.out.println("column7");
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
	
	private class DeckAndWasteListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent() == hand)
				System.out.println("hand");
			else if(e.getComponent() == waste)
				System.out.println("waste");
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

}
