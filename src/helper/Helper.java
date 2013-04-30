package helper;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * This class performs all database related functions as well as a few other necessary functions
 * @author Will Henry
 * @author David Sawyer
 *
 */
public class Helper {

	/**
	 * Statement to retrieve the list of requirements
	 */
	private PreparedStatement getRequirementListStatement;

	/**
	 * Statement to retrieve a list of courses for a particular requirement
	 */
	private PreparedStatement getCourseListStatement;

	/**
	 * Statement to retrieve a list of sections for a particular course
	 */
	private PreparedStatement getSectionListStatement;

	/**
	 * Statement to retrieve a list of meetings for a particular section
	 */
	private PreparedStatement getMeetingListStatement;

	/**
	 * Statement to retrieve a list of sections for a particular course
	 */
	private PreparedStatement addSectionStatement;

	/**
	 * Statement to retrieve a list of meetings for a particular section
	 */
	private PreparedStatement addMeetingStatement;

	/**
	 * Finds a course for the section to link up with
	 */
	private PreparedStatement getCourseStatement;

	/**
	 * Resets the Section table
	 */
	private PreparedStatement resetSection;

	/**
	 * Resets the Meeting table
	 */
	private PreparedStatement resetMeeting;

	/**
	 * Statement to retrieve a specific section
	 */
	private PreparedStatement getSectionStatement;
	/**
	* Statement to delete a section
	*/
	private PreparedStatement deleteSectionStatement;
	/**
	* statement to delete a meeting
	*/
	private PreparedStatement deleteMeetingStatement;
	/**
	 * Empty constructor. Opens a connection to the database and sets up PreparedStatements
	 * @author David Sawyer
	 */
	public Helper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("---Instantiated MySQL driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csci4300", "root", "mysql");
			//System.out.println("---Connected to MySQL!");

			getRequirementListStatement = conn.prepareStatement("select * from Requirement order by id");
			getCourseListStatement = conn.prepareStatement("select * from Course where reqFulfilled=? order by id");
			getSectionListStatement = conn.prepareStatement("select * from Section where courseId=? order by id");
			getMeetingListStatement = conn.prepareStatement("select * from Meeting where sectionId=? order by id");
			addSectionStatement = conn.prepareStatement("insert into Section (callNum, creditHours," + 
					"instructor, courseId) values (?,?,?,?)");
			addMeetingStatement = conn.prepareStatement("insert into Meeting (timeStart, timeEnd, meetingDay," + 
					"roomNumber, buildingNumber, sectionId) values (?,?,?,?,?,?)");
			getCourseStatement = conn.prepareStatement("select * from Course where coursePrefix=? and courseNum=?");
			getSectionStatement = conn.prepareStatement("select * from Section where callNum=?");
			resetSection = conn.prepareStatement("truncate table Section");
			resetMeeting = conn.prepareStatement("truncate table Meeting");
			deleteSectionStatement = conn.prepareStatement("delete from Section where id = ?");
			deleteMeetingStatement = conn.prepareStatement("delete from Meeting where id = ?");
		}
		catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	//	/**
	//	* prepared statement for getting requirements
	//	*/
	//	private PreparedStatement listRequirementsStatement;
	//	/**
	//	* prepared statement for getting courses
	//	*/
	//	private PreparedStatement listCoursesStatement;
	//	/**
	//	* prepared statement for getting sections
	//	*/
	//	private PreparedStatement listSectionsStatement;

	/**
	 * gets all requirements from the database
	 * @return list - an arrayList of all the requirements
	 * @author David Sawyer
	 */
	public ArrayList<Requirement> getRequirementList(){
		ArrayList<Requirement> list = new ArrayList<Requirement>();
		int id;
		String requirementArea;
		try{
			ResultSet set = getRequirementListStatement.executeQuery();
			// set the received values to create a Requirement object
			while(set.next()) {
				id=set.getInt("id");
				requirementArea=set.getString("requirementArea");
				Requirement requirement = new Requirement(id, requirementArea);
				list.add(requirement);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving Requirements List\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}


	/**
	 * get all courses that belong to a particular requirement
	 * @param r the requirement
	 * @return list - an array list of all courses of type r
	 * @author David Sawyer
	 */
	public ArrayList<Course> getCourseList(int reqFulfilled){
		ArrayList<Course> list = new ArrayList<Course>();
		int id;
		String coursePrefix, courseNum;
		try{
			getCourseListStatement.setInt(1, reqFulfilled);
			ResultSet set = getCourseListStatement.executeQuery();
			// set the received values to create a Course object
			while(set.next()) {
				id=set.getInt("id");
				coursePrefix=set.getString("coursePrefix");
				courseNum=set.getString("courseNum");
				Course course = new Course(id, reqFulfilled, coursePrefix, courseNum);
				list.add(course);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving Course List\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	/**
	 * get all sections that belong to a particular course
	 * @param c the course
	 * @return list - an arrayList of all sections of course c
	 * @author David Sawyer
	 */
	public ArrayList<Section> getSectionList(int courseId){
		ArrayList<Section> list = new ArrayList<Section>();
		int id;
		String /*title,*/ instructor, callNum, creditHours;
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		try{
			getSectionListStatement.setInt(1, courseId);
			ResultSet set = getSectionListStatement.executeQuery();
			// set the received values to create a Section object
			while(set.next()) {
				id=set.getInt("id");
				callNum=set.getString("callNum");
				creditHours=set.getString("creditHours");
				instructor=set.getString("instructor");
				meetings = getMeetingList(id);
				Section section = new Section(id, callNum, creditHours, instructor, courseId, meetings);
				list.add(section);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving Section List\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}


	/**
	 * get all meetings that belong to a particular section
	 * @param c the course
	 * @return list - an arrayList of all meetings of section c
	 * @author David Sawyer
	 */
	public ArrayList<Meeting> getMeetingList(int sectionId){
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		int id;
		String timeStart, timeEnd, meetingDay, roomNumber, buildingNumber;
		try{
			getMeetingListStatement.setInt(1, sectionId);
			ResultSet set = getMeetingListStatement.executeQuery();
			// set the received values to create a Meeting object
			while(set.next()) {
				id=set.getInt("id");
				timeStart=set.getString("timeStart");
				timeEnd=set.getString("timeEnd");
				meetingDay=set.getString("meetingDay");
				roomNumber=set.getString("roomNumber");
				buildingNumber=set.getString("buildingNumber");
				Meeting meeting = new Meeting(id, timeStart, timeEnd, meetingDay, roomNumber, buildingNumber, sectionId);
				list.add(meeting);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving Section List\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}


	/**
	 * Sets a new Section
	 * @param callNum		the call number of the section
	 * @param creditHours	the amount of credit hours the course gives
	 * @param title			the title of the section
	 * @param instructor	the instructor of the section
	 * @param courseId		the course that the section is of
	 * @return true if the Section was successfully added, false if failed
	 * @author David Sawyer
	 */
	public boolean addSection(String callNum, String creditHours, String instructor, int courseId) {

		try {
			addSectionStatement.setString(1, callNum);
			addSectionStatement.setString(2, creditHours);
			addSectionStatement.setString(3, instructor);
			addSectionStatement.setInt(4, courseId);
			addSectionStatement.executeUpdate();
			//System.out.println("Added Section!");
		} catch (Exception e) {
			if (!e.getClass().getName().toString().equalsIgnoreCase("com.mysql.jdbc." +
					"exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {				
				System.out.println("Error adding section \n " + e.getClass().getName() + ": " + e.getMessage());
			}
			return false;
		}
		return true;
	}


	/**
	 * Sets a new Meeting
	 * @param timeStart			the time of the meeting
	 * @param timeEnd			the the ending time of the meeting
	 * @param meetingDay		the day of the meeting
	 * @param roomNumber		the instructor of the section
	 * @param buildingNumber	the building number of the meeting
	 * @param sectionId			the section the meeting is of
	 * @return true if the Meeting was successfully added, false if failed 
	 * @author David Sawyer
	 */
	public boolean addMeeting(String timeStart, String timeEnd, String meetingDay, String roomNumber,
			String buildingNumber, int sectionId) {

		try {
			addMeetingStatement.setString(1, timeStart);
			addMeetingStatement.setString(2, timeEnd);
			addMeetingStatement.setString(3, meetingDay);
			addMeetingStatement.setString(4, roomNumber);
			addMeetingStatement.setString(5, buildingNumber);
			addMeetingStatement.setInt(6, sectionId);
			addMeetingStatement.executeUpdate();
			//System.out.println("Added Meeting!");
		} catch (Exception e) {
			System.out.println("Error adding meeting \n " + e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}


	/**
	 * get a list of courses using course prefix and course number
	 * @param r the requirement
	 * @return list - an array list of all courses of type r
	 * @author David Sawyer
	 */
	public ArrayList<Course> getCourseList(String coursePrefix, String courseNum){
		//System.out.println("touched the beginning");
		ArrayList<Course> list = new ArrayList<Course>(0);
		int id, reqFulfilled;
		String returnedCoursePrefix, returnedCourseNum;
		try{
			getCourseStatement.setString(1, coursePrefix);
			getCourseStatement.setString(2, courseNum);
			ResultSet set = getCourseStatement.executeQuery();
			// set the received values to create a Course object
			while (set.next()) {
				id=set.getInt("id");
				reqFulfilled=set.getInt("reqFulfilled");
				returnedCoursePrefix=set.getString("coursePrefix");
				returnedCourseNum=set.getString("courseNum");
				Course course = new Course(id, reqFulfilled, returnedCoursePrefix, returnedCourseNum);
				list.add(course);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving single Course\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}


	/**
	 * get a list of sections a certain call number
	 * @param callNum - the call number for the Section
	 * @return list - a section with the callNum
	 * @author David Sawyer
	 */
	public ArrayList<Section> getSectionList(String callNum){
		ArrayList<Section> list = new ArrayList<Section>(0);
		int id, courseId;
		String instructor, newCallNum, creditHours;
		// we haven't populated meetings yet, so there is nothing to put in the list of meetings
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		try{
			getSectionStatement.setString(1, callNum);
			ResultSet set = getSectionStatement.executeQuery();
			// set the received values to create a Section object
			while (set.next()) {
				id=set.getInt("id");
				newCallNum=set.getString("callNum");
				creditHours=set.getString("creditHours");
				instructor=set.getString("instructor");
				courseId=set.getInt("courseId");
				meetings=getMeetingList(id);
				Section section = new Section(id, newCallNum, creditHours, instructor, courseId, meetings);
				list.add(section);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving Section List\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return list;
	}

	/**
	 * Resets the Section and Meeting tables
	 * @author David Sawyer
	 */
	public boolean resetSectionAndMeeting() {
		try{
			resetMeeting.executeQuery();
			resetSection.executeQuery();
		}
		catch(Exception e) {
			System.out.println("Error retrieving single Course\n " + e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}
	/**
	* deletes a section with a specific id
	* @author Will Henry
	*/
	public void deleteSection(int sectionId){
		try{
			deleteSectionStatement.setInt(1, sectionId);
			deleteSectionStatement.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error deleting Section: " + e.getMessage());
		}
	}
	/**
	* deletes a meeting with specific id
	* @author Will Henry
	*/
	public void deleteMeeting(int meetingId){
		try{
			deleteMeetingStatement.setInt(1, meetingId);
			deleteMeetingStatement.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error deleting meeting: " + e.getMessage());
		}
	}
	/**
	 * adds a section of a course to class list
	 * @param s the section
	 * @param classes the list of classes currently signed up for
	 */
	public void addClass(Section s, ArrayList<Section> classes){

	}
	/**
	* tests if their is a conflict
	* @author Will Henry
	*/
	public boolean isConflict(Section sectionToAdd, ArrayList<Section>sections){
		 for(Section section: sections){
		 	//checks if an existing section causes a conflict
		 	for(Meeting meeting: section.getMeetings()){
		 		//checks if a meeting in a section conflicts with all meetings in section to add
		 		int meetingStart = convertToMilitary(meeting.getTimeStart());
		 		int meetingEnd = convertToMilitary(meeting.getTimeEnd());
		 		for(Meeting meetingToAdd: sectionToAdd.getMeetings()){
		 			//a meeting in sectionToAdd is compared to meeting
		 			if(meetingToAdd.getMeetingDay().equals(meeting.getMeetingDay())){
		 				int meetingToAddStart = convertToMilitary(meetingToAdd.getTimeStart());
		 				int meetingToAddEnd = convertToMilitary(meetingToAdd.getTimeEnd());
		 				if(meetingToAddStart >= meetingStart && meetingToAddStart <= meetingEnd || 
		 					meetingToAddEnd >= meetingStart && meetingToAddEnd <= meetingEnd){
		 					return true;
		 				}
		 			}

		 		}
		 	}
		 }
		 return false;
	}
	/**
	* converts time to military time
	* @author Will Henry
	*/
	public int convertToMilitary(String time){
		int militaryHours = Integer.parseInt(time.substring(0,4))/100;
		int militaryMinutes = Integer.parseInt(time.substring(0,4))%100;
		if(time.charAt(4) == 'A'){
			if(militaryHours == 12){
				militaryHours = 0;
			}
			return militaryHours*100+militaryMinutes;
		}
		else{
			return militaryHours*100+militaryMinutes+1200;
		}
	}
	/*
	public String getCourseByCall(int callNum){
		String nameNum=null;

		try{
			getCourseTitleStatement.setInt(1,callNum);
			ResultSet set = getCourseTitleStatement.executeQuery();
			while(set.next()){
				nameNum = set.getString("coursePrefix") + set.getString("courseNum");
			}
		}
		catch(Exception e){
			System.out.println("Error concatenating title and number: " + e.getMessage());
		}
		return nameNum; 
	}*/

}
