import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * The MainMenu class handles the display and logic of the game’s main menu, which is 
 * where users will return to both before and after any card game they play. The main 
 * menu has 3 options: Start Game, User Menu, and Exit. User Menu has not been 
 * implemented at this time. Exit will close the program. Start Game will bring the 
 * user to a menu to select one of 3 games: Solitaire, War, or Blackjack. Solitaire 
 * and Blackjack have not been implemented at this time. Selecting War will launch a game 
 * of war. MainMenu also calls and launches games and passes their displays to the 
 * GameWindow class for display.

 */

public class MainMenu {
	private JPanel windowPane;
	private JPanel mainMenuPanel;
	private JPanel gameSelectionPanel;
	
	public MainMenu() {
		windowPane = new JPanel();
		gameSelectionPanel = new JPanel();
		mainMenuPanel = new JPanel();
		windowPane.setBackground(new java.awt.Color(0, 122, 51));
		createGameSelectMenu();
		createMainMenu();
		
		windowPane.add(mainMenuPanel);
	}
	
	private void createMainMenu() {
		mainMenuPanel.setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("Argo Casino");
		title.setForeground(new java.awt.Color(0, 156, 222));
		title.setVerticalTextPosition(JLabel.NORTH);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 72));
		GridBagConstraints titleConstraints = setConstraint(1, 0, 1, 1, 0, 5);
		mainMenuPanel.add(title, titleConstraints);
		mainMenuPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints buttonPanelConstraints = setConstraint(1, 1, 1, 2, 0, 0);
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new java.awt.Color(0, 156, 222));
		startButton.setBackground(new java.awt.Color(0, 122, 51));
		startButton.setBorder(null);
		GridBagConstraints startConstraint = setConstraint(0, 0, 3, 1, 0, 0);
		buttonPanel.add(startButton, startConstraint);
		
		JButton userMenuButton = new JButton("Coming Soon!");
		GridBagConstraints userConstraint = setConstraint(0, 1, 3, 1, 0, 0);
		userMenuButton.setForeground(new java.awt.Color(0, 156, 222));
		userMenuButton.setBackground(new java.awt.Color(0, 122, 51));
		userMenuButton.setBorder(null);
		buttonPanel.add(userMenuButton, userConstraint);
		buttonPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.setForeground(new java.awt.Color(0, 156, 222));
		ExitButton.setBackground(new java.awt.Color(0, 122, 51));
		ExitButton.setBorder(null);
		GridBagConstraints exitConstraint = setConstraint(0, 2, 3, 1, 0, 0);
		buttonPanel.add(ExitButton, exitConstraint);
		
		mainMenuPanel.add(buttonPanel, buttonPanelConstraints);
		
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				displayGameSelect();
			}
		});
		
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	private void createGameSelectMenu() {
		gameSelectionPanel.setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("Select a Game");
		title.setForeground(new java.awt.Color(0, 156, 222));
		title.setVerticalTextPosition(JLabel.NORTH);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 72));
		GridBagConstraints titleConstraints = setConstraint(1, 0, 1, 1, 0, 5);
		gameSelectionPanel.add(title, titleConstraints);
		gameSelectionPanel.setBackground(new java.awt.Color(0, 122, 51));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints buttonPanelConstraints = setConstraint(1, 1, 1, 2, 0, 0);
		
		JButton solitaire = new JButton("Coming Soon!");
		solitaire.setForeground(new java.awt.Color(0, 156, 222));
		solitaire.setBackground(new java.awt.Color(0, 122, 51));
		solitaire.setBorder(null);
		GridBagConstraints sConstraint = setConstraint(0, 0, 1, 1, 0, 0);
		buttonPanel.add(solitaire, sConstraint);
		
		JButton war = new JButton("War");
		war.setForeground(new java.awt.Color(0, 156, 222));
		war.setBackground(new java.awt.Color(0, 122, 51));
		war.setBorder(null);
		GridBagConstraints warConstraint = setConstraint(1, 0, 1, 1, 0, 0);
		buttonPanel.add(war, warConstraint);
		
		JButton blackjack = new JButton("Coming Soon!");
		blackjack.setForeground(new java.awt.Color(0, 156, 222));
		blackjack.setBackground(new java.awt.Color(0, 122, 51));
		blackjack.setBorder(null);
		GridBagConstraints bjConstraint = setConstraint(2, 0, 1, 1, 0, 0);
		buttonPanel.add(blackjack, bjConstraint);
		
		JButton back = new JButton("Go Back");
		back.setForeground(new java.awt.Color(0, 156, 222));
		back.setBackground(new java.awt.Color(0, 122, 51));
		back.setBorder(null);
		GridBagConstraints backConstraint = setConstraint(1, 1, 1, 1, 0, 0);
		buttonPanel.add(back, backConstraint);
		
		buttonPanel.setBackground(new java.awt.Color(0, 122, 51));
		gameSelectionPanel.add(buttonPanel, buttonPanelConstraints);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayMainMenu();
			}
		});
		
		war.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchWar();
			}
		});
	}
	
	private GridBagConstraints setConstraint(int x, int y, int width, int height, int xpad, int ypad) {
		GridBagConstraints newConstraint = new GridBagConstraints();
		newConstraint.gridx = x;
		newConstraint.gridy = y;
		newConstraint.gridwidth = width;
		newConstraint.gridheight = height;
		newConstraint.ipadx = xpad;
		newConstraint.ipady = ypad;
		return newConstraint;
	}
	
	public void displayGameSelect() {
		windowPane.removeAll();
		windowPane.add(gameSelectionPanel);
		windowPane.revalidate();
		windowPane.repaint();
	}
	
	public void displayMainMenu() {
		windowPane.removeAll();
		windowPane.add(mainMenuPanel);
		windowPane.revalidate();
		windowPane.repaint();
	}
	
	public JPanel display() {
		return windowPane;
	}
	
	public void launchWar() {
		War warGame = new War();
		
		windowPane.add(warGame.display());
		windowPane.revalidate();
		windowPane.repaint();
		
		warGame.startGame();
		gameSelectionPanel.setVisible(true);
	}
	
}
