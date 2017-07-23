package Datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Descuento;
import Entidades.Libro;

public class DatosDescuento {
	
	public Descuento guardarDescuento(Descuento descuento){
		
		Descuento descuentoGuardado = null;
		Integer idDescuentoGuardado = null;
		
		Conexion conexion = new Conexion();
		
		conexion.crearConexion();
		
		Boolean nuevoDescuento = (descuento.getId() == null);
		
		String sql = null;
		
		// Guardo el descuento
		if (nuevoDescuento) {
			sql = "INSERT INTO descuentos(codigo,porcDesc,fechaDesde,fechaHasta,fechaAlta) VALUES (?,?,?,?,?)";
		}
		else{
			sql = "UPDATE descuentos SET porcDesc = ?, fechaDesde = ?, fechaHasta = ? WHERE id = ?";
		}
		
		PreparedStatement ps = conexion.preparedStatement(sql);
		try{
			int cont = 1;
			
			if (nuevoDescuento) {
				ps.setString(cont++, descuento.getCodigo());
			}
			ps.setFloat(cont++, descuento.getPorcDescuento());
			
			java.sql.Date sqlFechaDesde = new java.sql.Date(descuento.getFechaDesde().getTime());
			ps.setDate(cont++, sqlFechaDesde);
			
			java.sql.Date sqlFechaHasta = new java.sql.Date(descuento.getFechaHasta().getTime());
			ps.setDate(cont++, sqlFechaHasta);

			if (nuevoDescuento) {
				Calendar ahora = Calendar.getInstance();
				ps.setDate(cont++, new Date((ahora.getTime()).getTime()));
			}
			if (!nuevoDescuento) {
				ps.setInt(cont++, descuento.getId());
			}
			
			int resul = ps.executeUpdate();
			if (resul > 0) {
				if (nuevoDescuento) {
					ResultSet rs = ps.getGeneratedKeys();
					while(rs.next()){
						idDescuentoGuardado = rs.getInt(1);
					}
				}
				else{
					idDescuentoGuardado = descuento.getId();
				}
				
			descuentoGuardado = this.getDescuentoById(idDescuentoGuardado);
			}
			
			
		}catch (Exception e) {
			System.out.println("Hubo un problema al registrar el descuento: " + e.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return descuentoGuardado;
	}

	public Descuento getDescuentoById(int idDescuento) {
	
	Descuento descuento = null;
	
	Conexion con = new Conexion();
	String sql = "SELECT * FROM descuentos WHERE id=?";
	
	con.crearConexion();
	
	PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
	//cuando haya ganas mover esto adentro de Conexion	
	try{
		//Seteo los valores del preapred statement
		ps.setInt(1, idDescuento);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			descuento = new Descuento(rs);
		}
	}
	catch(Exception ex){
		System.out.println(ex.getMessage());
	}
	
	con.cerrarConexion();
	
	return descuento;
	}
	
	public Descuento getDescuentoByCodigo(String codigo) {
		
		Descuento descuento = null;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM descuentos WHERE codigo=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setString(1, codigo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				descuento = new Descuento(rs);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		
		return descuento;
		}
	
	public List<Descuento> getDescuentos() {
		
		List<Descuento> descuentos = new ArrayList<Descuento>();
		Descuento descuento=null;
		
		Conexion conexion = new Conexion();
		
		try{
			String sql="SELECT * FROM descuentos";
			
			conexion.crearConexion();
			ResultSet rs=conexion.consultarTabla(sql);
			
			while(rs.next()){
				descuento = new Descuento(rs.getString("codigo"),rs.getFloat("porcDesc"),rs.getDate("fechaDesde"),rs.getDate("fechaHasta"));
				descuento.setId(rs.getInt("id"));
				
				descuentos.add(descuento);
			}
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return descuentos;
	}
	
	public boolean validarDescuentoDisponibleUsuario(int idDescuento,int idUsuario) {
		
		boolean esValido = false;
		
		Conexion con = new Conexion();
		String sql = "SELECT * FROM usuarios_descuentos WHERE usuarios_id=? AND descuentos_id=?";
		
		con.crearConexion();
		
		PreparedStatement ps=con.preparedStatement(sql); //Creo el prepared statement
		//cuando haya ganas mover esto adentro de Conexion	
		try{
			//Seteo los valores del preapred statement
			ps.setInt(1, idUsuario);
			ps.setInt(2, idDescuento);
			
			ResultSet rs = ps.executeQuery();
			
			//Si no trajo ningun resultado es porque dicho usuario no usó el código
			if (!rs.isBeforeFirst() ) {    
			    esValido = true;
			}
			

		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		con.cerrarConexion();
		return esValido;
		
	}

	public Integer registrarDescuentoUtilizado(int idDescuento,int idUsuario) {
		// TODO Auto-generated method stub
		Integer idUsuarioDescuentoGuardado = null;
		
		Conexion conexion = new Conexion();
		
		conexion.crearConexion();
		
		// Guardo el descuento utilizado por el usuario logueado
		String sql = "INSERT INTO usuarios_descuentos(usuarios_id,descuentos_id,fechaUtilizado) VALUES (?,?,CURDATE());";

		
		PreparedStatement ps = conexion.preparedStatement(sql);
		try{
			int cont = 1;
			
			ps.setInt(cont++, idUsuario);
			ps.setInt(cont++, idDescuento);
			
			int resul = ps.executeUpdate();
			if (resul > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					while(rs.next()){
						idUsuarioDescuentoGuardado = rs.getInt(1);
					}
			}
			
			
		}catch (Exception e) {
			System.out.println("Hubo un problema al registrar el descuento utilizado: " + e.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return idUsuarioDescuentoGuardado;
		
	}

}
