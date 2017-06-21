package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	Sesion.setSession(request.getSession(true));
		usuario = Sesion.getUsuario();
		if (request.getSession().getAttribute("categorias") == null) {
			NegocioCategoria negocioCategoria = new NegocioCategoria();
			categorias = negocioCategoria.getCategorias();
			request.getSession().setAttribute("categorias", categorias);
		}
	}
    
    protected void responder(HttpServletResponse response) throws IOException {
		
    	response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String html = this.getHead() + this.getCabecera() + this.getCuerpo() + this.getPie();
		
		out.print(html);
		
	}

	public String getHead(){
    	
    	String html = "";
    	
    	html = html + "<!DOCTYPE html>";
    	html = html + "<html>";
    	html = html + "<head>";
    		html = html + "<meta charset=\"utf-8\">";
    		html = html + "<title>Mister Bookman</title>";
    		html = html + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
    		html = html + "<meta name=\"description\" content=\"\">";
    		html = html + "<meta name=\"author\" content=\"\">";
//    		html = html + "<!-- Latest compiled and minified CSS -->";
//    		html = html + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">";
    		html = html + "<!-- Optional theme -->";
    		html = html + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">";
    		html = html + "<!-- Latest compiled and minified JavaScript -->";
    		html = html + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>";
    		html = html + "<!-- Bootstrap style --> ";
    		html = html + "<link id=\"callCss\" rel=\"stylesheet\" href=\"themes/cerulean/bootstrap.min.css\" media=\"screen\"/>";
    		html = html + "<!-- <link href=\"themes/css/base.css\" rel=\"stylesheet\" media=\"screen\"/>  BASE -->";
    		html = html + "<!-- Bootstrap style responsive -->";
    		html = html + "<link href=\"themes/css/bootstrap-responsive.min.css\" rel=\"stylesheet\"/>";
    	  	html = html + "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">";
    		html = html + "<!-- Google-code-prettify -->";
    		html = html + "<link href=\"themes/js/google-code-prettify/prettify.css\" rel=\"stylesheet\"/>";
    		html = html + "<!-- fav and touch icons -->";
    		html = html + "<link rel=\"shortcut icon\" href=\"themes/images/ico/favicon.ico\">";
    		html = html + "<link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"themes/images/ico/apple-touch-icon-144-precomposed.png\">";
    		html = html + "<link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"themes/images/ico/apple-touch-icon-114-precomposed.png\">";
    		html = html + "<link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"themes/images/ico/apple-touch-icon-72-precomposed.png\">";
    		html = html + "<link rel=\"apple-touch-icon-precomposed\" href=\"themes/images/ico/apple-touch-icon-57-precomposed.png\">";
    		html = html + "<link rel=\"stylesheet\" type=\"text/css\" href=\"micss/micss.css\">";
    		html = html + "<style type=\"text/css\" id=\"enject\"></style>";
    	html = html + "</head>";
    	html = html + "<body>";
		
		return html;
    }
       
    public String getCabecera(){
    		
    	String html = "";
    	
		html = html + "<div class=\"container\">";
			html = html + "<!-- Navbar ================================================== -->";
			html = html + "<div id=\"logoArea\" class=\"navbar\">";
				html = html + "<a id=\"smallScreen\" data-target=\"#topMenu\" data-toggle=\"collapse\" class=\"btn btn-navbar\">";
					html = html + "<span class=\"icon-bar\"></span>";
					html = html + "<span class=\"icon-bar\"></span>";
					html = html + "<span class=\"icon-bar\"></span>";
				html = html + "</a>";
	  			html = html + "<div class=\"navbar-inner\">";
	    			html = html + "<a class=\"brand\" href=\"index\"><img id=\"logo\" src=\"themes/images/logo.png\" alt=\"Bootsshop\"/></a>";
					html = html + "<form class=\"form-inline navbar-search nav-element\" method=\"post\" action=\"products.html\" >";
						html = html + "<input id=\"\" class=\"srchTxt\" type=\"text\" placeholder=\"Buscar\" />";
			  			html = html + "<select class=\"srchTxt\">";
							html = html + "<option>Todas las categorías</option>";
								
							for (Categoria categoria : categorias) {
								html = html + "<option>" + categoria.getNombre() + "</option>";
							}
								
						html = html + "</select> ";
				  		html = html + "<button type=\"submit\" id=\"submitButton\" class=\"btn btn-primary\">Buscar</button>";
		    		html = html + "</form>";
		    		html = html + "<ul id=\"topMenu\" class=\"nav pull-right\">";
		 				html = html + "<li class=\"\"><a href=\"contact.html\">Contacto</a></li>";
		 				html = html + "<li class=\"\">";
		 				
		 				if (usuario != null) { //Esto hay que hacerlo lindo!!
		 					
							html = html + "<li class=\"dropdown\">";
	          				html = html + "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">" + usuario.getNombre() + " " + usuario.getApellido() + "<span class=\"caret\"></span></a>";
	          				html = html + "<ul class=\"dropdown-menu\">";
	            				html = html + "<li><a href=\"#\">Mi cuenta</a></li>";
	            				html = html + "<li><a href=\"#\">Historial de pedidos</a></li>";
	            				
	            				if (usuario.getTipoUsuario().getId() == Constantes.ID_TIPO_ADMINISTRADOR) {
	            					html = html + "<li><a href=\"admlibros\">Administración</a></li>";
								}
	            				
	            				html = html + "<li role=\"separator\" class=\"divider\"></li>";
	            				html = html + "<li><a href=\"logout\">Cerrar Sesión</a></li>";
	          				html = html + "</ul>";
	        				html = html + "</li>";
	        				
						}
		 				else{
		 					html = html + "<a id=\"btnLogin\" href=\"#login\" role=\"button\" data-toggle=\"modal\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Iniciar Sesión</span></a>";
							html = html + "<div id=\"login\" class=\"modal hide fade in\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"login\" aria-hidden=\"false\" >";
			  					html = html + "<div class=\"modal-header\">";
									html = html + "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button>";
									html = html + "<h3>Iniciar Sesión</h3>";
			  					html = html + "</div>";
			  					html = html + "<div class=\"modal-body\">";
									html = html + "<form action=\"login\" class=\"form-horizontal loginFrm\" method=\"post\">";
										html = html + "<div class=\"control-group\">";
											html = html + "<input type=\"text\" id=\"inputEmail\" name=\"email\" placeholder=\"Email\">";
				  						html = html + "</div>";
				  						html = html + "<div class=\"control-group\">";
				  							html = html + "<input type=\"password\" id=\"inputPassword\" name=\"password\" placeholder=\"Contraseña\">";
				  						html = html + "</div>";
				  						html = html + "<div class=\"control-group\">";
											html = html + "<label class=\"checkbox\">";
												html = html + "<input type=\"checkbox\"> Recuerdame";
											html = html + "</label>";
				  						html = html + "</div>";
				  						html = html + "<button type=\"submit\" class=\"btn btn-success\">Iniciar Sesión</button>";
										html = html + "<button class=\"btn\" data-dismiss=\"modal\" aria-hidden=\"true\">Cerrar</button>";
									html = html + "</form>";
								html = html + "</div>";
							html = html + "</div>";
		 				}
		
						html = html + "</li>";
					html = html + "</ul>";
				html = html + "</div>";
			html = html + "</div>";
		html = html + "</div>";
	html = html + "</div>";
		
		return html;
			
	}
    
    public String getCuerpo() {
    	
    	String html = "";
    	
	    html = html + "<div id=\"mainBody\">";
			html = html + "<div class=\"container\">";
				html = html + "<div class=\"row\">";
					
					//html = html + this.getSideBar() + this.getContenido();
					
				html = html + "</div>";
			html = html + "</div>";
		html = html + "</div>";
		
		return html;
	}

	public String getSideBar() {
    	
    	String html = "";
    	
    	html = html + "<!-- Sidebar ================================================== -->";
		html = html + "<div id=\"sidebar\" class=\"span3\">";
			html = html + "<div class=\"well well-small\"><a id=\"myCart\" href=\"product_summary.html\"><img src=\"themes/images/ico-cart.png\" alt=\"cart\">3 Items en tu carrito <span class=\"badge badge-warning pull-right\">$155.00</span></a></div>";
			html = html + "<ul id=\"sideManu\" class=\"nav nav-tabs nav-stacked\">";
				html = html + "<li class=\"subMenu open\"><a> Categorías</a>";
					html = html + "<ul>";
					
						for (Categoria categoria : categorias) {
							html = html + "<li><a class=\"active\" href=\"libros?categoria=" + categoria.getId() + "\"><i class=\"icon-chevron-right\"></i>" + categoria.getNombre() + "</a></li>";
						}
						
					html = html + "</ul>";
				html = html + "</li>";
			html = html + "</ul>";
		html = html + "</div>";
		html = html + "<!-- Sidebar end=============================================== -->";
		
		return html;
	}
	
	//abstract public String getContenido();

	public String getPie(){
    	
    	String html = "";
    	
	    html = html + "<!-- Footer ================================================================== -->";
			html = html + "<div  id=\"footerSection\">";
				html = html + "<div class=\"container\">";
					html = html + "<div class=\"row\">";
						html = html + "<div class=\"span4\">";
							html = html + "<h5>CUENTA</h5>";
							html = html + "<a href=\"login.html\">Tu cuenta</a>";
							html = html + "<a href=\"login.html\">Informacion personal</a>  ";
							html = html + "<a href=\"login.html\">Historial de pedidos</a>";
							html = html + "<a href=\"registrate\">Registrate</a>";
						html = html + "</div>";
						html = html + "<div class=\"span4\">";
							html = html + "<h5>Información</h5>";
							html = html + "<a href=\"contact.html\">Contacto</a>    ";
						html = html + "</div>";
						html = html + "<div class=\"span4\">";
							html = html + "<h5>Nuestras ofertas</h5>";
							html = html + "<a href=\"#\">Nuevos productos</a> ";
							html = html + "<a href=\"#\">Los mas vendidos</a>  ";
						html = html + "</div>";
						html = html + "<!-- ";
						html = html + "<div id=\"socialMedia\" class=\"span3 pull-right\">";
							html = html + "<h5>SOCIAL MEDIA </h5>";
							html = html + "<a href=\"#\"><img width=\"60\" height=\"60\" src=\"themes/images/facebook.png\" title=\"facebook\" alt=\"facebook\"/></a>";
							html = html + "<a href=\"#\"><img width=\"60\" height=\"60\" src=\"themes/images/twitter.png\" title=\"twitter\" alt=\"twitter\"/></a>";
							html = html + "<a href=\"#\"><img width=\"60\" height=\"60\" src=\"themes/images/youtube.png\" title=\"youtube\" alt=\"youtube\"/></a>";
						html = html + "</div>  -->";
					html = html + "</div>";
				html = html + "</div><!-- Container End -->";
			html = html + "</div>";
			html = html + "<!-- Placed at the end of the document so the pages load faster ============================================= -->";
			html = html + "<script src=\"themes/js/jquery.js\" type=\"text/javascript\"></script>";
			html = html + "<script src=\"themes/js/bootstrap.min.js\" type=\"text/javascript\"></script>";
			html = html + "<script src=\"themes/js/google-code-prettify/prettify.js\"></script>";
			html = html + "<script src=\"themes/js/bootshop.js\"></script>";
			html = html + "<script src=\"themes/js/jquery.lightbox-0.5.js\"></script>";
		html = html + "</body>";
		html = html + "</html>";
		
		return html;
    	
    }
	
	public Usuario getUsuario(){
		return this.usuario;
	}

}
