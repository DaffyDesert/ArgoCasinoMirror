import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Users {
	private ArrayList<User> userList;
	private User selectedUser;
	private int numUsers;
	private final int startingCash = 500;
	private final File saveFile = new File("saves/saves.txt");
	private final File saveDir = new File("saves");
	
	public Users() {
		userList = new ArrayList<User>();
		selectedUser = new User();
		numUsers = 0;
		loadAllData();
	}
	
	public void addUser(String name, boolean isAdmin) {
		userList.add(new User(name, startingCash, isAdmin, 0));
		numUsers++;
	}
	
	public void copyUser(int index) {
		User newUser = new User();
		newUser.setName(userList.get(index).getName());
		newUser.setCurrency(userList.get(index).getCurrency());
		newUser.setAdmin(userList.get(index).isAdmin());
		newUser.setHighScore(userList.get(index).getHighScore());
		
		userList.add(newUser);
		numUsers++;
	}
	
	public void deleteUser(int index) {
		userList.remove(index);
		numUsers--;
	}
	
	public void logIn(int index) {
		selectedUser = userList.get(index);
	}
	
	public User getUser(int index) {
		return userList.get(index);
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	
	public int getSelectedUserIndex() {
		for (int i = 0; i < numUsers; ++i) {
			if (userList.get(i) == selectedUser) {
				return i;
			}
		}
		return -1;
	}
	
	public int getNumUsers() {
		return numUsers;
	}
	
	public boolean loadAllData() {
		try {
			saveDir.mkdir();
			if (saveFile.createNewFile()) {
				FileWriter output = new FileWriter(saveFile);
				output.write('0');
				output.write("\n");
				output.close();
				numUsers = 0;
			}
			BufferedReader input = new BufferedReader(new FileReader(saveFile));
			numUsers = Integer.parseInt(input.readLine());
			for (int i = 0; i < numUsers; ++i) {
				String name = "";
				double currency;
				String admin = "";
				int score;
				

				name = input.readLine();
				currency = Double.parseDouble(input.readLine());
				admin = input.readLine();
				score = Integer.parseInt(input.readLine());
				input.readLine();
				
				if (admin.equals("Admin")) {
					userList.add(new User(name, currency, true, score));
				}
				else if (admin.equals("notAdmin")) {
					userList.add(new User(name, currency, false, score));
				}
				
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public boolean saveAllData() {
		try {
			saveDir.mkdir();
			saveFile.createNewFile();
			FileWriter output = new FileWriter(saveFile);
			output.write((numUsers + 48));
			output.write("\n");
			for (int i = 0; i < numUsers; ++i) {
				output.write(userList.get(i).toString());
				output.write("\n\n");
			}
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteAllData() {
		numUsers = 0;
		selectedUser = new User();
		userList.clear();
		
		if(!saveFile.delete()) {
			return false;
		}
		
		try {
			saveFile.createNewFile();
			FileWriter output = new FileWriter(saveFile);
			output.write((numUsers + 48));
			output.write("\n");
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
