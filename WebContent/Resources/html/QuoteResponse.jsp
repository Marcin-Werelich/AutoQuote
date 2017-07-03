<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>

<head>
<title>Cost Estimation</title>
</head>

<body>

	<table>
		<tr>
			<td><h3>File name(s):</h3></td>
			<td><h3>
					<c:out value="${fileName}" />
				</h3></td>
		</tr>
		<tr>
			<td><h3>Total word count:</h3></td>
			<td><h3>
					<c:out value="${wordCount}" />
				</h3></td>
		</tr>
	</table>
	<table border="1">
		<tr>
			<td align="center">Language Pair</td>
			<td align="center">Price per word</td>
			<td align="center">Total in €</td>
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
			<td><b>Total w/o VAT:</b></td>
			<td><b><c:out value="${totalPrice}" /></td>
		</tr>
	</table>
	<br />
	<br />Please note that all prices are in Euro.
	<br />
	<br />Please note that this is only an approximate,
	automatically-generated cost estimate. For a detailed cost analysis,
	please contact us directly per email.
	<br />
	
	<br><br><a href="/AutoQuote/quote">Back</a>

</body>
</html>