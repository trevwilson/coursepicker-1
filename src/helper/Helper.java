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
	 * Empty constructor. Opens a connection to the database and sets up PreparedStatements
	 */
	public Helper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("---Instantiated MySQL driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csci4300", "root", "mysql");
			System.out.println("---Connected to MySQL!");
			
			getRequirementListStatement = conn.prepareStatement("select * from Requirement");
			getCourseListStatement = conn.prepareStatement("select * from Course where reqFulfilled=?");
			getSectionListStatement = conn.prepareStatement("select * from Section where courseId=?");
			getMeetingListStatement = conn.prepareStatement("select * from Meeting where sectionId=?");
			addSectionStatement = conn.prepareStatement("insert into Section (callNum, creditHours, title," + 
					"instructor, courseId) values (?,?,?,?,?)");
			addMeetingStatement = conn.prepareStatement("insert into Meeting (timeStart, timeEnd, meetingDay," + 
					"roomNumber, buildingNumber, sectionId) values (?,?,?,?,?,?)");
			getCourseStatement = conn.prepareStatement("select * from Course where coursePrefix=? and courseNum=?");
		}
		catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	* prepared statement for getting requirements
	*/
	private PreparedStatement listRequirementsStatement;
	/**
	* prepared statement for getting courses
	*/
	private PreparedStatement listCoursesStatement;
	/**
	* prepared statement for getting sections
	*/
	private PreparedStatement listSectionsStatement;
	
	/**
	 * gets all requirements from the database
	 * @return list - an arrayList of all the requirements
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
	 */
	public ArrayList<Section> getSectionList(int courseId){
		ArrayList<Section> list = new ArrayList<Section>();
		int id, callNum, creditHours;
		String title, instructor;
		try{
			getSectionListStatement.setInt(1, courseId);
			ResultSet set = getSectionListStatement.executeQuery();
			// set the received values to create a Section object
			while(set.next()) {
				id=set.getInt("id");
				callNum=set.getInt("callNum");
				creditHours=set.getInt("creditHours");
				title=set.getString("title");
				instructor=set.getString("instructor");
				
				//getMeetingList(int sectionId)
				
				Section section = new Section(id, callNum, creditHours, title, instructor, courseId);
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
	 */
	public ArrayList<Meeting> getMeetingList(int sectionId){
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		int id, roomNumber, buildingNumber;
		String timeStart, timeEnd, meetingDay;
		try{
			getMeetingListStatement.setInt(1, sectionId);
			ResultSet set = getMeetingListStatement.executeQuery();
			// set the received values to create a Meeting object
			while(set.next()) {
				id=set.getInt("id");
				timeStart=set.getString("timeStart");
				timeEnd=set.getString("timeEnd");
				meetingDay=set.getString("meetingDay");
				roomNumber=set.getInt("roomNumber");
				buildingNumber=set.getInt("buildingNumber");
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
	 */
	public boolean addSection(int callNum, int creditHours, String title, String instructor, String courseId) {

		try {
			addSectionStatement.setInt(1, callNum);
			addSectionStatement.setInt(2, creditHours);
			addSectionStatement.setString(3, title);
			addSectionStatement.setString(4, instructor);
			addSectionStatement.setString(5, courseId);
			addSectionStatement.executeUpdate();
			System.out.println("Added Section!");
		} catch (Exception e) {
			System.out.println("Error adding section \n " + e.getClass().getName() + ": " + e.getMessage());
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
	 */
	public boolean addMeeting(String timeStart, String timeEnd, String meetingDay, int roomNumber,
			int buildingNumber, int sectionId) {

		try {
			addMeetingStatement.setString(1, timeStart);
			addMeetingStatement.setString(2, timeEnd);
			addMeetingStatement.setString(3, meetingDay);
			addMeetingStatement.setInt(4, roomNumber);
			addMeetingStatement.setInt(5, buildingNumber);
			addMeetingStatement.setInt(6, sectionId);
			addMeetingStatement.executeUpdate();
			System.out.println("Added Meeting!");
		} catch (Exception e) {
			System.out.println("Error adding meeting \n " + e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * get a specific course
	 * @param r the requirement
	 * @return list - an array list of all courses of type r
	 */
	public Course getCourseList(String coursePrefix, String courseNum){
		Course course = null;
		int id, reqFulfilled;
		String returnedCoursePrefix, returnedCourseNum;
		try{
			getCourseStatement.setString(1, coursePrefix);
			getCourseStatement.setString(2, courseNum);
			ResultSet set = getCourseListStatement.executeQuery();
			// set the received values to create a Course object
			while(set.next()) {
				id=set.getInt("id");
				reqFulfilled=set.getInt("reqFulfilled");
				returnedCoursePrefix=set.getString("coursePrefix");
				returnedCourseNum=set.getString("courseNum");
				course = new Course(id, reqFulfilled, returnedCoursePrefix, returnedCourseNum);
			}
		}
		catch(Exception e) {
			System.out.println("Error retrieving single Course\n " + e.getClass().getName() + ": " + e.getMessage());
		}
		return course;
	}
	
	/**
	 * adds a section of a course to class list
	 * @param s the section
	 * @param classes the list of classes currently signed up for
	 */
	public void addClass(Section s, ArrayList<Section> classes){

	}
}
