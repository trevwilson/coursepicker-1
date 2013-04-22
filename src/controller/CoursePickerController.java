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
	}

}
