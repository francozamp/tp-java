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
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String direccion1 = request.getParameter("direccion");
		String direccion2 = request.getParameter("direccion2");
		String telefono = request.getParameter("telefono");
		Integer idTipoUsuario = Integer.valueOf(request.getParameter("idTipoUsuario"));
		
		Usuario usuario = new Usuario(nombre,apellido,email,password,direccion1,direccion2,telefono, idTipoUsuario);
		
		NegocioUsuario negocioUsuario = new NegocioUsuario();
		negocioUsuario.guardarUsuario(usuario);
		
		response.sendRedirect("/libros/index");
		
	}

	public String getContenido() {
		// TODO Auto-generated method stub
		return null;
	}

}
