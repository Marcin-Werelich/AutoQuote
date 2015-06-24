<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a href="/AutoQuote/admin">Back to admin menu</a>
<h2><br></h2>
	<h5>Price List</h5>
		<c:forEach items="${adminData.priceList}" var="priceListItem">
			<input id="${priceListItem.value}_prc_pair" type="text" value="${priceListItem.key}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="${priceListItem.key}_prc_text" type="text" value="${priceListItem.value}" disabled/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${priceListItem.key}_prc_edit" value="Edit" type="button" onclick="enableTextBoxEdit('${priceListItem.key}_prc')"/>Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="${priceListItem.key}_prc_save" value="Save" type="button" onclick="saveTextBoxEdit('${priceListItem.key}_prc')" disabled/>Save</button><br>			
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