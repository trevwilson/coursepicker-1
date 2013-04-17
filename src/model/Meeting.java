package model;
/**
 * the model class for a meeting. A Meeting represents a physical meeting time that takes place within a section
 * @author Will Henry
 * @author David Sawyer
 *
 */
public class Meeting {

	private int id;
	private String timeStart;
	private String timeEnd;
	private String meetingDay;
	private int roomNumber;
	private int buildingNumber;
	private int sectionId;

	/**
	 * @param id
	 * @param timeStart
	 * @param timeEnd
	 * @param meetingDay
	 * @param roomNumber
	 * @param buildingNumber
	 * @param section
	 */
	public Meeting(int id, String timeStart, String timeEnd, String meetingDay,
			int roomNumber, int buildingNumber, int sectionId) {
		super();
		this.id = id;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.meetingDay = meetingDay;
		this.roomNumber = roomNumber;
		this.buildingNumber = buildingNumber;
		this.sectionId = sectionId;
	}

	/**
	 * getter for the id of the meeting
	 * @return id - the id of the meeting
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter for the starting time of the meeting
	 * @return timeStart - the starting time of the meeting
	 */
	public String getTimeStart() {
		return timeStart;
	}

	/**
	 * getter for the ending time of the meeting
	 * @return timeEnd - the ending time of the meeting
	 */
	public String getTimeEnd() {
		return timeEnd;
	}

	/**
	 * getter for the meeting day of the meeting
	 * @return meetingDay - the meeting day of the meeting
	 */
	public String getMeetingDay() {
		return meetingDay;
	}

	/**
	 * getter for the room number of the meeting
	 * @return roomNumber - the room number of the meeting
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * getter for the building number of the meeting
	 * @return buildingNumber - the building number of the meeting
	 */
	public int getBuildingNumber() {
		return buildingNumber;
	}

	/**
	 * getter for the section
	 * @return section - the section that the meeting belongs to
	 */
	public int getSection() {
		return sectionId;
	}
}