package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Usuario;
import Negocio.NegocioUsuario;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public login() {
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

		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		NegocioUsuario negocioUsuario = new NegocioUsuario();
		
		Usuario usuario = negocioUsuario.hacerLogin(email, pass);
		
		this.responder(response,usuario);
	}

	private void responder(HttpServletResponse response, Usuario usuario) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		if (usuario!=null) {
			out.println("<h1>" + usuario.getNombre() + "</h1>");
			out.println("<h1>" + usuario.getApellido() + "</h1>");
		}
		else{
			out.println("<h1>Usuario o contraseña incorrectos</h1>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
