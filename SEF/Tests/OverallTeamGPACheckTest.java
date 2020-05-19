//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class OverallTeamGPACheckTest {
//
//    @BeforeEach
//    void setUp() throws Exception {
//        Student s = new Student();
//
//        Student s1 = new Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 4, 3, 'm', '0');
//        s.allStudents.add(s1);
//
//        Student s2 = new Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 4, 3, 'm', '0');
//        s.allStudents.add(s2);
//
//        Student s3 = new Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 4, 3, 'm', '0');
//        s.allStudents.add(s3);
//
//        Student s4 = new Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 4, 3, 'm', '0');
//        s.allStudents.add(s4);
//
//        Student s5 = new Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 3.7, 3,'m', '0');
//        s.allStudents.add(s5);
//
//        Student s6 = new Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3.7, 3, 'm', '0');
//        s.allStudents.add(s6);
//
//        Student s7 = new Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 2, 3, 'm',  '0');
//        s.allStudents.add(s7);
//
//        Student s8 = new Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3.7, 3, 'm', '0');
//        s.allStudents.add(s8);
//
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//    }
//
//    @Test
//    void test() {
//
//        ProjectManager.overallTeamGPACheck();
//        double sumOfGPA = 0;
//        for (int i = 4; i < 8; i++) {
//            sumOfGPA = sumOfGPA + ProjectManager.getTeamStudent().get(i).getGPA();
//        }
//
//        int check = 0;
//        if (sumOfGPA / 4 <= 3.5)
//            check++;
//
//        assertEquals(1, check);
//    }
//
//    @Test
//    void test1() {
//
//        ProjectManager.overallTeamGPACheck();
//        double sumOfGPA = 0;
//        for (int i = 0; i < 4; i++) {
//            sumOfGPA = sumOfGPA + ProjectManager.getTeamStudent().get(i).getGPA();
//        }
//
//        int check = 0;
//        if (sumOfGPA / 4 <= 3.5)
//            check++;
//
//        assertEquals(1, check);
//    }
//
//}
