<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*,helper.*" %>
<%Helper helper = new Helper();%>

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
	    <h2>Step 3: View Schedule</h2>
	    <canvas id="schedule" width="600" height="680"
	    style="border:1px solid #000000">
	    </canvas>
		
	<script>drawCanvas()</script>
	<script>
	<%if(session.getAttribute("error") != null && session.getAttribute("error").equals("true")){%>
		alert("Error: Couldn't add section - conflicts with current schedule");
		<%session.setAttribute("error","false");
	}
	else if(session.getAttribute("error") != null && session.getAttribute("error").equals("samesec")){%>
		alert("Error: Couldn't add section - you are already signed up for that course");
		<%session.setAttribute("error","false");
	}
	ArrayList<Section> currentSectionsList = new ArrayList<Section>();
	ArrayList<String> currentSectionTitles = new ArrayList<String>();
	currentSectionsList = (ArrayList<Section>)(session.getAttribute("currentCourses"));
	currentSectionTitles = (ArrayList<String>)(session.getAttribute("currentSectionTitles"));
	if(currentSectionsList != null && currentSectionsList.size() > 0){
		for(int i=0; i<currentSectionsList.size(); i++){
			ArrayList<Meeting> sectionMeetings = new ArrayList<Meeting>();
			sectionMeetings = currentSectionsList.get(i).getMeetings();
			if(sectionMeetings != null && sectionMeetings.size() > 0){
				for(int j=0; j<sectionMeetings.size(); j++){%>
					drawClass("<%=currentSectionTitles.get(i)%>","<%=currentSectionTitles.get(i)%>","<%=helper.convertToMilitary(sectionMeetings.get(j).getTimeStart())%>","<%=helper.convertToMilitary(sectionMeetings.get(j).getTimeEnd())%>","<%=sectionMeetings.get(j).getMeetingDay()%>");
				<%}
			}
		}
	}%>
	</script>
	</div>
    
</body>
</html>
