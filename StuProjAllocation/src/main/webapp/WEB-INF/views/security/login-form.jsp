<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login page</title>
<style>
.error {
	color: red;
}

.col-centered{
float: none;
margin: 0 auto;
}
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>
</head>


<body style="background-color: #4b5257">


<p>
<c:if test="${error == true}">
	<b class="error">Invalid login or password.</b>
</c:if>
<c:if test="${logout == true}">
	<b class="logout">You have been logged out.</b>
</c:if>
</p>

<div class="login-form">
	<h2 class="text-center">Log in</h2> 
	<c:url value="/login" var="loginUrl"/>
	<form action="${loginUrl}" method="post" modelAttribute="user">       
		<c:if test="${param.error != null}">        
			<p>
				Invalid username and password.
			</p>
		</c:if>
		<c:if test="${param.logout != null}">       
			<p>
				You have been logged out.
			</p>
		</c:if>
		
		<div class="form-group">
		<p>
			<label for="username">Username</label>
			<input type="text" class="form-control" id="username" name="username"/>	
		</p>
		</div>
		
		<div class="form-group">
		<p>
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password"/>	
		</p>
		</div>
        <div class="custom-checkbox">
        <label><input type="checkbox"> Remember me</label>
     	 </div>
     	 
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button type="submit" class="btn-primary form-control">Log in</button>
	</form>	
</div>

</body>
</html>