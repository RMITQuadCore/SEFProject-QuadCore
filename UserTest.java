import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

class UserTest {
	User u = new User();
	User u1 = new User("st100", "juilee", "kulkarni", "juileekul", "juileekul", "123rty", "rmit"); // already signed up
																									// user
	String[] login = { "juileekul", "123rty" }; // dummy values for login
	String[] signup = { "st102", "jk", "kul", "jkul", "jkul", "123rty", "monash" }; // dummy values for signup

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	@Order(1)
	public void testASignup() {
		// u.start();

		u.signup();
		String[] input = new String[6];
		input[0] = u.getId();
		input[1] = u.getFirstName();
		input[2] = u.getLastName();
		input[3] = u.getEmailID();
		input[4] = u.getUserName();
		input[5] = u.getPassword();

		List<String> list = Arrays.asList(input);
		int check = 0;
		for (int i = 0; i < input.length; i++) {
			if (list.contains(signup[i])) {
				check++;
			}
		}
		assertEquals(6, check);

	}

	@Test
	@Order(2)
	public void testBLogin() {

		u.details.add(u1);
		u.login();
		String[] logininput = new String[2];
		logininput[0] = u.getUserName();
		logininput[1] = u.getPassword();

		List<String> list = Arrays.asList(logininput);
		int check = 0;
		for (int i = 0; i < logininput.length; i++) {
			// System.out.println("inside for");
			if (list.contains(login[i])) {

				check++;
			}
			assertEquals(2, check);

		}
	}
}
