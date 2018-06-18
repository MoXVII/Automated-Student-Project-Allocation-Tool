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
<title>Home Page</title>

<style>

  .row {
  width: 100%;
   top: 20;
   padding-top: 100px;
   	color: #F5F5DC;
	text-align: center;
  }    

 .notAuthorized {
  color: currentColor;
  cursor: not-allowed;
  display: inline-block; 
  opacity: 0.5;
  text-decoration: none;
}

.container{
	position: relative;
	font: normal 12px Myriad Pro, Verdana, Geneva, sans-serif;
	text-align: center;
	 
	 
}
.container h2{
	color: #F5F5DC;
	text-align: center;
	}
.container h5, tr{
	color: #F5F5DC;
	
	text-align: center;
	}
.col-md-4{
	text-align:left;
	color: #F5F5DC;
	
}	
	
	
 </style>


<body style="background-color: #4b5257">

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Automated Student Project Allocation Tool</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a class ="notAuthorized" href="/accessDenied">Students</a></li>
      <li><a class ="notAuthorized" href="/accessDenied">Projects</a></li>
      <li><a class ="notAuthorized" href="/accessDenied">Preferences</a></li>
      <li><a href="/logout">Logout</a></li>
    </ul>
  </div>
</nav>
  
<div class="container" style="margin-top:100px">
  <h2>Automated Student Project Allocation</h2>
  
  <div class="row">
    <div class ="col-md-4">
    <h5>How to use this tool:</h5><br>
    <ol>
    <li>Upload the 3 required files on the right.</li><br>
    <li>Once complete, you will be redirected to a page listing all the projects available.</li><br>
    <li>Navigate the tool using the nav-bar in order to see the allocations made for each student.</li>
    </ol>
    </div>
   <div class="col-md-8"> 
   
	   	<form method="POST" action="/parsingInput" enctype="multipart/form-data">
	   	<input type="hidden"
                                name="${_csrf.parameterName}"
                                value="${_csrf.token}"/>
	   	<table>
	   	<tr>
	 			<td><label><h5>Upload the Student Preferences:</h5></label>	
        		<td><h5><input class="form-control" type="file" accept=".csv" name="prefFile"></h5></td>
		</tr>
		<tr>		
				<td><label><h5>Upload the Student's Results:</h5></label></td>
        		<td><h5><input class="form-control" type="file" accept=".csv" name="studResult"></h5></td>

        </tr>
        <tr>		
        		<td><label><h5>Upload the Available Projects:</h5></label></td>
        		<td><h5><input class="form-control" type="file"  accept=".csv" name="projList"></td>
        </tr>
        <tr>		
			 <td><input type="submit" name="action" value="Initialise" class="btn btn-success"/></td>
	 	</tr>
		</table>	
			</form>
    </div>

   </div>
   <div class="row">
	   <div class="col-md-12">
    		<h5>In order to make allocations, complete the sections above and click initialise to continue. Please ensure you are submitting the correct files in the correct steps and that they are in the correct format or else the algorithm will fail to make allocations.</h5>
		</div>
  </div>
</div>


</body>
</html>
