

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class StudentEntersArrayofDislikedStudentsTest {

	ArrayList<String> emptyList = new ArrayList<String>();
	String [] emptyarray4 = new String [4];//Dummy Objects
	String [] emptyarray3 = new String [3];
	String [] emptyarray2 = new String [2];
	Student s1 = new Student("s1","s1","","",0.0,0.0,'F',emptyList,emptyList,emptyarray4,emptyarray2,emptyarray3);
	Student s2 = new Student("s2","s2","","",0.0,0.0,'F',emptyList,emptyList,emptyarray4,emptyarray2,emptyarray3);
	Student s3 = new Student("s3","s3","","",0.0,0.0,'F',emptyList,emptyList,emptyarray4,emptyarray2,emptyarray3);
	Student s4 = new Student("s4","s4","","",0.0,0.0,'F',emptyList,emptyList,emptyarray4,emptyarray2,emptyarray3);
	
    String testforDisliked[]= {"s2","s3","s4"};
    Student testforStudentEntry = s1;
	

	
	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
	
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}
	

	
	@Test
	public void testfordislikedStudentEntry() {
		
		Student.addStudent(s2);
		Student.addStudent(s3);
		Student.addStudent(s4);	
		for(int j = 0 ; j <Student.allStudents.size(); j++)
		{
			System.out.println("Student ID of member " + (j+1) + " is " + Student.allStudents.get(j).getstudentID());
		}
		System.out.println("-----------------------------");
		s1.enterdislikedMembers();
		String[]input = new String[3];
		input=s1.getdislikedMembers();
	    List<String> list = Arrays.asList(input);
	    int check = 0;
	       for (int i = 0; i < input.length; i++)  
	       {
	    	   if(list.contains(testforDisliked[i]))
	    	   {
		            check++;
		       }
		   }
	            
		assertEquals(3,check);
	}
	
	
	@Test
        public void testforStudententry() 
	{
		
		Student.addStudent(s1);
		assertEquals(testforStudentEntry, s1);
	}

	
}
