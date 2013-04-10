package helper;
import model.*;
import java.util.*;
/**
 * This class performs all database related functions as well as a few other necessary functions
 * @author Will Henry
 *
 */
public class Helper {
	/**
	 * gets all requirements from the database
	 * @return list - an arrayList of all the requirements
	 */
	public ArrayList<Requirement> getRequirementList(){
		ArrayList<Requirement> list = new ArrayList<Requirement>();
		return list;
	}
	/**
	 * get all courses that belong to a particular requirement
	 * @param r the requirement
	 * @return list - an array list of all courses of type r
	 */
	public ArrayList<Course> getCourseList(Requirement r){
		ArrayList<Course> list = new ArrayList<Course>();
		return list;
	}
	/**
	 * get all sections that belong to a particular course
	 * @param c the course
	 * @return list - an arrayList of all sections of course c
	 */
	public ArrayList<Section> getSectionList(Course c){
		ArrayList<Section> list = new ArrayList<Section>();
		return list;
	}
	/**
	 * adds a section of a course to class list
	 * @param s the section
	 * @param classes the list of classes currently signed up for
	 */
	public void addClass(Section s, ArrayList<Section> classes){
		
	}
}
