package Entidades;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	public int id;
	public Date fecha;
	public Usuario usuario;
	public Estado estado;
	public List<LineaPedido> lineasPedido;
	

}
