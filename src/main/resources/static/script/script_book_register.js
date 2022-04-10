var fileChoosed = false;

function showPreview(event) {
	if (event.target.files.length > 0) {
		var src = URL.createObjectURL(event.target.files[0]);
		var preview = document.getElementById("file-ip-1-preview");
		preview.src = src;
		preview.style.display = "block";
	}
	fileChoosed = true;
}

var btnRemoveAuthorIsClicked = false;

function btnRemoveAuthorCliked() {
	btnRemoveAuthorIsClicked = true;
}

function submitForm() {
	if (!btnRemoveAuthorIsClicked) {
		if (document.getElementById("divAuthors") !== null) {
			if (fileChoosed === false) {
				return confirm('Parece que você esqueceu de selecionar uma capa para este livro. \n\nDESEJA REALMENTE SALVÁ-LO SEM UMA IMAGEM DE CAPA?');
			}
		}
	}
}


function showHiddenInputAuthor() {
	let fields = document.getElementsByClassName("inputHiddenAuthor");

	document.getElementById("divAuthorList").style.display = "none";

	for (let i = 0; i < fields.length; i++) {
		fields[i].type = "text";
	}


}








