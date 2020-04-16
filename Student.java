
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Student {
    static Scanner scan = new Scanner(System.in);
    private String systemID;// super class
	private String studentID;
	private String emailID;// super class
	private String universityName;// super class
	private double gPA;
	private double experience;
	private char gender;
	private ArrayList<String> Skills = new ArrayList<String>();
	private ArrayList<String> Frameworks = new ArrayList<String>();
	private String [] preferredProjects = new String [4];
	private String [] preferredRoles = new String [2];
	private String [] dislikedMembers = new String [3];
	public static ArrayList<Student>allStudents = new ArrayList<Student>();
	public static int studentsregistered;
	public Student(String systemID,String studentID, String emailID, String universityName, double gPA,double experience, char gender,ArrayList<String> Skills,ArrayList<String> Frameworks,  String [] preferredProjects, String [] preferredRoles, String [] dislikedMembers )
	
	{   this.systemID= systemID;
		this.studentID =studentID;
		this.emailID=emailID;
		this.gender=gender;
		this.dislikedMembers= dislikedMembers;
		this.experience=experience;
		this.Frameworks= Frameworks;
		this.preferredProjects=preferredProjects;
		this.preferredRoles=preferredRoles;
		this.Skills=Skills;
		this.universityName=universityName;
		this.gPA=gPA;
		
	}
	
	public String getstudentID()
	{
		return studentID;
	}

	public void setstudentID(String studentID) 
	{
		this.studentID = studentID;
		
	}
	public String getemailID()
	{
		return emailID;
	}

	public void setemailID(String emailID) 
	{
		this.emailID = emailID;
	}
	public char getgender()
	{
		return gender;
	}

	public void setgender(char gender) 
	{
		this.gender = gender;
	}
	public String getuniversityName()
	{
		return universityName;
	}

	public void setuniversityName(String universityName) 
	{
		this.universityName = universityName;
	}
	
	public double getgPA()
	{
		return gPA;
	}	

	public void setgPA(double gPA) 
	{
		this.gPA = gPA;
	}
	public double getexperience()
	{
		return experience;
	}

	public void setexperience(double experience) 
	{
		this.experience = experience;
	}
	
	public String[] getpreferredProjects()
	{
		return preferredProjects;
	}

	public void setpreferredProjects(String[] preferredProjects) 
	{
		this.preferredProjects = preferredProjects;
		
	}
	public String[] getpreferredRoles()
	{
		return preferredRoles;
	}

	public void setpreferredRoles(String[] preferredRoles) 
	{
		this.preferredRoles = preferredRoles;
		
	}
	public String[] getdislikedMembers()
	{
		return dislikedMembers;
	}

	public void setdislikedMembers(String [] dislikedMembers) 
	{
		this.dislikedMembers = dislikedMembers;
		
	}

	
	public void enterpreferredProjects()	
	{   
		
	    System.out.println(" You are allowed to enter 4 projects you would like to work for.");
	  //DISPLAY ALL Projects-PROJECTS here
	    
		for (int i =0; i<4; i++)
		{
			boolean checkclient = false;
			do {
				
				System.out.print("\n Please enter your preferred project number " + (i + 1) + ":");
				String input = scan.next();		
				boolean existing = false;
				for (int k =0 ; k< preferredProjects.length;k++)
                {
               	 if (input.equals(preferredProjects[k])){existing =true;}  	 
                }
				if(!existing)
				{
					//project.projectpopularity = project.projectpopularity + (4 -i);
					preferredProjects[i] = input;
					checkclient = true;
				}
				else 
				{
					checkclient = false; 
				    System.out.println("You have already registered this Client !!");
				}
				
			} while (!checkclient);
		}
			
	}
	
	public void enterpreferredRoles()
	{
		
		System.out.println(" You are allowed to enter 2 roles of your preference for this project.");
		for (int i =0; i<2; i++)
		{
			boolean checkpref = false;
			do {
				System.out.print("\n Please enter your preferred role number " + (i + 1) + ":");
				String input = scan.next();		
				boolean existing = false;
				for (int k =0 ; k< preferredRoles.length;k++)
                {
               	 if (input.equals(preferredRoles[k])){existing =true;}  	 
                }
				if(!existing)
				{
					preferredRoles[i] = input;
					checkpref = true;
				}
				else 
				{
					checkpref = false; 
				    System.out.println("You have already registered this preference !!");
				}
				
			} while (!checkpref);
		}
				
	}
	
	public void enterdislikedMembers()
	{   
		
		System.out.println(getstudentID() + "! You are allowed to enter 3 members you do not wish to team up with.");
		for (int i =0; i<3; i++)
		{
		   boolean checkstudent = false;
		   do { 
			     System.out.print(" \nPlease enter the student ID of member number " + (i+1) + ":");
				 String input = scan.next();
				 // add for null
                 boolean existing = false;
                 for (int k =0 ; k< dislikedMembers.length;k++)
                 {   
                	 if (input.equals(dislikedMembers[k])){existing =true;}  	 
                 }
					if (!existing) 
					{   						
						if (input.equals(getstudentID())) {
							System.out.println("\nYou cannot enter your own ID!!");
						} else {
							for (int j = 0; j < allStudents.size(); j++) {
								if (input.equals(allStudents.get(j).getstudentID())) {
									checkstudent = true;
									dislikedMembers[i] = input;
								}
							}
							if (!checkstudent) {
								System.out.println("\nNo student found. Please enter an existing student's ID");
							}
						} 
					} else 
					{
						checkstudent = false; 
					    System.out.println("You have already registered this student ID as disliked Member!!");
				    }
		        }while(!checkstudent);  
	   }
						
	}
	
	public static void addStudent(Student student)
	{
		boolean studentpresent = false;
		for(int j=0; j< allStudents.size(); j++)
		{
			if (student.getstudentID().equals(allStudents.get(j).getstudentID()))
			{
				System.out.println("Student Already exists");
				studentpresent=true;
			}	
		}
		if(!studentpresent)
		{
			allStudents.add(student);
		}
	}
	
	public static void display(int stagenumber)
	{
		switch (stagenumber) {
		case 1: {
			     System.out.println();
			     
			     break;
		         }
		
		case 2: {
			     
			      		         
		         break;
		         }
		
		case 3: {
			     
			     			         
		         break;
		        }
		
		case 4: { 
			    
		          break;
	          }
		
	 
		case 5: {
			       
			     
			        break;
		         }
		
		  }		
		
		
	}
}
