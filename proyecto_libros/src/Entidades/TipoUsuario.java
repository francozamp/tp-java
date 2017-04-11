package Entidades;

public class TipoUsuario {
	
	public int Id;
	public String Nombre;
	
	public TipoUsuario(int idTipoUsu, String nombre) {
		
		this.Id=idTipoUsu;
		this.Nombre=nombre;
		
	}
        public int getID() {
		// TODO Auto-generated method stub
		return Id;
	}

}
