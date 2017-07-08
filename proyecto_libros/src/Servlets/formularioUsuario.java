package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Estado;
import Entidades.TipoUsuario;
import Entidades.Usuario;
import Negocio.NegocioTipoUsuario;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class formularioUsuario
 */
@WebServlet("/formularioUsuario")
public class formularioUsuario extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public formularioUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			if (request.getParameter("idUsuario") != null) {
				this.validarAdministrador();
				NegocioUsuario negocioUsuario = new NegocioUsuario();
				Usuario usuarioEditar = negocioUsuario.getUsuarioPorId(Integer.valueOf(request.getParameter("idUsuario")));
				request.setAttribute("usuarioEditar", usuarioEditar);
				List<TipoUsuario> tipoUsuarioList = new NegocioTipoUsuario().getTipoUsuarioList();
				request.setAttribute("tipoUsuarioList", tipoUsuarioList);
			}
			else{
				this.validarUsuarioLogueado();
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("frmusuario.jsp");
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
