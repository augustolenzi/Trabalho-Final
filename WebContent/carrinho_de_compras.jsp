<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.mysql.jdbc.ResultSet"%>
<%@page import="com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pacotes.Produto" %>
<%@ page import="pacotes.Usuario" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Lista de Produtos</title>
	</head>
	<body>
	<%@ include file="inc.menu.jsp" %>
	
	<h1> Carrinho de Compras</h1>

	<table border="1" cellspacing="4">
		<tr>
			<td colspan="3" align="center"><%="Comprador: " + session.getAttribute(Usuario.NOM)%>

			</td>
		</tr>
		<tr>
			<td>Cod</td>
			<td>Nome</td>
			<td>Valor</td>
		</tr>
		<%
			
			Produto p = new Produto();
			ArrayList<Produto> lista = new ArrayList<Produto>();
			
			if(session.getAttribute("carrinho") !=null){
				lista = (ArrayList) session.getAttribute("carrinho");		
			}		
						
			String codd = request.getParameter("codprod");	
				
			ResultSet res = (ResultSet) p.consulta("SELECT * FROM tb_produto WHERE pr_codprod = '"+codd+"'");
				
			try{
				res.first();    
				 		    
				p.setNom(res.getString("pr_nomprod"));
				p.setCod(res.getString("pr_codprod"));
				p.setVlr(res.getString("pr_vlrprod"));
				p.setQtd("1");
				
			}catch (Exception e) {}
				    
			int ox = 0;
			
			for (int x = 0; x < lista.size(); x++) {
				
				if(lista.get(x).getCod() == p.getCod()){					
					
					ox = x;
					break;
					
				}
				
			}
			
			if(ox != 0){
				
				Produto ps = new Produto();					
				ps = lista.get(ox);					
				int qtd = Integer.valueOf(ps.getQtd());
				qtd++;
				ps.setQtd(qtd);										
				lista.set(ox, ps);
				
			}else{				
				
				lista.add(p);
								
			}	
						
			session.setAttribute("carrinho", lista);

			for (int i = 0; i < lista.size(); i++) {			
								
		%>
		<tr>
			<td><%=lista.get(i).getCod()%></td>
			<td><%=lista.get(i).getNom()%></td>
			<td><%=lista.get(i).getVlr()%></td>
			<td><a href="<%=lista.get(i).getCod()%>"> Excluir </a>
		</tr>
		<%
			}
			
			
		%>
	</table>
	<a href="produtos_listar.jsp"> Continuar Comprando </a>
</body>
</html>