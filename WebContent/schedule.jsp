<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
	<title>Schedule</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

	<script src="./alljs.js"></script>

	
</head>
<body>
	<div id="schedulediv">
	    <h2>Schedule View</h2>
	    <canvas id="schedule" width="600" height="600"
	    style="border:1px solid #000000">
	    </canvas>
		
	<script>drawCanvas()</script>
	<script>
	<%ArrayList<Section> currentSectionsList = (ArrayList<Section>)(session.getAttribute("currentSectionsList"));
	for(int i=0; i<currentSectionsList.size(); i++){
		ArrayList<Meeting> sectionMeetings = currentSectionsList.get(i).getMeetings();
		for(int j=0; j<sectionMeetings.size(); j++){%>
			drawClass("<%=currentSectionsList.get(i).getCallNum()%>","<%=currentSectionsList.get(i).getCallNum()%>","<%=sectionMeetings.get(j).getTimeStart()%>","<%=sectionMeetings.get(j).getTimeEnd()%>","<%=sectionMeetings.get(j).getMeetingDay()%>");
		<%}
	}
	%>
	</script>
	</div>
    
</body>
</html>