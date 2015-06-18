<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html><head><body>
	<h3><c:out value="${errorMessage}"/></h3>
		<br/><br/>
		<c:out value="${stackTrace}"/>
		<br/><br/>
</body></html>