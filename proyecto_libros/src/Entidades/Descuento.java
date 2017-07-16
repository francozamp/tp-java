package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Negocio.NegocioPrecio;

public class Descuento {

	private Integer id;
	private String codigo;
	private Float porcDescuento;
	private Date fechaDesde;
	private Date fechaHasta;
	
	public Descuento(String codigo,Float porcDescuento,Date fechaDesde,Date fechaHasta){
		this.codigo = codigo;
		this.porcDescuento = porcDescuento;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}
	
public Descuento(ResultSet rs) throws SQLException {
		
		this.id=rs.getInt("id");
		this.codigo=rs.getString("codigo").replaceAll("\\s+", "");
		this.porcDescuento=rs.getFloat("porcDesc");
		this.fechaDesde=rs.getDate("fechaDesde");
		this.fechaHasta=rs.getDate("fechaHasta");	
		
	}
	
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
