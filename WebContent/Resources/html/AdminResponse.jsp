<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html lang="en-US" prefix="og: http://ogp.me/ns#"
	class="csstransforms csstransforms3d csstransitions">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<table>
	<tr>
		<td><h5>File name(s):</h5></td>
		<td><h5>
				<c:out value="${fileName}" />
			</h5></td>
	</tr>
	<tr>
		<td><h5>Total word count:</h5></td>
		<td><h5>
				<c:out value="${wordCount}" />
			</h5></td>
	</tr>
</table>
<table border="1">
	<tr>
		<td>Language Pair</td>
		<td>Price per word</td>
		<td>Total in €</td>
	</tr>
	<c:forEach items="${quoteElementList}" var="quoteElement">
		<tr>
			<td align="center"><c:out value="${quoteElement.languagePair}"></c:out></td>
			<td align="center"><c:out value="${quoteElement.unitPrice}" /></td>
			<td align="center"><c:out value="${quoteElement.totalUnitPrice}" /></td>
		</tr>
	</c:forEach>
	<tr></tr>
	<tr>
		<td></td>
		<td><h5>Total w/o VAT:</h5></td>
		<td><h5>
				<c:out value="${totalPrice}" />
			</h5></td>
	</tr>
</table>
<br />
<br />Please note that all prices are in Euro.
<br />
<br />Please note that this is only an approximate,
automatically-generated cost estimate. For a detailed cost analysis,
please
<a href="mailto:hello@altagram.de">contact us</a> directly per email.
<br />
</body>
</html>