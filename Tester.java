import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String args[]) {
        User u1 = new User();
        Project p = new Project();
        Student s = new Student();
        ArrayList<Student> tempStudent = new ArrayList<Student>();
        ArrayList<Student> teamStudent = new ArrayList<Student>();
        ArrayList<Student> projectGroupStudent = new ArrayList<Student>();

        Project p1 = new Project("CL101", "PROJ201", "xyz1", "xyz1", "xyz1", null, 7);
        p.pr.add(p1);

        Project p2 = new Project("CL102", "PROJ202", "xyz2", "xyz2", "xyz2", null, 3);
        p.pr.add(p2);

        Project p3 = new Project("CL103", "PROJ203", "xyz3", "xyz3", "xyz3", null, 5);
        p.pr.add(p3);

        Project p4 = new Project("CL104", "PROJ204", "xyz4", "xyz4", "xyz4", null, 8);
        p.pr.add(p4);

        Student s1 = new Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 2.8, 1, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s1);
        u1.details.add(s1);

        Student s2 = new Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 3.8, 1, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s2);
        u1.details.add(s2);

        Student s3 = new Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 2, 4, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s3);
        u1.details.add(s3);

        Student s4 = new Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 3, 1, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s4);
        u1.details.add(s4);

        Student s5 = new Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 4, 6, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s5);
        u1.details.add(s5);

        Student s6 = new Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3.2, 5, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s6);
        u1.details.add(s6);

        Student s7 = new Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 3.5, 6, 'm', null, null, null,
                null, null, '0');
        s.allStudents.add(s7);
        u1.details.add(s7);

        Student s8 = new Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3, 3, 'f', null, null, null,
                null, null, '0');
        s.allStudents.add(s8);
        u1.details.add(s8);
//
//		Student s9 = new Student("ST109", "abc9", "abc9", "abc9", "abc9", "abc9", "abc9", 3.2, 2, 'm', null, null, null,
//				null, null, '0');
//		s.allStudents.add(s9);
//		u1.details.add(s9);
//
//		Student s10 = new Student("ST110", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'f', null, null,
//				null, null, null, '0');
//		s.allStudents.add(s10);
//		u1.details.add(s10);
//
//		Student s11 = new Student("ST111", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'm', null, null,
//				null, null, null, '0');
//		s.allStudents.add(s11);
//		u1.details.add(s11);

        tempStudent.addAll(s.allStudents);
        u1.start();

        int numOfStudents = Student.allStudents.size();
        int numOfTempTeams = numOfStudents / 4;
        int numOfextraStudents = numOfStudents % 4; // extra students
        int i, j = 0, femaleCount = 0, experienceCounter = 0;
        Integer numOfStudentsInATeam[];

        if (numOfextraStudents == 0) {
            numOfStudentsInATeam = new Integer[numOfTempTeams];
        } else {
            numOfStudentsInATeam = new Integer[numOfTempTeams + 1];
        }

        if (numOfextraStudents == 3) {

            for (i = 0; i < numOfTempTeams; i++) {
                numOfStudentsInATeam[i] = 4;
            }
            numOfStudentsInATeam[i] = 3;
        } else if (numOfextraStudents == 2) {

            for (i = 0; i < numOfTempTeams; i++) {
                numOfStudentsInATeam[i] = 4;
            }
            numOfStudentsInATeam[i] = 2;
        } else if (numOfextraStudents == 1) {

            for (i = 0; i < numOfTempTeams - 1; i++) {
                numOfStudentsInATeam[i] = 4;
            }
            numOfStudentsInATeam[i] = 3;
            numOfStudentsInATeam[i + 1] = 2;
        } else {
            for (i = 0; i < numOfTempTeams; i++) {
                numOfStudentsInATeam[i] = 4;
            }
        }

        for (int y = 0; y < numOfStudentsInATeam.length; y++) {
            System.out.println(" " + numOfStudentsInATeam[y]);
        }

        for (Student t : tempStudent) {
            if (t.getgender() == 'f') {
                femaleCount++;
            }
            System.out.println(t.getId());
        }

        System.out.println("numOfStudentsInATeam length=" + numOfStudentsInATeam.length);
        // System.out.println(numOfStudentsInATeam[0]);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while (j < numOfStudentsInATeam.length) {
            int k = 0;
            boolean femaleFound = false;
            while (k < numOfStudentsInATeam[j]) {
                for (int z = 0; z < tempStudent.size(); z++) {
                    if (tempStudent.get(z).getgender() == 'f') {
                        System.out.println("j = " + j + "k = " + k + "z = " + z + "\tFemale found, assigning to team");
                        teamStudent.add(tempStudent.get(z));
                        femaleFound = true;
                        femaleCount--;
                        System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
                        tempStudent.remove(tempStudent.get(z));
                        System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
                        k++;
                        break;
                    }

                    // for (int z = 0; z < tempStudent.size(); z++) {
                    if (tempStudent.get(z).getgender() == 'm' && femaleFound == true) {
                        System.out.println("j = " + j + "k = " + k + "z = " + z + "\tmale found, assigning to team");
                        teamStudent.add(tempStudent.get(z));
                        System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
                        tempStudent.remove(tempStudent.get(z));
                        System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
                        k++;
                        break;

                    } else {
                        if (femaleCount == 0) {
                            teamStudent.add(tempStudent.get(z));
                            System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
                            tempStudent.remove(tempStudent.get(z));
                            System.out
                                    .println("removed from tempStudent, length of team student: " + tempStudent.size());
                            k++;
                            break;
                        }
                    }

                }

            }

            for (Student t : tempStudent) {
                System.out.println(t.getId());
            }
            experienceCounter = 0;
            System.out.println("*************************\nTeam " + j);
            for (Student tm : teamStudent) {
                // System.out.println(tm.getId());
                if (tm.getexperience() >= 5) {
                    experienceCounter++;
                }
            }
            for (Student tm : teamStudent) {
                if (experienceCounter == 0) {
                    if (tm.getgender() == 'm') {
                        for (int a = 0; a < tempStudent.size(); a++) {
                            if (tempStudent.get(a).getgender() == 'm' && tempStudent.get(a).getgPA() == tm.getgPA()
                                    && tempStudent.get(a).getexperience() >= 5) {

                                tempStudent.add(tm);
                                teamStudent.remove(tm);

                                teamStudent.add(tempStudent.get(a));
                                tempStudent.remove(tempStudent.get(a));
                                System.out.println(tm.getId() + " swapped with " + tempStudent.get(a).getId());
                                break;
                            }
                        }

                    } else {
                        if (tm.getgender() == 'f') {
                            for (int a = 0; a < tempStudent.size(); a++) {
                                if (tempStudent.get(a).getgender() == 'f' && tempStudent.get(a).getgPA() == tm.getgPA()
                                        && tempStudent.get(a).getexperience() >= 5) {

                                    tempStudent.add(tm);
                                    teamStudent.remove(tm);

                                    teamStudent.add(tempStudent.get(a));
                                    tempStudent.remove(tempStudent.get(a));
                                    System.out.println(tm.getId() + " swapped");
                                    break;
                                }
                            }
                        }

                    }
                }
                break;
            }

            for (Student newTeam : teamStudent) {

                System.out.println(newTeam.getId());
            }
            j++;
            // soft constraint
            // counter for 5 yrs more than 1 person with >=5 exp, if not swap: male->male or
            // female ->female with same gpa

            teamStudent.clear();
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

}
