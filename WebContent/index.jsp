<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*,java.util.*,helper.*" %>
<%
Helper reqListHelper = new Helper();
request.setAttribute("reqList", reqListHelper.getRequirementList());%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
    <title>Course Finder</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
   	<script src="./alljs.js"></script>
</head>

<body>
	<div style="float:left">
		<h2>Select Requirement:</h2>
		<form>
			<select id="requirements" name="reqID" onChange="selectUpdate(this)">
				<c:forEach items="${reqList}" var="requirement">
					<option value="${requirement.id}">${requirement.requirementArea}</option>
				</c:forEach>
			</select>
		</form>
		<br>
		<h2>Classes to fulfill the requirement:</h2>
		<div id="courseAccordion">	
			<jsp:include page="courseAccordion.jsp"/>
		</div>
	</div>
	<div style="float:right" id="canvasSchedule">
		<jsp:include page="schedule.jsp"/>
	</div>
</body>
</html>