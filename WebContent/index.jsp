<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*" %>
<%request.setAttribute("currentSection", -1);%>
<%request.setAttribute("currentMeeting", -1);%>

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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
    <title>Course Finder</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <script>
		$(function() {
			$( "#accordion").accordion({
				collapsible:true,
				active: true,
				activate: function(event, ui){
					var active = $("#accordion").accordion("option", "active");
					$.get("./Control",function(data){
						//var courseList = $.parseJSON(data);
						alert("got data from servlet");
						<c:forEach items="${courseList}" var="course">
						$("#accordioncontents").append("<h3><a href=\"#\">${course.coursePrefix}  ${course.courseNum}</a></h3>");
						$("#accordioncontents").append("<div>");
							$("#accordioncontents").append("<c:forEach items=\"${secList}\" var=\"section\">");
							$("#accordioncontents").append("<form action=\"javascript:drawMeetingsForSection(&quot;${course.coursePrefix}&quot;,&quot;${course.courseNum}&quot;,&quot;${section.meetings}&quot;)\">");
							$("#accordioncontents").append("<c:set var=\"currentSection\" scope=\"request\" value=\"${section.id}\"/>");
								$("#accordioncontents").append("<p><b>${section.callNum} - ${section.instructor}</b> &nbsp; <input id=\"sectionadd\" type=\"Submit\" value=\"Add Section\"/> </p>");
								$("#accordioncontents").append("<table border=\"1\">");
									$("#accordioncontents").append("<tr><th>Time</th><th>Days</th><th>Building</th><th>Room</th></tr>");
									$("#accordioncontents").append("<c:forEach items=\"${meetList}\" var=\"meeting\">");
										$("#accordioncontents").append("<c:if test=\"${currentSection == meeting.sectionId}\">");			
											$("#accordioncontents").append("<tr><td>${meeting.timeStart} - ${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.buildingNumber}</td><td>${meeting.roomNumber}</td></tr>");
										$("#accordioncontents").append("</c:if>");
									$("#accordioncontents").append("</c:forEach>");
								$("#accordioncontents").append("</table>");
							$("#accordioncontents").append("</form>");
							$("#accordioncontents").append("</c:forEach>");
		//$(function() {
		//	$("#sectionadd").click(){
		//		
		//	}
		//});
	</script>
</head>
<body>
	<div style="float:left">
	<h2>Select Requirement:</h2>
	<form>
		<select id="requirements" name="reqID" onChange="selectUpdate(this)">
			<c:forEach items="${reqList}" var="requirement">
				<option value="${requirement.id}">${requirement.requirementArea}</option>
			</c:forEach>
			<!-- <option value="1">Cultural Diversity Requirement</option>
			<option value="2">Environmental Literacy Requirement</option>
			<option value="3">Core Curriculum I: Foundation Courses</option>
			<option value="4">Core Curriculum II: Physical Sciences</option>
			<option value="5">Core Curriculum II: Life Sciences</option>
			<option value="6">Core Curriculum III: Quantitative Reasoning</option>
			<option value="7">Core Curriculum IV: World Languages and Culture</option>
			<option value="8">Core Curriculum IV: Humanities and Arts</option>
			<option value="9">Core Curriculum V: Social Sciences</option>
			<option value="10">Franklin College Foreign Language</option>
			<option value="11">Franklin College Literature</option>
			<option value="12">Franklin College Fine Arts/Philosophy/Religion</option>
			<option value="13">Franlin College History</option>
			<option value="14">Franklin CollegeSocial Sciences other than History</option>
			<option value="15">Franklin College Biological Sciences</option>
			<option value="16">Franklin College:Physical Sciences</option>
			<option value="17">Franklin College Multicultural Requirement</option>
			<option value="18">Core CurriculumVI: Major related courses</option>
			<option value="19">Computer Science Major Courses</option>-->
		</select>
		<input type="submit" value="Search"/>
	</form>
	<br>
	<h2>Classes to fulfill the requirement:</h2>
	<div id="accordion">
		<c:forEach items="${courseList}" var="course">
			<h3><a href="#">${course.coursePrefix}  ${course.courseNum}</a></h3>
			<div id="accordioncontents">
				<!--<c:forEach items="${secList}" var="section">
				<form action="javascript:drawMeetingsForSection(&quot;${course.coursePrefix}&quot;,&quot;${course.courseNum}&quot;,&quot;${section.meetings}&quot;)">
				<c:set var="currentSection" scope="request" value="${section.id}"/>
					<p><b>${section.callNum} - ${section.instructor}</b> &nbsp; <input id="sectionadd" type="Submit" value="Add Section"/> </p>
					<table border="1">
						<tr><th>Time</th><th>Days</th><th>Building</th><th>Room</th></tr>
						<c:forEach items="${meetList}" var="meeting">
							<c:if test="${currentSection == meeting.sectionId}">			
								<tr><td>${meeting.timeStart} - ${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.buildingNumber}</td><td>${meeting.roomNumber}</td></tr>
							</c:if>
						</c:forEach>
					</table>
				</form>
				</c:forEach>-->
			</div>
		</c:forEach>
	</div>
	
	
	
	
	
	<!-- <form>
		<table border="1">
			<tr><th>Department</th><th>Number</th><th>Time</th><th>Days</th><th>Add to Schedule</th></tr>
			<c:forEach items="${meetingList}" var="meeting">
				<c:if test="${meeting.section.course.id != currentCourse}">
					<c:set var="currentCourse" scope="request" value="${meeting.section.course.id}"/>
					<tr><td>${meeting.section.course.coursePrefix}</td><td>${meeting.section.course.courseNum}</td><td>${meeting.section.title}</td><td>${meeting.section.creditHours}</td><td></td><td></td><td></td></tr>
				</c:if>
				<c:choose>
					<c:when test="${meeting.section.callNum != currentSection}">
						<c:set var="currentSection" scope="request" value="${meeting.section.callNum}"/>
						<tr><td><input type="submit" value="Add"/></td><td>${meeting.section.callNum}</td><td>${meeting.timeStart}-${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.section.instructor}</td><td>${meeting.roomNumber}</td><td>${meeting.buildingNumber}</td></tr>
					</c:when>
					<c:when test="${meeting.section.callNum == currentSection}">
						<tr><td></td><td></td><td>${meeting.timeStart}-${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.section.instructor}</td><td>${meeting.roomNumber}</td><td>${meeting.buildingNumber}</td></tr>
					</c:when>
				</c:choose>
			</c:forEach>	
		</table>
	</form>-->
	</div>
	<div style="float:right">
    <h2>Schedule View</h2>
    <canvas id="schedule" width="600" height="600"
    style="border:1px solid #000000;">
    </canvas>
	<h2>Current Classes</h2>
	<form>
		<table border="1">
			<tr><th>Department</th><th>Number</th><th>Time</th><th>Days</th><th>Remove from Schedule</th></tr>
			<tr><td>CSCI</td><td>4300</td><td>10:10-11:00</td><td>MWF</td><td><input type="submit" value="Remove"></td>
			<tr><td>CSCI</td><td>4320</td><td>11:15-12:05</td><td>MWF</td><td><input type="submit" value="Remove"></td>
			<tr><td>CSCI</td><td>4500</td><td>2:00-3:15</td><td>TR</td><td><input type="submit" value="Remove"></td>
		</table>
	</form>
	
	</div>
    <script>
        var c=document.getElementById("schedule");
        var ctx=c.getContext("2d");
        drawDaysTimes();
        drawGrid();
        
        //Draw the Days and Times
        function drawDaysTimes(){
			ctx.font="bold 18px Arial";
	        ctx.fillText("Monday",105,30);
	        ctx.fillText("Tuesday",205,30);
	        ctx.fillText("Wed.",305,30);
	        ctx.fillText("Thursday",405,30);
	        ctx.fillText("Friday",505,30);
	        ctx.fillText("8:00am",5,70);
	        ctx.fillText("9:00",5,110);
	        ctx.fillText("10:00",5,150);
	        ctx.fillText("11:00",5,190);
	        ctx.fillText("12:00pm",5,230);
	        ctx.fillText("1:00",5,270);
	        ctx.fillText("2:00",5,310);
	        ctx.fillText("3:00",5,350);
	        ctx.fillText("4:00",5,390);
	        ctx.fillText("5:00",5,430);
	        ctx.fillText("6:00",5,470);
	        ctx.fillText("7:00",5,510);
	        ctx.fillText("8:00",5,550);
	        ctx.fillText("9:00",5,590);
		}
        
        //Draw the gridlines
        function drawGrid(){
	        //Vertical
        	for(var i=1; i<6; i++){
	        	ctx.moveTo(i*100,0);
	        	ctx.lineTo(i*100,600);
	        	ctx.stroke();
	        }
        	
			//Horizontal
	        ctx.strokeStyle = "#A8A8A8";
	        for(var i=1; i<15; i++){
	            ctx.moveTo(0,40*i);
	            ctx.lineTo(600,40*i);
	            ctx.stroke();
	        }    
        }
        
        function drawClass(prefix, num, start, end, days){
        	alert("drawClass" + prefix + num + start + end + days);
        	var c=document.getElementById("schedule");
            var ctx=c.getContext("2d");
        	var newStart = "";
        	var newEnd = "";
        	for(var i=0; i<start.length; i++){
                if(!(start[i] === ':')){
        			newStart += start[i];
        		}
        	}
        	for(var i=0; i<end.length; i++){
                if(!(end[i] === ':')){
        			newEnd += end[i];
        		}
            }
        	if(newStart.length == 3){
        		newStart = "0" + newStart;
        	}
            if(newEnd.length == 3){
        		newEnd = "0" + newEnd;
        	}
            ctx.font = "14px Times New Roman";
            
            var xStart = (40 + 40*(parseInt((newStart.substring(0,2))%8)) + .6666667*parseInt(newStart.substring(2,4)));
    		var xEnd = (40 + 40*(parseInt((newEnd.substring(0,2))%8)) + .6666667*parseInt(newEnd.substring(2,4)));
    	    
    		alert(days.length);
    		alert(days);
    		
        	for(var i=0; i<days.length; i++){
        		alert(i);
        		if(days[i] === 'M'){
                    ctx.fillStyle="black";
        			ctx.fillRect(100,xStart,100,xEnd-xStart);
        	        ctx.fillStyle="white";
					ctx.fillText(prefix+num,115,xStart+25);
        		}
        		if(days[i] === 'T'){
                    ctx.fillStyle="black";
        			ctx.fillRect(200,xStart,100,xEnd-xStart);
        	        ctx.fillStyle="white";
					ctx.fillText(prefix+num,215,xStart+25);

        		}
        		else if(days[i] === 'W'){
                    ctx.fillStyle="black";
        			ctx.fillRect(300,xStart,100,xEnd-xStart);
        	        ctx.fillStyle="white";
					ctx.fillText(prefix+num,315,xStart+25);

        		}
        		if(days[i] === 'R'){
                    ctx.fillStyle="black";
        			ctx.fillRect(400,xStart,100,xEnd-xStart);
        	        ctx.fillStyle="white";
					ctx.fillText(prefix+num,415,xStart+25);

        		}
        		else if(days[i] === 'F'){
                    ctx.fillStyle="black";
        			ctx.fillRect(500,xStart,100,xEnd-xStart);
        	        ctx.fillStyle="white";
					ctx.fillText(prefix+num,515,xStart+25);
        		}
        		
        	}
        	alert("done drawing");
     
        }
    </script>
</body>
</html>