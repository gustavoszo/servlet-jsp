function validateForm() {
   const form = document.forms["new-contact"];
   const name = form["name"].value.trim();
   const phone = form["phone"].value.trim();

   if (name == "") {
      alert("O Nome não pode estar em branco");
      form["name"].focus();
      return false;
   }

   if (phone == "") {
      alert("O Telefone não pode estar em branco.");
      form["phone"].focus();
      return false;
   }

   form.submit();
}

function confirmDelete(id) {
	let response = confirm(`Deseja realmente remover o contato '${id}'?`);
	
	if (response == true) {
		window.location.href = "/Agenda/contacts/delete?id=" + id
	}
}

