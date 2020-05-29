//package src;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProjectManagerTest {
//  model.ProjectManager pm =new model.ProjectManager();
//  model.Project p=new model.Project();
//    model.Student s = new model.Student();
//
//    @BeforeEach
//    void setUp() throws Exception {
//
//        model.Project p1 = new model.Project("CL101", "PROJ201", "xyz1","xyz1", null, 7);
//
//        p.proj.add(p1);
//
//       model.Project p2 = new model.Project("CL102", "PROJ202",  "xyz2", "xyz2", null, 9);
//        p.proj.add(p2);
//
//        model.Project p3 = new model.Project("CL103", "PROJ203", "xyz3", "xyz3", null, 1);
//        p.proj.add(p3);
//
//        model.Project p4 = new model.Project("CL104", "PROJ204",  "xyz4", "xyz4", null, 5);
//        p.proj.add(p4);
//
//        model.Student s1 = new model.Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 3, 3, 'f', '0');
//        s.allStudents.add(s1);
//
//        model.Student s2 = new model.Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 3, 3, 'm',  '0');
//        s.allStudents.add(s2);
//
//        model.Student s3 = new model.Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 3, 3, 'm',  '0');
//        s.allStudents.add(s3);
//
//        model.Student s4 = new model.Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 3, 3, 'f',  '0');
//        s.allStudents.add(s4);
//
//        model.Student s5 = new model.Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 3, 3, 'm',  '0');
//        s.allStudents.add(s5);
//
//        model.Student s6 = new model.Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3, 3, 'f',  '0');
//        s.allStudents.add(s6);
//
//        model.Student s7 = new model.Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 3, 3, 'm', '0');
//        s.allStudents.add(s7);
//
//        model.Student s8 = new model.Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3, 3, 'f', '0');
//        s.allStudents.add(s8);
//
//    }
//
//    @Test
//    @org.junit.jupiter.api.Test
//    void discardUnpopularProjects() {
//        int check = 0;
//
//        try {
//            if (pm.discardUnpopularProjects())
//                check++;
//        } catch (model.ProjectMismatchException e) {
//            // Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        assertEquals(1, check);
//    }
//}