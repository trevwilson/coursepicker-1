package model;

/**
 * model class for a section. A course has many sections
 * @author Will Henry
 *
 */
public class Section {

	private int id;
	private String sectNum;
	private String timeStart;
	private String timeEnd;
	private String meetingDays;
	private int roomNumber;
	private int buildingNumber;
	private String instructor;
	private Course course;
	/**
	 * constructor for a Section object
	 * @param id id of section
	 * @param sectNum section number
	 * @param timeStart start time of section
	 * @param timeEnd end time of section
	 * @param meetingDays meeting days of section
	 * @param roomNumber room number for the section
	 * @param buildingNumber building number for section
	 * @param instructor teacher of the section
	 * @param course the course that this section is of
	 */
	public Section(int id, String sectNum, String timeStart, String timeEnd,
			String meetingDays, int roomNumber, int buildingNumber,
			String instructor, Course course) {
		this.id = id;
		this.sectNum = sectNum;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.meetingDays = meetingDays;
		this.roomNumber = roomNumber;
		this.buildingNumber = buildingNumber;
		this.instructor = instructor;
		this.course = course;
	}
	/**
	 * getter for the id of the section
	 * @return id - the id of the section
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter for the section number
	 * @return sectNum - the section number
	 */
	public String getSectNum() {
		return sectNum;
	}
	/**
	 * getter for the start time of the section
	 * @return timeStart - the start time of the section
	 */
	public String getTimeStart() {
		return timeStart;
	}
	/**
	 * getter for the time end
	 * @return timeEnd - the ending time of the section
	 */
	public String getTimeEnd() {
		return timeEnd;
	}
	/**
	 * getter for the meeting days of the section
	 * @return meetingDays - the days the section meets on
	 */
	public String getMeetingDays() {
		return meetingDays;
	}
	/**
	 * getter for the room number of the section
	 * @return roomNumber - the number of the room the section will be in
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * getter for the building number
	 * @return buildingNumber - the number of the building where the section will be
	 */
	public int getBuildingNumber() {
		return buildingNumber;
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
	public Course getCourse() {
		return course;
	}
}
