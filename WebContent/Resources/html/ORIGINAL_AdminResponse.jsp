<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<h5>Source Langs</h5>
		<c:forEach items="${adminData.sourceLanguageNamesList}" var="sourceLangItem">
			<input id="${sourceLangItem.value}_src_text" type="text" value="${sourceLangItem.key}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${sourceLangItem.value}_src_edit" value="Edit" type="button" onclick="enableTextBoxEdit('${sourceLangItem.value}_src')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${sourceLangItem.value}_src_save" value="Save" type="button" onclick="saveTextBoxEdit('${sourceLangItem.value}_src')" disabled/>Save</button><br>
			
		</c:forEach>
	
	<h5><br></h5>
	<h5>Target Langs</h5>
		<c:forEach items="${adminData.targetLanguageNamesList}" var="targetLangItem">
			<input id="${targetLangItem.value}_tgt_text" type="text" value="${targetLangItem.key}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${targetLangItem.value}_tgt_edit" value="Edit" type="button" onclick="enableTextBoxEdit('${targetLangItem.value}_tgt')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${targetLangItem.value}_tgt_save" value="Save" type="button" onclick="saveTextBoxEdit('${targetLangItem.value}_tgt')" disabled/>Save</button><br>			
		</c:forEach>
		
	<h5><br></h5>
	<h5>Price List</h5>
		<c:forEach items="${adminData.priceList}" var="priceListItem">
			<input id="${priceListItem.value}_prc_text" type="text" value="${priceListItem.key}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="${priceListItem.value}_prc_text" type="text" value="${priceListItem.value}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${priceListItem.value}_prc_edit" value="Edit" type="button" onclick="enableTextBoxEdit('${priceListItem.value}_prc')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${priceListItem.value}_prc_save" value="Save" type="button" onclick="saveTextBoxEdit('${priceListItem.value}_prc')" disabled/>Save</button><br>			
		</c:forEach>
		
<script type="text/javascript" language="JavaScript">		
	
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