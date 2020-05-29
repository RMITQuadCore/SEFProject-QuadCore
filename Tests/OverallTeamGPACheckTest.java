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
//        model.Student s = new model.Student();
//
//        model.Student s1 = new model.Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 4, 3, 'm', '0');
//        s.allStudents.add(s1);
//
//        model.Student s2 = new model.Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 4, 3, 'm', '0');
//        s.allStudents.add(s2);
//
//        model.Student s3 = new model.Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 4, 3, 'm', '0');
//        s.allStudents.add(s3);
//
//        model.Student s4 = new model.Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 4, 3, 'm', '0');
//        s.allStudents.add(s4);
//
//        model.Student s5 = new model.Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 3.7, 3,'m', '0');
//        s.allStudents.add(s5);
//
//        model.Student s6 = new model.Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3.7, 3, 'm', '0');
//        s.allStudents.add(s6);
//
//        model.Student s7 = new model.Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 2, 3, 'm',  '0');
//        s.allStudents.add(s7);
//
//        model.Student s8 = new model.Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3.7, 3, 'm', '0');
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
//        model.ProjectManager.overallTeamGPACheck();
//        double sumOfGPA = 0;
//        for (int i = 4; i < 8; i++) {
//            sumOfGPA = sumOfGPA + model.ProjectManager.getTeamStudent().get(i).getGPA();
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
//        model.ProjectManager.overallTeamGPACheck();
//        double sumOfGPA = 0;
//        for (int i = 0; i < 4; i++) {
//            sumOfGPA = sumOfGPA + model.ProjectManager.getTeamStudent().get(i).getGPA();
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
