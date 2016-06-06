<%@ page import="pacotes.Instalacao" %>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Instalador</title>
	</head>
	<body>
		<%
			//Colocando um titulo na pagina
			pageContext.setAttribute("meuTitulo","Instalador");
		
			//Include adiciona todos os contudos do arquivo
		%>
		<%@ include file="../_inclusoes/menu.jsp" %>
		
		<% out.print( new Instalacao().verificaExistencia() ); %>
	</body>
</html>