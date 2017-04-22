package Entidades;

import Datos.DatosEstado;
import Datos.DatosTipoUsuario;

public class Usuario {
	
	private String email;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private String password;
	private Estado estado;
	private TipoUsuario tipoUsuario;
	
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
	
	public String getEmail(){
		return this.email;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public String getTelefono(){
		return this.telefono;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public Estado getEstado(){
		return this.estado;
	}
	
	public TipoUsuario getTipoUsuario(){
		return this.tipoUsuario;
	}

}
