package pacotes;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Conexao extends Configuracao{

	public Conexao(){
		conecta();
	}
	
	private void conecta(){
		if(this.conn == null){
			try{
				// Registro de Driver de comunicacao do java com o banco
				Class.forName("com.mysql.jdbc.Driver");
				// Conexao com a base
				this.conn = DriverManager.getConnection(BANCO+BASE, USER, PASS);
				this.stmt = conn.createStatement();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/** Executando uma Query
	 * @param String SQL
	 * @retunr ResultSet da sentensa, ou Null
	 */
	
	public java.sql.ResultSet consulta(String str){
		ResultSet res = null;
		conecta();
		try {
			res = (ResultSet) this.stmt.executeQuery(""+str+"");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * Executa um Insert<br>
	 * @param String SQL
	 * @return int o codigo do ultimo registro adicionado
	 */
	public int inserir(String sql){
		int cod = 0;
		conecta();
		try {
			this.stmt.executeUpdate(sql);
			ResultSet rs = (ResultSet) this.stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if (rs.next()){
				cod = rs.getInt(1);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return cod;
	}
	/**
	 * Executa um Update ou um Delete<br>
	 * @param String SQL
	 * @return int numero de registros afetados
	 */
	public int comando(String sql){
		conecta();
		int qtd = 0;
		try {
			qtd = this.stmt.executeUpdate(sql);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return qtd;
	}
}

