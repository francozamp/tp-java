package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Categoria;
import Entidades.Libro;
import Entidades.Pedido;
import Entidades.Usuario;
import Negocio.NegocioCategoria;
import Negocio.NegocioLibro;
import Negocio.NegocioPedido;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class buscar
 */
@WebServlet("/buscar")
public class buscar extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public buscar() {
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
			
			String descripcion = request.getParameter("descripcion");
			String objeto = request.getParameter("objeto");
			
			RequestDispatcher requestDispatcher = null;
			
			switch (objeto) {
			case "pedido":
				List<Pedido> pedidosList = new NegocioPedido().findByDescripcion(descripcion);
				request.setAttribute("pedidos", pedidosList);
				break;
				
			case "libro":
				List<Libro> librosList = new NegocioLibro().findByDescripcion(descripcion);
				request.setAttribute("libros", librosList);
				break;
				
			case "categoria":
				List<Categoria> categoriasList = new NegocioCategoria().findByDescripcion(descripcion);
				request.setAttribute("categorias", categoriasList);
				break;
				
			case "usuario":
				List<Usuario> usuariosList = new NegocioUsuario().findByDescripcion(descripcion);
				request.setAttribute("usuarios", usuariosList);
				break;

			default:
				break;
			}
			
			requestDispatcher = request.getRequestDispatcher("administracion.jsp");
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
