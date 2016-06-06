<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pacotes.Usuario" %>
<%
	String metodo = request.getMethod();
	Usuario u = null;
	if(metodo.equalsIgnoreCase("POST"))
	{
		//Ok do Form
		String email = request.getParameter("inp_email");
		String senha = request.getParameter("inp_senha");
		u = new Usuario().login(email, senha);
		if(u!=null){
			session.setAttribute(Usuario.NOM, u.getNom());
			session.setAttribute(Usuario.EMA, u.getEma());
			session.setAttribute(Usuario.SEN, u.getSen());
	}else{
		out.print("<script>alert('Credenciais inválidas!')</script>");
		}
	}
	else{
		//Get normal
		if(request.getParameter("sair") != null){
			//Get sair
			//apagando session
			session.invalidate();
			session = null;
		}
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>INDEX</title>
	</head>
	<body>		   
		<div style="border:solid 1px #ddd; width:97%; padding:10px; position:fixed; top:10px">
			<a href="instalador/">Instalador</a>
			<a href="index.jsp">Home</a>
		</div>
		<% //LOGIN ADMIN CANTO SUPERIOR DIREITO
		/**
		*</div>
		*<div style="top:20px; position:fixed; right:20px;">
		*	<a href="menu.adm.jsp">Administrador</a>
		*</div>
		**/
		%>
		<br><br>
		<div style="float:width: 50%; ">
		<%
		if(u == null){
		%>
		<center>
		<form action="index.jsp" method="post">
		<h2>Login</h2>
			Email:<input name="inp_email" type="text"><br>
			Senha:<input name="inp_senha" type="password"><br>
			<input name="bt_enviar" value="Enviar" type="submit"><br>
		</form>
		</center>
		</div>
		<%
		}
		else{
			%>
			<h2> Olá <% u.getNom(); %>!</h2>
			<a href="usuarios.jsp">Usuários</a>
			<a href="produtos_listar.jsp">Listar Produtos</a>
			<a href="index.jsp?sair=1">Sair</a>
			<%
			}
		%>
	</body>
</html>