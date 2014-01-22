import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;
import controllers.*;

public class BasicTest extends UnitTest {

    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();

        // Retrieve the user with bob username
        User bob = User.find("byEmail", "bob@gmail.com").first();

        // Test 
        assertNotNull(bob);
        assertEquals("Bob", bob.username);
    }
    
	@Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();

        // Test 
        assertNull(Application.logined("bob@gmail.com", "secret"));
	}
}