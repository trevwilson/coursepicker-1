package model;
/**
 * the model class for a section. A requirement is made up of many courses
 * @author Will Henry
 * @author David Sawyer
 *
 */
public class Course {

	private int id;
	private int reqFulfilled;
	private String coursePrefix;
	private String courseNum;
	private String title;

	/**
	 * @param id
	 * @param reqFulfilled
	 * @param coursePrefix
	 * @param courseNum
	 * @author Will Henry
	 */
	public Course(int id, int reqFulfilled, String coursePrefix,
			String courseNum) {
		this.id = id;
		this.reqFulfilled = reqFulfilled;
		this.coursePrefix = coursePrefix;
		this.courseNum = courseNum;
	}

	/**
	 * getter for the id of the Course
	 * @return id - the id of the course
	 * @author Will Henry
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter for the requirement
	 * @return requirement - the requirement that this course fulfills
	 * @author Will Henry
	 */
	public int getReqFulfilled() {
		return reqFulfilled;
	}

	/**
	 * getter for the course prefix
	 * @return coursePrefix - the prefix of the course
	 * @author Will Henry
	 */
	public String getCoursePrefix() {
		return coursePrefix;
	}

	/**
	 * getter for the course number
	 * @return courseNum - the course number 
	 * @author Will Henry
	 */
	public String getCourseNum() {
		return courseNum;
	}

	/**
	 * @return the title
	 * @author David Sawyer
	 */
	public String getTitle() {
		return title;
	}
}