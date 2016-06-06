package pacotes;

public abstract class Configuracao {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String BANCO = "jdbc:mysql://localhost/";
	public static final String BASE = "trabalho";
	
	public static final String USER = "root";
	public static final String PASS = "";
	
	public java.sql.Connection conn;
	public java.sql.Statement stmt;
	
}
