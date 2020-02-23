function addField() {
	var div = document.createElement("div");
	var selectBox = document.getElementById("selectBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	var ph="";
	switch(selectedValue) {
		case 'author':	ph='Автор';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'theme':	ph='Тема';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'description':	ph='Описание';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'comment':	ph='Комментарий';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		default: return false;
				 break;
	}
	//div.attributes.
	div.innerHTML = '<input class="input" name=\"'+selectedValue+'\" placeholder=\"'+ph+' содержит\" /> <a style="color:red;" selectCount="'+selectBox.selectedIndex+'" href="#" class="addInput" onclick="deleteField(this)">x</a>'
	document.getElementById("parentId").appendChild(div);
	return false;
}
function deleteField(a) {
	var contDiv = a.parentNode;
	var selectedCount = a.getAttribute("selectCount");
	var selectBox = document.getElementById("selectBox");
	selectBox.options[selectedCount].disabled=false;
	contDiv.parentNode.removeChild(contDiv);
	return false;
}
