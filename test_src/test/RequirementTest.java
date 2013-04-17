package test;

import junit.framework.TestCase;
import java.util.ArrayList;
import model.*;
import helper.*;

/**
* Tester for the Requirement model class
* @author Will Henry
*/
public class RequirementTest extends TestCase{

	/**
	* creates a Requirement from the constructor and makes sure that the get methods work
	* @author Will Henry
	*/
	public void testConstructor() {
		//create first requirement and test getters
		Requirement requirement1 = new Requirement(1, "Cultural Diversity Requirement");
		assertEquals("requirement 1 id", 1, requirement1.getId());
		assertEquals("requirement 1 area", "Cultural Diversity Requirement", requirement1.getRequirementArea());

		//create a second requirement and test getters
		Requirement requirement2 = new Requirement(2, "Environmental Literacy Requirement");
		assertEquals("requirement 2 id", 2, requirement2.getId());
		assertEquals("requirement 2 area", "Environmental Literacy Requirement", requirement2.getRequirementArea());	
	}

}
