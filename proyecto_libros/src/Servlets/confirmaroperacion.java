package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class confirmaroperacion
 */
@WebServlet("/confirmaroperacion")
public class confirmaroperacion extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public confirmaroperacion() {
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
		super.doGet(request, response);
		
		String nroTarjeta = request.getParameter("nroTarjeta");
		String nombreApellido = request.getParameter("nombYAp");
		String mesVto = request.getParameter("mesVto");
		String anioVto = request.getParameter("anioVto");
		String codigo = request.getParameter("codigo");
		
		String dir1 = request.getParameter("dir1");
		String dir2 = request.getParameter("dir2");
		String codpos = request.getParameter("codpos");
		String localidad = request.getParameter("localidad");
		String provincia = request.getParameter("provincia");
		
		//TODO validar que no falte ningun dato
		
		boolean todoOk = true;
		
		RequestDispatcher requestDispatcher = null;
		if(todoOk){
			nroTarjeta = nroTarjeta.substring(nroTarjeta.length() - 4);
			request.setAttribute("nroTarjeta", nroTarjeta);
			String direccion = dir1 + ", " + dir2;
			request.setAttribute("direccion", direccion);
			request.setAttribute("localidad", localidad);
			request.setAttribute("codPostal", codpos);
			request.setAttribute("provincia", provincia);
			requestDispatcher = request.getRequestDispatcher("confirmaroperacion.jsp");
		}
		else{
			requestDispatcher = request.getRequestDispatcher("resultadooperacion.jsp");
		}
		requestDispatcher.forward(request, response);
	}

}
