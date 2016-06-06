package pacotes;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Instalacao extends Configuracao{
	
	public String verificaExistencia()
	{
		String temp = "";
		try{
			//Registrando o Driver JDBC
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(BANCO, USER, PASS);
			stmt = conn.createStatement();
			
			//Verificacao de Banco Existente
			java.sql.ResultSet res = stmt.executeQuery(
									" SELECT COUNT(1) AS qtd"
									+"  FROM information_schema.SCHEMATA "
									+" WHERE SCHEMA_NAME = '"+BASE+"';");
			res.first();
			if(res.getInt(1)==0){
				temp += "<br>Base ("+BASE+") ainda nao existe.";
				stmt.executeUpdate("CREATE DATABASE "+BASE);
				temp += "<br>Base ("+BASE+") criada com sucesso.";
				stmt.close();
				conn.close();
				
				temp += "<br>Conectando novamente mas com a BASE criada.";
				conn = DriverManager.getConnection(BANCO+BASE, USER, PASS);
				stmt = conn.createStatement();
				
				temp += "<br> 1 - Criacao de tabela Usuario";
				
				stmt.executeUpdate( Usuario.SQL_CREAT);
				
				stmt.executeUpdate( " INSERT INTO tb_usuario (us_nomusu, us_senusu, us_emausu) "
								  + " 		VALUES ( 'Admin' , 'Admin' , 'admin@admin.com' ) "
								  + " 			 , ( 'teste' , 'teste' , 'teste@teste.com' ) "
								  + " 			 , ( 'teste2','teste2' ,'teste2@teste.com' ) ");
				
				temp += "<br> 2 - Ultimo registro de Usuario";
				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
				
				if (rs.next()){
					temp += "<br> Ultimo usuario registrado"+rs.getInt(1);
					//retorna o proximo codigo de usuario a ser registrado
				}
				
				temp += "<br> 3 - Criacao de tabela Produto";
				
				stmt.executeUpdate( Produto.SQL_CHEAT);
				
				stmt.executeUpdate( " INSERT INTO tb_produto (pr_nomprod, pr_vlrprod) "
								  + "	   VALUES ('teste'  ,  2.30)"
								  + "			, ('teste2' , 30.49)"
								  + "           , ('teste3' , 20.39)");
				
				temp += "<br>  Ultimo registro de Produto";
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
				
				if (rs.next()){
					temp += "<br> Ultimo produto registrado"+rs.getInt(1);
					//retorna o proximo codigo de usuario a ser registrado
				}
				
				temp += "<br> 3 - Criacao de tabela Vendas";
				
				stmt.executeUpdate( Vendas.SQL_CHEAT);
				
				stmt.executeUpdate( " INSERT INTO tb_vendas (vd_codusu, vd_itmvend, vd_vlrvend) "
								  + "	   VALUES (1, 'teste', 2.30)"
								  + "			, (1, 'teste 2',30.49)"
								  + "           , (1, 'teste 3',20.39)");
				
				temp += "<br> 4 - Ultimo registro de Vendas";
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
				
				if (rs.next()){
					
					temp += "<br> Ultima venda registrada"+rs.getInt(1);
					//retorna o proximo codigo de usuario a ser registrado
				}
			}
			else{
				temp += "<br>Base ("+BASE+") Ja existente.";
				stmt.close();
				conn.close();
				
				conn = DriverManager.getConnection(BANCO+BASE, USER, PASS);
				stmt = conn.createStatement();
				
				temp += "<br> Mostrar usuários:";
				//Query de retorno de usuarios cadastrados
				ResultSet result = stmt.executeQuery( " SELECT us_codusu, "+
													  "us_nomusu, "+
													  "us_senusu, "+
													  "us_emausu "+
													  "FROM tb_usuario ");
				
				temp += "<table border =\"1\"> ";
				temp += "<tr>";
				temp +=   "<td> Codigo </td> <td> Nome </td> <td> Senha </td><td> Email </td>";
				temp += "</tr>";
				
				//proxima linha
				
				while(result.next()){
					temp += "<tr>";
			        temp +=   "<td>"+result.getInt("us_codusu")+"</td>" ;
			        temp +=   "<td>"+result.getString("us_nomusu")+"</td>" ;
			        temp +=   "<td>"+result.getString("us_senusu")+"</td>" ;
			        temp +=   "<td>"+result.getString("us_emausu")+"</td>" ;
			        temp += "</tr>";
				}
				temp += "</table>";
				
				Integer qtd = stmt.executeUpdate( " UPDATE tb_usuario SET us_nomusu = 'teste3' WHERE us_codusu = 3");
				temp += " <br> UPDATE("+qtd.toString()+") usuario alterado";
				
				Integer qtde = stmt.executeUpdate( " DELETE FROM tb_usuario  WHERE us_codusu = 3");
				temp += " <br> DELETE("+qtde.toString()+") usuario removido ";
				
				//Produtos
				temp += "<br> Mostrar produtos: <br>";
				//Query de retorno de produtos cadastrados
				result = stmt.executeQuery( " SELECT pr_codprod, "+
													  "pr_nomprod, "+
													  "pr_vlrprod "+
													  "FROM tb_produto ");
				
				temp += "<table border =\"1\"> ";
				temp += "<tr>";
				temp +=   "<td> Codigo </td> <td> Nome </td> <td> Valor </td>";
				temp += "</tr>";
				
				//proxima linha
				
				while(result.next()){
					temp += "<tr>";
			        temp +=   "<td>"+result.getInt("pr_codprod")+"</td>" ;
			        temp +=   "<td>"+result.getString("pr_nomprod")+"</td>" ;
			        temp +=   "<td>"+result.getDouble("pr_vlrprod")+"</td>" ;
			        temp += "</tr>";
				}
				temp += "</table>";
				
				qtd = stmt.executeUpdate( " UPDATE tb_produto SET pr_nomprod = 'teste3' WHERE pr_codprod = 3");
				temp += " <br> UPDATE("+qtd.toString()+") produto alterado";
				
				qtde = stmt.executeUpdate( " DELETE FROM tb_produto  WHERE pr_codprod = 3");
				temp += " <br> DELETE("+qtde.toString()+") produtos removido ";
			
				temp += "<br> Mostrar vendas: <br>";
				//Query de retorno de produtos cadastrados
				result = stmt.executeQuery( " SELECT vd_codvend, "+
													  "vd_codusu, "+
													  "vd_itmvend,"+
													  "vd_vlrvend "+
													  "FROM tb_vendas ");
				
				temp += "<table border =\"1\"> ";
				temp += "<tr>";
				temp +=   "<td> Codigo </td> <td> Codigo Usuario </td> <td> Itens </td> <td> Valor </td>";
				temp += "</tr>";
				
				//proxima linha
				
				while(result.next()){
					temp += "<tr>";
			        temp +=   "<td>"+result.getInt("vd_codvend")+"</td>" ;
			        temp +=   "<td>"+result.getInt("vd_codusu")+"</td>" ;
			        temp +=   "<td>"+result.getString("vd_itmvend")+"</td>" ;
			        temp +=   "<td>"+result.getDouble("vd_vlrvend")+"</td>" ;
			        temp += "</tr>";
				}
				temp += "</table>";
				
				qtd = stmt.executeUpdate( " UPDATE tb_vendas SET vd_itmvend = 'teste2' WHERE vd_codvend = 2");
				temp += " <br> UPDATE("+qtd.toString()+") venda alterada";
				
				qtde = stmt.executeUpdate( " DELETE FROM tb_vendas  WHERE vd_codvend = 2");
				temp += " <br> DELETE("+qtde.toString()+") venda removida ";
			
			}
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			temp += " se = "+se.getMessage();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			temp += " e = "+e.getMessage();
		}
		return temp;
	}

	
}
