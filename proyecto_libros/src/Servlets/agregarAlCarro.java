package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Carro;
import Helpers.Sesion;

/**
 * Servlet implementation class agregarAlCarro
 */
@WebServlet("/agregarAlCarro")
public class agregarAlCarro extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public agregarAlCarro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		String redir ="/libros/index";
		if(request.getParameter("idLibro")!=null){
			int idLibro = Integer.valueOf(request.getParameter("idLibro"));
			int cantidad = request.getParameter("cantidad") != null ? Integer.valueOf(request.getParameter("cantidad")) : 1;
			 redir = request.getParameter("redir")!=null? request.getParameter("redir") : "/libros/index";
			if(cantidad>0){
				Carro carro = Sesion.getCarro();
				carro.agregarAlCarro(idLibro, cantidad);
				Sesion.setCarro(carro);
			}
		}
		
		response.sendRedirect(redir);
		//ver como volver exactamente a la misma página que estaba antes
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
