<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<h5>Source Langs</h5>
		<c:forEach items="${adminData.sourceLanguageNamesList}" var="sourceLangItem">
			<c:out value="${sourceLangItem.value}" />${sourceLangItem.key}<br>
		</c:forEach>

</body>
</html>