<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!-- saved from url=(0035)http://altagram.de/en/legal-notice/ -->
<html>
<body>

		
<h1 id="page-title">Translation cost estimate:</h1>

		<form action="Quote" name="quoteForm" method="post" enctype="multipart/form-data">
    <p><h2>1. Select input file(s):</h2></p>
    <input type="file" name="file" value="Select file" multiple required/>
    <br/><br/>Supported file types: .DOCX, .XLSX, .PPTX, OpenOffice formats, .HTML, .XML, .IDML, .TXT
    <br>The uploaded files will not be stored on the server and are deleted after analysis.<br><br>

    <h2>2. Select source language:</h2>

    <select id="src" name="source" required onchange="return disableDuplicateTarget()">
        <option value ="X" disabled selected>Select source language...</option>
        <c:forEach items="${adminData.sourceLanguageNamesList}" var="hashMapItem">           
        	<option value="${hashMapItem.value}">${hashMapItem.key}</option>                
        </c:forEach>
    </select>
    <br><br>
    <h2>3. Select target languages:</h2>
    <table>
    	<c:set var="count" value="${1}" scope="page" />
    	<tr>	
    	<c:forEach items="${adminData.targetLanguageNamesList}" var="hashMapItem" varStatus="status"> 
    		<td><input type="checkbox" name="target" value="${hashMapItem.value}"/>${hashMapItem.key}</td>
    		<c:set var="count" value="${count + 1}" scope="page" />
    		<c:if test="${count > 5}">
    			</tr><tr>
    			<c:set var="count" value="${1}" scope="page" />
    		</c:if>	    		
    	</c:forEach>    	
        </tr>         
    </table>
    <input type="submit" value="Submit data" onclick="return validateForm();">
</form>

<script type="text/javascript" language="JavaScript">
    function validateForm() {
        var index;
        var select = document.getElementById('src');
        if(select.value.match('X')) {
            alert('Please select the source lanuage!');
            return false;
        }
        
        var checkBoxes = document.getElementsByName('target');
        for (index = 0; index < checkBoxes.length; index++) {
            if (checkBoxes[index].checked) {
                return true;
            }
        }
        alert('Please select at least one target lanuage!');
        return false;
    }
    
    function disableDuplicateTarget() {
        var select = document.getElementById('src');
        var checkBoxes = document.getElementsByName('target');
        for (index = 0; index < checkBoxes.length; index++) {
            checkBoxes[index].disabled = checkBoxes[index].value.match(select.value);
        }
        return true;
    }
</script>

</body></html>