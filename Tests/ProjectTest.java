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
        String[] input = new String[2];
        pr.createProject("CL001");
        input[0] = pr.getProjectTitle();
        input[1] = pr.getProjectDetails();

        pr.getRole();
        int check = 0;
        int i = 0;
        for (i = 0; i < 2; i++) {
            if (input[i].equalsIgnoreCase(testPr[i])) {
                check++;
            }
        }
        for (Role r : pr.getRole()) {
            if (r.getRoleName().equalsIgnoreCase(testPr[i])) {
                check++;
            }
            i++;
            for (String f : r.getFrameworks()) {
                if (f.equalsIgnoreCase(testPr[i])) {
                    check++;
                }
                i++;
            }

        }

        assertEquals(5, check);
    }

}
