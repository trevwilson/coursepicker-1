package test;

import junit.framework.TestCase;
import java.util.ArrayList;
import model.*;
import helper.*;

/**
* Tester for the Helper class
* @author Will Henry
*/
public class HelperTest extends TestCase{
	/**
	* tests that the entire requirement list is returned from the database
	*/
	public void testGetRequirementList(){
		//create helper
		Helper helper = new Helper();
		//return the list of requirements from the database
		ArrayList<Requirement> requirements = helper.getRequirementList();
		//check the size
		assertEquals("Requirements size", 19, requirements.size());
		//check that the first requirement has values as tuple with id 1 in db
		Requirement requirement1 = requirements.get(0);
		assertEquals("Requirement 1 id", 1, requirement1.getId());
		assertEquals("Requirement 1 area", "Cultural Diversity Requirement", requirement1.getRequirementArea());
		//check random middle requirement
		Requirement requirement2 = requirements.get(8);
		assertEquals("Requirement 2 id", 9, requirement2.getId());
		assertEquals("Requirement 2 area", "Core Curriculum V: Social Sciences", requirement2.getRequirementArea());
		//check last requirement
		Requirement requirement3 = requirements.get(18);
		assertEquals("Requirement 3 id", 19, requirement3.getId());
		assertEquals("Requirement 3 area", "Computer Science Major Courses", requirement3.getRequirementArea());		
	}
}