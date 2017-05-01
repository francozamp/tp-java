package Entidades;

public class TipoUsuario {
	
	private int id;
	private String nombre;
	
	public TipoUsuario(int idTipoUsu, String nombre) {
		
		this.id=idTipoUsu;
		this.nombre=nombre;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}

}
