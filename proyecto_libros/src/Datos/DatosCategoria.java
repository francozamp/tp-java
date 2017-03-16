package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.Categoria;

public class DatosCategoria {
	
	public Categoria guardarCategoria(Categoria categoria){
		
		int clave = 0;
		Conexion conexion=new Conexion();
		conexion.crearConexion();
		
		String sql = null;
		
		if(categoria.getId()==0){
			sql="INSERT INTO categorias (nombre, descripcion) VALUES (?,?)";
		}
		else
		{
			sql="UPDATE categorias SET nombre=?, descripcion=? WHERE idcategorias=?";			
		}
		
		PreparedStatement ps=conexion.preparedStatement(sql);
		
		try{
			ps.setString(1, categoria.getNombre());
			ps.setString(2, categoria.getDescripcion());
			
			if(categoria.getId()!=0){
				ps.setInt(3, categoria.getId());
			}
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
               clave = rs.getInt(1);
            }
            
            if (clave!=0){
            	categoria = this.getCategoria(clave);
            }
            else{
            	categoria = null;
            }
            
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return categoria;
		
	}

	private Categoria getCategoria(int clave) {

		Categoria categoria=null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM categorias WHERE idcategorias=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, clave);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategorias"));
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return categoria;
	}

	public List<Categoria> getCategorias() {
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria=null;
		
		Conexion conexion = new Conexion();
		
		try{
			String sql="SELECT * FROM categorias";
			
			conexion.crearConexion();
			ResultSet rs=conexion.consultarTabla(sql);
			
			while(rs.next()){
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategorias"));
				
				categorias.add(categoria);
			}
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		return categorias;
	}

}