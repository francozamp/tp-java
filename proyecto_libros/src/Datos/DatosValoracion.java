package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import Entidades.Usuario;
import Entidades.Valoracion;
import Negocio.NegocioLibro;
import Negocio.NegocioUsuario;

public class DatosValoracion {

	public Valoracion guardarValoracion(Valoracion valoracion) {
		Valoracion valoracionGuardada = null;
		Integer idValoracionGuardada = null;
		
		Conexion conexion = new Conexion();
		
		conexion.crearConexion();
		
		Boolean nuevaValoracion = (valoracion.getId() == null);
		
		String sql = null;
		
		//Primero guardo el libro
		if (nuevaValoracion) {
			sql = "INSERT INTO valoracion (idUsuario, idLibro) VALUES (?,?)";
		}
		else{
			sql = "UPDATE valoracion SET puntaje = ?, comentario = ? WHERE id = ?";
		}
		
		PreparedStatement ps = conexion.preparedStatement(sql);
		try{
			
			if (nuevaValoracion) {
				ps.setInt(1, valoracion.getUsuario().getId());
				ps.setInt(2, valoracion.getLibro().getId());
			}
			else {
				if(valoracion.getPuntaje() != null){
					ps.setInt(1, valoracion.getPuntaje());
				}
				else{
					ps.setNull(1, Type.INT);
				}				
				ps.setString(2, valoracion.getComentario());
				ps.setInt(3, valoracion.getId());
			}
			
			int resul = ps.executeUpdate();
			if (resul > 0) {
				if (nuevaValoracion) {
					ResultSet rs = ps.getGeneratedKeys();
					while(rs.next()){
						idValoracionGuardada = rs.getInt(1);
					}
				}
				else{
					idValoracionGuardada = valoracion.getId();
				}
				
				valoracionGuardada = this.findById(idValoracionGuardada);
			}
			
		}catch (Exception e) {
			System.out.println("Hubo un problema al registrar el libro: " + e.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return valoracionGuardada;
	}

	public Valoracion findById(Integer idValoracion) {
		Valoracion valoracion = null;
		
		Conexion conexion = new Conexion();
		conexion.crearConexion();
		
		String query = "SELECT * FROM valoracion WHERE id = ?";
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			ps.setInt(1, idValoracion);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				valoracion = new Valoracion();
				valoracion.setId(rs.getInt("id"));
				valoracion.setUsuario(new NegocioUsuario().getUsuarioPorId(rs.getInt("idUsuario")));
				valoracion.setLibro(new NegocioLibro().getLibroById(rs.getInt("idLibro")));
				valoracion.setPuntaje(rs.getInt("puntaje"));
				valoracion.setComentario(rs.getString("comentario"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valoracion;
	}

	public List<Valoracion> findByUsuario(Usuario usuario) {
		List<Valoracion> valoracionesList = new ArrayList<Valoracion>();
		
		Conexion conexion = new Conexion();
		conexion.crearConexion();
		
		String query = "SELECT * FROM valoracion WHERE idUsuario = ?";
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			ps.setInt(1, usuario.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Valoracion valoracion = new Valoracion();
				valoracion.setId(rs.getInt("id"));
				valoracion.setUsuario(new NegocioUsuario().getUsuarioPorId(rs.getInt("idUsuario")));
				valoracion.setLibro(new NegocioLibro().getLibroById(rs.getInt("idLibro")));
				valoracion.setPuntaje(rs.getInt("puntaje"));
				valoracion.setComentario(rs.getString("comentario"));
				valoracionesList.add(valoracion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valoracionesList;
	}

	public List<Valoracion> findByLibro(Integer idLibro) {
		List<Valoracion> valoracionesList = new ArrayList<Valoracion>();
		
		Conexion conexion = new Conexion();
		conexion.crearConexion();
		
		String query = "SELECT * FROM valoracion WHERE idLibro = ?";
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			ps.setInt(1, idLibro);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Valoracion valoracion = new Valoracion();
				valoracion.setId(rs.getInt("id"));
				valoracion.setUsuario(new NegocioUsuario().getUsuarioPorId(rs.getInt("idUsuario")));
				valoracion.setLibro(new NegocioLibro().getLibroById(rs.getInt("idLibro")));
				valoracion.setPuntaje(rs.getInt("puntaje"));
				valoracion.setComentario(rs.getString("comentario"));
				valoracionesList.add(valoracion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return valoracionesList;
	}

}
