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
		super.doGet(request, response);
		
		try {
			
			String codigo = request.getParameter("codigoDescuento");
			
			//Si el descuento no es valido para aplicar se devuelve null
			Descuento descuento = new NegocioDescuento().validarDescuento(codigo);
			
			if(descuento != null){
				Sesion.setDescuento(descuento);
			}
			
		} catch (Exception e) {			
			request.setAttribute("errores", e.getMessage());
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("verCarro");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
//		try {
//			String codigo = request.getParameter("codigoDescuento");
//			
//			//Si el descuento no es valido para aplicar se devuelve null
//			Descuento descuento = new NegocioDescuento().validarDescuento(codigo);
//			
//			if(descuento != null){
//				Sesion.setDescuento(descuento);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("resumencarro.jsp");
//		requestDispatcher.forward(request, response);
	}

}
