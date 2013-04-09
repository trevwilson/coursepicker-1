package model;
/**
 * the model class for a section. A requirement is made up of many courses
 * @author Will Henry
 *
 */
public class Course {

	private int id;
	private String coursePrefix;
	private String courseNum;
	private String courseTitle;
	private int creditHours;
	private Requirement reqFulfilled;
	/**
	 * constructor for a course object
	 * @param id the id of the course 
	 * @param coursePrefix the course prefix 
	 * @param courseNum the course number
	 * @param courseTitle the course's title
	 * @param creditHours the number of credit hours completing the course earns
	 * @param reqFulfilled the requirement that the course fulfills
	 */
	public Course(int id, String coursePrefix, String courseNum,
			String courseTitle, int creditHours, Requirement reqFulfilled) {
		this.id = id;
		this.coursePrefix = coursePrefix;
		this.courseNum = courseNum;
		this.courseTitle = courseTitle;
		this.creditHours = creditHours;
		this.reqFulfilled = reqFulfilled;
	}
	/**
	 * getter for the id of the Course
	 * @return id - the id of the course
	 */
	public int getId() {
		return id;
	} 
	/**
	 * getter for the course prefix
	 * @return coursePrefix - the prefix of the course
	 */
	public String getCoursePrefix() {
		return coursePrefix;
	}
	/**
	 * getter for the course number
	 * @return courseNum - the course number 
	 */
	public String getCourseNum() {
		return courseNum;
	}
	/**
	 * getter for the course title
	 * @return courseTitle - the title of the course
	 */
	public String getCourseTitle() {
		return courseTitle;
	}
	/**
	 * getter for the credit hours
	 * @return creditHours - the number of hours of credit this course earns
	 */
	public int getCreditHours() {
		return creditHours;
	}
	/**
	 * getter for the requirement
	 * @return requirement - the requirement that this course fulfills
	 */
	public Requirement getReqFulfilled() {
		return reqFulfilled;
	}
}
