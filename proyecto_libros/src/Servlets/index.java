package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Libro;
import Negocio.NegocioLibro;
import Negocio.NegocioValoracion;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Libro> librosRecientes = null;
		NegocioLibro negocioLibro = new NegocioLibro();
		librosRecientes = negocioLibro.getRecientes();
		
		for (Libro libro : librosRecientes) {
			libro.setPuntajePromedio(new NegocioValoracion().getPromedioByIdLibro(libro.getId()));
		}
		
		request.setAttribute("librosRecientes", librosRecientes);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
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
