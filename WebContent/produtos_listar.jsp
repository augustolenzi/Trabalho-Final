<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pacotes.Produto" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Lista de Produtos</title>
	</head>
	<body>
	<%@ include file="inc.menu.jsp" %>
	
	<h1> Listar Produtoa</h1>
	
	<a href="produtos_editar.jsp">novo</a>
	
	<table border="1" cellspacing="4">
		<tr>
			<td>Cod</td>
			<td>Nome</td>
			<td>Valor</td>
		</tr>
		<%
			Produto p = new Produto();
			ArrayList<Produto> lista = p.lista();
			for(int i=0; i<lista.size(); i++)
			{
				%>
				<tr>
					<td><%=lista.get(i).getCod()%></td>
					<td><%=lista.get(i).getNom()%></td>
					<td><%=lista.get(i).getVlr()%></td>
					<td>
						<a href="carrinho_de_compras.jsp?codprod=<%=lista.get(i).getCod()%>">
							Comprar
						</a>
				</tr>
				<%
			}
			%>
	</table>
	</body>
</html>