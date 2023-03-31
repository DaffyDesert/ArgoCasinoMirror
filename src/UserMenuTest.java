import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserMenuTest {
	
	UserMenu menu;
	
	@Test
	void testConstructor() {
		menu = new UserMenu();
		assertEquals(3, menu.getNumUsers());
		assertEquals("DaffyDesert 100.0 admin 0", menu.getUsers().get(0).toString());
		assertEquals("RoeMello 500.0 notadmin 0", menu.getUsers().get(1).toString());
		assertEquals("NoName 500.0 notadmin 0", menu.getUsers().get(2).toString());
	}
	
	@Test
	void testLogIn() {
		menu = new UserMenu();
		menu.logIn(0);
		assertEquals("DaffyDesert 100.0 admin 0", menu.getSelectedUser().toString());
		
		menu.logIn(1);
		assertEquals("RoeMello 500.0 notadmin 0", menu.getSelectedUser().toString());
	}
	
	@Test
	void testAdding() {
		menu = new UserMenu();
		menu.addUser("Big Man", false);
		assertEquals(4, menu.getNumUsers());
		assertEquals("Big Man 500.0 notadmin 0", menu.getUsers().get(3).toString());
	}
	
	@Test
	void testCopying() {
		menu = new UserMenu();
		menu.copyUser(1);
		assertEquals(4, menu.getNumUsers());
		assertEquals("RoeMello 500.0 notadmin 0", menu.getUsers().get(3).toString());
		assertEquals("RoeMello 500.0 notadmin 0", menu.getUsers().get(1).toString());
	}
	
	@Test
	void testDeleting() {
		menu = new UserMenu();
		menu.deleteUser(1);
		assertEquals(2, menu.getNumUsers());
	}
	
	@Test
	void testSaving() {
		menu = new UserMenu();
		menu.saveAllUserData();
	}
}
