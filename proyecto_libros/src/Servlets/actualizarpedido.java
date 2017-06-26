package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Pedido;
import Negocio.NegocioEstado;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class actualizarpedido
 */
@WebServlet("/actualizarpedido")
public class actualizarpedido extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public actualizarpedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int idPedido = Integer.valueOf(request.getParameter("idPedido"));
		int idEstado = Integer.valueOf(request.getParameter("idEstado"));
		String codigoSeguimiento = request.getParameter("seguimiento");
		
		Pedido pedido = new NegocioPedido().findById(idPedido);
		
		pedido.setEstado(new NegocioEstado().getEstadoPorId(idEstado));
		pedido.setSeguimiento(codigoSeguimiento);
		
		new NegocioPedido().actualizarPedido(pedido);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admPedidos");
		requestDispatcher.forward(request, response);
		
	}

}
