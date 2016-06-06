package pacotes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produto extends Conexao{

	public static final String SQL_CHEAT = "CREATE TABLE tb_produto ( "
										   + " "+Produto.COD+" int(11) NOT NULL AUTO_INCREMENT, "
										   + " "+Produto.NOM+" varchar(100) NOT NULL, "
										   + " "+Produto.VLR+" double(8,2) NOT NULL, "
										   + "  PRIMARY KEY ("+Produto.COD+") )";
	
	
	//Cria as variaveis do Produto
	public static final String COD = "pr_codprod";
	public static final String NOM = "pr_nomprod";
	public static final String VLR = "pr_vlrprod";
	
	//Atributos dinamicos
	private String cod;
	private String nom;
	private String vlr;
	private String qtd;
	
	// Polimorfismo nos construtores Construtores
		/** Construtor simples, não preenche os atributos dinâmiicos da classe*/
	public Produto() {
		super();
		setCod("0");
		setNom("");
		setVlr("");
		
	}
	
	/** Construtor inteligente, quando recebe o codigo 
	 * preenche seus atributos conforme esta
	 *  o registro na base*/
	public Produto(String cod) {
		super();
		instancia(cod);
	}
	
	/** Construtor de atributos 
	 * para novo registro */
	public Produto(String nom, String vlr){
		super();
		setCod("0");
		setNom(nom);
		setVlr(vlr);
		
	}
	
	/** Construtor de com 
	 * todos os atributos */
	public Produto(String cod, String nom, String vlr) {
		super();
		setCod(cod);
		setNom(nom);
		setVlr(vlr);
		
	}
	
	public Produto instancia(String cod) {
		ResultSet res = (ResultSet) this.consulta("SELECT * FROM tb_produto WHERE pr_codprod = '"+cod+"'");
		try {
				res.first() ;								
				this.setNom(res.getString("pr_nomprod"));
				this.setCod(res.getString("pr_codprod"));
				this.setVlr(res.getString("pr_vlrprod"));
			 
		}
		catch (SQLException e) {
			//e.printStackTrace();
			return null;
			//Retorna o DAOProduto instancia()
		}
		return this;
		//Retorna o objeto com os valores
	}
	
	public ArrayList<Produto> lista(){
		return lista(null,null,null);
	}
	public ArrayList<Produto> lista(String where){
		return lista(where,null,null);
	}
	public ArrayList<Produto> lista(String where, String order){
		return lista(where,order,null);
	}
	public ArrayList<Produto> lista(String where, String order, String limit){
		if(where==null) where="1=1";
		if(order==null) order="1";
		if(limit==null) limit="100";
		ArrayList<Produto> retLista = new ArrayList<Produto>();
		ResultSet r = (ResultSet) consulta("SELECT * FROM tb_produto WHERE "+where+" ORDER BY "+order+" LIMIT "+limit);
		try {
			while(r.next()){
				retLista.add( new Produto( new Integer( r.getInt(Produto.COD) ).toString()
						                    ,r.getString(Produto.NOM)
						                    ,r.getString(Produto.VLR)
											) 
							);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		return retLista;
	}
	
	public boolean salvar() {
		if( Integer.parseInt(this.getCod().trim()) == 0 ){
			String sql = "INSERT INTO tb_produto(pr_nomprod, pr_vlrprod) "
					   +                " VALUES( '"+this.getNom()+"' , '"+this.getVlr()+"' ) ";
			this.setCod( inserir(sql) );
			return !this.getCod().equals("0");
		}
		else{
			String sql = "UPDATE tb_produto SET "+Produto.NOM+" = '"+this.getNom()+"' "
				                           	+", "+Produto.VLR+" = '"+this.getVlr()+"' "
				                           	+ " WHERE "+Produto.COD+" =  "+this.getCod()+" ";
			return comando(sql)>0;
		}
	}
	
	public boolean deletar() {
		String sql = "DELETE FROM tb_produto WHERE "+Produto.COD+" =  "+this.getCod()+" ";
		return comando(sql)>0;
	}

	//Getter and Setter Codigo
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public void setCod(int cod) {
		this.cod = new Integer(cod).toString();
	}
	
	//Getter and Setter Nome
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	//getter and setter Valor
	public String getVlr() {
		return vlr;
	}
	public void setVlr(String vlr) {
		this.vlr = vlr;
	}
	public void setVlr(Double vlr) {
		this.vlr = new Double(vlr).toString();
	}
	
	//getter and setter quantidade	
	
	public void setQtd(String val) {		
		this.qtd = val;
	}
	public void setQtd(int val) {		
		this.qtd = String.valueOf(val);
	}
	public String getQtd() {
		return qtd;
	}
		
}
