package Entidades;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Pedido {
	
	private int id;
	private Date fecha;
	private Usuario usuario;
	private Estado estado;
	private List<LineaPedido> lineasPedido;
	
	public Pedido(){
		this.fecha = new Date();
		lineasPedido = new ArrayList<LineaPedido>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
	
	public int getCantLibros(){
		int cantidad = 0;
		for (LineaPedido lineaPedido : lineasPedido) {
			cantidad += lineaPedido.getCantidad();
		}
		return cantidad;
	}
	
	public double getMontoTotal(){
		double monto = 0;
		for (LineaPedido lineaPedido : lineasPedido) {
			monto += lineaPedido.getLibro().getPrecioActual().getPrecio() + lineaPedido.getCantidad();
		}
		return monto;
	}
	
	public String getMontoTotalView(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(this.getMontoTotal());
	}

}
