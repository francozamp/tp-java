package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Descuento;
import Negocio.NegocioDescuento;
import Negocio.NegocioValoracion;

/**
 * Servlet implementation class guardarValoracion
 */
@WebServlet("/guardarValoracion")
public class guardarValoracion extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarValoracion() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		super.doGet(request, response);
		
		try {
			//this.validarUsuarioLogueado();();
			
			//Recuperas todos los parametros y guardas la valoración
			String comentario = request.getParameter("comentario");
			String punt = request.getParameter("puntaje");
			Integer puntaje = punt != null ? Integer.parseInt(punt) : null;
			Integer idValoracion = Integer.parseInt(request.getParameter("idValoracion"));
			
			new NegocioValoracion().guardarValoracion(idValoracion, puntaje, comentario);		
			
			response.sendRedirect("mislibros");
		}
		catch (Exception e) {
			
			request.setAttribute("errores", e.getMessage());
			
			//Obtengo url anterior al servlet
			String referer = request.getHeader("Referer");

			//Obtengo solo el nombre de la pagina con parametros (referer tenia la URL completa)
			referer = referer.substring( referer.lastIndexOf('/')+1, referer.length() );
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(referer);
			requestDispatcher.forward(request, response);

		}
		
	}

}
