 package helper;
import java.util.*;
import model.*;
/**
 * data transer class
 * @author Will Henry
 *
 */
public class Transfer {

	private ArrayList<Requirement> requirements;
	private ArrayList<Course> courses;
	private ArrayList<Section> sections;
	public Transfer() {
		requirements = new ArrayList<Requirement>();
		courses = new ArrayList<Course>();
		sections = new ArrayList<Section>();
	}
	/**
	 * get the requirement list
	 * @return requirements - the list of all requirements
	 */
	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}
	/**
	 * setter for the requirements
	 * @param requirements the requirement list to set
	 */
	public void setRequirements(ArrayList<Requirement> requirements) {
		this.requirements = requirements;
	}
	/**
	 * getter for the list of courses
	 * @return courses - the list of courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}
	/**
	 * setter for the list of courses
	 * @param courses the list of courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	/**
	 * getter for the section list
	 * @return sections - the list of sections
	 */
	public ArrayList<Section> getSections() {
		return sections;
	}
	/**
	 * setter for the section list
	 * @param sections the section list to set
	 */
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
}
