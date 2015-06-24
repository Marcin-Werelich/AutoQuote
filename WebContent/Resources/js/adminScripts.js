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