package Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class filtrosreporte
 */
@WebServlet("/filtrosreporte")
public class filtrosreporte extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public filtrosreporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			this.validarAdministrador();
			
			RequestDispatcher requestDispatcher = null;
			String reporte = request.getParameter("reporte");
			
			switch (reporte) {
			case "ventas":
				requestDispatcher = request.getRequestDispatcher("filtrosReporteVentas.jsp");
				break;

			default:
				requestDispatcher = request.getRequestDispatcher("reportes");
				break;
			}
			
			Calendar calendar = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date hasta = calendar.getTime();
			String hastaDefault = df.format(hasta);
			calendar.roll(Calendar.MONTH, false);
			Date desde = calendar.getTime();
			String desdeDefault = df.format(desde);
			
			request.setAttribute("desdeDefault", desdeDefault);
			request.setAttribute("hastaDefault", hastaDefault);
			
			requestDispatcher.forward(request, response);
			
		} catch (Exception e) {
			response.sendRedirect(e.getMessage());
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
