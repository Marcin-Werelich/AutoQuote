<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>Admin login</title>
</head>
<body>
	<h3>Please state login credentials for Admin site</h3>
	<form action="j_security_check" method="post">
		Username:<br>
		<input type="text" name="j_username"> 
		<br>Password:<br>
		<input type="password" name="j_password"> 
		<br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>