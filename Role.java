import java.util.ArrayList;
import java.util.Scanner;

public class Role {
    static Scanner scan = new Scanner(System.in);
    private String id;
    private String roleName;
    private ArrayList<String> frameworks;

    public Role (String id, String roleName)
    {
      this.id =id;
      this.roleName= roleName;
    }

    public String getID()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public void enterFrameworks()
    {
        System.out.println("Enter the framework : ");
        String input = scan.next();
        frameworks.add(input);
    }

}
