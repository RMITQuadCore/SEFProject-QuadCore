import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest {
    Project p = new Project();
    Student s = new Student();

    @Before
    public void setUp() throws Exception {
        Project p1 = new Project("CL101", "PROJ201", "xyz1", "xyz1", "xyz1", null, 7);

        p.pr.add(p1);

        Project p2 = new Project("CL102", "PROJ202", "xyz2", "xyz2", "xyz2", null, 9);
        p.pr.add(p2);

        Project p3 = new Project("CL103", "PROJ203", "xyz3", "xyz3", "xyz3", null, 1);
        p.pr.add(p3);

        Project p4 = new Project("CL104", "PROJ204", "xyz4", "xyz4", "xyz4", null, 5);
        p.pr.add(p4);

        Student s1 = new Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 3, 3, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s1);

        Student s2 = new Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 3, 3, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s2);

        Student s3 = new Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 3, 3, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s3);

        Student s4 = new Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 3, 3, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s4);

        Student s5 = new Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 3, 3, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s5);

        Student s6 = new Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3, 3, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s6);

        Student s7 = new Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 3, 3, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s7);

        Student s8 = new Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3, 3, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s8);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    void test() {
        int check = 0;

        try {
            if (p.discardUnpopularProjects())
                check++;
        } catch (ProjectMismatchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(1, check);
    }
}