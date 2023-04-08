import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserMenu {
	private JPanel MenuPane; //The whole menu panel
	private JPanel UserListObj; //The User list in the middle of the Menu, separate panel for cleanness
	private Users users;
	private JFrame dialogFrame;
	
	public UserMenu() {
		MenuPane = new JPanel();
		UserListObj = new JPanel();
		users = new Users();
	}
	
	public void createMenu() {
		JList<String> userList;
		JScrollPane scroll;
		JPanel ButtonPanel;
		JButton addButton;
		JButton copyButton;
		JButton deleteButton;
		JButton selectButton;
		JButton backButton;
		JButton manageButton;
		JButton clearAllButton;
		
		UserListObj.setVisible(true);
		UserListObj.setBounds(0, 0, 900, 510);
		UserListObj.setPreferredSize(new Dimension(900, 510));
		UserListObj.setBackground(new java.awt.Color(0, 122, 51));
		
		addButton = new JButton("Add New User");
		addButton.setForeground(new java.awt.Color(0, 156, 222));
		addButton.setBackground(new java.awt.Color(0, 122, 51));
		addButton.setBorder(null);
		
		copyButton = new JButton("Copy Selected User");
		copyButton.setForeground(new java.awt.Color(0, 156, 222));
		copyButton.setBackground(new java.awt.Color(0, 122, 51));
		copyButton.setBorder(null);
		
		deleteButton = new JButton("Delete Selected User");
		deleteButton.setForeground(new java.awt.Color(0, 156, 222));
		deleteButton.setBackground(new java.awt.Color(0, 122, 51));
		deleteButton.setBorder(null);
		
		backButton = new JButton("Go Back");
		backButton.setForeground(new java.awt.Color(0, 156, 222));
		backButton.setBackground(new java.awt.Color(0, 122, 51));
		backButton.setBorder(null);
		
		selectButton = new JButton("Select User");
		selectButton.setForeground(new java.awt.Color(0, 156, 222));
		selectButton.setBackground(new java.awt.Color(0, 122, 51));
		selectButton.setBorder(null);
		
		manageButton = new JButton("View Selected User");
		manageButton.setForeground(new java.awt.Color(0, 156, 222));
		manageButton.setBackground(new java.awt.Color(0, 122, 51));
		manageButton.setBorder(null);
		
		clearAllButton = new JButton("Clear All Data");
		clearAllButton.setForeground(new java.awt.Color(0, 156, 222));
		clearAllButton.setBackground(new java.awt.Color(0, 122, 51));
		clearAllButton.setBorder(null);
		
		ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new java.awt.Color(0, 122, 51));
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));
		ButtonPanel.add(backButton);
		ButtonPanel.add(addButton);
		ButtonPanel.add(copyButton);
		ButtonPanel.add(deleteButton);
		ButtonPanel.add(selectButton);
		ButtonPanel.add(manageButton);
		ButtonPanel.add(clearAllButton);
		
		MenuPane.setBounds(0, 0, 1270, 720);
		MenuPane.setLayout(new BorderLayout());
		MenuPane.setBackground(new java.awt.Color(0, 122, 51));
		
		MenuPane.add(UserListObj, BorderLayout.CENTER);
		MenuPane.add(ButtonPanel, BorderLayout.PAGE_END);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserProcess();
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMenu();
			}
		});
		
		clearAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllData();
			}
		});
		
		if (!(users.getNumUsers() == 0)) {
			String[] data = new String[users.getNumUsers()];
			
			for (int i = 0; i < users.getNumUsers(); ++i) {
				data[i] = (i + 1) + ". " +users.getUser(i).getName();
			}
			userList = new JList<String>(data);
			userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			userList.setLayoutOrientation(JList.VERTICAL);
			userList.setVisibleRowCount(3);
			userList.setPreferredSize(new Dimension(900, 480));
			userList.setVisible(true);
			
			scroll = new JScrollPane(userList);
			scroll.setPreferredSize(new Dimension(900, 480));
			scroll.setBackground(new java.awt.Color(181, 181, 181));
			scroll.setVisible(true);
			
			UserListObj.add(userList);
			
			userList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting() == false) {
						
						if (userList.getSelectedIndex() == -1) {
							copyButton.setEnabled(false);
							deleteButton.setEnabled(false);
							selectButton.setEnabled(false);
						}
						else {
							copyButton.setEnabled(true);
							deleteButton.setEnabled(true);
							selectButton.setEnabled(true);
						}
					}
					
				}
			});
			
			copyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = userList.getSelectedValue();
					int index = Character.getNumericValue(selected.charAt(0));
					copyUserProcess(index - 1);
				}
			});
			
			selectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = userList.getSelectedValue();
					int index = Character.getNumericValue(selected.charAt(0));
					logIn(index - 1);
				}
			});
			
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = userList.getSelectedValue();
					int index = Character.getNumericValue(selected.charAt(0));
					deleteUserProcess(index - 1);
				}
			});
			
			manageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String selected = userList.getSelectedValue();
					int index = Character.getNumericValue(selected.charAt(0));
					manageUser(index - 1);
				}
			});
		}
		
	}	
	
	public JPanel display() {
		MenuPane.removeAll();
		UserListObj.removeAll();
		createMenu();
		MenuPane.revalidate();
		MenuPane.repaint();
		UserListObj.revalidate();
		UserListObj.repaint();
		return MenuPane;
	}
	
	public User getActiveUser() {
		return users.getSelectedUser();
	}
	
	public void logIn(int index) {
		users.logIn(index);
		JOptionPane.showMessageDialog(dialogFrame, "Logged in as " + users.getSelectedUser().getName());
	}
	
	public void addUserProcess() {
		dialogFrame = new JFrame();
		String name = (String)JOptionPane.showInputDialog(dialogFrame, "What should the name be of this new user?", "User Creation", 
				JOptionPane.PLAIN_MESSAGE);
		if (users.getNumUsers() != 0) users.addUser(name, false);
		else users.addUser(name, true);
		
		users.saveAllData();
		display();
	}
	
	public void copyUserProcess(int index) {
		dialogFrame = new JFrame();
		int confirm = JOptionPane.showConfirmDialog(dialogFrame, "Are you sure you'd like to copy the selected user?", "Are You Sure?",
				JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			users.copyUser(index);
			users.saveAllData();
			display();
		}
		else {
			JOptionPane.showMessageDialog(dialogFrame, "User Copying Cancelled.");
		}
	}
	
	public void deleteUserProcess(int index) {
		dialogFrame = new JFrame();
		int confirm = JOptionPane.showConfirmDialog(dialogFrame, "Are you sure you'd like to delete the selected user?", "Are You Sure?",
				JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			if ((users.getSelectedUser().isAdmin()) || (users.getSelectedUserIndex() == index)) {
				users.deleteUser(index);
				users.saveAllData();
				display();
			}
			else {
				JOptionPane.showMessageDialog(dialogFrame, "You are not the owner of this User Profile\nOr you are not an administrator on\n"
						+ "this system. Please get permission from the Profile owner\nOr an administrator to continue with\ndeletion.", 
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(dialogFrame, "User Deletion Cancelled.");
		}
	}
	
	public void manageUser(int index) {
		dialogFrame = new JFrame();
		Object[] options = {"Manage Admin Privileges", "Change Username", "Go Back"};
		int answer = JOptionPane.showOptionDialog(dialogFrame, users.getUser(index).statDisplay() + "\nWhat would you like to do with this user?",
				"Manage User", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
		
		if (answer == JOptionPane.YES_OPTION) { //Manage Admin Privileges
			if (users.getSelectedUser().isAdmin()) {
				Object[] options2 = {"Promote to Admin", "Revoke Admin"};
				int answer2 = JOptionPane.showOptionDialog(dialogFrame, users.getUser(index).statDisplay() + "\nWhat would you like to do with this user?", "Manage Administrative Privileges",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, options[2]);
				if(answer2 == JOptionPane.YES_OPTION) {
					if (users.getUser(index).isAdmin()) {
						JOptionPane.showMessageDialog(dialogFrame, "User is already an administrator");
					} else {
						users.getUser(index).setAdmin(true);
						users.saveAllData();
						display();
						JOptionPane.showMessageDialog(dialogFrame, "User promoted to administrator");
					}
				}
				else if (answer2 == JOptionPane.NO_OPTION) {
					if(users.getUser(index).isAdmin()) {
						users.getUser(index).setAdmin(false);
						users.saveAllData();
						display();
						JOptionPane.showMessageDialog(dialogFrame, "User administrator privileges revoked");
					}
					else {
						JOptionPane.showMessageDialog(dialogFrame, "This user is not an administrator");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(dialogFrame, "You are not an administrator on\n"
						+ "this system. Please get permission from an administrator\nto continue with User Management.", 
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		else if (answer == JOptionPane.NO_OPTION){ //Change Username
			if ((users.getSelectedUser().isAdmin()) || (users.getSelectedUserIndex() == index)) {
				String newUsername = JOptionPane.showInputDialog(dialogFrame, "Choose what your new username will be.", "Change Username", JOptionPane.PLAIN_MESSAGE);
				users.getUser(index).setName(newUsername);
				users.saveAllData();
				display();
				JOptionPane.showMessageDialog(dialogFrame, "Username Successfully Changed");
			}
			else {
				JOptionPane.showMessageDialog(dialogFrame, "You are not an administrator on\nthis system nor the owner of this user profile.\n"
						+ "Please get permission from the user or an administrator to continue.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void clearAllData() {
		dialogFrame = new JFrame();
		int confirm1 = JOptionPane.showConfirmDialog(dialogFrame, "Are you sure you'd like to do this? This will delete ALL users on the system.", "HOLD ON", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (confirm1 == JOptionPane.YES_OPTION) {
			int confirm2 = JOptionPane.showConfirmDialog(dialogFrame, "Are you REALLY sure you'd like to do this?", "You're serious?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (confirm2 == JOptionPane.YES_OPTION) {
				int confirm3 = JOptionPane.showConfirmDialog(dialogFrame, "Are you positive?", "Last Chance...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirm3 == JOptionPane.YES_OPTION) {
					users.deleteAllData();
					users.saveAllData();
					display();
					JOptionPane.showMessageDialog(dialogFrame, "All user data erased.");
				}
				else {
					JOptionPane.showMessageDialog(dialogFrame, "Deletion Canceled");
				}
			}
			else {
				JOptionPane.showMessageDialog(dialogFrame, "Deletion Canceled");
			}
		}
		else {
			JOptionPane.showMessageDialog(dialogFrame, "Deletion Canceled");
		}
	}
	
	public void closeMenu() {
		MenuPane.removeAll();
		MenuPane.revalidate();
		MenuPane.repaint();
		UserListObj.removeAll();
		UserListObj.revalidate();
		UserListObj.repaint();
	}
}
