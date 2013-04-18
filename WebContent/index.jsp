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
	
	Section sec1 = new Section(1, 123, 4, "Intro..", "Staff", 1);
	Section sec2 = new Section(2, 124, 4, "Intro..", "Everett", 1);
	Section sec3 = new Section(3, 125, 3, "Intermediate...", "Staff", 2);
	Section sec4 = new Section(4, 126, 3, "Advanced...", "Staff", 3);
	ArrayList<Section> secList = new ArrayList<Section>();
	secList.add(sec1);
	secList.add(sec2);
	secList.add(sec3);
	secList.add(sec4);
	request.setAttribute("secList", secList);

	
	Meeting meet1 = new Meeting(1, "10:10", "11:00", "MWF", 207, 1023, 1);
	Meeting meet2 = new Meeting(2, "9:30", "10:45", "T", 207, 1023, 1);
	Meeting meet3 = new Meeting(3, "11:00", "12:15", "TR", 207, 1023, 2);
	Meeting meet4 = new Meeting(4, "12:20", "1:10", "W", 207, 1023, 2);
	Meeting meet5 = new Meeting(5, "8:00", "8:50", "MWF", 207, 1023, 3);
	Meeting meet6 = new Meeting(6, "9:05", "9:55", "MWF", 207, 1023, 4);
	ArrayList<Meeting> meetList = new ArrayList<Meeting>();
	meetList.add(meet1);
	meetList.add(meet2);
	meetList.add(meet3);
	meetList.add(meet4);
	meetList.add(meet5);
	meetList.add(meet6);
	request.setAttribute("meetList", meetList);


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
					alert(active);
				}
			});
		});
	</script>
	<script>
		function selectUpdate(sel){
			var value = sel.options[sel.selectedIndex].value;
			$.get(); 
		}
		
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
			<h3><a href="#">${course.coursePrefix} &nbsp; ${course.courseNum}</a></h3>
			<div>
				<c:forEach items="${secList}" var="section">
				<c:set var="currentSection" scope="request" value="${section.id}"/>
					<p><b>${section.callNum} - ${section.instructor}</b></p>
					<table border="1">
						<tr><th>Time</th><th>Days</th><th>Building</th><th>Room</th></tr>
						<c:forEach items="${meetList}" var="meeting">
							<c:if test="${currentSection == meeting.section}">
								<tr><td>${meeting.timeStart} - ${meeting.timeEnd}</td><td>${meeting.meetingDay}</td><td>${meeting.buildingNumber}</td><td>${meeting.roomNumber}</td></tr>
							</c:if>
						</c:forEach>
					</table>
				</c:forEach>
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
        
		//Draw the Days and Times
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
        
        //Draw the gridlines
        ctx.moveTo(100,0);
        ctx.lineTo(100,600);
        ctx.stroke();
        ctx.moveTo(200,0);
        ctx.lineTo(200,600);
        ctx.stroke();
        ctx.moveTo(300,0);
        ctx.lineTo(300,600);
        ctx.stroke();        
        ctx.moveTo(400,0);
        ctx.lineTo(400,600);
        ctx.stroke();
        ctx.moveTo(500,0);
        ctx.lineTo(500,600);
        ctx.stroke();
        
        ctx.strokeStyle = "#A8A8A8";
        for(var i=1; i<15; i++){
            ctx.moveTo(0,40*i);
            ctx.lineTo(600,40*i);
            ctx.stroke();
        }        
        
        ctx.font = "14px Times New Roman";
        ctx.fillStyle="black";
        ctx.fillRect(100,126,100,33);
        ctx.fillRect(300,126,100,33);
        ctx.fillRect(500,126,100,33);
        
        ctx.fillRect(100,160,100,33);
        ctx.fillRect(300,160,100,33);
        ctx.fillRect(500,160,100,33);
        
        ctx.fillRect(200,280,100,50);
        ctx.fillRect(400,280,100,50);
        
        ctx.fillStyle="white";
        ctx.fillText("CSCI4300",115,150);
        ctx.fillText("CSCI4300",315,150);
        ctx.fillText("CSCI4300",515,150);

        ctx.fillText("CSCI4320",115,184);
        ctx.fillText("CSCI4320",315,184);
        ctx.fillText("CSCI4320",515,184);

        ctx.fillText("CSCI4500",215,310);
        ctx.fillText("CSCI4500",415,310);



    </script>
</body>
</html>