import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ProjectTest {
	Project pr = new Project();
	String[] testPr = { "Making application", "Database application", "DBA", "Oracle", "MySql" };

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}

	@Test
	void test() {
		System.out.println("\n The Project details entered are : ");
		for (int j = 0; j < 5; j++) {
			System.out.println((j + 1) + ". " + testPr[j]);
		}
		System.out.println("----------------------------------");
		String[] input = new String[3];
		String[] inputFramework = new String[2];
		pr.createProject();
		input[0] = pr.getProjectTitle();
		input[1] = pr.getProjectDetails();
		input[2] = pr.getRole();
		inputFramework = pr.getFramework();
		int check = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				if (input[j].equalsIgnoreCase(testPr[i])) {
					check++;
				}
			}
			for (int k = 0; k < 2; k++) {
				if (inputFramework[k].equalsIgnoreCase(testPr[i])) {
					check++;
				}
			}
		}
		assertEquals(5, check);
	}

}
