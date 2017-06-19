package Negocio;

import java.util.List;

import Datos.DatosLineaPedido;
import Entidades.LineaPedido;

public class NegocioLineaPedido {

	public List<LineaPedido> getLineasPorPedido(int idPedido) {
		return new DatosLineaPedido().getLineasPorPedido(idPedido);
	}

	public int guardarLineaPedido(LineaPedido lp, int idPedido) {
		return new DatosLineaPedido().guardarLineaPedido(lp, idPedido);
	}

}
