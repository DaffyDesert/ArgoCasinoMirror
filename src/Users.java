import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class representing the collection of all users in the Argo Casino game.
 * @author Alexander DeAngelis
 * @contributor Daniel Miller
 *
 */

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
	
	public void addUser(String name, String password, boolean isAdmin) {
		LocalTime time = LocalTime.of(0, 0, 0);
		userList.add(new User(name, startingCash, isAdmin, 0, time, 0));
		numUsers++;
		
		try {
			String encryptedPw = generateHash(password);
			
			saveDir.mkdir();
			File userSave = new File(userList.get(numUsers - 1).getSaveFile());
			userSave.createNewFile();
			
			FileWriter output = new FileWriter(userSave);
			output.write(userList.get(numUsers - 1).getName());
			output.write("\n");
			output.write(encryptedPw);
			output.write("\n");
			
			output.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void copyUser(int index) {
		User newUser = new User();
		newUser.setName(userList.get(index).getName());
		newUser.setCurrency(userList.get(index).getCurrency());
		newUser.setAdmin(userList.get(index).isAdmin());
		newUser.setBlackjackWinCount(userList.get(index).getBlackjackWinCount());
		newUser.setWarBestTime(userList.get(index).getWarBestTime());
		newUser.setSolitaireHighScore(userList.get(index).getSolitaireHighScore());
		
		userList.add(newUser);
		numUsers++;
	}
	
	public void deleteUser(int index) {
		File userSave = new File(userList.get(index).getSaveFile());
		userSave.delete();
		
		userList.remove(index);
		numUsers--;
	}
	
	public void renameUser(int index, String username) {
		try {
			File userSave = new File(userList.get(index).getSaveFile());
			BufferedReader input = new BufferedReader(new FileReader(userSave));
			input.readLine();
			String password = input.readLine();
			input.close();
			userSave.delete();
			
			userList.get(index).setName(username);
			userSave = new File(userList.get(index).getSaveFile());
			userSave.createNewFile();
			
			FileWriter output = new FileWriter(userSave);
			output.write(userList.get(index).getName());
			output.write("\n");
			output.write(password);
			output.write("\n");
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean logIn(String password, int index) {
		try {
			String encryptedPw = generateHash(password);
			
			File userSave = new File(userList.get(index).getSaveFile());
			BufferedReader input = new BufferedReader(new FileReader(userSave));
			
			input.readLine();
			
			String storedPw = input.readLine();
			
			if (!encryptedPw.equals(storedPw)) {
				input.close();
				return false;
			}
			else {
				selectedUser = userList.get(index);
				input.close();
				return true;
			}
			
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
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
				int bjwin;
				LocalTime warTime;
				int sScore;

				name = input.readLine();
				currency = Double.parseDouble(input.readLine());
				admin = input.readLine();
				bjwin = Integer.parseInt(input.readLine());
				warTime = LocalTime.parse(input.readLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
				sScore = Integer.parseInt(input.readLine());
				input.readLine();
				
				if (admin.equals("Admin")) {
					userList.add(new User(name, currency, true, bjwin, warTime, sScore));
				}
				else if (admin.equals("notAdmin")) {
					userList.add(new User(name, currency, false, bjwin, warTime, sScore));
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
		for (int i = 0; i < numUsers; ++i) {
			File userFile = new File(userList.get(i).getSaveFile());
			userFile.delete();
		}
		
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
	
	/**
	 * The method that generates an encrypted password from user-input plain text.
	 * @param pwrdClear The plain-text user input password
	 * @return An encrypted password string
	 * @throws NoSuchAlgorithmException
	 * @author Daniel Miller
	 */
	private String generateHash(String pwrdClear) throws NoSuchAlgorithmException {
		String hashString;
		byte[] hashBytes;
		BigInteger hashNumbers;
		StringBuilder hashHex;
		MessageDigest hashMD = MessageDigest.getInstance("SHA-256");
        
		hashBytes = hashMD.digest(pwrdClear.getBytes(StandardCharsets.UTF_8)); 	//turn clear password into SHA-256 byte array
		hashNumbers = new BigInteger(1, hashBytes); 							//turn byte array into signed int number 
		hashHex  = new StringBuilder(hashNumbers.toString(16)); 				// turn signed int number into hex value
		while(hashHex.length() < 64) 											//pad hex value with leading zeros
			hashHex.insert(0, '0');
		hashString = hashHex.toString();
		
		return hashString;
	}
	
}
