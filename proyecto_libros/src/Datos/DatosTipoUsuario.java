package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entidades.TipoUsuario;

public class DatosTipoUsuario {
	
	public TipoUsuario getTipoUsuario(int id){
		
		TipoUsuario tipoUsuario=null;
		
		Conexion conexion=new Conexion();
		String sql="SELECT * FROM tipousu WHERE idtipousu=?";
		
		conexion.crearConexion();
		
		PreparedStatement ps=conexion.preparedStatement(sql);
		
		try{
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tipoUsuario=new TipoUsuario(rs.getInt("idtipousu"), rs.getString("nombre"));
			}
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return tipoUsuario;
		
		
	}

}
