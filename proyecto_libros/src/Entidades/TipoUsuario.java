package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoUsuario {
	
	private int id;
	private String nombre;
	
	public TipoUsuario(int idTipoUsu, String nombre) {
		
		this.id=idTipoUsu;
		this.nombre=nombre;
		
	}
	
	public TipoUsuario(int idTipoComun) {
		this.id = idTipoComun;
	}

	public TipoUsuario(ResultSet rs) throws SQLException {
		this.id = rs.getInt("idtipousu");
		this.nombre = rs.getString("nombre");
	}

	public int getId(){
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}


}
