<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*" %>
<%request.setAttribute("currentSection", -1);%>
<%request.setAttribute("currentMeeting", -1);%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">


<html>
<head>
	<title>Courses</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	<script src="./alljs.js"></script>
	
	<script>
	function loadSecForCourse(event,ui){
		$.ajaxSetup({async: false,cache: false});
		$.post("./CoursePickerController", {courseId:ui.newHeader[0].id});
		$.get("./sectionTables.jsp");
		$.get("./sectionTables.jsp", function(data){
			$("#accordioncontents" + ui.newHeader[0].id).html(data);
		});
	};
	$(document).ready(function() {
        $( "#accordion" ).accordion({
        	collapsable:true,
        	active:true,
        	heightStyle:"content",
			activate:loadSecForCourse
        });
    });
	</script>
</head>
<body>
	<div id="accordion">
		<c:forEach items="${courseList}" var="course">
			<h3 id="${course.id}"><a href="#">${course.coursePrefix}  ${course.courseNum}</a></h3>
				<div id="accordioncontents${course.id}">
				</div>
		</c:forEach>
	</div>
</body>

</html>