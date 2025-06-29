<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="images/favicon.png">
  	<link rel="stylesheet" href="css/style.css">
  	<title>Agenda de contatos</title>
</head>
<body>
	<h1>Novo contato</h1>

	<div class="container">
		<form name="new-contact" action="contacts" method="post">
			<div class="form-group">
				<label>Nome</label>
				<input type="text" name="name" class="input-form">
			</div>
			<div class="form-group">
				<label>Telefone</label>
				<input type="text" name="phone" class="input-form">
			</div>
			<div class="form-group">
				<label>Email</label>
				<input type="email" name="email" class="input-form">
			</div>
			<input type="button" value="Adicionar" class="button" onclick="validateForm()">
		</form>
	</div>
	
	<script src="js/script.js"></script>
</body>

</html>