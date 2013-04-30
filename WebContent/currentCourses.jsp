<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Courses</title>
</head>
<body>
<h2>Step 4: Adjust Schedule</h2>
<table>
<c:forEach items="${currentSectionTitles}" var="section">
	<tr><td>${section}</td><td><button name="${section}" onclick="removeClass(this)">Remove</button></td></tr>
</c:forEach>
</table>
</body>
</html>
