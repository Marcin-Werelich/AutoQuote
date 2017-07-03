<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<h5>Target Languages</h5>
		<c:forEach items="${adminData.targetLanguageNamesList}" var="targetLangItem">
			<select id="${targetLangItem.value}_tgt_text" type="text" value="${targetLangItem.key}" disabled/>
				<option value="${targetLangItem.value}" selected>${targetLangItem.key}</option>
				<c:forEach items="${adminData.allLanguageNamesList}" var="allLangItem">
					<option value="${allLangItem.value}">${allLangItem.key}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${targetLangItem.value}_tgt_edit" value="Edit" type="button" onclick="enableTextBoxEdit('${targetLangItem.value}_tgt')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${targetLangItem.value}_tgt_save" value="Save" type="button" onclick="saveTextBoxEdit('${targetLangItem.value}_tgt')" disabled/>Save</button><br>			
		</c:forEach>

<br><br><a href="/AutoQuote/admin">Back to admin menu</a>

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