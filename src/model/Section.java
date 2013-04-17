package model;

import java.util.ArrayList;

/**
 * model class for a section. A course has many sections
 * @author Will Henry
 * @author David Sawyer
 *
 */
public class Section {
	
	private int id;
	private int callNum;
	private int creditHours;
	private String title;
	private String instructor;
	private int courseId;
	private ArrayList<Meeting> meetings = new ArrayList<Meeting>(0);

	/**
	 * @param id
	 * @param callNum
	 * @param creditHours
	 * @param title
	 * @param instructor
	 * @param course
	 */
	public Section(int id, int callNum, int creditHours, String title,
			String instructor, int courseId) {
		super();
		this.id = id;
		this.callNum = callNum;
		this.creditHours = creditHours;
		this.title = title;
		this.instructor = instructor;
		this.courseId = courseId;
	}

	/**
	 * getter for the id of the section
	 * @return id - the id of the section
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter for the call number
	 * @return callNum - the call number
	 */
	public int getCallNum() {
		return callNum;
	}

	/**
	 * getter for the credit hours
	 * @return creditHours - the credit hours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * getter for the title
	 * @return title - the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getter for the instructor of the class
	 * @return instructor - the instructor who teaches the class
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * getter for the course
	 * @return course - the course that the section belongs to
	 */
	public int getCourse() {
		return courseId;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @return the meetings
	 */
	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}
}