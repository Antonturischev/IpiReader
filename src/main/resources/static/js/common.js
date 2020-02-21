function addField() {
	var div = document.createElement("div");
	div.innerHTML = "<input placeholder="тест"/>"
	document.getElementById("parentId").appendChild(div);
	return false;
}
function deleteField(a) {
	var contDiv = a.parentNode;
	contDiv.parentNode.removeChild(contDiv);
	return false;
}