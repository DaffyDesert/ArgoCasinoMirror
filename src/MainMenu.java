import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenu {
	JPanel currentPanel;
	JPanel mainMenuPanel;
	JPanel gameSelectionPanel;
	JPanel gamePanel;
	
	public MainMenu() {
		createMainMenu();
		createGameSelectMenu();
		swapTo(mainMenuPanel);
	}
	
	private void createMainMenu() {
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new GridBagLayout());
		
		JLabel title = new JLabel("Argo Casino");
		title.setVerticalTextPosition(JLabel.NORTH);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 72));
		GridBagConstraints titleConstraints = setConstraint(1, 0, 1, 1, 0, 5);
		mainMenuPanel.add(title, titleConstraints);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints buttonPanelConstraints = setConstraint(1, 1, 1, 2, 0, 0);
		
		JButton startButton = new JButton("Start Game");
		GridBagConstraints startConstraint = setConstraint(0, 0, 3, 1, 0, 0);
		buttonPanel.add(startButton, startConstraint);
		
		JButton userMenuButton = new JButton("Coming Soon!");
		GridBagConstraints userConstraint = setConstraint(0, 1, 3, 1, 0, 0);
		buttonPanel.add(userMenuButton, userConstraint);
		
		JButton ExitButton = new JButton("Exit");
		GridBagConstraints exitConstraint = setConstraint(0, 2, 3, 1, 0, 0);
		buttonPanel.add(ExitButton, exitConstraint);
		
		mainMenuPanel.add(buttonPanel, buttonPanelConstraints);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				swapTo(gameSelectionPanel);
			}
		});
		
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	
	private void createGameSelectMenu() {
		
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
	
	private void swapTo(JPanel newPanel) {
		currentPanel = newPanel;
	}
	
	public JPanel display() {
		return currentPanel;
	}
}
