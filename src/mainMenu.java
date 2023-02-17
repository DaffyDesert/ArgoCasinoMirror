import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Trivia");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		
		JPanel panel = new JPanel(new GridLayout(3,3));
		frame.add(panel);
		
		JLabel label = new JLabel("Trivia Game");
		panel.add(label);
		
		JButton button = new JButton("Start game");
		button.setPreferredSize(new Dimension(40,40));
		panel.add(button);
		
		frame.setVisible(true);
	}

}
