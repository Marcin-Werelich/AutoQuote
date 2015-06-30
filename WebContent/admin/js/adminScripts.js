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
	
	function createNewLangEntry(name) {
        
		var para = document.createElement("P");
        var input = document.createElement("INPUT");
        var undoButton = document.createElement("BUTTON");
        var counter;

        var text = document.createTextNode("DELETE");
        
        var allNewLangNodes = document.getElementsByName('newLang');
        if (allNewLangs == null) {
            counter = 1;
        }
        else {
            for(i = 0; i < allNewLangNodes.length; i++) {
                counter = i + 1;
            }
        }
        undoButton.appendChild(text);


        para.appendChild(input);
        para.appendChild(undoButton);
        para.setAttribute('name','newLang')
        para.setAttribute('id',counter)
        para.setAttribute('onclick','deleteThisLang(' + counter + ')')

        document.body.appendChild(para);


    }

    function deleteThisLang(id) {

        document.body.removeChild(document.getElementById(id));

    }