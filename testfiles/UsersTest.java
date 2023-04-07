import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class UsersTest {
	
	Users users;
	
	@Test
	void testAConstructor() {
		users = new Users();
		assertEquals(0, users.getNumUsers());
		assertEquals("NoName", users.getSelectedUser().getName());
		assertEquals(-1, users.getSelectedUserIndex());
	}
	
	@Test
	void testBAddUser() {
		users = new Users();
		users.addUser("DaffyDesert", true);
		users.addUser("Danny", false);
		users.addUser("RoeMello", false);
		
		assertEquals(3, users.getNumUsers());
		users.logIn(0);
		assertEquals("DaffyDesert", users.getSelectedUser().getName());
		assertEquals(0, users.getSelectedUserIndex());
		users.logIn(1);
		assertEquals("Danny", users.getSelectedUser().getName());
		assertEquals(1, users.getSelectedUserIndex());
		users.logIn(2);
		assertEquals("RoeMello", users.getSelectedUser().getName());
		assertEquals(2, users.getSelectedUserIndex());
	}
	
	@Test
	void testCCopyUser() {
		users = new Users();
		users.addUser("DaffyDesert", true);
		users.addUser("Danny", false);
		users.addUser("RoeMello", false);
		users.copyUser(1);
		
		assertEquals(4, users.getNumUsers());
		users.logIn(0);
		assertEquals("DaffyDesert", users.getSelectedUser().getName());
		assertEquals(0, users.getSelectedUserIndex());
		users.logIn(1);
		assertEquals("Danny", users.getSelectedUser().getName());
		assertEquals(1, users.getSelectedUserIndex());
		users.logIn(2);
		assertEquals("RoeMello", users.getSelectedUser().getName());
		assertEquals(2, users.getSelectedUserIndex());
		users.logIn(3);
		assertEquals("Danny", users.getSelectedUser().getName());
		assertEquals(3, users.getSelectedUserIndex());
	}
	
	@Test
	void testDDeleteUser() {
		users = new Users();
		users.addUser("DaffyDesert", true);
		users.addUser("Danny", false);
		users.addUser("RoeMello", false);
		users.deleteUser(2);
		
		assertEquals(2, users.getNumUsers());
		users.logIn(0);
		assertEquals("DaffyDesert", users.getSelectedUser().getName());
		assertEquals(0, users.getSelectedUserIndex());
		users.logIn(1);
		assertEquals("Danny", users.getSelectedUser().getName());
		assertEquals(1, users.getSelectedUserIndex());
	}
	
	@Test
	void testESaveData() {
		users = new Users();
		users.addUser("DaffyDesert", true);
		users.addUser("Danny", false);
		users.addUser("RoeMello", false);
		
		assertTrue(users.saveAllData());
	}
	
	@Test
	void testFLoadData() {
		users = new Users();
		
		assertTrue(users.loadAllData());
		
		assertEquals(3, users.getNumUsers());
		users.logIn(0);
		assertEquals("DaffyDesert", users.getSelectedUser().getName());
		assertEquals(0, users.getSelectedUserIndex());
		users.logIn(1);
		assertEquals("Danny", users.getSelectedUser().getName());
		assertEquals(1, users.getSelectedUserIndex());
		users.logIn(2);
		assertEquals("RoeMello", users.getSelectedUser().getName());
		assertEquals(2, users.getSelectedUserIndex());
	}
	
	@Test
	void testGClearData() {
		users = new Users();
		users.addUser("DaffyDesert", true);
		users.addUser("Danny", false);
		users.addUser("RoeMello", false);
		
		assertTrue(users.deleteAllData());
		
		assertEquals(0, users.getNumUsers());
		users.loadAllData();
		assertEquals(0, users.getNumUsers());
	}

}
