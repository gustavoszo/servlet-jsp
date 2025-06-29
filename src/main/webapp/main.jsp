<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.entities.Contact" %>
<% ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="images/favicon.png">
  	<link rel="stylesheet" href="css/style.css?v=3">
  	<title>Agenda de contatos</title>
</head>
<body>
	<h1>Agenda de contatos</h1>
	
	<div class="container">
		<a href="contacts" class="button">Novo contato</a>
		
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
				<% for (var contact : contacts) { %>
					<tr>
						<td><%= contact.getId() %></td>
						<td><%= contact.getName() %></td>
						<td><%= contact.getPhone() %></td>
						<td><%= contact.getEmail() %></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</body>
</html>