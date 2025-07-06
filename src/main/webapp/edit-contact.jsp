<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.entities.Contact" %>
<% Contact contact = (Contact) request.getAttribute("contact"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="<%= request.getContextPath() + "/images/favicon.png" %>">	
  	<link rel="stylesheet" href="<%= request.getContextPath() + "/css/style.css?v=5" %>">
  	<title>Agenda de contatos</title>
</head>
<body>
	<h1>Editar contato</h1>

	<div class="container">
		<form name="new-contact" action="<%= request.getContextPath() + "/contacts/update" %>" method="post">
			<div class="form-group">
				<label>ID</label>
				<input type="text" name="id" class="input-form" value="<%= contact.getId() %>" readonly>
			</div>
			<div class="form-group">
				<label>Nome</label>
				<input type="text" name="name" class="input-form" value="<%= contact.getName() %>">
			</div>
			<div class="form-group">
				<label>Telefone</label>
				<input type="text" name="phone" class="input-form" value="<%= contact.getPhone() %>">
			</div>
			<div class="form-group">
				<label>Email</label>
				<input type="email" name="email" class="input-form" value="<%= contact.getEmail() %>">
			</div>
			<input type="button" value="Adicionar" class="button" onclick="validateForm()">
		</form>
	</div>
	
	<script src="<%= request.getContextPath() + "/js/script.js" %>"></script>
</body>

</html>