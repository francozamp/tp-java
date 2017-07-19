package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Valoracion;
import Negocio.NegocioValoracion;

/**
 * Servlet implementation class editarvaloracion
 */
@WebServlet("/editarvaloracion")
public class editarvaloracion extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public editarvaloracion() {
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
			
			Valoracion valoracion = null;
			//TODO Recuperar la valoracion
			
			int idValoracion = Integer.valueOf(request.getParameter("idValoracion"));
			valoracion = new NegocioValoracion().findById(idValoracion);
			
			request.setAttribute("valoracion", valoracion);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("editarvaloracion.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
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
