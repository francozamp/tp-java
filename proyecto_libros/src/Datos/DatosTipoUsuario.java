package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	public List<TipoUsuario> getTipoUsuarioList() {
		List<TipoUsuario> tipoUsuarioList = new ArrayList<TipoUsuario>();
		
		Conexion conexion=new Conexion();
		String sql="SELECT * FROM tipousu";
		
		conexion.crearConexion();
		
		PreparedStatement ps=conexion.preparedStatement(sql);
		
		try{
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				tipoUsuarioList.add(new TipoUsuario(rs));
			}
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return tipoUsuarioList;
	}

}
