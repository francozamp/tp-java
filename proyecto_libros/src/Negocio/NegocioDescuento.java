package Negocio;

import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import Datos.DatosDescuento;
import Entidades.Categoria;
import Entidades.Descuento;
import Helpers.Notificacion;
import Helpers.Sesion;

public class NegocioDescuento {
	
	public Descuento guardarDescuento(String codigo,Float porcDesc,Date fechaDesde,Date fechaHasta) throws Exception
	{
		Notificacion notif = validarNuevoDescuento(porcDesc);
		
		if(!notif.tieneErrores()){
			DatosDescuento dd = new DatosDescuento();
			Descuento descuento = dd.getDescuentoByCodigo(codigo);
		
			if(descuento != null){
				descuento.setFechaDesde(fechaDesde);
				descuento.setFechaHasta(fechaHasta);
				descuento.setPorcDescuento(porcDesc);
			}
			else{
				descuento = new Descuento(codigo,porcDesc,fechaDesde,fechaHasta);			
			}

			return new DatosDescuento().guardarDescuento(descuento);
		}
		else{
			Exception ex = new Exception(notif.getMensaje());
			throw ex;
			//System.out.println(notif.getMensaje());
			//return null;
		}
	}
		
	
	private Notificacion validarNuevoDescuento(Float porcDesc){
		Notificacion notif = new Notificacion();
		
		if(porcDesc < 0 || porcDesc > 1){
			notif.agregarError("El porcentaje de descuento debe ser un valor entre 0 y 1.");
		}
		
		return notif;
	}
	
	public Integer registrarDescuentoUtilizado(){
		
		if(Sesion.getDescuento() != null){
			int idDescuento = Sesion.getDescuento().getId();
			int idUsuario = Sesion.getUsuario().getId();
			
			return new DatosDescuento().registrarDescuentoUtilizado(idDescuento,idUsuario);
		}
		
		return 0;
	}
	
	public List<Descuento> getDescuentos() {
		
		List<Descuento> descuentos=null;
		
		DatosDescuento dDescuento = new DatosDescuento();
		descuentos=dDescuento.getDescuentos();
		
		return descuentos;
	}
	
	public Descuento getDescuentoById(int idDescuento) {
		return new DatosDescuento().getDescuentoById(idDescuento);
	}
	
	public Descuento getDescuentoByCodigo(String codigo) {
		return new DatosDescuento().getDescuentoByCodigo(codigo);
	}
	
	public Descuento validarDescuento(String codigo) throws Exception
	{
		
		//boolean esValido = false;
		Descuento descuento = this.getDescuentoByCodigo(codigo);
		
		if(descuento != null){
			
			Notificacion notifVigencia = new Notificacion();
			Notificacion notifDescUtilizado = new Notificacion();
		
			//Valido si el descuento se encuentra vigente
			notifVigencia = this.validarDescuentoVigente(descuento.getId());
		
			//Valido si el descuento ya fue utilizado por el usuario
			notifDescUtilizado = this.validarDescuentoDisponibleUsuario(descuento.getId());
		
			if(notifVigencia.tieneErrores() || notifDescUtilizado.tieneErrores()){
				Exception ex = new Exception(notifVigencia.getMensaje() + notifDescUtilizado.getMensaje());
				throw ex;
			}
		
		}
		else{
			Exception ex = new Exception("* El c�digo ingresado es inexistente.");
			throw ex;
		}
		
		return descuento;
	}
	
	private Notificacion validarDescuentoDisponibleUsuario(int idDescuento)
	{
		int idUsuario = Sesion.getUsuario().getId();
		Notificacion notif = new Notificacion();
		
		if(!new DatosDescuento().validarDescuentoDisponibleUsuario(idDescuento,idUsuario)){
			notif.agregarError("El c�digo de descuento ya fue utilizado por el usuario.");
		}
		
		return notif;
	}
	
	private Notificacion validarDescuentoVigente(int idDescuento)
	{
		Notificacion notif = new Notificacion();
		
		Descuento descuento = this.getDescuentoById(idDescuento);
		
		if(descuento != null){
			Date fechaDesde = descuento.getFechaDesde();
			Date fechaHasta = descuento.getFechaHasta();
			Date fechaHoy = new Date();
			
			//Si la fechaHoy esta contenida entre la fecha desde y hasta del descuento, es valido
			//if(fechaHoy.compareTo(fechaDesde) >= 0 && fechaHoy.compareTo(fechaHasta) <= 0){
				//esValido = true;
			//}
			//Si la fechaHoy NO esta contenida entre la fecha desde y hasta del descuento, agrego error
			if(fechaHoy.compareTo(fechaDesde) < 0 || fechaHoy.compareTo(fechaHasta) > 0){
				notif.agregarError("El descuento no se encuentra vigente.");
			}
		}
		
		return notif;
	
	}
	
	
	

}
