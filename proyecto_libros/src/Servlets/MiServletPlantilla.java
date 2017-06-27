package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Usuario;
import Helpers.Sesion;
import Negocio.NegocioCategoria;

/**
 * Servlet implementation class MiServletPlantilla
 */
//@WebServlet("/MiServletPlantilla")
public abstract class MiServletPlantilla extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	private Usuario usuario;
	private List<Categoria> categorias;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiServletPlantilla() {
        super();  
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	this.request = request;
    	this.response = response;
    	
    	Sesion.setSession(request.getSession(true));
		usuario = Sesion.getUsuario();
		List<Categoria> categorias = (List<Categoria>) request.getSession().getAttribute("categorias");
		if (categorias == null || categorias.isEmpty()) {
			NegocioCategoria negocioCategoria = new NegocioCategoria();
			categorias = negocioCategoria.getCategorias();
			request.getSession().setAttribute("categorias", categorias);
		}
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public void validarUsuarioLogueado() throws ServletException, IOException{
		
		if(this.usuario == null){
			RequestDispatcher requestDispatcher = this.request.getRequestDispatcher("login");
			requestDispatcher.forward(this.request, this.response);
		}
		
	}
	
	public void validarAdministrador() throws ServletException, IOException{
		this.validarUsuarioLogueado();
		
		if(usuario.getTipoUsuario().getId() != Constantes.ID_TIPO_ADMINISTRADOR){
			RequestDispatcher requestDispatcher = this.request.getRequestDispatcher("index");
			requestDispatcher.forward(this.request, this.response);
		}
	}

}
