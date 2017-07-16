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
import Helpers.Sesion;
import Negocio.NegocioDescuento;

/**
 * Servlet implementation class aplicarDescuento
 */
@WebServlet("/aplicarDescuento")
public class aplicarDescuento extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aplicarDescuento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			//this.validarUsuarioLogueado();
			
			String codigo = request.getParameter("codigoDescuento");
			
			//Si el descuento no es valido para aplicar se devuelve null
			Descuento descuento = new NegocioDescuento().validarDescuento(codigo);
			
			if(descuento != null){
				Sesion.setDescuento(descuento);
			}
			//else{
				//request.setAttribute("errores", "No se pudo aplicar el descuento.");
			//}
			
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumencarro.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			//response.sendRedirect(e.getMessage());			
			request.setAttribute("errores", e.getMessage());
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumencarro.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		try {
			//this.validarUsuarioLogueado();
			
			String codigo = request.getParameter("codigoDescuento");
			
			//Si el descuento no es valido para aplicar se devuelve null
			Descuento descuento = new NegocioDescuento().validarDescuento(codigo);
			
			if(descuento != null){
				Sesion.setDescuento(descuento);
			}
			
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumencarro.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			response.sendRedirect(e.getMessage());
		}
	}

}
