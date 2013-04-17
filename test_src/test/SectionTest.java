package test;

import junit.framework.TestCase;
import java.util.ArrayList;
import model.*;
import helper.*;

/**
* Tester for the Section model class, which in turn tests the meeting class
* @author Will Henry
*/
public class SectionTest extends TestCase{

	/**
	* creates a Section from the constructor and makes sure that the get methods work
	* @author Will Henry
	*/
	public void testConstructor {
		ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		//create 2 meeting objects and add to meetings
		Meeting meeting1(1, "200P", "315P", "T R", 206, 1029, 1);
		meetings.add(meeting1);
		Meeting meeting2(2, "230P", "320P", "M", 206, 1029, 1);
		meetings.add(meeting2); 

		//create first section and test getters
		Section section1 = new Section(1, 123456, 4, "Dan Everett", 1, meetings);
		assertEquals("Section 1 id", 1, section1.getId());
		assertEquals("Section 1 callNum", 123456, section1.getCallNum());
		assertEquals("Section 1 credit hours", 4, section1.getCreditHours());
		assertEquals("Section 1 instructor", "Dan Everett", section1.getCreditHours());
		assertEquals("Section 1 courseId", 1, section1.getCourseId());
		assertEquals("Section 1' meeting list size", 2, section1.getMeetings().size());
		Meeting firstMeeting = section1.getMeetings().get(0);
		Meeting secondMeeting = section1.getMeetings().get(1);

		//test first meeting
		assertEquals("Meeting 1 id", 1, firstMeeting.getId());
		assertEquals("Meeting 1 start time", "200P", firstMeeting.getTimeStart());
		assertEquals("Meeting 1 end time", "315P", firstMeeting.getTimeEnd());
		assertEquals("Meeting 1 days", "T R", firstMeeting.getMeetingDay());
		assertEquals("Meeting 1 room number", 206, firstMeeting.getRoomNumber());
		assertEquals("Meeting 1 building number", 1029, firstMeeting.getBuildingNumber());
		assertEquals("Meeting 1 section id", 1, firstMeeting.getSectionId());

		//test second meeting
		assertEquals("Meeting 2 id", 1, secondMeeting.getId());
		assertEquals("Meeting 2 start time", "230P", secondMeeting.getTimeStart());
		assertEquals("Meeting 2 end time", "320P", secondMeeting.getTimeEnd());
		assertEquals("Meeting 2 days", "M", secondMeeting.getMeetingDay());
		assertEquals("Meeting 2 room number", 206, secondMeeting.getRoomNumber());
		assertEquals("Meeting 2 building number", 1029, secondMeeting.getBuildingNumber());
		assertEquals("Meeting 2 section id", 1, secondMeeting.getSectionId());
	}

}
