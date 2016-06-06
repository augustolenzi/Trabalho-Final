<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pacotes.Usuario" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Usuários</title>
	</head>
	<body>
	<%@ include file="inc.menu.jsp" %>
	
	<h1> Manter Usuários</h1>
	
	<a href="usuarios_editar.jsp">novo</a>
	
	<table border="1" cellspacing="4">
		<tr>
			<td>Cod</td>
			<td>nome</td>
			<td>senha</td>
			<td>email</td>
		</tr>
		<%
			Usuario u = new Usuario();
			ArrayList<Usuario> lista = u.lista();
			for(int i=0; i<lista.size(); i++)
			{
				%>
				<tr>
					<td><%=lista.get(i).getCod()%></td>
					<td><%=lista.get(i).getNom()%></td>
					<td><%=lista.get(i).getSen()%></td>
					<td><%=lista.get(i).getEma()%></td>
					<td>
						<a href="usuarios_editar.jsp?codusu=<%=lista.get(i).getCod()%>">
							Editar
						</a>
				</tr>
				<%
			}
			%>
	</table>
	</body>
</html>