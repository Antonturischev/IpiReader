function addField() {
	var div = document.createElement("div");
	var selectBox = document.getElementById("selectBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	var ph="";
	var type ="";
	switch(selectedValue) {
		case 'author':	ph='Автор содержит';
						type='text';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'theme':	ph='Тема содержит';
						type='text';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'description':	ph='Описание содержит';
						type='text';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'comment':	ph='Комментарий содержит';
						type='text';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'responsible':	ph='Ответственный содержит';
						type='text';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
		case 'datecreatedd':	ph='Дата создания больше';
						type='date';
			selectBox.options[selectBox.selectedIndex].disabled=true;
			break;
		case 'datecreatedu':	ph='Дата создания меньше';
						type='date';
			selectBox.options[selectBox.selectedIndex].disabled=true;
			break;
		default: return false;
				 break;
	}

	div.innerHTML = '<input class="input selected-params-input" name=\"'+selectedValue+'\" placeholder=\"'+ph+'\" type=\"'+type+'\" /> <a style="color:red;" selectCount="'+selectBox.selectedIndex+'" href="#" class="addInput" onclick="deleteField(this)">x</a>'
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
