<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<title>Student Preferences</title>

 <style type="text/css">
 
.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
.TFtable td{ 
		padding:7px; border:#4e95f4 1px solid;
	}
	/* provide some minimal visual accomodation for IE8 and below */
	.TFtable tr{
		background: #b8d1f3;
	}
	/*  Define the background color for all the ODD background rows  */
	.TFtable tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	/*  Define the background color for all the EVEN background rows  */
	.TFtable tr:nth-child(even){
		background: #dae5f4;
	}
  .row {
  width: 100%;
   top: 20;
   padding-top: 300px;
  }    

.container h2{
	color: #F5F5DC;
	text-align: center;
	}
.container h5{
	color: #F5F5DC;
	text-align: center;
	}
 </style>


<body style="background-color: #4b5257">

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Automated Student Project Allocation Tool</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/students/">Students</a></li>
      <li><a href="/projects/">Projects</a></li>
      <li><a href="/preferences/">Preferences</a></li>
      <li><a href="/allocations/">Allocations</a></li>
      <li><a href="/logout/">Logout</a></li>
    </ul>
  </div>
</nav>
  
<div class="container" style="margin-top:50px">
  <h2>Student Preferences</h2>
<br>
<h5>Below are all the students and their 3 preferences for projects from the list of available projects. </h5>
<br>
<br>
<table class="TFtable">
<tr>
  <td><h3>Name</h3></td>
  <td><h3>Course Code</h3></td>
  <td><h3>Course Name</h3></td>
  <td><h3>Preference 1</h3></td>
  <td><h3>Preference 2</h3></td>
  <td><h3>Preference 3</h3></td>
</tr>
<c:forEach items="${prefList}" var="pref">
<tr>
	<td><c:out value="${pref.getStudentName()}"/></td>
	<td><c:out value="${pref.getStudentCourse().getCourseCode()}"/></td>
	<td><c:out value="${pref.getStudentCourse().getCourseName()}"/></td>
	<td><c:out value="${pref.getFirstPreference().getName()}"/></td>
	<td><c:out value="${pref.getSecondPreference().getName()}"/></td>
	<td><c:out value="${pref.getThirdPreference().getName()}"/></td>
</tr>
</c:forEach>
</table>

  
</div>


</body>
</html>
