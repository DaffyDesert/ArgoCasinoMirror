import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("Trivia");
		JButton button = new JButton("Start game");
		JFrame frame = new JFrame("Argo Trivia");
		JPanel panel= new JPanel(new GridLayout(4,4,4,4));
		
		button.setPreferredSize(new Dimension(40,40));
		panel.add(label);
		panel.add(button);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setPreferredSize(new Dimension(300,400));
		frame.pack();
		frame.setVisible(true);
	}

}
