package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Libro;

public class DatosLibro {
	
	public Libro guardarLibro(Libro libro){
		
		boolean libroGuardado = false;
		boolean categoriaGuardada = false;
		
		String ISBN = null;
		String ISBN2 = null;
		int idCategoria = 0;
		
		Conexion conexion=new Conexion();
		conexion.crearConexion();
		
		String sql = null;
		String sql2 = null;
		
		if(!this.existeLibro(libro.getISBN())){
			sql="INSERT INTO libros (ISBN, titulo, autor, editorial, edicion, descripcion, estados_idestados) VALUES (?,?,?,?,?,?,?)";
			sql2="INSERT INTO libros_categorias (libros_ISBN, categorias_idCategorias) VALUES (?,?)";
		}
		
		PreparedStatement ps=conexion.preparedStatement(sql);
		
		try{
			ps.setString(1, libro.getISBN());
			ps.setString(2, libro.getTitulo());
			ps.setString(3, libro.getAutor());
			ps.setString(4, libro.getEditorial());
			ps.setString(5, libro.getEdicion());
			ps.setString(6, libro.getDescripcion());
			ps.setInt(7, libro.getIdEstado());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
               ISBN = rs.getString(1);
            }
                   
            if (!ISBN.replaceAll("\\s+", "").isEmpty()){
            	libroGuardado = true;
            }
            
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		ps=conexion.preparedStatement(sql2);
		
		try{
			for (Categoria categoria : libro.getCategorias()) {
				
				ps.setString(1, libro.getISBN());
				ps.setInt(2, categoria.getId());
				
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
	            while (rs.next()) {
	               ISBN2 = rs.getString(1);
	               idCategoria = rs.getInt(2);
	            }
	                   
	            if (!(libro.getISBN()==ISBN2&&categoria.getId()==idCategoria)){
	            	categoriaGuardada = false;
	            }		
			}      
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		if(libroGuardado){
			libro = this.getLibro(ISBN);
		}
		
		conexion.cerrarConexion();
		
		return libro;
	}

	public Libro getLibro(String isbn) {
		
		Libro libro = null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		boolean activo = false;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM libros WHERE ISBN=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if (rs.getInt("estados_idestados")==Constantes.ID_ESTADO_ACTIVO) {
					activo = true;
				}
				libro = new Libro(rs.getString("ISBN"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("edicion"), rs.getString("descripcion"), activo, categorias);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		if (libro != null) {
			Categoria categoria = null;
			
			String sql2 = "SELECT * FROM libros_categorias INNER JOIN categorias ON libros_categorias.categorias_idcategorias=categorias.idcategorias WHERE libros_ISBN=?";
			
			PreparedStatement ps2=con.preparedStatement(sql2);
			
			try{
				//Seteo los valores del preapred statement
				ps.setString(1, isbn);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
					categoria.setId(rs.getInt("idcategorias"));
					categorias.add(categoria);
				}
				
				libro.setCategorias(categorias);
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}	
		con.cerrarConexion();
		
		return libro;
	}

	public boolean existeLibro(String isbn) {
		boolean existe = false;
		
		Conexion con = new Conexion();
		String sql = "SELECT ISBN FROM libros WHERE ISBN=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				existe = true;
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return existe;
	}
	
	public List<Libro> getLibros(){
		
		List<Libro> libros = new ArrayList<Libro>();
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM libros l INNER JOIN libros_categorias lc ON l.ISBN=lc.libros_isbn INNER JOIN categorias c ON lc.categorias_idcategorias=c.idcategorias";
		
		con.crearConexion();

		try{
			Libro libro = null;
			Categoria categoria = null;
			List<Categoria> categorias = new ArrayList<Categoria>();
			boolean disponible = false;
			String proximo = "";
			
			ResultSet rs = con.consultarTabla(sql);
			
			while(rs.next()){
				
				proximo = "";
				if(rs.next()){
					proximo = rs.getString("ISBN");
				}
				rs.previous();
				
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategoria"));
				categorias.add(categoria);
				
				if(proximo != rs.getString("ISBN")){			
					disponible = rs.getInt("estados_idestado") == Constantes.ID_ESTADO_ACTIVO ? true : false;
					libro = new Libro(rs.getString("ISBN"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("edicion"), rs.getString("descripcion"), disponible, categorias);
					libros.add(libro);
					categorias = new ArrayList<Categoria>();
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return libros;
	}

}
