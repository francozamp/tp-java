package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Constantes;

/**
 * Servlet implementation class administracionCategorias
 */
@WebServlet("/admcategorias")
public class administracionCategorias extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administracionCategorias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if (this.getUsuario() != null && this.getUsuario().getTipousu() == Constantes.ID_TIPO_ADMINISTRADOR) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("administracion.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("/libros/index");
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
