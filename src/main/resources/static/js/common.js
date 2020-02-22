function addField() {
	var div = document.createElement("div");
	var selectBox = document.getElementById("selectBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	var ph="";
	switch(selectedValue) {
		case 'number':	ph='Номер';
						selectBox.options[selectBox.selectedIndex].disabled=true;
						break;
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


// function refreshField(key,param) {
// 	var div = document.createElement("div");
// 	var selectBox = document.getElementById("selectBox");
// 	var selectedCount=0;
// 	var ph="";
// 	switch(key.value) {
// 		case 'number':	ph='Номер';
// 			document.getElementById('number').disabled=true;
// 			selectedCount=1;
// 			break;
// 		case 'author':	ph='Автор';
// 			document.getElementById('number').disabled=true;
// 			selectedCount=2;
// 			break;
// 		case 'theme':	ph='Тема';
// 			document.getElementById('number').disabled=true;
// 			selectedCount=3;
// 			break;
// 		case 'description':	ph='Описание';
// 			selectBox.options[selectBox.selectedIndex].disabled=true;
// 			selectedCount=4;
// 			break;
// 		case 'comment':	ph='Комментарий';
// 			document.getElementById('number').disabled=true;
// 			selectedCount=5;
// 			break;
// 		default: return false;
// 			break;
// 	}
// 	//div.attributes.
// 	div.innerHTML = '<input class="input" name=\"'+key.value+'\" placeholder=\"'+ph+' содержит\" value="'+param+'"/> <a style="color:red;" selectCount="'+selectedCount+'" href="#" class="addInput" onclick="deleteField(this)">x</a>'
// 	document.getElementById("parentId").appendChild(div);
// 	return false;
// }