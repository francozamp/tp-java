package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		NegocioUsuario negocioUsuario = new NegocioUsuario();
		
		Usuario usuario = negocioUsuario.hacerLogin(email, pass);
		
		HttpSession session = request.getSession(true);
		
		if (usuario!=null) {
			session.setAttribute("usuario", usuario);
		}
			
		String destino = (String)request.getParameter("paginaLlamado");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destino);
		requestDispatcher.forward(request, response);
		
//		response.sendRedirect(request.getHeader("referer"));
		
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index");
		//dispatcher.include(request, response);
		
		//request.getRequestDispatcher("index").forward(request, response);
		
		//response.sendRedirect(request.getHeader("referer"));
	}

}
