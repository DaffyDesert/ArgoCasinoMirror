import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		
		ButtonPanel = new JPanel();
		ButtonPanel.setBackground(new java.awt.Color(0, 122, 51));
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0));
		ButtonPanel.add(backButton);
		ButtonPanel.add(addButton);
		ButtonPanel.add(copyButton);
		ButtonPanel.add(deleteButton);
		ButtonPanel.add(selectButton);
		
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
						+ "this system. Please get permission from the Profile owner\nOr an administrator to continue with\ndeletion.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(dialogFrame, "User Deletion Cancelled.");
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
