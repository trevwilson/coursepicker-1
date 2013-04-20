<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*" %>
<%request.setAttribute("currentSection", -1);%>
<%request.setAttribute("currentMeeting", -1);%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
    
<html>
<head>
	<title>Table</title>
</head>
<body>

<%  
	Requirement Req1 = new Requirement(1, "Req1");
	Requirement Req2 = new Requirement(2, "Req2");
	Requirement Req3 = new Requirement(3, "Req3");
	Requirement Req4 = new Requirement(4, "Req4");
	Requirement Req5 = new Requirement(5, "Req5");
	Requirement Req6 = new Requirement(6, "Req6");
	Requirement Req7 = new Requirement(7, "Req7");
	ArrayList<Requirement> reqList = new ArrayList<Requirement>();
	reqList.add(Req1);
	reqList.add(Req2);
	reqList.add(Req3);
	reqList.add(Req4);
	reqList.add(Req5);
	reqList.add(Req6);
	reqList.add(Req7);
	request.setAttribute("reqList", reqList);
	
	Course course1 = new Course(1, 1, "CSCI", "1000");
	Course course2 = new Course(2, 1, "CSCI", "1001");
	Course course3 = new Course(3, 2, "MATH", "3500");
	ArrayList<Course> courseList = new ArrayList<Course>();
	courseList.add(course1);
	courseList.add(course2);
	courseList.add(course3);
	request.setAttribute("courseList", courseList);
		
	final Meeting meet1 = new Meeting(1, "10:10", "11:00", "MWF", "207", "1023", 1);
	final Meeting meet2 = new Meeting(2, "9:30", "10:45", "T", "207", "1023", 1);
	final Meeting meet3 = new Meeting(3, "11:00", "12:15", "TR", "207", "1023", 2);
	final Meeting meet4 = new Meeting(4, "12:20", "1:10", "W", "207", "1023", 2);
	final Meeting meet5 = new Meeting(5, "8:00", "8:50", "MWF", "207", "1023", 3);
	final Meeting meet6 = new Meeting(6, "9:05", "9:55", "MWF", "207", "1023", 4);
	final ArrayList<Meeting> meetList = new ArrayList<Meeting>();
	
	meetList.add(meet1);
	meetList.add(meet2);
	meetList.add(meet3);
	meetList.add(meet4);
	meetList.add(meet5);
	meetList.add(meet6);
	request.setAttribute("meetList", meetList);

	Section sec1 = new Section(1, "123", "4", "Staff", 1, new ArrayList<Meeting>(){{add(meet1);add(meet2);}});
	Section sec2 = new Section(2, "124", "4", "Everett", 1, new ArrayList<Meeting>(){{add(meet3);add(meet4);}});
	Section sec3 = new Section(3, "125", "3", "Staff", 2, new ArrayList<Meeting>(){{add(meet4);}});
	Section sec4 = new Section(4, "126", "3", "Staff", 3, new ArrayList<Meeting>(){{add(meet5);}});
	ArrayList<Section> secList = new ArrayList<Section>();
	secList.add(sec1);
	secList.add(sec2);
	secList.add(sec3);
	secList.add(sec4);
	request.setAttribute("secList", secList);

%>

<c:forEach items="${secList}" var="section">
	<p><b>${section.callNum} - ${section.instructor}</b> &nbsp;</p>
	<table border="1">
		<tr><th>Time</th><th>Days</th><th>Building</th><th>Room</th></tr>
		<c:forEach items="${section.meetings}" var="meeting">		
			<tr><td>${meeting.timeStart} - ${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.buildingNumber}</td><td>${meeting.roomNumber}</td></tr>
		</c:forEach>
	</table>
</c:forEach>

</body>
</html>