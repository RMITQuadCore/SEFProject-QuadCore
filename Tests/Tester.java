import java.util.ArrayList;




//public class Tester {
//
//    public static void main(String args[]) {
//        model.User u1 = new model.User();
//        model.Project p = new model.Project();
//        model.Student s = new model.Student();
//
//        ArrayList<model.Student> tempStudent = new ArrayList<model.Student>();
//        ArrayList<model.Student> teamStudent = new ArrayList<model.Student>();
//        ArrayList<model.Student> projectGroupStudent = new ArrayList<model.Student>();
//
//        model.Project p1 = new model.Project("CL101", "PROJ201", "xyz1", "xyz1", "xyz1", null, 7);
//        p.pr.add(p1);
//
//        model.Project p2 = new model.Project("CL102", "PROJ202", "xyz2", "xyz2", "xyz2", null, 3);
//        p.pr.add(p2);
//
//        model.Project p3 = new model.Project("CL103", "PROJ203", "xyz3", "xyz3", "xyz3", null, 5);
//        p.pr.add(p3);
//
//        model.Project p4 = new model.Project("CL104", "PROJ204", "xyz4", "xyz4", "xyz4", null, 8);
//        p.pr.add(p4);
//
//        model.Student s1 = new model.Student("ST101", "abc1", "abc1", "abc1", "abc1", "abc1", "abc1", 2.8, 1, 'm', 'a');
//        s.allStudents.add(s1);
//        u1.allUserDetails.add(s1);
//
//        model.Student s2 = new model.Student("ST102", "abc2", "abc2", "abc2", "abc2", "abc2", "abc2", 3.8, 1, 'm', 'a');
//        s.allStudents.add(s2);
//        u1.allUserDetails.add(s2);
//
//        model.Student s3 = new model.Student("ST103", "abc3", "abc3", "abc3", "abc3", "abc3", "abc3", 2, 4, 'm', 'b');
//        s.allStudents.add(s3);
//        u1.allUserDetails.add(s3);
//
//        model.Student s4 = new model.Student("ST104", "abc4", "abc4", "abc4", "abc4", "abc4", "abc4", 3, 1, 'm', 'c');
//        s.allStudents.add(s4);
//        u1.allUserDetails.add(s4);
//
//        model.Student s5 = new model.Student("ST105", "abc5", "abc5", "abc5", "abc5", "abc5", "abc5", 4, 6, 'm', 'd');
//        s.allStudents.add(s5);
//        u1.allUserDetails.add(s5);
//
//        model.Student s6 = new model.Student("ST106", "abc6", "abc6", "abc6", "abc6", "abc6", "abc6", 3.2, 5, 'm', 'd');
//        s.allStudents.add(s6);
//        u1.allUserDetails.add(s6);
//
//        model.Student s7 = new model.Student("ST107", "abc7", "abc7", "abc7", "abc7", "abc7", "abc7", 3.5, 6, 'm', '0');
//        s.allStudents.add(s7);
//        u1.allUserDetails.add(s7);
//
//        model.Student s8 = new model.Student("ST108", "abc8", "abc8", "abc8", "abc8", "abc8", "abc8", 3, 3, 'm', '0');
//        s.allStudents.add(s8);
//        u1.allUserDetails.add(s8);
//
//
//        model.Student s9 = new model.Student("ST109", "abc9", "abc9", "abc9", "abc9", "abc9", "abc9", 3.2, 2, 'm', '0');
//        s.allStudents.add(s9);
//        u1.allUserDetails.add(s9);
//
//        model.Student s10 = new model.Student("ST110", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'm',  '0');
//        s.allStudents.add(s10);
//        u1.allUserDetails.add(s10);
//
//        model.Student s11 = new model.Student("ST111", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'm',  '0');
//        s.allStudents.add(s11);
//        u1.allUserDetails.add(s11);
//
//        model.Student s12 = new model.Student("ST112", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'm',  '0');
//        s.allStudents.add(s12);
//        u1.allUserDetails.add(s12);
//
//        model.Student s13 = new model.Student("ST113", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'f',  '0');
//        s.allStudents.add(s13);
//        u1.allUserDetails.add(s13);
//
//        model.Student s14 = new model.Student("ST114", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'f',  '0');
//        s.allStudents.add(s14);
//        u1.allUserDetails.add(s14);
//
//        tempStudent.addAll(s.allStudents);
//        teamStudent = model.ProjectManager.femaleHardConstraintApplicator(teamStudent,tempStudent,4);
//        for(model.Student st:teamStudent){
//            System.out.println("model.Student "+st.getId());
//        }
//
//
//        teamStudent = model.ProjectManager.femaleHardConstraintApplicator(teamStudent,tempStudent,4);
//        for(model.Student st:teamStudent){
//            System.out.println("model.Student 1 "+st.getId());
//        }
//
//        teamStudent = model.ProjectManager.femaleHardConstraintApplicator(teamStudent,tempStudent,2);
//        for(model.Student st:teamStudent){
//            System.out.println("model.Student 2 "+st.getId());
//        }
//    }
//}

//		model.Student s11 = new model.Student("ST111", "abc10", "abc10", "abc10", "abc10", "abc10", "abc10", 3, 3, 'm', null, null,
//				null, null, null, '0');
//		s.allStudents.add(s11);
//		u1.details.add(s11);
//

//        u1.start();
//
//        int numOfStudents = model.Student.allStudents.size();
//        int numOfTempTeams = numOfStudents / 4;
//        int numOfextraStudents = numOfStudents % 4; // extra students
//        int i, j = 0, femaleCount = 0, experienceCounter = 0;
//        Integer numOfStudentsInATeam[];
//
//        if (numOfextraStudents == 0) {
//            numOfStudentsInATeam = new Integer[numOfTempTeams];
//        } else {
//            numOfStudentsInATeam = new Integer[numOfTempTeams + 1];
//        }
//
//        if (numOfextraStudents == 3) {
//
//            for (i = 0; i < numOfTempTeams; i++) {
//                numOfStudentsInATeam[i] = 4;
//            }
//            numOfStudentsInATeam[i] = 3;
//        } else if (numOfextraStudents == 2) {
//
//            for (i = 0; i < numOfTempTeams; i++) {
//                numOfStudentsInATeam[i] = 4;
//            }
//            numOfStudentsInATeam[i] = 2;
//        } else if (numOfextraStudents == 1) {
//
//            for (i = 0; i < numOfTempTeams - 1; i++) {
//                numOfStudentsInATeam[i] = 4;
//            }
//            numOfStudentsInATeam[i] = 3;
//            numOfStudentsInATeam[i + 1] = 2;
//        } else {
//            for (i = 0; i < numOfTempTeams; i++) {
//                numOfStudentsInATeam[i] = 4;
//            }
//        }
//
//        for (int y = 0; y < numOfStudentsInATeam.length; y++) {
//            System.out.println(" " + numOfStudentsInATeam[y]);
//        }
//
//        for (model.Student t : tempStudent) {
//            if (t.getgender() == 'f') {
//                femaleCount++;
//            }
//            System.out.println(t.getId());
//        }
//
//        System.out.println("numOfStudentsInATeam length=" + numOfStudentsInATeam.length);
//        // System.out.println(numOfStudentsInATeam[0]);
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        while (j < numOfStudentsInATeam.length) {
//            int k = 0;
//            boolean femaleFound = false;
//            while (k < numOfStudentsInATeam[j]) {
//                for (int z = 0; z < tempStudent.size(); z++) {
//                    if (tempStudent.get(z).getgender() == 'f') {
//                        System.out.println("j = " + j + "k = " + k + "z = " + z + "\tFemale found, assigning to team");
//                        teamStudent.add(tempStudent.get(z));
//                        femaleFound = true;
//                        femaleCount--;
//                        System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
//                        tempStudent.remove(tempStudent.get(z));
//                        System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
//                        k++;
//                        break;
//                    }
//
//                    // for (int z = 0; z < tempStudent.size(); z++) {
//                    if (tempStudent.get(z).getgender() == 'm' && femaleFound == true) {
//                        System.out.println("j = " + j + "k = " + k + "z = " + z + "\tmale found, assigning to team");
//                        teamStudent.add(tempStudent.get(z));
//                        System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
//                        tempStudent.remove(tempStudent.get(z));
//                        System.out.println("removed from tempStudent, length of team student: " + tempStudent.size());
//                        k++;
//                        break;
//
//                    } else {
//                        if (femaleCount == 0) {
//                            teamStudent.add(tempStudent.get(z));
//                            System.out.println("added to teamStudent, length of team student: " + teamStudent.size());
//                            tempStudent.remove(tempStudent.get(z));
//                            System.out
//                                    .println("removed from tempStudent, length of team student: " + tempStudent.size());
//                            k++;
//                            break;
//                        }
//                    }
//
//                }
//
//            }
//
//            for (model.Student t : tempStudent) {
//                System.out.println(t.getId());
//            }
//            experienceCounter = 0;
//            System.out.println("*************************\nTeam " + j);
//            for (model.Student tm : teamStudent) {
//                // System.out.println(tm.getId());
//                if (tm.getexperience() >= 5) {
//                    experienceCounter++;
//                }
//            }
//            for (model.Student tm : teamStudent) {
//                if (experienceCounter == 0) {
//                    if (tm.getgender() == 'm') {
//                        for (int a = 0; a < tempStudent.size(); a++) {
//                            if (tempStudent.get(a).getgender() == 'm' && tempStudent.get(a).getgPA() == tm.getgPA()
//                                    && tempStudent.get(a).getexperience() >= 5) {
//
//                                tempStudent.add(tm);
//                                teamStudent.remove(tm);
//
//                                teamStudent.add(tempStudent.get(a));
//                                tempStudent.remove(tempStudent.get(a));
//                                System.out.println(tm.getId() + " swapped with " + tempStudent.get(a).getId());
//                                break;
//                            }
//                        }
//
//                    } else {
//                        if (tm.getgender() == 'f') {
//                            for (int a = 0; a < tempStudent.size(); a++) {
//                                if (tempStudent.get(a).getgender() == 'f' && tempStudent.get(a).getgPA() == tm.getgPA()
//                                        && tempStudent.get(a).getexperience() >= 5) {
//
//                                    tempStudent.add(tm);
//                                    teamStudent.remove(tm);
//
//                                    teamStudent.add(tempStudent.get(a));
//                                    tempStudent.remove(tempStudent.get(a));
//                                    System.out.println(tm.getId() + " swapped");
//                                    break;
//                                }
//                            }
//                        }
//
//                    }
//                }
//                break;
//            }
//
//            for (model.Student newTeam : teamStudent) {
//
//                System.out.println(newTeam.getId());
//            }
//            j++;
//            // soft constraint
//            // counter for 5 yrs more than 1 person with >=5 exp, if not swap: male->male or
//            // female ->female with same gpa
//
//            teamStudent.clear();
//        }
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    }
//
//}
