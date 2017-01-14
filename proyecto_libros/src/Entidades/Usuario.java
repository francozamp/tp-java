package Entidades;

import Datos.DatosEstado;
import Datos.DatosTipoUsuario;

public class Usuario {
	
	public String email;
	public String nombre;
	public String apellido;
	public String telefono;
	public String direccion;
	public String password;
	public Estado estado;
	public TipoUsuario tipoUsuario;
	
	public Usuario(String email,String nombre, String apellido, String telefono,String direccion, String pass, int idEstado, int idTipoUsu){
		
		this.email=email;
		this.nombre=nombre;
		this.apellido=apellido;
		this.telefono=telefono;
		this.direccion=direccion;
		this.password=pass;
		
		DatosEstado de=new DatosEstado();
		this.estado=de.getEstado(idEstado);
		
		DatosTipoUsuario dtu=new DatosTipoUsuario();
		this.tipoUsuario=dtu.getTipoUsuario(idTipoUsu);
		
	}

}
