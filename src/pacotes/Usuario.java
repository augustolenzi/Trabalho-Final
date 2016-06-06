package pacotes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pacotes.Usuario;

public class Usuario extends Conexao{
	
	public static final String SQL_CREAT = "CREATE TABLE tb_usuario ( "
						                   + " "+Usuario.COD+" int(11) NOT NULL AUTO_INCREMENT, "
						                   + " "+Usuario.NOM+" varchar(100) NOT NULL, "
						                   + " "+Usuario.SEN+" varchar(100) NOT NULL, "
						                   + " "+Usuario.EMA+" varchar(100) NOT NULL, "
						                   + "  PRIMARY KEY ("+Usuario.COD+") )";
	
	//Cria as variaveis do usuario
	public static final String COD = "us_codusu";
	public static final String NOM = "us_nomusu";
	public static final String SEN = "us_senusu";
	public static final String EMA = "us_emausu";
	
	//Atributos dinamicos dependem do objeto DAOUsuario
	private String cod;
	private String nom;
	private String ema;
	private String sen;
	
	// Polimorfismo nos construtores Construtores
		/** Construtor simples, não preenche os atributos dinâmiicos da classe*/
		public Usuario() {
			super();
			setCod("0");
			setNom("");
			setSen("");
			setEma("");
		}
		
		/** Construtor inteligente, quando recebe o codigo 
		 * preenche seus atributos conforme esta
		 *  o registro na base*/
		public Usuario(String cod) {
			super();
			instancia(cod);
		}
		
		/** Construtor de atributos 
		 * para novo registro */
		public Usuario(String nom, String sen, String ema) {
			super();
			setCod("0");
			setNom(nom);
			setSen(sen);
			setEma(ema);
		}
		
		/** Construtor de com 
		 * todos os atributos */
		public Usuario(String cod, String nom, String sen, String ema) {
			super();
			setCod(cod);
			setNom(nom);
			setSen(sen);
			setEma(ema);
		}
		
		public Usuario instancia(String cod) {
			ResultSet result = (ResultSet) consulta("SELECT * FROM tb_usuario WHERE "+Usuario.COD+" = "+ cod );
			try {
				if(result.first() ){
					// first move o cursor para a primeira linha 
					// da lista de linhas em result, ou null
					setCod( result.getInt(Usuario.COD) );
					setNom(result.getString(Usuario.NOM));
					setSen(result.getString(Usuario.SEN));
					setEma(result.getString(Usuario.EMA));
				}
			} 
			catch (SQLException e) {
				//e.printStackTrace();
				return null; 
				// Aqui encerra o metodo DaoUsuario instancia()
			}
			return this; 
			// Retorna o objeto agora com os valores setados
		}
		
		public ArrayList<Usuario> lista(){
			return lista(null,null,null);
		}
		public ArrayList<Usuario> lista(String where){
			return lista(where,null,null);
		}
		public ArrayList<Usuario> lista(String where, String order){
			return lista(where,order,null);
		}
		public ArrayList<Usuario> lista(String where, String order, String limit ){
			if(where==null) where="1=1";
			if(order==null) order="1";
			if(limit==null) limit="100";
			ArrayList<Usuario> retLista = new ArrayList<Usuario>();
			ResultSet r = (ResultSet) consulta("SELECT * FROM tb_usuario WHERE "+where+" ORDER BY "+order+" LIMIT "+limit );
			try {
				while(r.next()){
					retLista.add( new Usuario( new Integer( r.getInt(Usuario.COD) ).toString()
							                    ,r.getString(Usuario.NOM)
							                    ,r.getString(Usuario.SEN)
							                    ,r.getString(Usuario.EMA)
												) 
								);
				}
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			return retLista;
		}

		public Usuario login(String usu, String sen){
			ArrayList<Usuario> l = lista( " "+Usuario.NOM+" = '"+usu+"' AND "+Usuario.SEN+" = '"+sen+"' " );
			if( l.size() == 1){
				this.instancia(  l.get(0).getCod() );
				return this;
			}
			else{
				return null;
			}
		}
		
		public boolean salvar() {
			if( Integer.parseInt(this.getCod().trim()) == 0 ){
				String sql = "INSERT INTO tb_usuario(    us_nomusu        ,  us_senusu         ,  us_emausu          ) "
						   +                " VALUES( '"+this.getNom()+"' , '"+this.getSen()+"', '"+this.getEma()+"' ) ";
				this.setCod( inserir(sql) );
				return !this.getCod().equals("0");
			}
			else{
				String sql = "UPDATE tb_usuario SET "+Usuario.NOM+" = '"+this.getNom()+"' "
					                           	+", "+Usuario.SEN+" = '"+this.getSen()+"' "
					                           	+", "+Usuario.EMA+" = '"+this.getEma()+"' "
						                        + " WHERE "+Usuario.COD+" =  "+this.getCod()+" ";
				return comando(sql)>0;
			}
			
		}

		public boolean deletar() {
			String sql = "DELETE FROM tb_usuario WHERE "+Usuario.COD+" =  "+this.getCod()+" ";
			return comando(sql)>0;
		}
		
		
		// Padrao de encapsulamento de atributos
		public String getCod() {
			return cod;
		}
		public void setCod(String cod) {
			this.cod = cod;
		}
		public void setCod(int cod) {
			this.cod = new Integer(cod).toString();
		}
		
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public String getEma() {
			return ema;
		}
		public void setEma(String ema) {
			this.ema = ema;
		}
		
		public String getSen() {
			return sen;
		}
		public void setSen(String sen) {
			this.sen = sen;
		}

}
