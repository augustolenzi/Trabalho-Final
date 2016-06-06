<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pacotes.Usuario" %>

<div style="border:solid 1px #ddd; width:97%; padding:10px; position:fixed; top:10px">
	<a href="index.jsp">Home</a>
	<a href="usuarios.jsp">Usuários</a>
	
	<div style="float:right; margin-right:10px;">
		<%= session.getAttribute(Usuario.COD)==null 
			? session.getAttribute(Usuario.NOM) 
			: "" %>
		<a href="index.jsp?sair">Sair</a>
	</div>
	
</div>

<br><br>
