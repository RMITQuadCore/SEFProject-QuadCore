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
    public static void saveUserDetails(String fileName, ArrayList<? extends User> userList) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);//since the array contains an object
        out.writeObject(userList);
        out.close();
        file.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<? extends User> readUserDetails(String fileName) throws IOException, ClassNotFoundException {
        f = new File(fileName);
        ArrayList<? extends User> userList = new ArrayList<>();
        if (f.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            userList = (ArrayList<? extends User>) in.readObject();
            in.close();
        } else {
        }
        return userList;
    }
}
