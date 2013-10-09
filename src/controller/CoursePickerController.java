package controller;

import helper.Helper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.Section;

/**
 * Controller servlet for the course picker application
 */
@WebServlet("/CoursePickerController")
public class CoursePickerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public CoursePickerController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * method to deal with GET requests
	 * @ author David Sawyer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = this.getServletContext();
		RequestDispatcher dispatcher;

		Helper helper = new Helper();

		String requirementId = request.getParameter("requirementId");
		String courseId = request.getParameter("courseId");

		if (requirementId != null) {
			try {
				ArrayList<Course> courses = helper.getCourseList(Integer.parseInt(requirementId));
				request.setAttribute("courses", courses);
			} catch (Exception e) {
				System.out.println("Could not parse requirementId.");
			}
		}
		else if (courseId != null) {
			try {				
				ArrayList<Section> sections = helper.getSectionList(Integer.parseInt(courseId));
				request.setAttribute("sections", sections);
			} catch (Exception e) {
				System.out.println("Could not parse courseId.");
			}
		}

		dispatcher = ctx.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * handles getting all requirements, courses, sections, and adding a course
	 * @author Ryan Linnane
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		Helper helper = new Helper();
		HttpSession session = request.getSession();

		String callNum = request.getParameter("callNum");
		String requirementId = request.getParameter("requirementId");
		String courseId = request.getParameter("courseId");
		String callNumRem = request.getParameter("callNumRem");

		
		//If user is trying to add a section, store the Section info and Course title in a session
		//attribute to be used to draw the canvas
		if(callNum!=null)
		{	
			Section currentSection = helper.getSectionList(callNum).get(0);
			String currentST = helper.getCourseByCall(Integer.parseInt(callNum));
			ArrayList<Section> currentCourses;
			ArrayList<String> currentSectionTitle;
		
			//If this is the first course added
			if(session.getAttribute("currentCourses") == null){
				currentCourses = new ArrayList<Section>();
				currentSectionTitle = new ArrayList<String>();
			}

			//If there are already other courses
			else{
				currentCourses = (ArrayList<Section>)(session.getAttribute("currentCourses"));
				currentSectionTitle = (ArrayList<String>)(session.getAttribute("currentSectionTitles"));
			}

			//session.setAttribute("currentSectionTitles"))							

			//Check for timing conflicts
			if(helper.isConflict(currentSection, currentCourses)){
				session.setAttribute("error", "true");
				return;
			}

			//Compare the course title being added to a current course in the registered list
			for(int i=0; i<currentSectionTitle.size(); i++){
				//Check for adding the same course twice
				if(currentST.equals(currentSectionTitle.get(i))){
					session.setAttribute("error", "samesec");
					return;
				}
			}//for

			currentCourses.add(currentSection);
			currentSectionTitle.add(currentST);
			session.setAttribute("currentCourses",currentCourses);
			session.setAttribute("currentSectionTitles", currentSectionTitle);
			return;
		}
		//take in requirementId and set the session attribute such that it contains the given courseList.
		//(receive param from index.jsp)

		//Called when user selects a requirement from the dropdown select box, used to load the proper course accordions
		else if(requirementId!=null)
		{
			ArrayList<Course> courseList = helper.getCourseList(Integer.parseInt(requirementId));
			session.setAttribute("courseList", courseList);
			return;
		}

		//Called when user removes a course, deletes the Section and the Course title from the session attributes
		else if(callNumRem != null){
			ArrayList<Section> currentSections = new ArrayList<Section>();
			ArrayList<String> currentSectionTitles = new ArrayList<String>();
			currentSections = (ArrayList<Section>)(session.getAttribute("currentCourses"));
			currentSectionTitles = (ArrayList<String>)(session.getAttribute("currentSectionTitles"));

			//Compare the course title to be removed to an item in the current list of classes
			for(int i=0; i<currentSectionTitles.size(); i++){

				//Check to see if this is the course to be removed
				if(currentSectionTitles.get(i).equals(callNumRem)){
					currentSections.remove(i);
					currentSectionTitles.remove(i);
				}
			}//for

			session.setAttribute("currentCourses",currentSections);
			session.setAttribute("currentSectionTitles",currentSectionTitles);
			return;
		}

		//receiving courseId from course accordian(dropdown).
		//set session object to list of sections for that course
		else if(courseId!=null)
		{
			ArrayList<Section> courseList = helper.getSectionList(Integer.parseInt(courseId));
			session.setAttribute("secForCourseList", courseList);
			return;
		}		

	}

}
