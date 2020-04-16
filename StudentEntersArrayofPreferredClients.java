
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class StudentEntersArrayofPreferredClients {
	ArrayList<String> emptyList = new ArrayList<String>();
	String [] emptyarray4 = new String [4];
	String [] emptyarray3 = new String [3];
	String [] emptyarray2 = new String [2];
	Student s1 = new Student("","","","",0.0,0.0,'F',emptyList,emptyList,emptyarray4,emptyarray2,emptyarray3);
    String testOfferedPreferredProjects[]= {"Amazon","Google","HP","Telstra"};//dummy
    
    @Before
	public void setUp() throws Exception {
		
		System.out.println("Before");
		
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}
	@Test
	public void test() {
		System.out.println("\n Projects available are : -");
		for (int j=0;j<4;j++) {
		System.out.println((j+1) +". " + testOfferedPreferredProjects[j]);
		}
		System.out.println("----------------------------------");
		String[]input = new String[4];
		s1.enterpreferredProjects();
		input = s1.getpreferredProjects();
	    List<String> list = Arrays.asList(input);
	    int check = 0;
	       for (int i = 0; i < input.length; i++)  
	       {
	    	   if(list.contains(testOfferedPreferredProjects[i]))
	    	   {
		            check++;
		       }
		   }
		
		assertEquals(4,check);
	}

}
