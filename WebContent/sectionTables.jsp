<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*" %>

<%request.setAttribute("currentSection", -1);%>
<%request.setAttribute("currentMeeting", -1);%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
    
<html>
<head>
	<title>Table</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	<script src="./alljs.js"></script>
	
</head>
<body>

<c:forEach items="${secForCourseList}" var="section">
	<p><b>${section.callNum} - ${section.instructor}</b> &nbsp; <button name="${section.callNum}" onclick="registerClass(this)">Add</button></p>
	<table border="1">
		<tr><th>Time</th><th>Days</th><th>Building</th><th>Room</th></tr>
		<c:forEach items="${section.meetings}" var="meeting">		
			<tr><td>${meeting.timeStart} - ${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.buildingNumber}</td><td>${meeting.roomNumber}</td></tr>
		</c:forEach>
	</table>
</c:forEach>

</body>
</html>