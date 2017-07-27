package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Usuario;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class nuevousuario
 */
@WebServlet("/nuevousuario")
public class nuevousuario extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public nuevousuario() {
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
		
		Integer idUsuario = null;
		if(request.getParameter("idUsuario") != null){
			idUsuario = Integer.valueOf(request.getParameter("idUsuario"));
		}
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String direccion1 = request.getParameter("direccion");
		String direccion2 = request.getParameter("direccion2");
		String telefono = request.getParameter("telefono");
		Integer idTipoUsuario = null;
		if(request.getParameter("idTipoUsuario") != null){
			idTipoUsuario = Integer.valueOf(request.getParameter("idTipoUsuario")); 
		}
		
		Usuario usuario = new Usuario(nombre,apellido,email,password,direccion1,direccion2,telefono, idTipoUsuario);
		
		if (idUsuario != null) {
			usuario.setId(idUsuario);
		}
		
		NegocioUsuario negocioUsuario = new NegocioUsuario();
		negocioUsuario.guardarUsuario(usuario);
		
		//Cambiar para que recupere la ultima pagina
		String callback = request.getParameter("callback") != null ? request.getParameter("callback") : "index";
		response.sendRedirect(callback);
		
	}

}
