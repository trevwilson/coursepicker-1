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
	private String callNum;
	private String creditHours;
	private String instructor;
	private int courseId;
	private ArrayList<Meeting> meetings = new ArrayList<Meeting>();

	/**
	 * @param id
	 * @param callNum
	 * @param creditHours
	 * @param instructor
	 * @param course
	 * @author Will Henry
	 */
	public Section(int id, String callNum, String creditHours,
			String instructor, int courseId, ArrayList<Meeting> meetings) {
		this.id = id;
		this.callNum = callNum;
		this.creditHours = creditHours;
		this.instructor = instructor;
		this.courseId = courseId;
		this.meetings = meetings;
	}

	/**
	 * getter for the id of the section
	 * @return id - the id of the section
	 * @author Will Henry
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter for the call number
	 * @return callNum - the call number
	 * @author Will Henry
	 */
	public String getCallNum() {
		return callNum;
	}

	/**
	 * getter for the credit hours
	 * @return creditHours - the credit hours
	 * @author Will Henry
	 */
	public String getCreditHours() {
		return creditHours;
	}

	/**
	 * getter for the instructor of the class
	 * @return instructor - the instructor who teaches the class
	 * @author Will Henry
	 */
	public String getInstructor() {
		return instructor;
	}


	/**
	 * getter for the course Id
	 * @return the courseId
	 * @author David Sawyer
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * getter for the meetings ArrayList
	 * @return the meetings
	 * @author David Sawyer
	 */
	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}
}