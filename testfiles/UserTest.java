import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	User newUser1;
	User newUser2;
	
	@Test
	void testUser() {
		System.out.println("Creating blank User1");
		newUser1 = new User();
		
		System.out.println("Creating User 2: RoeMello");
		newUser2 = new User("RoeMello", 200, true, 0);
		
		assertEquals("NoName", newUser1.getName());
		assertEquals(500, newUser1.getCurrency());
		assertEquals(false, newUser1.isAdmin());
		assertEquals(0, newUser1.getHighScore());
		
		assertEquals("RoeMello", newUser2.getName());
		assertEquals(200, newUser2.getCurrency());
		assertEquals(true, newUser2.isAdmin());
		assertEquals(0, newUser2.getHighScore());
	}
	
	@Test
	void testString() {
		System.out.println("Creating blank User1");
		newUser1 = new User();
		
		System.out.println("Creating User 2: RoeMello");
		newUser2 = new User("RoeMello", 200, true, 0);
		
		assertEquals("NoName\n500.0\nnotAdmin\n0", newUser1.toString());
		assertEquals("RoeMello\n200.0\nAdmin\n0", newUser2.toString());
	}

}
