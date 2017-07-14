package Entidades;

import java.util.Date;

public class Descuento {

	private Integer id;
	private String codigo;
	private Float porcDescuento;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getPorcDescuento() {
		return porcDescuento;
	}

	public void setPorcDescuento(Float porcDescuento) {
		this.porcDescuento = porcDescuento;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
