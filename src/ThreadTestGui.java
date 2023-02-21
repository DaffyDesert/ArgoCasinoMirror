import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadTestGui {
	public static void main(String args[]){
		JFrame frame = new JFrame("Stopwatch Thread Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300,300);
	    
	    Stopwatch sw = new Stopwatch();
	    Thread t1 = new Thread(sw);
	    t1.start();
	    JButton button = new JButton("Press Me!");
	    FlowLayout layout = new FlowLayout();
	    frame.getContentPane().setLayout(layout);
	    frame.getContentPane().add(sw.display());
	    frame.getContentPane().add(button);
	    frame.setVisible(true);
	    
	    ActionListener ButtonPress = new ActionListener() {
	    	private boolean pressed = false;
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		if (pressed == false) {
	    			JLabel newLabel = new JLabel("Button Pressed!");
	    			frame.getContentPane().add(newLabel);
	    			pressed = true;
	    		}
	    		else {
	    			Component[] getAllComponents = frame.getContentPane().getComponents();
	    			frame.getContentPane().remove(getAllComponents.length - 1);
	    			frame.getContentPane().revalidate();
	    			frame.getContentPane().validate();
	    			frame.getContentPane().repaint();
	    			pressed = false;
	    		}
	    	}	
	    };
	    
	    button.addActionListener(ButtonPress);
	    
	}
	
	class TestThread implements Runnable{
		public JPanel display() {
			JPanel buttonPanel = new JPanel();
			FlowLayout layout = new FlowLayout();
			
			buttonPanel.setLayout(layout);
			JButton button = new JButton("Press Me!");
		}
		public void run() {
			
		}
	}
}
