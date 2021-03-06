package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.Categoria;
import Entidades.Libro;
import Negocio.NegocioLibro;

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

	public Categoria getCategoria(int idCategoria) {

		Categoria categoria=null;
		List<Libro> libros = new ArrayList<>();
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM categorias c "
//				+ "INNER JOIN libros_categorias lc on c.idcategorias=lc.categorias_idcategorias "
//				+ "INNER JOIN libros l on lc.libros_id=l.id "
				+ "WHERE idcategorias=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, idCategoria);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				categoria = new Categoria(rs);
//				if (categoria == null){
//					categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
//					categoria.setId(rs.getInt("idcategorias"));
//				}
//				libros.add(new Libro(rs));
			}
			categoria.setLibros(new NegocioLibro().getLibrosPorCategoria(idCategoria));
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
		
		conexion.cerrarConexion();
		
		return categorias;
	}

	public List<Categoria> findByDescripcion(String descripcion) {

		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria=null;
		
		String sql = "SELECT * FROM categorias "
				+ "WHERE nombre like ?";
		
		Conexion conexion = new Conexion();
		
		conexion.crearConexion();
		
		PreparedStatement ps = conexion.preparedStatement(sql);
		
		try{
			
			descripcion = "%" + descripcion.replaceAll("\\s+", "%") + "%";
			ps.setString(1, descripcion);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategorias"));
				
				categorias.add(categoria);
			}
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return categorias;
	}

}