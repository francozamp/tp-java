package Helpers;

import java.util.ArrayList;

public class Notificacion {
	
	private ArrayList<String> errores;
	
	public Notificacion(){
		errores = new ArrayList<String>();
	}
	
	public void agregarError(String error){
		errores.add(error);
	}
	
	
	public boolean tieneErrores(){
		return errores.size() > 0;
	}
	
	public String getMensaje(){
		String mje = "";
		for(String error : errores){
			mje += mje + "* "+error+System.lineSeparator();
		}
		
		return mje;
	}

}
