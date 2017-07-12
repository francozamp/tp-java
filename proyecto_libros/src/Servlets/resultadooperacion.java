package Servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Constantes;
import Entidades.Pedido;
import Helpers.Sesion;
import Negocio.NegocioEstado;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class resultadooperacion
 */
@WebServlet("/resultadooperacion")
public class resultadooperacion extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public resultadooperacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		RequestDispatcher requestDispatcher = null;
		Pedido pedido = Sesion.getPedido();
		if(pedido!=null){
			pedido.setEstado(new NegocioEstado().getEstadoPorId(Constantes.ID_ESTADO_PEDIDO_PAGADO));
			pedido = new NegocioPedido().actualizarEstado(pedido);
			if(pedido!=null){
				Sesion.vaciarCarro();
				Sesion.removePedido();
				request.setAttribute("pedido", pedido);
				
				Map<String, String> direccionCompleta = Sesion.getDireccionCompleta();
				if(direccionCompleta != null){
					String direccion = direccionCompleta.get("dir1") + ", " + direccionCompleta.get("dir2");
					request.setAttribute("direccion", direccion);
					request.setAttribute("localidad", direccionCompleta.get("localidad"));
					request.setAttribute("codPostal", direccionCompleta.get("codpos"));
					request.setAttribute("provincia", direccionCompleta.get("provincia"));
					
					Sesion.removerDireccionCompleta();
				}
				requestDispatcher = request.getRequestDispatcher("resultadooperacion.jsp");
			}
		}
		
		requestDispatcher = request.getRequestDispatcher("resultadooperacion.jsp");
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
