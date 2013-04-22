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
	* @author Will Henry
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

	/**
	* tests that the entire course list is returned from the database
	* @author Will Henry
	*/
	public void testGetCourseList(){
		//create helper
		Helper helper = new Helper();
		//return the list of Courses from the database with requirement id 1
		ArrayList<Course> courses = helper.getCourseList(1);
		//check the size
		assertEquals("Course size", 315, courses.size());
		for(Course c: courses){
			assertEquals("Requirement Id is 1", 1, c.getReqFulfilled());
		}
		//return the list of Courses from the database with requirement id 7
		courses = helper.getCourseList(7);
		for(Course c: courses){
			assertEquals("Requirement Id is 7", 7, c.getReqFulfilled());
		}
	}
	/**
	* test that the entire Section list is returned for a particular course
	* @author Will Henry
	*/
	public void testGetSectionList(){
		//create helper
		Helper helper = new Helper();
		//return the list of Sections from the database with section id 1
		ArrayList<Section> sections = helper.getSectionList(1);
		for(Section s:sections){
			assertEquals("Course Id is 1", 1, s.getCourseId());
		}
		//test for section id 10
		sections = helper.getSectionList(10);
		for(Section s:sections){
			assertEquals("Course Id is 10", 10, s.getCourseId());
		}	
	}
	/**
	* test that the meeting list is returned for a particular section
	* @author Will Henry
	*/
	public void testGetMeetingList(){
		//create helper
		Helper helper = new Helper();
		//return the list of Meetings from the database with section id 1
		ArrayList<Meeting> meetings = helper.getMeetingList(1);
		for(Meeting m: meetings){
			assertEquals("Section Id is 1", 1, m.getSectionId());
		}
		meetings = helper.getMeetingList(7);
		for(Meeting m: meetings){
			assertEquals("Section Id is 7", 7, m.getSectionId());
		}		
	}
	/**
	* tests that a section is added to the database
	* @author Will Henry
	*/
	public void testAddSection(){
		//create helper
		Helper helper = new Helper();
		//get current section list
		ArrayList<Section> sections = helper.getSectionList(1);
		int size = sections.size();
		//add a Section
		helper.addSection("123456", "4", "Dan Everett", 1);
		sections = helper.getSectionList(1);
		assertEquals("Size of section list increased by 1", size+1, sections.size());
		//add a second section
		helper.addSection("123455", "5", "Dan Everett", 1);
		sections = helper.getSectionList(1);
		assertEquals("Size of section list increased by 2", size+2, sections.size());
		//delete them
		helper.deleteSection(sections.get(size).getId());
		helper.deleteSection(sections.get(size+1).getId());
		sections = helper.getSectionList(1);
		assertEquals("both sections deleted", size, sections.size());



	}
}	