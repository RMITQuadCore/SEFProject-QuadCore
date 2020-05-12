import java.util.ArrayList;
import java.util.Scanner;

public class Role {
    static Scanner scan = new Scanner(System.in);
    private String id;
    private String roleName;
    private ArrayList<String> frameworks = new ArrayList<>();


    public Role (String id, String roleName)
    {
      this.id =id;
      this.roleName= roleName;
    }

    public ArrayList<String> getFrameworks()
    {
        return frameworks;
    }

    public String getId()
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

    public void enterFrameworks(Role role)
    {
        boolean frameWorkFound = false;
        do {
            System.out.println("Enter the framework : ");
            String input = scan.next();

            for (int i = 0; i < role.getFrameworks().size(); i++)
            {
                if(input.equalsIgnoreCase(role.getFrameworks().get(i)))
                {
                   frameWorkFound = true;
                }
                if (frameWorkFound)
                {
                    frameworks.add(input);
                }
                else {
                    System.out.println(" There is no such framework required for this role.");
                     }
            }
            frameworks.add(input);
        }while(!frameWorkFound);
    }
    public void enterFrameworks()
    {
        System.out.println("Enter the framework : ");
        String input = scan.next();
        frameworks.add(input);
    }

}