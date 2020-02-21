function addField() {
	var div = document.createElement("div");
	var selectBox = document.getElementById("selectBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	var ph="";
	switch(selectedValue) {
		case 'number':	ph='Номер';
						break;
		case 'author':	ph='Автор';
						break;
		case 'theme':	ph='Тема';
						break;
		case 'description':	ph='Описание';
						break;
		case 'comment':	ph='Комментарий';
						break;
		default: return false;
				 break;
	}
	div.innerHTML = '<input calss="input" name=\"'+selectedValue+'\" placeholder=\"'+ph+' содержит\" /> <a style="color:red;" href="#" class="addInput" onclick="deleteField(this)">x</a>'
	document.getElementById("parentId").appendChild(div);
	return false;
}
function deleteField(a) {
	var contDiv = a.parentNode;
	contDiv.parentNode.removeChild(contDiv);
	return false;
}
