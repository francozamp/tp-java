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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiServletPlantilla() {
        super();  
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
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
	
	public void validarUsuarioLogueado() throws Exception{
		
		if(this.usuario == null){
			Exception exception = new Exception("login.jsp");
			throw exception;
		}
		
	}
	
	public void validarAdministrador() throws Exception{
		this.validarUsuarioLogueado();
		
		if(usuario.getTipoUsuario().getId() != Constantes.ID_TIPO_ADMINISTRADOR){
			Exception exception = new Exception("index");
			throw exception;
		}
	}

}
