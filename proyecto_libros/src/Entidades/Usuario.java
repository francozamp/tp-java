package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import Datos.DatosEstado;
import Datos.DatosTipoUsuario;
import Negocio.NegocioTipoUsuario;

public class Usuario {
	
	private Integer id;
	private String email;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private String direccion2;
	private String password;
	private Estado estado;
	private TipoUsuario tipoUsuario;
	private LocalDate fechaAlta;
	
//	public Usuario(String email,String nombre, String apellido, String telefono,String direccion, String pass, int idEstado, int idTipoUsu){
//		
//		this.email=email;
//		this.nombre=nombre;
//		this.apellido=apellido;
//		this.telefono=telefono;
//		this.direccion=direccion;
//		this.password=pass;
//		
//		DatosEstado de=new DatosEstado();
//		this.estado=de.getEstado(idEstado);
//		
//		DatosTipoUsuario dtu=new DatosTipoUsuario();
//		this.tipoUsuario=dtu.getTipoUsuario(idTipoUsu);
//		
//	}
	
	public Usuario(ResultSet rs) throws SQLException {
		this.id=rs.getInt("id");
		this.email=rs.getString("email");
		this.password=rs.getString("contrasena");
		this.nombre=rs.getString("nombre");
		this.apellido=rs.getString("apellido");
		this.telefono=rs.getString("telefono");
		this.direccion=rs.getString("direccion");
		this.direccion2=rs.getString("direccion2");
		
		DatosEstado de=new DatosEstado();
		this.estado=de.getEstado(rs.getInt("estados_idestados"));
		
		DatosTipoUsuario dtu=new DatosTipoUsuario();
		this.tipoUsuario=dtu.getTipoUsuario(rs.getInt("tipousu_idtipousu"));
		
		this.fechaAlta= rs.getDate("fechaAlta").toLocalDate();
		
	}

	public Usuario(String nombre, String apellido, String email, String password, String direccion, String direccion2, String telefono, Integer idTipoUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.direccion = direccion;
		this.direccion2 = direccion2;
		this.telefono = telefono;
		if(idTipoUsuario != null){
			this.tipoUsuario = new NegocioTipoUsuario().getTipoUsuario(idTipoUsuario);
		}
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

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

    public String getContrasena() {
        return this.password;
    }

    public int getTipousu() {
        return this.tipoUsuario.getId();
    }
    
    public LocalDate getFechaAlta(){
    	return this.fechaAlta;
    }

	public void setEstado(int idEstadoActivo) {
		this.estado = new Estado(idEstadoActivo);	
	}

	public void setTipoUsuario(int idTipoComun) {
		this.tipoUsuario = new TipoUsuario(idTipoComun);
		
	}

	public void setFechaAlta(LocalDate now) {
		this.fechaAlta = now;
	}
	
	public String getPasswordView(){
		char[] caracteres = new char[this.password.length()];
		Arrays.fill(caracteres, '*');
		return new String(caracteres);
	}
	
	public String getNombreApellido(){
		return this.nombre + " " + this.apellido;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

}
