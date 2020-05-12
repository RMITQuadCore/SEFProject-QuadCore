import java.util.ArrayList;


public class PersonalityConstraint {

    //Function to make sure teams have unique personalities
    // and A or B type personality is present in a team
    //Satisfying Personality Soft Constraint
    public static void personalityConstraint() {

        ArrayList<Student> tempStudent = new ArrayList<Student>();
        ArrayList<Student> teamStudent = new ArrayList<Student>();
        ArrayList<Student> projectGroupStudent = new ArrayList<Student>();

        Student s = new Student();
        tempStudent = s.allStudents;

        System.out.println("tempStudent: " + tempStudent);

        while (!tempStudent.isEmpty()) {

            for (int i = 0; i < 4; i++) {
                teamStudent.add(i, tempStudent.get(0));
                tempStudent.remove(0);
            }

            for (Student s1 : teamStudent) {
                System.out.println("in teamStudent: " + s1);
            }


            //To make team of unique personalities
            char studPersonality = '0';
            char[] teamPersonality = new char[4];
            char duplicatePersonality[] = new char[4];
            int nosOfDuplicatePerso = 0;

            for (int i = 0; i < 4; i++) {
                teamPersonality[i] = teamStudent.get(i).getStudentPersonality();
            }

            //To check if duplicate personalities are present
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    k = +j;
                    if (j == k) {
                        continue;
                    } else if (teamPersonality[j] == teamPersonality[k]) {
                        duplicatePersonality[nosOfDuplicatePerso] = teamPersonality[j];
                        nosOfDuplicatePerso++;
                    }
                }

            }

            //Removing duplicate personalities
            while (nosOfDuplicatePerso != 0) {
                int i = 0;
                for (i = 0; i < tempStudent.size(); i++) {
                    if (tempStudent.get(i).getgender() == 'm'
                            && tempStudent.get(i).getStudentPersonality() != duplicatePersonality[1]
                            && tempStudent.get(i).getStudentPersonality() != duplicatePersonality[2]
                            && tempStudent.get(i).getStudentPersonality() != duplicatePersonality[3]) {

                        for (Student tm : teamStudent) {
                            if (tempStudent.get(i).getgender() == 'm'
                                    && (tm.getStudentPersonality() == duplicatePersonality[1]
                                    || tm.getStudentPersonality() == duplicatePersonality[2]
                                    || tm.getStudentPersonality() == duplicatePersonality[3])) {

                                tempStudent.add(tm);
                                teamStudent.remove(tm);
                                teamStudent.add(tempStudent.get(i));
                                tempStudent.remove(tempStudent.get(i));
                                nosOfDuplicatePerso--;
                                break;
                            }
                        }
                    }
                }

                //If no student remains to satisfy condition
                if (i == tempStudent.size()) {
                    break;
                }
            }

            //To ensure A or B personality is present
            char persoA = 'A';
            char persoB = 'B';
            boolean persoAOrBPresent = true;

            for (int j = 0; j < 4; j++) {
                if (!(teamPersonality[j] == persoA || teamPersonality[j] == persoB)) {
                    //if A or B personality is not present in team
                    persoAOrBPresent = false;

                    //Adding A or B type personalty in team
                    while (persoAOrBPresent == false) {
                        int i = 0;
                        for (i = 0; i < tempStudent.size(); i++) {
                            if (tempStudent.get(i).getgender() == 'm'
                                    && (tempStudent.get(i).getStudentPersonality() == persoA
                                    || tempStudent.get(i).getStudentPersonality() == persoB)) {

                                for (Student tm : teamStudent) {
                                    if (tempStudent.get(i).getgender() == 'm'
                                            && (tempStudent.get(i).getStudentPersonality() != persoA
                                            || tempStudent.get(i).getStudentPersonality() != persoB)) {

                                        tempStudent.add(tm);
                                        teamStudent.remove(tm);
                                        teamStudent.add(tempStudent.get(i));
                                        tempStudent.remove(tempStudent.get(i));
                                        persoAOrBPresent = true;
                                        break;
                                    }
                                }
                            }
                        }

                        //If no student remains to satisfy condition
                        if (i == tempStudent.size()) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
