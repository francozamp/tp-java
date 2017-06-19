package Servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Carro;
import Entidades.Constantes;
import Entidades.Estado;
import Entidades.Libro;
import Entidades.LineaPedido;
import Entidades.Pedido;
import Helpers.Sesion;
import Negocio.NegocioLibro;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class pagarPedido
 */
@WebServlet("/pagoyenvio")
public class pagoyenvio extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public pagoyenvio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		NegocioLibro negocioLibro =  new NegocioLibro();
		
		Pedido pedido = new Pedido();
		Libro libro = null;
		int cantidad = 0;
		LineaPedido lineaPedido = null;
		
		Carro carro = Sesion.getCarro();
		for (int idLibro : carro.getLibrosCarro().keySet()) {
			if(request.getParameter("cant" + idLibro)!=null && Integer.valueOf(request.getParameter("cant" + idLibro))>0){
				libro = negocioLibro.getLibroById(idLibro);
				cantidad = Integer.valueOf(request.getParameter("cant" + idLibro));
				lineaPedido = new LineaPedido(libro, cantidad);
				pedido.getLineasPedido().add(lineaPedido);
			}
		}
		
		pedido.setUsuario(this.getUsuario());
		pedido.setFecha(new Date());
		pedido.setEstado(new Estado(Constantes.ID_ESTADO_PEDIDO_PENDIENTE, "Pendiente"));
		
		NegocioPedido negocioPedido = new NegocioPedido();
		pedido = negocioPedido.guardarPedido(pedido);
		
		RequestDispatcher requestDispatcher = null;
		if (pedido != null) {
			Sesion.setPedido(pedido);
			requestDispatcher = request.getRequestDispatcher("pagoyenvio.jsp");
		}
		else{
			requestDispatcher = request.getRequestDispatcher("resultadooperacion.jsp");
		}
		requestDispatcher.forward(request, response);
		
	}

}
