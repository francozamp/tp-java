package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entidades.Precio;

public class DatosPrecio {

	public Precio getPrecioActualPorLibroId(int idLibro) {
		
		Precio precioActual = null;
		
		Conexion conexion = new Conexion();
		String query = "SELECT max(fecha_des) as fecha, libros_id, precio FROM precios WHERE libros_id = ?";
		
		conexion.crearConexion();
		
		PreparedStatement ps = conexion.preparedStatement(query);
		try {
			ps.setInt(1, idLibro);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				precioActual = new Precio(rs);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		conexion.cerrarConexion();
		
		return precioActual;
	}

}
