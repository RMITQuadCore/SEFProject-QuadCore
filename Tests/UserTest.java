//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class UserTest {
//    User u = new User();
//    User u1 = new Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc4", "abc1", 2.8, 1, 'm', '0');
//
//    String[] login = { "abc1", "abc4" }; // dummy values for login
//    String[] signup = { "jk", "kul", "rmit", "jkul", "jkul", "jkul" }; // dummy values for signup
//
//    @Before
//    public void setUp() throws Exception {
//        Project pr = new Project();
//
//        Project p1 = new Project("CL101", "PROJ201", "xyz1", "xyz1", null, 7);
//        pr.proj.add(p1);
//    }
//
//    @Test
//    public void signup() {
//        try {
//            u.signup();
//        } catch (IncorrectInputException e) {
//            // TODO Auto-generated catch block
//            System.err.println(e.getMessage());
//        }
//        String[] input = new String[6];
//       // input[0] = u.getId();
//        input[0] = u.getFirstName();
//        input[1] = u.getLastName();
//        input[2] = u.getOrg();
//        input[3] = u.getEmailID();
//        input[4] = u.getUserName();
//        input[5] = u.getPassword();
//
//        List<String> list = Arrays.asList(input);
//        int check = 0;
//        for (int i = 0; i < input.length; i++) {
//            if (list.contains(signup[i])) {
//                check++;
//            }
//        }
//        assertEquals(6, check);
//
//    }
//
//    @Test
//    public void login() {
//        u.details.add(u1);
//        try {
//            u.login();
//        } catch (IncorrectInputException e) {
//            // TODO Auto-generated catch block
//            System.err.println(e.getMessage());
//        }
//        String[] logininput = new String[2];
//        logininput[0] = u.getUserName();
//        logininput[1] = u.getPassword();
//
//        List<String> list = Arrays.asList(logininput);
//        int check = 0;
//        for (int i = 0; i < logininput.length; i++) {
//            // System.out.println("inside for");
//            if (list.contains(login[i])) {
//
//                check++;
//            }
//
//
//        }
//        assertEquals(2, check);
//    }
//}