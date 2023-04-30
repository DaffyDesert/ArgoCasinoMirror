import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Solitaire extends JPanel implements Game{
	private Stopwatch stopwatch;
	private Thread thread;
	/*
	 * KNOWN BUGS:
	 * - When after picking up a card from a column and placing it somewhere else, the last card of the last column clicked does not switch to face up
	 * - When in the scenario above, you can pick up the face down cards
	 * - When you have a card in your hand, you can actually put it in the discard pile, even if its from the columns
	 * - Unable to pick up an entire stack of face up cards
	 * - Foundations are able to store the Ace but any card after wards is eaten
	 * - Not a bug but in hindsight, i could've used an array for the columns
	 */

	JPanel board;
	JPanel tableau;
	JPanel foundations;
	JPanel handAndWaste;
	JPanel selectedCardPanel;

	ArrayList<SolitaireColumn> tableauColumns = new ArrayList<SolitaireColumn>();

	SolitaireBoard solitaire;
	Card currCard;

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
		currCard = new Card();
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
		board.setBackground(new java.awt.Color(0, 122, 40));
		board.setLayout(new BorderLayout());
		board.setPreferredSize(new Dimension(1270, 650));

		tableauBuilder();
		foundationsBuilder();
		handAndWasteBuilder();

		board.add(tableau, BorderLayout.CENTER);
		board.add(foundations, BorderLayout.EAST);
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

	public void selectedCardBuilder() {
		selectedCardPanel = new JPanel();
		selectedCardPanel.setLayout(new FlowLayout());
		selectedCardPanel.setBackground(new java.awt.Color(0, 122, 51));
		selectedCardPanel.setPreferredSize(new Dimension(6000, 200));

		tableau.add(selectedCardPanel);
	}

	public void tableauBuilder() {
		tableau = new JPanel();
		tableau.setLayout(new FlowLayout());
		tabeauColumnsAdder();
		selectedCardBuilder();
		tableau.setBackground(new java.awt.Color(0, 122, 51));
		tableau.setPreferredSize(new Dimension(6000, 210));

		tableau.revalidate();
		tableau.repaint();
	}

	public void tabeauColumnsAdder() {
		column1 = new JPanel();
		column1.setPreferredSize(new Dimension(135, 500));
		column1.setLayout(new FlowLayout());
		column1.add(solitaire.getColumnAt(0).display());
		column1.addMouseListener(new ColumnListener());
		column1.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column1);

		column2 = new JPanel();
		column2.setPreferredSize(new Dimension(135, 500));
		column2.setLayout(new FlowLayout());
		column2.add(solitaire.getColumnAt(1).display());
		column2.addMouseListener(new ColumnListener());
		column2.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column2);

		column3 = new JPanel();
		column3.setPreferredSize(new Dimension(135, 500));
		column3.setLayout(new FlowLayout());
		column3.add(solitaire.getColumnAt(2).display());
		column3.addMouseListener(new ColumnListener());
		column3.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column3);

		column4 = new JPanel();
		column4.setPreferredSize(new Dimension(135, 500));
		column4.setLayout(new FlowLayout());
		column4.add(solitaire.getColumnAt(3).display());
		column4.addMouseListener(new ColumnListener());
		column4.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column4);

		column5 = new JPanel();
		column5.setPreferredSize(new Dimension(135, 500));
		column5.setLayout(new FlowLayout());
		column5.add(solitaire.getColumnAt(4).display());
		column5.addMouseListener(new ColumnListener());
		column5.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column5);

		column6 = new JPanel();
		column6.setPreferredSize(new Dimension(135, 500));
		column6.setLayout(new FlowLayout());
		column6.add(solitaire.getColumnAt(5).display());
		column6.addMouseListener(new ColumnListener());
		column6.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column6);

		column7 = new JPanel();
		column7.setPreferredSize(new Dimension(135, 500));
		column7.setLayout(new FlowLayout());
		column7.add(solitaire.getColumnAt(6).display());
		column7.addMouseListener(new ColumnListener());
		column7.setBackground(new java.awt.Color(0, 122, 51));
		tableau.add(column7);
	}

	public void foundationsBuilder() {
		heartFoundation = new JPanel();
		heartFoundation.setBackground(new java.awt.Color(0, 122, 51));
		heartFoundation.setPreferredSize(new Dimension(100, 145));
		heartFoundation.addMouseListener(new FoundationListener());
		heartFoundation.setBorder(BorderFactory.createTitledBorder("Hearts"));

		spadeFoundation = new JPanel();
		spadeFoundation.setBackground(new java.awt.Color(0, 122, 51));
		spadeFoundation.setPreferredSize(new Dimension(100, 145));
		spadeFoundation.addMouseListener(new FoundationListener());
		spadeFoundation.setBorder(BorderFactory.createTitledBorder("Spades"));

		diamondFoundation = new JPanel();
		diamondFoundation.setBackground(new java.awt.Color(0, 122, 51));
		diamondFoundation.setPreferredSize(new Dimension(100, 145));
		diamondFoundation.addMouseListener(new FoundationListener());
		diamondFoundation.setBorder(BorderFactory.createTitledBorder("Diamonds"));

		clubFoundation = new JPanel();
		clubFoundation.setBackground(new java.awt.Color(0, 122, 51));
		clubFoundation.setPreferredSize(new Dimension(100, 145));
		clubFoundation.addMouseListener(new FoundationListener());
		clubFoundation.setBorder(BorderFactory.createTitledBorder("Club"));

		foundations = new JPanel();
		foundations.setLayout(new FlowLayout(0, 10, 20));
		foundations.setBackground(new java.awt.Color(0, 122, 51));
		foundations.setPreferredSize(new Dimension(130, 250));
		foundations.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		foundations.add(heartFoundation);
		foundations.add(spadeFoundation);
		foundations.add(diamondFoundation);
		foundations.add(clubFoundation);

	}

	public void handAndWasteBuilder() {
		hand = new JPanel();
		hand = solitaire.getDeck().draw();
		hand.setPreferredSize(new Dimension(100, 200));
		hand.addMouseListener(new DeckAndWasteListener());

		waste = new JPanel();
		waste.setBackground(new java.awt.Color(0, 122, 51));
		waste.setPreferredSize(new Dimension(100, 200));
		waste.addMouseListener(new DeckAndWasteListener());

		handAndWaste = new JPanel();
		handAndWaste.setLayout(new FlowLayout(0, 10, 20));
		handAndWaste.setBackground(new java.awt.Color(0, 122, 51));
		handAndWaste.setPreferredSize(new Dimension(130, 250));
		handAndWaste.setBorder(BorderFactory.createLineBorder(Color.black, 2));

		handAndWaste.add(hand);
		handAndWaste.add(waste);
	}
	
	public void revalidateAllColumns() {
		column1.revalidate();
		column1.repaint();
		column2.revalidate();
		column2.repaint();
		column3.revalidate();
		column3.repaint();
		column4.revalidate();
		column4.repaint();
		column5.revalidate();
		column5.repaint();
		column6.revalidate();
		column6.repaint();
		column7.revalidate();
		column7.repaint();
	}
	
	private class FoundationListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getComponent() == heartFoundation && solitaire.addToHeartFoundation(currCard)) {
				selectedCardPanel.removeAll();
				selectedCardPanel.revalidate();
				selectedCardPanel.repaint();
				
				currCard.setFaceDown(false);
				heartFoundation.add(currCard.draw());
				heartFoundation.revalidate();
				heartFoundation.repaint();
				currCard = new Card();
			} else if (e.getComponent() == spadeFoundation && solitaire.addToSpadeFoundation(currCard)) {
				selectedCardPanel.removeAll();
				selectedCardPanel.revalidate();
				selectedCardPanel.repaint();
				
				currCard.setFaceDown(false);
				spadeFoundation.add(currCard.draw());
				spadeFoundation.revalidate();
				spadeFoundation.repaint();
				currCard = new Card();
			} else if (e.getComponent() == diamondFoundation && solitaire.addToDiamondFoundation(currCard)) {
				selectedCardPanel.removeAll();
				selectedCardPanel.revalidate();
				selectedCardPanel.repaint();
				
				currCard.setFaceDown(false);
				diamondFoundation.add(currCard.draw());
				diamondFoundation.revalidate();
				diamondFoundation.repaint();
				currCard = new Card();
			} else if (e.getComponent() == clubFoundation && solitaire.addToClubFoundation(currCard)) {
				selectedCardPanel.removeAll();
				selectedCardPanel.revalidate();
				selectedCardPanel.repaint();
				
				currCard.setFaceDown(false);
				clubFoundation.add(currCard.draw());
				clubFoundation.revalidate();
				clubFoundation.repaint();
				currCard = new Card();
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
			if (currCard.getRank() != 0) {
				if (e.getComponent() == column1 && solitaire.getColumnAt(0).addCard(currCard)) {  
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column1.removeAll();
					column1.add(solitaire.getColumnAt(0).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column2 && solitaire.getColumnAt(1).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column2.removeAll();
					column2.add(solitaire.getColumnAt(1).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column3 && solitaire.getColumnAt(2).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column3.removeAll();
					column3.add(solitaire.getColumnAt(2).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column4 && solitaire.getColumnAt(3).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column4.removeAll();
					column4.add(solitaire.getColumnAt(3).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column5 && solitaire.getColumnAt(4).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column5.removeAll();
					column5.add(solitaire.getColumnAt(4).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column6 && solitaire.getColumnAt(5).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column6.removeAll();
					column6.add(solitaire.getColumnAt(5).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column7 && solitaire.getColumnAt(6).addCard(currCard)) {
					currCard = new Card();
					selectedCardPanel.removeAll();
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column7.removeAll();
					column7.add(solitaire.getColumnAt(6).display());
					revalidateAllColumns();
				}
			}
			else {
				if (e.getComponent() == column1) {
					currCard = solitaire.getColumnAt(0).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column1.removeAll();
					column1.add(solitaire.getColumnAt(0).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column2) {
					currCard = solitaire.getColumnAt(1).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column2.removeAll();
					column2.add(solitaire.getColumnAt(1).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column3) {
					currCard = solitaire.getColumnAt(2).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column3.removeAll();
					column3.add(solitaire.getColumnAt(2).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column4) {
					currCard = solitaire.getColumnAt(3).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column4.removeAll();
					column4.add(solitaire.getColumnAt(3).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column5) {
					currCard = solitaire.getColumnAt(4).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column5.removeAll();
					column5.add(solitaire.getColumnAt(4).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column6) {
					currCard = solitaire.getColumnAt(5).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column6.removeAll();
					column6.add(solitaire.getColumnAt(5).display());
					revalidateAllColumns();
				} else if (e.getComponent() == column7) {
					currCard = solitaire.getColumnAt(6).getColumn().dealTopCard();
					selectedCardPanel.add(currCard.draw());
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
					
					column7.removeAll();
					column7.add(solitaire.getColumnAt(6).display());
					revalidateAllColumns();
				}
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
			if (e.getComponent() == hand && currCard.getRank() == 0) {
				if (solitaire.getDeck().getStackSize() == 0) {
					solitaire.resetDeck();
					waste.removeAll();
					waste.revalidate();
					waste.repaint();
					
					hand.add(solitaire.getDeck().peekCard(0).draw());
					hand.revalidate();
					hand.repaint();
				} 
				else {
					
					currCard = solitaire.drawFromDeck();
					currCard.setFaceDown(false);
					selectedCardPanel.removeAll();
					selectedCardPanel.add(currCard.draw());
					
					if(solitaire.getDeck().getStackSize() == 0) {
						hand.removeAll();
						hand.revalidate();
						hand.repaint();
					}
					
					selectedCardPanel.revalidate();
					selectedCardPanel.repaint();
				}
			}
			
			if (e.getComponent() == waste && currCard.getRank() != 0) {
				selectedCardPanel.removeAll();
				selectedCardPanel.revalidate();
				selectedCardPanel.repaint();
				
				waste.removeAll();
				solitaire.addToDiscardPile(currCard);
				waste.add(currCard.draw());
				currCard = new Card();
				waste.revalidate();
				waste.repaint();
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
