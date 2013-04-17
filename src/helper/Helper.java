package helper;
import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		}
		catch(Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

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
	 * get all meetings that belong to a particular section
	 * @param c the course
	 * @return list - an arrayList of all meetings of section c
	 */
	public ArrayList<Section> getMeetingList(){
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
