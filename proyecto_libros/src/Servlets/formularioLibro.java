package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Libro;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class formularioLibro
 */
@WebServlet("/formularioLibro")
public class formularioLibro extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public formularioLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if (request.getParameter("idLibro")!=null) {
			NegocioLibro negocioLibro = new NegocioLibro();
			Libro libro = negocioLibro.getLibroById(Integer.valueOf(request.getParameter("idLibro")));
			request.setAttribute("libro", libro);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frmlibro.jsp");
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
