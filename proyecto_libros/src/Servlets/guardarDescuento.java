package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Descuento;
import Negocio.NegocioDescuento;

/**
 * Servlet implementation class guardarDescuento
 */
@WebServlet("/guardarDescuento")
public class guardarDescuento extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public guardarDescuento() {
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
		
		try {
			this.validarAdministrador();
			
			//Recuperas todos los parametros y guardas el descuento
			String codigo = request.getParameter("codigo");
			Float porcDesc = Float.parseFloat(request.getParameter("porcDesc"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaDesde = format.parse(request.getParameter("fechaDesde"));
			Date fechaHasta = format.parse(request.getParameter("fechaHasta"));
			
			Descuento descuento = new NegocioDescuento().guardarDescuento(codigo, porcDesc, fechaDesde, fechaHasta);
			
			
			response.sendRedirect("admdescuentos");
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
