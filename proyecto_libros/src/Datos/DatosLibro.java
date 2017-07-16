package Datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Libro;
import Entidades.Usuario;

public class DatosLibro {
	
//	public Libro guardarLibro(Libro libro){
//		
//		boolean libroGuardado = false;
//		boolean categoriaGuardada = false;
//		
//		String idLibro = null;
//		String ISBN2 = null;
//		int idCategoria = 0;
//		
//		Conexion conexion=new Conexion();
//		conexion.crearConexion();
//		
//		String sql = null;
//		String sql2 = null;
//		
//		if(!this.existeLibro(libro.getISBN())){
//			sql="INSERT INTO libros (ISBN, titulo, autor, editorial, edicion, descripcion, estados_idestados) VALUES (?,?,?,?,?,?,?)";
//		}
//		
//		PreparedStatement ps=conexion.preparedStatement(sql);
//		
//		try{
//			ps.setString(1, libro.getISBN());
//			ps.setString(2, libro.getTitulo());
//			ps.setString(3, libro.getAutor());
//			ps.setString(4, libro.getEditorial());
//			ps.setString(5, libro.getEdicion());
//			ps.setString(6, libro.getDescripcion());
//			ps.setInt(7, libro.getIdEstado());
//			
//			ps.executeUpdate();
//			
//			ResultSet rs = ps.getGeneratedKeys();
//            while (rs.next()) {
//               idLibro = rs.getString(1);
//            }
//                   
//            if (!idLibro.replaceAll("\\s+", "").isEmpty()){
//            	libroGuardado = true;
//            }
//            
//		}
//		catch(Exception exception){
//			System.out.println(exception.getMessage());
//		}
//		
//		ps=conexion.preparedStatement(sql2);
//		
//		try{
//			for (Categoria categoria : libro.getCategorias()) {
//				
//				ps.setInt(1, Integer.parseInt(idLibro));
//				ps.setInt(2, categoria.getId());
//				
//				ps.executeUpdate();
//				
//				ResultSet rs = ps.getGeneratedKeys();
//	            while (rs.next()) {
//	               ISBN2 = rs.getString(1);
//	               idCategoria = rs.getInt(2);
//	            }
//	                   
////	            if (!(libro.getISBN()==ISBN2&&categoria.getId()==idCategoria)){
////	            	categoriaGuardada = false;
////	            }		
//			}      
//		}
//		catch(Exception exception){
//			System.out.println(exception.getMessage());
//		}
//		
//		if(libroGuardado){
//			libro = this.getLibro(libro.getISBN());
//		}
//		
//		conexion.cerrarConexion();
//		
//		return libro;
//	}
	
	public Libro guardarLibro(Libro libro){
		
		Libro libroGuardado = null;
		Integer idLibroGuardado = null;
		
		Conexion conexion = new Conexion();
		
		conexion.crearConexion();
		
		Boolean nuevoLibro = (libro.getId() == null);
		
		String sql = null;
		
		//Primero guardo el libro
		if (nuevoLibro) {
			sql = "INSERT INTO libros (ISBN, titulo, autor, editorial, edicion, descripcion, estados_idestados, fechaAlta) VALUES (?,?,?,?,?,?,?,?)";
		}
		else{
			sql = "UPDATE libros SET titulo = ?, autor = ?, editorial = ?, edicion = ?, descripcion = ?, estados_idestados = ? WHERE id = ?";
		}
		
		PreparedStatement ps = conexion.preparedStatement(sql);
		try{
			int cont = 1;
			
			if (nuevoLibro) {
				ps.setString(cont++, libro.getISBN());
			}
			ps.setString(cont++, libro.getTitulo());
			ps.setString(cont++, libro.getAutor());
			ps.setString(cont++, libro.getEditorial());
			ps.setString(cont++, libro.getEdicion());
			ps.setString(cont++, libro.getDescripcion());
			ps.setInt(cont++, libro.getIdEstado());
			if (nuevoLibro) {
				Calendar ahora = Calendar.getInstance();
				ps.setDate(cont++, new Date((ahora.getTime()).getTime()));
			}
			if (!nuevoLibro) {
				ps.setInt(cont++, libro.getId());
			}
			
			int resul = ps.executeUpdate();
			if (resul > 0) {
				if (nuevoLibro) {
					ResultSet rs = ps.getGeneratedKeys();
					while(rs.next()){
						idLibroGuardado = rs.getInt(1);
					}
				}
				else{
					idLibroGuardado = libro.getId();
				}
			}
			
			if(idLibroGuardado != null){
				//Si se guardo el libro guardo la relacion con las categorias
				//La voy a hacer facil.. primero elimino todas las relaciones del libro con categorias y despues agrego las seleccionadas
				
				String sqlDelete = "DELETE FROM libros_categorias WHERE	libros_id = ?";
				
				ps = conexion.preparedStatement(sqlDelete);
				ps.setInt(1, idLibroGuardado);
				ps.executeUpdate();
				
				sql = "INSERT INTO libros_categorias (libros_id,categorias_idcategorias) VALUES (?,?)";
				
				int registrosCreados = 0;
				
				for (Categoria categoria : libro.getCategorias()) {
					ps = conexion.preparedStatement(sql);
					ps.setInt(1, idLibroGuardado);
					ps.setInt(2, categoria.getId());
					
					registrosCreados += ps.executeUpdate();
				}
				
				if (registrosCreados > 0) {
					libroGuardado = this.getLibroById(idLibroGuardado);
				}
				
			}
			
		}catch (Exception e) {
			System.out.println("Hubo un problema al registrar el libro: " + e.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return libroGuardado;
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
		
//		if (libro != null) {
//			Categoria categoria = null;
//			
//			String sql2 = "SELECT * FROM libros_categorias INNER JOIN categorias ON libros_categorias.categorias_idcategorias=categorias.idcategorias WHERE libros_ISBN=?";
//			
//			PreparedStatement ps2=con.preparedStatement(sql2);
//			
//			try{
//				//Seteo los valores del preapred statement
//				ps.setString(1, isbn);
//				
//				ResultSet rs = ps.executeQuery();
//				
//				while(rs.next()){
//					categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
//					categoria.setId(rs.getInt("idcategorias"));
//					categorias.add(categoria);
//				}
//				
//				libro.setCategorias(categorias);
//			}
//			catch(Exception ex){
//				System.out.println(ex.getMessage());
//			}
//		}	
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
		String sql = "SELECT * FROM libros l INNER JOIN libros_categorias lc ON l.id=lc.libros_id INNER JOIN categorias c ON lc.categorias_idcategorias=c.idcategorias GROUP BY l.id";
		
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
				categoria.setId(rs.getInt("idcategorias"));
				categorias.add(categoria);
				
				if(proximo != rs.getString("ISBN")){			
					disponible = rs.getInt("estados_idestados") == Constantes.ID_ESTADO_ACTIVO ? true : false;
					libro = new Libro(rs);
					libros.add(libro);
					categorias = new ArrayList<Categoria>();
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return libros;
	}

	public List<Libro> getRecientes() {
		List<Libro> libros = new ArrayList<Libro>();
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM libros l "
				+ " INNER JOIN libros_categorias lc ON l.id=lc.libros_id "
				+ " INNER JOIN categorias c ON lc.categorias_idcategorias=c.idcategorias"
				+ " ORDER BY fechaAlta DESC LIMIT 9";
		
		con.crearConexion();

		try{
			Libro libro = null;
			Categoria categoria = null;
			List<Categoria> categorias = new ArrayList<Categoria>();
			boolean disponible = false;
			int proximo = 0;
			
			ResultSet rs = con.consultarTabla(sql);
			
			while(rs.next()){
				
				proximo = 0;
				if(rs.next()){
					proximo = rs.getInt("id");
				}
				rs.previous();
				
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategorias"));
				categorias.add(categoria);
				
				if(proximo != rs.getInt("id")){			
					disponible = rs.getInt("estados_idestados") == Constantes.ID_ESTADO_ACTIVO ? true : false;
					libro = new Libro(rs);
					libros.add(libro);
					categorias = new ArrayList<Categoria>();
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return libros;
	}

	public List<Libro> getLibrosPorCategoria(int idCategoria) {
		
		List<Libro> libros = new ArrayList<Libro>();
		Libro libro = null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM libros_categorias lc inner join libros l on lc.libros_id=l.id inner join categorias c on lc.categorias_idcategorias=c.idcategorias where c.idcategorias = ? order by titulo";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, idCategoria);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if (libro == null || (libro != null && libro.getId() != rs.getInt("id"))) {
					libro = new Libro(rs);
					libros.add(libro);
				}
				
				libro.addCategoria(new Categoria(rs));
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			
		con.cerrarConexion();
		
		return libros;
	}

	public Libro getLibroById(int idLibro) {
		
		Libro libro = null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM libros WHERE id=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, idLibro);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				libro = new Libro(rs);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return libro;
	}

	public List<Libro> getLibroPorTituloYCategoria(String tituloLibro, int idCategoria) {
		List<Libro> librosResultado = new ArrayList<Libro>();
		
		Conexion conexion = new Conexion();
		String query = "SELECT * FROM libros l "
				+ " INNER JOIN libros_categorias lc on lc.libros_id = l.id "
				+ " INNER JOIN categorias c ON lc.categorias_idcategorias=c.idcategorias "
				+ " WHERE l.titulo like ? ";
		if(idCategoria != 0){
			query += " AND lc.categorias_idcategorias = ?";
		}
		
		conexion.crearConexion();
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			Libro libro = null;
			Categoria categoria = null;
			List<Categoria> categorias = new ArrayList<Categoria>();
			boolean disponible = false;
			int proximo = 0;
			
			tituloLibro = "%" + tituloLibro.replaceAll("\\s+", "%") + "%";
			ps.setString(1, tituloLibro);
			if(idCategoria != 0){
				ps.setInt(2, idCategoria);
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				proximo = 0;
				if(rs.next()){
					proximo = rs.getInt("id");
				}
				rs.previous();
				
				categoria = new Categoria(rs.getString("nombre"), rs.getString("descripcion"));
				categoria.setId(rs.getInt("idcategorias"));
				categorias.add(categoria);
				
				if(proximo != rs.getInt("id")){			
					disponible = rs.getInt("estados_idestados") == Constantes.ID_ESTADO_ACTIVO ? true : false;
					libro = new Libro(rs);
					librosResultado.add(libro);
					categorias = new ArrayList<Categoria>();
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return librosResultado;
	}

	public List<Libro> findByDescripcion(String descripcion) {
		return this.getLibroPorTituloYCategoria(descripcion, 0);
	}

	public Set<Integer> findIdLibrosPorUsuario(Usuario usuario) {
		
		Set<Integer> librosUsuarioList = new HashSet<Integer>();
		
		Conexion conexion = new Conexion();
		String query = "SELECT * FROM valoracion v "
				+ " INNER JOIN libros l ON v.idLibro = l.id "
				+ " WHERE v.idUsuario = ?";
		
		conexion.crearConexion();
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			ps.setInt(1, usuario.getId());
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				librosUsuarioList.add(rs.getInt("l.id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return librosUsuarioList;
	}

}
