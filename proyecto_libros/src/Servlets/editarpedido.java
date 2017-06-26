package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Estado;
import Entidades.Pedido;
import Negocio.NegocioEstado;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class editarpedido
 */
@WebServlet("/editarpedido")
public class editarpedido extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public editarpedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		//this.validarAdministrador();
		
		int idPedido = Integer.valueOf(request.getParameter("idPedido"));
		
		Pedido pedido = new NegocioPedido().findById(idPedido);
		
		//Esto es una negrada pero ya fue
		List<Estado> estadosList = new NegocioEstado().getEstados();
		
		List<Estado> estadosABorrar = new ArrayList<>();
		for (Estado estado : estadosList) {
			if(estado.getID() < pedido.getEstado().getID()){
				estadosABorrar.add(estado);
			}
		}
		
		estadosList.removeAll(estadosABorrar);
		
		request.setAttribute("pedido", pedido);
		request.setAttribute("editarPedido", Boolean.TRUE);
		request.setAttribute("estadosList", estadosList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumenpedido.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
