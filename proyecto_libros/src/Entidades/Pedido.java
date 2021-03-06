package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Negocio.NegocioEstado;
import Negocio.NegocioLineaPedido;
import Negocio.NegocioUsuario;

public class Pedido {
	
	private int id;
	private Date fecha;
	private Usuario usuario;
	private Estado estado;
	private List<LineaPedido> lineasPedido;
	private String seguimiento;
	
	public Pedido(){
		this.fecha = new Date();
		lineasPedido = new ArrayList<LineaPedido>();
	}
	
	public Pedido(ResultSet rs) throws SQLException {
		this.id = rs.getInt("idpedidos");
		this.fecha = rs.getDate("fecha");
		this.usuario = new NegocioUsuario().getUsuarioPorId(rs.getInt("usuarios_id"));
		this.estado = new NegocioEstado().getEstadoPorId(rs.getInt("estados_idestados"));
		this.lineasPedido = new NegocioLineaPedido().getLineasPorPedido(this.id);
		this.seguimiento = rs.getString("seguimiento");
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
			monto += lineaPedido.getLibro().getPrecioActual().getPrecio() * lineaPedido.getCantidad();
		}
		return monto;
	}
	
	public String getMontoTotalView(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(this.getMontoTotal());
	}

	public String getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

}
