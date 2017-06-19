package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entidades.LineaPedido;

public class DatosLineaPedido {

	public List<LineaPedido> getLineasPorPedido(int idPedido) {
		
		List<LineaPedido> lineaPedidoList = new ArrayList<LineaPedido>();
		
		Conexion conexion =  new Conexion();
		String query = "SELECT * FROM lineaspedido WHERE pedidos_idpedidos=?";
		
		conexion.crearConexion();
		
		PreparedStatement ps = conexion.preparedStatement(query);
		
		try {
			ps.setInt(1, idPedido);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				lineaPedidoList.add(new LineaPedido(rs));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return lineaPedidoList;
	}

	public int guardarLineaPedido(LineaPedido lp, int idPedido) {
		int idLineaGuardada = 0;
		if(lp.getId()!=0){
			//TODO actualizar linea pedido
		}
		else{
			idLineaGuardada = this.nuevaLineaPedido(lp, idPedido);
		}
		
		return idLineaGuardada;
	}

	private int nuevaLineaPedido(LineaPedido lp, int idPedido) {
		int idLineaGuardada = 0;
		
		Conexion conexion = new Conexion();
		conexion.crearConexion();
		String query = "INSERT INTO lineaspedido (pedidos_idpedidos, libros_id, cantidad) VALUES (?,?,?)";
		
		PreparedStatement ps = conexion.preparedStatement(query);
		
		try {
			ps.setInt(1, idPedido);
			ps.setInt(2, lp.getLibro().getId());
			ps.setInt(3, lp.getCantidad());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()){
				idLineaGuardada = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return idLineaGuardada;
	}

}
