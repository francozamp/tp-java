package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Pedido;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class historialpedidos
 */
@WebServlet("/historialpedidos")
public class historialpedidos extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public historialpedidos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			this.validarUsuarioLogueado();
			
			List<Pedido> pedidosList = new NegocioPedido().findPedidosByUsuario(this.getUsuario().getId());
			
			request.setAttribute("pedidosList", pedidosList);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("historialpedidos.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(e.getMessage());
			//requestDispatcher = request.getRequestDispatcher(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
