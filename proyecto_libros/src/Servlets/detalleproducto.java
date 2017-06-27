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
 * Servlet implementation class detalleproducto
 */
@WebServlet("/detalleproducto")
public class detalleproducto extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
    private Libro libro;
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public detalleproducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int idLibro = Integer.parseInt(request.getParameter("id"));
		NegocioLibro negocioLibro= new NegocioLibro();
		libro= negocioLibro.getLibroById(idLibro);
		request.setAttribute("libro", libro);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("detalleproducto.jsp");
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
