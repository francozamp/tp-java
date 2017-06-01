package Entidades;

import java.text.DecimalFormat;
import java.util.HashMap;

import Negocio.NegocioLibro;

public class Carro {
	
	private HashMap<Integer, Integer> librosCarro;
	
	public Carro(){
		librosCarro = new HashMap<>();
	}

	public HashMap<Integer, Integer> getLibrosCarro() {
		return librosCarro;
	}
	
	public void agregarAlCarro(int idLibro, int cantidad){
		
		//Si el libro ya estaba en el carro acumulo la cantidad, sino lo agrego
		if(librosCarro.get(idLibro)!=null){
			librosCarro.put(idLibro, librosCarro.get(idLibro) + cantidad);
		} else {
			librosCarro.put(idLibro, cantidad);
		}
	}
	
	public int getCantLibros(){
		int cant = 0;
		for (int idLibro : librosCarro.keySet()) {
			cant += librosCarro.get(idLibro);
		}
		return cant;
	}
	
	public float getMontoTotal(){
		float monto = 0;
		NegocioLibro negocioLibro = new NegocioLibro();
		
		for (int idLibro : librosCarro.keySet()) {
			monto += 10 * librosCarro.get(idLibro);
			monto += negocioLibro.getLibroById(idLibro).getPrecioActual().getPrecio() * librosCarro.get(idLibro);
		}
		
		return monto;
	}
	
	public String getMontoTotalView(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(this.getMontoTotal());
	}

}
