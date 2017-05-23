package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Constantes;
import Entidades.Usuario;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class administracionUsuarios
 */
@WebServlet("/admusuarios")
public class administracionUsuarios extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administracionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if (this.getUsuario() != null && this.getUsuario().getTipousu() == Constantes.ID_TIPO_ADMINISTRADOR) {
			
			NegocioUsuario negocioUsuario = new NegocioUsuario();
			List<Usuario> usuariosList = negocioUsuario.getUsuarios();
			
			request.setAttribute("usuarios", usuariosList);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("administracion.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("/libros/index");
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
