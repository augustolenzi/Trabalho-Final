package pacotes;

import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.ResultSet;

import pacotes.Vendas;

public class Vendas extends Conexao{
	public static final String SQL_CHEAT = "CREATE TABLE tb_vendas ( "
										   + " "+Vendas.COD+" int(11) NOT NULL AUTO_INCREMENT, "
										   + " "+Vendas.CODU+" int(11) NOT NULL, "
										   + " "+Vendas.ITM+" varchar(200) NOT NULL, "
										   + " "+Vendas.VLR+" double(8,2) NOT NULL, "
										   + "  PRIMARY KEY ("+Vendas.COD+") )";
	
	//Cria as variaveis da Venda
	public static final String COD = "vd_codvend";
	public static final String CODU = "vd_codusu";
	public static final String ITM = "vd_itmvend";
	public static final String VLR = "vd_vlrvend";
	
	//Atributos dinamicos
	private String cod;
	private String codu;
	private String itm;
	private String vlr;
	
	//Polimorfismo nos construtores
		/**SIMPLES*/
	public Vendas(){
		super();
		setCod("0");
		setCodu("");
		setItm("");
		setVlr("");
	}
	
		/**SOMENTE O CODIGO*/
	public Vendas(String cod){
		super();
		instancia(cod);
	}
	
		/**COD USUARIO + item*/
	public Vendas (String codu, String itm){
		super();
		setCod("0");
		setCodu(codu);
		setItm(itm);
		setVlr("");
	}
	
		/**COD + CODU + ITEM*/
	public Vendas(String cod, String codu, String itm){
		super();
		setCod(cod);
		setCodu(codu);
		setItm(itm);
		setVlr("");
	}
	
	/**COD + CODU + ITM + VLR*/
	public Vendas(String cod, String codu, String itm, String vlr){
		super();
		setCod(cod);
		setCodu(codu);
		setItm(itm);
		setVlr(vlr);
	}
	
	public Vendas instancia(String cod){
		ResultSet result = (ResultSet) consulta("SELECT * FROM tb_vendas WHERE "+Vendas.COD+" = "+cod);
		try{
			if(result.first() ){
				//cursor para o inicio
				setCod(result.getInt(Vendas.COD));
				setCodu(result.getInt(Vendas.CODU));
				setItm(result.getString(Vendas.ITM));
				setVlr(result.getDouble(Vendas.VLR));
			}
		}
		catch (SQLException e){
			//e.printStackTrace();
			return null;
			//Retorna o DAOVenda instancia()
		}
		return this;
		//Retorna o objeto com os valores
	}
	public ArrayList<Vendas> lista(){
		return lista(null,null,null);
	}
	public ArrayList<Vendas> lista(String where){
		return lista(where,null,null);
	}
	public ArrayList<Vendas> lista(String where, String order){
		return lista(where,order,null);
	}
	public ArrayList<Vendas> lista(String where, String order, String limit){
		if(where==null) where="1=1";
		if(order==null) order="1";
		if(limit==null) limit="100";
		ArrayList<Vendas> retLista = new ArrayList<Vendas>();
		ResultSet r = (ResultSet) consulta("SELECT * FROM tb_vendas WHERE "+where+" ORDER BY "+order+" LIMIT "+limit);
		try {
			while(r.next()){
				retLista.add( new Vendas( new Integer( r.getInt(Vendas.COD) ).toString()
						                    ,r.getString(Vendas.CODU)
						                    ,r.getString(Vendas.ITM)
						                    ,r.getString(Vendas.VLR)
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
			String sql = "INSERT INTO tb_vendas(vd_qtdvend, vd_itmvend, vd_vlrvend) "
					   +                " VALUES( '"+this.getItm()+"' , '"+this.getVlr()+"' ) ";
			this.setCod( inserir(sql) );
			return !this.getCod().equals("0");
		}
		else{
			String sql = "UPDATE tb_vendas SET "+Vendas.ITM+" = '"+this.getItm()+"' "
				                           	+", "+Vendas.VLR+" = '"+this.getVlr()+"' "
				                           	+ " WHERE "+Vendas.COD+" =  "+this.getCod()+" ";
			return comando(sql)>0;
		}
	}
	
	public boolean deletar() {
		String sql = "DELETE FROM tb_vendas WHERE "+Vendas.COD+" =  "+this.getCod()+" ";
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
	
	//Getter and Setter Codigo Usuario
	public String getCodu(){
		return codu;
	}
	public void setCodu(String codu){
		this.codu = codu;
	}
	public void setCodu(int codu){
		this.codu = new Integer(codu).toString();
	}
	
	//Getter and Setter Item
	public String getItm() {
		return itm;
	}
	public void setItm(String itm) {
		this.itm = itm;
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
}
