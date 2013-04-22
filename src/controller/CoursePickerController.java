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
	 * welcome page
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
				System.out.println("Could not parse requirementId.");
			}
		}
		
		dispatcher = ctx.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * handles getting all requirements, courses, sections, and adding a course
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		Helper helper = new Helper();
		HttpSession session = request.getSession();
		
		String callNum = request.getParameter("callNum");
		String requirementId = request.getParameter("requirementId");
		String courseId = request.getParameter("courseId");

		//Receive callNum of a course selection. Then add to currentCourses list attribute
		//(receive param from schedule table which is then reflected in the schedule)
		if(callNum!=null)
		{	
			//method which takes in courseId then finds and returns the corresponding course
			Section currentSection = helper.getSection(callNum);
			ArrayList<Section> currentSectionsList = new ArrayList<Section>();
			currentSectionsList.add(currentSection);
			session.setAttribute("currentCourses", currentSectionsList);
			return;
		}
		//take in requirementId and set the session attribute such that it contains the given courseList.
		//(receive param from index.jsp)
		else if(requirementId!=null)
		{
			ArrayList<Course> courseList = helper.getCourseList(Integer.parseInt(requirementId));
			session.setAttribute("courseList", courseList);
			return;
		}
		//receiving courseId from course accordian(dropdown).
		//set session object to  secForCourseList
		else if(courseId!=null)
		{
			ArrayList<Section> courseList = helper.getSectionList(Integer.parseInt(courseId));
			session.setAttribute("secForCourseList", courseList);
			return;
		}		
		
	}

}
