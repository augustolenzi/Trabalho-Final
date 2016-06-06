<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Editar Usu�rio</title>
	</head>
	<body>
	
		<%@ include file="inc.menu.jsp" %>
		
		<%
			String codusu = request.getParameter("codusu");
			Usuario u = new Usuario();
			String msg = "";
			if(request.getMethod().equalsIgnoreCase("post"))
			{
				if(request.getParameter("excluir")!=null){
					u.instancia(codusu);
					if(u.deletar())
					{
						%>
						<script>
							alert('Usu�rio exclu�do com sucesso!');
							window.location = 'usuarios.jsp';
						</script>
						<%
					}
					else
					{
						msg = "Error ao excluir o Usu�rio!";
					}
				}
				else
				{
					String nom = request.getParameter("nom");
					String ema = request.getParameter("ema");
					String sen = request.getParameter("sen");
					u = new Usuario(codusu, nom, ema, sen);
					msg = "Opera��o realizada com: "+(u.salvar() ? "Sucesso":"Erro");
				}
			}
			else
			{
				if(codusu!=null) u.instancia(codusu);	
			}
		%>
		
		<h1><% codusu==null ? "Novo usu�rio" : "Editar usu�rio cod("+u.getCod()+")" %></h1>
		<%
			if(msg.length()>0){
				%>
				<div id="msg"
			}
		%>
	</body>
</html>