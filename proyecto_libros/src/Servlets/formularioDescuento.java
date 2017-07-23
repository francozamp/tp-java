package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Descuento;
import Negocio.NegocioDescuento;

/**
 * Servlet implementation class formularioDescuento
 */
@WebServlet("/formularioDescuento")
public class formularioDescuento extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public formularioDescuento() {
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
			
			Integer idDescuento = null;
			if(request.getParameter("idDescuento") != null){
				idDescuento = Integer.valueOf(request.getParameter("idDescuento"));
			}
			
			if(idDescuento != null){
				
				Descuento descuento = new NegocioDescuento().getDescuentoById(idDescuento);
				
				//Si te pasan un descuento como parametro apra editar aca lo recuperas y lo pasas a laproxima pagina
				
				request.setAttribute("descuento", descuento);
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("frmdescuento.jsp");
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
