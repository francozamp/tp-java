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
			//TODO Hacer metodo para actualizar pedido
		}
		else{
			pedidoGuardado = this.nuevoPedido(pedido);
		}
		
		return pedidoGuardado;
	}

	public Pedido actualizarEstado(Pedido pedido) {
		Pedido pedidoGuardado = null;
		
		Conexion conexion = new Conexion();
		String query = "UPDATE pedidos SET estados_idestados=?";
		
		conexion.crearConexion();
		PreparedStatement ps = conexion.preparedStatement(query);
		
		try{
			ps.setInt(1, pedido.getEstado().getID());
			
			int resul = ps.executeUpdate();
			
			if(resul>0){
				pedidoGuardado = this.findById(pedido.getId());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
		
		pedidoGuardado = this.findById(idGuardado);
		
		return pedidoGuardado;
	}

	public Pedido findById(int idPedido) {
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
