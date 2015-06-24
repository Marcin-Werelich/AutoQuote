<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a href="/AutoQuote/admin">Back to admin menu</a>
<h2><br></h2>
	<h5>Source Languages</h5>
		<c:forEach items="${adminData.sourceLanguageNamesList}" var="sourceLangItem">
			<select id="${sourceLangItem.value}_src_text" type="text" value="${sourceLangItem.key}" disabled/>
				<option value="${sourceLangItem.value}" selected>${sourceLangItem.key}</option>
				<c:forEach items="${adminData.allLanguageNamesList}" var="allLangItem">
					<option value="${allLangItem.value}">${allLangItem.key}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${sourceLangItem.value}_src_edit" value="Edit" type="button" onclick="return enableTextBoxEdit('${sourceLangItem.value}_src')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${sourceLangItem.value}_src_save" value="Save" type="button" onclick="return saveTextBoxEdit('${sourceLangItem.value}_src')" disabled/>Save</button><br>
			
		</c:forEach>

<script type="text/javascript">
function enableTextBoxEdit(name){
    var editButton = document.getElementById(name + '_edit');
    var saveButton = document.getElementById(name + '_save');
    var textBox = document.getElementById(name + '_text');

    editButton.disabled = true;
    saveButton.disabled = false;
    textBox.disabled = false;
}

function saveTextBoxEdit(name){
    var editButton = document.getElementById(name + '_edit');
    var saveButton = document.getElementById(name + '_save');
    var textBox = document.getElementById(name + '_text');

    editButton.disabled = false;
    saveButton.disabled = true;
    textBox.disabled = true;
}
</script>	

</body>
</html>