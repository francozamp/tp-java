package Negocio;

import java.util.List;
import java.util.Set;

import Datos.DatosPedido;
import Entidades.Libro;
import Entidades.LineaPedido;
import Entidades.Pedido;
import Entidades.Valoracion;

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

	public Pedido pagarPedido(Pedido pedido) {
		
		pedido = new DatosPedido().actualizarEstado(pedido);
		
		Set<Integer> idLibrosUsuarioList = new NegocioLibro().findIdLibrosPorUsuario(pedido.getUsuario());
		
		for (LineaPedido lp : pedido.getLineasPedido()) {
			
			if(!idLibrosUsuarioList.contains(lp.getLibro().getId())){
				Valoracion valoracion = new Valoracion();
				valoracion.setLibro(lp.getLibro());
				valoracion.setUsuario(pedido.getUsuario());
				
				valoracion = new NegocioValoracion().guardarValoracion(valoracion);
			}
		}
		
		return pedido;
	}

}
