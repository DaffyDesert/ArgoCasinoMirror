import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	public void saveAllUserData() {
			FileOutputStream outFileStream;
		
		for (int i = 0; i < numUsers; i++) {
			
		}
	}
	
	public void loadAllUserData() {
		
	}
	
	public void deleteAllUserData() {
		
	}
}
