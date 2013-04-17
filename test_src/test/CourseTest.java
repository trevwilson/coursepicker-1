package test;

import junit.framework.TestCase;
import java.util.ArrayList;
import model.*;
import helper.*;

/**
* Tester for the Course model class
* @author Will Henry
*/
public class CourseTest extends TestCase{

	/**
	* creates an Course from the constructor and makes sure that the get methods work
	* @author Will Henry
	*/
	public void testConstructor {
		//create first course and test getters
		Course course1 = new Course(1, 1, "AFAM", "2000");
		assertEquals("course 1 id", 1, course1.getId());
		assertEquals("course 1 requirement fulfilled", 1, course1.getReqFulfilled());
		assertEquals("course 1 prefix", "AFAM", course1.getCoursePrefix());
		assertEquals("course 1 number", "2000", course1.getCourseNum());

		//create a second course and test getters
		Course course2 = new Course(2, 6, "CSCI", "4300");
		assertEquals("course 2 id", 2, course2.getId());
		assertEquals("course 2 requirement fulfilled", 6, course2.getReqFulfilled());
		assertEquals("course 2 prefix", "CSCI", course2.getCoursePrefix());
		assertEquals("course 2 number", "4300", course2.getCourseNum());		
	}

}
