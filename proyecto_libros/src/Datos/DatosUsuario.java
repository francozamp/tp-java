package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				usu=new Usuario(rs.getString("email"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("contrasena"), rs.getInt("estados_idestados"), rs.getInt("tipousu_idtipousu"));
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return usu;
		
	}

}
