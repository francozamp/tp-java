package Datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.Usuario;

public class DatosUsuario {
	
	public Usuario hacerLogin(String email, String pass){
		
		Usuario usu = null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM usuarios WHERE email=? and contrasena=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				usu=new Usuario(rs);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return usu;
		
	}

    public Usuario guardarUsuario(Usuario usuario) {
        int guardado = 0;
        String sql = null;
        boolean insert;
        Conexion conexion = new Conexion();
        conexion.crearConexion();
        insert = !this.existeUsuario(usuario.getEmail());
        if (insert) {
            sql = "INSERT INTO usuarios (email, nombre, apellido, telefono, direccion, contrasena, estados_idestados, tipousu_idtipousu, fechaAlta, direccion2) VALUES (?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=?, contrasena=?, estados_idestados=?, tipousu_idtipousu=?, direccion2=? WHERE email=?";
        }

        PreparedStatement ps = conexion.preparedStatement(sql);

        try {
            if (insert) {
                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getTelefono());
                ps.setString(5, usuario.getDireccion());
                ps.setString(6, usuario.getContrasena());
                ps.setInt(7, usuario.getEstado().getID());
                ps.setInt(8, usuario.getTipoUsuario().getId());
                ps.setDate(9, Date.valueOf(usuario.getFechaAlta()));
                ps.setString(10, usuario.getDireccion2());
            } else {
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellido());
                ps.setString(3, usuario.getTelefono());
                ps.setString(4, usuario.getDireccion());
                ps.setString(5, usuario.getContrasena());
                ps.setInt(6, usuario.getEstado().getID());
                ps.setInt(7, usuario.getTipoUsuario().getId());
                ps.setString(8, usuario.getDireccion2());
                ps.setString(9, usuario.getEmail());
            }

            guardado = ps.executeUpdate();

            if (guardado != 0) {
                usuario = this.getUsuario(usuario.getEmail());
            } else {
                usuario = null;
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        conexion.cerrarConexion();

        return usuario;
    }

    public boolean existeUsuario(String email) {
        boolean existe = false;

        Conexion con = new Conexion();
        String sql = "SELECT email FROM usuarios WHERE email=?";

        con.crearConexion();

        PreparedStatement ps = con.preparedStatement(sql); //Creo el prepared statement
        //cuando haya ganas mover esto adentro de Conexion	
        try {
            //Seteo los valores del preapred statement
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                existe = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        con.cerrarConexion();

        return existe;
    }

    public Usuario getUsuario(String clave) {
        Usuario usuario = null;

        Conexion con = new Conexion();
        String sql = "SELECT * FROM usuarios WHERE email=?";

        con.crearConexion();

        PreparedStatement ps = con.preparedStatement(sql); //Creo el prepared statement
        //cuando haya ganas mover esto adentro de Conexion	
        try {
            //Seteo los valores del preapred statement
            ps.setString(1, clave);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        con.cerrarConexion();

        return usuario;
    }

	public Usuario getUsuarioPorId(int idUsuario) {
		
		Usuario usu = null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM usuarios WHERE id=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				usu = new Usuario(rs);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return usu;
	}

	public List<Usuario> getUsuarios() {
		List<Usuario> usuariosList = new ArrayList<Usuario>();
		
		Conexion con = new Conexion();
		String sql = "select * from usuarios u "
				+ "inner join estados e on u.estados_idestados=e.idestados "
				+ "inner join tipousu tu on u.tipousu_idtipousu=tu.idtipousu";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				usuariosList.add(new Usuario(rs));
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return usuariosList;
	}

}
