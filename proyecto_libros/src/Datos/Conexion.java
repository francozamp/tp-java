package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
	
	private Connection conexion;
	
	public boolean crearConexion()
	{
		boolean rta = true;
		
		try{		
			Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/libros?autoReconnect=true&useSSL=false","root","root");	
		}
		catch(SQLException ex){
			ex.printStackTrace();
			rta = false;
		}
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
			rta = false;
		}
		catch(Exception ex){
			ex.printStackTrace();
			rta = false;
		}
	 
	   return rta;
	}
	
	public boolean cerrarConexion()
	{
		boolean rta = true;
		
		try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            rta = false;
        }
		
        return rta;
	}
	
	public ResultSet consultarTabla(String sql){
		
		ResultSet resultado = null;
		
		try {
			Statement sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery(sql);
			
		}
		catch (SQLException ex){
			ex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return resultado;	
	}

	public PreparedStatement preparedStatement(String sql) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = this.conexion.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}

}
