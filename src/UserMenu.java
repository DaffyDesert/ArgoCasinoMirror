import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class UserMenu {
	private JPanel MenuPane; //The whole menu panel
	private JPanel UserListObj; //The User list in the middle of the Menu, separate panel for cleanness
	private ArrayList<User> Users;
	private User selectedUser;
	private int numUsers;
	private final int startingCash = 500;
	
	public UserMenu() {
		MenuPane = new JPanel();
		UserListObj = new JPanel();
		Users = new ArrayList<User>();
		selectedUser = new User();
		numUsers = 0;
		
		loadAllUserData();
		createMenu();
	}
	
	public JPanel display() {
		return MenuPane;
	}
	
	public void createMenu() {
		
	}
	
	public void beginAddUserProcess() {
		
	}
	
	public void beginCopyUserProcess() {
		
	}
	
	public void beginDeleteUserProcess() {
		
	}
	
	public void addUser(String name, boolean isAdmin) {
		Users.add(new User(name, startingCash, isAdmin, 0));
		numUsers = numUsers + 1;
	}
	
	public void copyUser(int index) {
		User newUser = new User();
		newUser.setName(Users.get(index).getName());
		newUser.setCurrency(Users.get(index).getCurrency());
		newUser.setAdmin(Users.get(index).isAdmin());
		newUser.setHighScore(Users.get(index).getHighScore());
		
		Users.add(newUser);
		numUsers = numUsers + 1;
	}
	
	public void deleteUser(int index) {
		Users.remove(index);
		numUsers = numUsers - 1;
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	
	public void logIn(int index) {
		selectedUser = Users.get(index);
	}
	
	//File I/O simply is not working. Cannot get it to work the way I was before in intermediate
	//Also not able to figure it out with online tutorials. Will try again when brain is less fried.
	public void saveAllUserData() {
		PrintWriter out;
		File saveFile;
		File generalSave;
			
		generalSave = new File("./general.txt");
		
		try {
			generalSave.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			out = new PrintWriter(generalSave);
			out.println(numUsers);
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i = 0; i < numUsers; i++) {
			saveFile = new File("./user" + i + ".txt");
			
			try {
				saveFile.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				out = new PrintWriter(saveFile);
				out.println(Users.get(i).toString());
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public void loadAllUserData() {
		User user1 = new User("DaffyDesert", 100, true, 0);
		User user2 = new User("RoeMello", 500, false, 0);
		User user3 = new User();
		Users.add(user1);
		Users.add(user2);
		Users.add(user3);
		numUsers = 3;
	}
	
	public void deleteAllUserData() {
		
	}

	public ArrayList<User> getUsers() {
		return Users;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
}
