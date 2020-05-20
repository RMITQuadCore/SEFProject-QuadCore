import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileReadWrite {
    private static File f;

    //This Class aids in writing and reading data from a text file
    public static void saveUserDetails(String fileName, ArrayList<User> userList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(userList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<User> readUserDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<User> userList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            userList = (ArrayList<User>) in.readObject();
            in.close();
        } else {
        }
        return userList;
    }

    //This Class aids in writing and reading data from a text file
    public static void saveStudentDetails(String fileName, ArrayList<Student> studentList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(studentList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Student> readStudentDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<Student> studentList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            studentList = (ArrayList<Student>) in.readObject();
            in.close();
        } else {
        }
        return studentList;
    }

    public static void saveProjectDetails(String fileName, ArrayList<Project> projectList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(projectList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Project> readProjectDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<Project> projectList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            projectList = (ArrayList<Project>) in.readObject();
            in.close();
        } else {
        }
        return projectList;
    }
    public static void saveTeamDetails(String fileName, ArrayList<Team> teamList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(teamList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Team> readTeamDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<Team> teamList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            teamList = (ArrayList<Team>) in.readObject();
            in.close();
        } else {
        }
        return teamList;
    }
    public static void saveRoleDetails(String fileName, ArrayList<Role> roleList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(roleList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Role> readRoleDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<Role> roleList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            roleList = (ArrayList<Role>) in.readObject();
            in.close();
        } else {
        }
        return roleList;
    }
}