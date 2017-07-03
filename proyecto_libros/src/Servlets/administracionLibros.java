package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Constantes;
import Entidades.Libro;
import Entidades.Pedido;
import Negocio.NegocioLibro;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class administracionLibros
 */
@WebServlet("/admlibros")
public class administracionLibros extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
    
	private List<Libro> libros;
	
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public administracionLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			this.validarAdministrador();
			
			NegocioLibro negocioLibro = new NegocioLibro();
			libros = negocioLibro.getLibros();
			
			request.setAttribute("libros", libros);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("administracion.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(e.getMessage());
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
