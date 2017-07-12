package Negocio;

import java.util.List;
import Datos.DatosPedido;
import Entidades.Pedido;

public class NegocioPedido {

	public Pedido guardarPedido(Pedido pedido) {
		
		DatosPedido datosPedido = new DatosPedido();
		return datosPedido.guardarPedido(pedido);
	}

	public Pedido findById(int id) {
		return new DatosPedido().findById(id);
	}

	public Pedido actualizarEstado(Pedido pedido) {
		return new DatosPedido().actualizarEstado(pedido);
	}

	public List<Pedido> findPedidosByUsuario(int idUsuario) {
		return new DatosPedido().findPedidosByUsuario(idUsuario);
	}

	public List<Pedido> getPedidosList() {
		return new DatosPedido().getPedidosList();
	}

	public Pedido actualizarPedido(Pedido pedido) {

		pedido = new DatosPedido().actualizarPedido(pedido);
		
		return pedido;
	}

	public List<Pedido> findByDescripcion(String descripcion) {
		return new DatosPedido().findByDescripcion(descripcion);
	}

}
