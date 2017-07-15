package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Carro;
import Entidades.Libro;
import Entidades.LineaPedido;
import Entidades.Pedido;
import Helpers.Sesion;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class verCarro
 */
@WebServlet("/verCarro")
public class verCarro extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public verCarro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		NegocioLibro negocioLibro =  new NegocioLibro();
		
		Pedido pedido = new Pedido();
		Libro libro = null;
		int cantidad = 0;
		LineaPedido lineaPedido = null;
		
		Carro carro = Sesion.getCarro();
		for (int idLibro : carro.getLibrosCarro().keySet()) {
			libro = negocioLibro.getLibroById(idLibro);
			cantidad = carro.getLibrosCarro().get(idLibro);
			lineaPedido = new LineaPedido(libro, cantidad);
			pedido.getLineasPedido().add(lineaPedido);
		}
		
		Sesion.setPedido(pedido);
		request.setAttribute("redireccion", "verCarro");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumencarro.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
