package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Libro;
import Entidades.Valoracion;
import Negocio.NegocioLibro;
import Negocio.NegocioValoracion;

import java.util.List;

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
		int idLibro = Integer.parseInt(request.getParameter("idLibro"));
		NegocioLibro negocioLibro= new NegocioLibro();
		libro= negocioLibro.getLibroById(idLibro);
		request.setAttribute("libro", libro);
		
		//Recupero valoraciones de los usuarios y su promedio y los agrego al Request como parametros
		NegocioValoracion nv = new NegocioValoracion();
		List<Valoracion> valoraciones = nv.findByLibro(idLibro);
		Integer promedio = nv.getPuntajePromedio(valoraciones);
		
		request.setAttribute("valoraciones", valoraciones);
		request.setAttribute("puntajePromedio", promedio);
		
		
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
