<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US" prefix="og: http://ogp.me/ns#" class="csstransforms csstransforms3d csstransitions"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<h3><c:out value="${errorMessage}"/></h3>
		<br/><br/>
		<c:out value="${stackTrace}"/>
		<br/><br/>
</body></html>