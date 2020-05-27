import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Temp {


    static ArrayList<Project> totalProjects = new ArrayList<>();
    public static Team setProjectForTeam(ArrayList<Student> teamStudent)
    {
        HashMap<Project , Integer> projectPopularity = new HashMap<>();
        for (int i = 0; i < totalProjects.size() ; i++)
        {
            projectPopularity.put(totalProjects.get(i),0);
        }

        for (int i = 0; i <teamStudent.size(); i++)
        {
            Project [] preference = new Project[4];
            preference = teamStudent.get(i).getPreferredProjects();
            for (int j = 0; j < 4; j++)
            {
                if(projectPopularity.containsKey(preference[j]))
                {
                    projectPopularity.put(preference[j], projectPopularity.get(preference[j]) + (4-j));
                }
            }
        }
        Team team01 = new Team("TEAM01");

        int maxValueInMap=(Collections.max(projectPopularity.values()));
        for (HashMap.Entry<Project, Integer> entry : projectPopularity.entrySet())
        {
            if (entry.getValue()==maxValueInMap) {
                team01.setProjectAssigned(entry.getKey());
            }
        }
        team01.setStudentsInTeam(teamStudent);
        return team01;
    }
    public static void main(String args[]) {
        ArrayList<Student> teamStudent = new ArrayList<Student>();
        ArrayList<Student> tempStudent = new ArrayList<>();

//        Student s1 = new Student("S1", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
//        Student s2 = new Student("S2", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'F', 'A');
//        Student s3 = new Student("S3", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
//        Student s4 = new Student("S4", "Karan", "Thapar", "karan.234@gmail.com", "karan234", "12345", "RMIT", 3.4, 2.2, 'M', 'A');
        Project proj1 = new Project();
        Project proj2 = new Project();
        Project proj3 = new Project();
        Project proj4 = new Project();
        proj1.setProjectId("PROJ001");
        proj2.setProjectId("PROJ002");
        proj3.setProjectId("PROJ003");
        proj4.setProjectId("PROJ004");
        totalProjects.add(proj1);
        totalProjects.add(proj2);
        totalProjects.add(proj3);
        totalProjects.add(proj4);

        //Project 1 3

        //Project 2 2 + 4
        // Project 3 1
        // Project 4 4
        Project[] preference1 = new Project[]{proj4, proj1, proj2, proj3};
        Project[] preference2 = new Project[]{proj2, proj3, proj4, proj1};
        Project[] preference3 = new Project[]{proj1, proj3, proj4, proj2};
        Project[] preference4 = new Project[]{proj4, proj3, proj1, proj2};

//        s1.setPreferredProjects(preference1);
//        s2.setPreferredProjects(preference2);
//        s3.setPreferredProjects(preference3);
//        s4.setPreferredProjects(preference4);

//        teamStudent.add(s1);
//        teamStudent.add(s2);
//        teamStudent.add(s3);
//        teamStudent.add(s4);

        Team t1 = setProjectForTeam(teamStudent);

        System.out.println(t1.getProjectAssigned().getProjectId());
        System.out.println(t1.getStudentsInTeam().size());
    }
}
