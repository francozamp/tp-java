package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Entidades.Estado;

public class DatosEstado {
	
	@SuppressWarnings("null")
	public List<Estado> getEstados(){
		
		List<Estado> estados = null;
		Estado estado = null;
		Conexion con = new Conexion();
		String sql = "SELECT * FROM estados";
		con.crearConexion();
		ResultSet rs = con.consultarTabla(sql);
		
		try{
			while (rs.next()){
				estado = new Estado(rs.getInt("idestados"), rs.getString("nombre"));
				estados.add(estado);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		con.cerrarConexion();
		
		return estados;
	}
	
	public Estado getEstado(int id){
		
		Estado estado=null;
		
		Conexion conexion=new Conexion();
		String sql="SELECT * FROM estados WHERE idestados=?";
		
		conexion.crearConexion();
		
		PreparedStatement ps=conexion.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				estado=new Estado(rs.getInt("idestados"), rs.getString("nombre"));
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return estado;
		
	}

}
