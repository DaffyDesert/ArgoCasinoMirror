import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LaunchData {
	private boolean hasLaunchedBefore;
	private boolean hasUsersOnFile;
	private File dataFolder = new File("saves");
	private File dataFile = new File("saves/data.txt");
	
	public LaunchData() {
		readData();
	}
	
	public void readData() {
		try {
			dataFolder.mkdir();
			if (dataFile.exists()) {
				BufferedReader input = new BufferedReader(new FileReader(dataFile));
				String ip = input.readLine();
				if (ip.equals("false")) {
					hasLaunchedBefore = false;
				} else if (ip.equals("true")) {
					hasLaunchedBefore = true;
				}
				
				ip = input.readLine();
				if (ip.equals("false")) {
					hasUsersOnFile = false;
				} else if (ip.equals("true")) {
					hasUsersOnFile = true;
				}
				
				input.close();
			}
			else {
				dataFile.createNewFile();
				writeDefaultData();
				readData();
			}
		} catch (IOException e) {
			
		}
	}
	
	public void writeDefaultData() {
		try {
			FileWriter output = new FileWriter(dataFile);
			output.write("false\n");
			output.write("false\n");
			output.close();
		} catch (IOException e) {
			
		}
	}
	
	public void writeData() {
		try {
			FileWriter output = new FileWriter(dataFile);
			if (hasLaunchedBefore) output.write("true\n");
			else output.write("false\n");
			
			if (hasUsersOnFile) output.write("true\n");
			else output.write("false\n");
			
			output.close();
		} catch (IOException e) {
			
		}
	}
	
	public void setHasUsers(boolean bool) {
		hasUsersOnFile = bool;
	}
	
	public void displayLaunchMessages() {
		if (!hasLaunchedBefore) {
			JOptionPane.showMessageDialog(new JFrame(), "Hello and Welcome to Argo Casino!\n\n"
					+ "In this game, you can play a variety of 3 different card games. To see the games available, select \"Start Game\" on the Main Menu.\n"
					+ "If you need any help or tips, select \"Help\" in the top left.\n"
					+ "You can use the \"Menu\" option in the top left to navigate the menus if you get lost.", "Welcome!", JOptionPane.PLAIN_MESSAGE);
			hasLaunchedBefore = true;
			writeData();
		}
		if(!hasUsersOnFile) {
			JOptionPane.showMessageDialog(new JFrame(), "You have no profiles saved!\n\n"
					+ "To save your game data, go to the \"User Menu\" to create a profile.\n"
					+ "This profile will save your game data as you play, so long as you log in before playing.", "No Profiles", JOptionPane.ERROR_MESSAGE);
		}
	}
}
