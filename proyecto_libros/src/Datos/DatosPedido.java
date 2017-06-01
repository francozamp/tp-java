package Datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entidades.LineaPedido;
import Entidades.Pedido;
import Negocio.NegocioLineaPedido;

public class DatosPedido {

	public Pedido guardarPedido(Pedido pedido) {
		
		Pedido pedidoGuardado = null;
		if(pedido.getId()!=0){
			//TODO crear metodo para actualizar pedido existente
		}
		else{
			pedidoGuardado = this.nuevoPedido(pedido);
		}
		
		return pedidoGuardado;
	}

	private Pedido nuevoPedido(Pedido pedido) {
		
		Pedido pedidoGuardado = null;
		int idGuardado = 0;
		
		Conexion conexion = new Conexion();
		String sql = "INSERT INTO pedidos (fecha, estados_idestados, usuarios_id) VALUES (?,?,?)";
		
		conexion.crearConexion();
		PreparedStatement ps = conexion.preparedStatement(sql);
		
		try {
			ps.setDate(1, new Date(pedido.getFecha().getTime()));
			ps.setInt(2, pedido.getEstado().getID());
			ps.setInt(3, pedido.getUsuario().getId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()){
				
				idGuardado = rs.getInt(1);
				NegocioLineaPedido negocioLineaPedido = new NegocioLineaPedido();
				for (LineaPedido lp : pedido.getLineasPedido()) {
					int idLineaPedido = negocioLineaPedido.guardarLineaPedido(lp, idGuardado);
					if(idLineaPedido==0){
						Exception exception = new Exception("No se pudo guardar correctamente una linea de pedido!!!");
						throw exception;
					}
				}
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		conexion.cerrarConexion();
		
		pedidoGuardado = this.getFindPedidoById(idGuardado);
		
		return pedidoGuardado;
	}

	private Pedido getFindPedidoById(int idPedido) {
		Pedido pedido = null;
		
		Conexion conexion = new Conexion();
		String query = "SELECT * FROM pedidos p WHERE idPedidos=?";
		
		conexion.crearConexion();
		PreparedStatement ps = conexion.preparedStatement(query);
		
		try {
			ps.setInt(1, idPedido);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				pedido = new Pedido(rs);
			}
			
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		return pedido;
	}

}
