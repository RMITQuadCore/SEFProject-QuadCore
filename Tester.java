
public class Tester {

	public static void main(String args[]) {
		User u1 = new User();
		Project p = new Project();
		Student s = new Student();
		// 4 students and 4 projects
		// So 3 unpopular projects will get discarded

		Project p1 = new Project("CL101", "PROJ201", "xyz1", "xyz1", "xyz1", null, 0);
		p.pr.add(p1);

		Project p2 = new Project("CL102", "PROJ202", "xyz2", "xyz2", "xyz2", null, 0);
		p.pr.add(p2);

		Project p3 = new Project("CL103", "PROJ203", "xyz3", "xyz3", "xyz3", null, 0);
		p.pr.add(p3);

		Project p4 = new Project("CL104", "PROJ204", "xyz4", "xyz4", "xyz4", null, 0);
		p.pr.add(p4);

		Student s1 = new Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 3, 3, 'f', null, null, null,
				null, null, '0');
		s.allStudents.add(s1);
		u1.details.add(s1);

		Student s2 = new Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 3, 3, 'f', null, null, null,
				null, null, '0');
		s.allStudents.add(s2);
		u1.details.add(s2);

		Student s3 = new Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 3, 3, 'f', null, null, null,
				null, null, '0');
		s.allStudents.add(s3);
		u1.details.add(s3);

		Student s4 = new Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 3, 3, 'f', null, null, null,
				null, null, '0');
		s.allStudents.add(s4);
		u1.details.add(s4);

		u1.start();
	}

}
