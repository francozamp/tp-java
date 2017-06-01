package Negocio;

import Datos.DatosPedido;
import Entidades.Pedido;

public class NegocioPedido {

	public Pedido guardarPedido(Pedido pedido) {
		
		DatosPedido datosPedido = new DatosPedido();
		return datosPedido.guardarPedido(pedido);
	}

}
