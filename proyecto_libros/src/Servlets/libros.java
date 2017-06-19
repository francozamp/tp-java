package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Libro;
import Negocio.NegocioCategoria;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class libros
 */
@WebServlet("/libros")
public class libros extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
	
	private int idCategoria;
	private Categoria categoria;
	private List<Libro> librosPagina;
	private int cantPaginas;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public libros() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String tituloLibro = request.getParameter("titulo");
		idCategoria  = Integer.parseInt(request.getParameter("idCategoria"));
		
		int paginaActual = 0;
		
		HttpSession session = request.getSession();
		
		if(tituloLibro == null || tituloLibro.isEmpty()){
			if (request.getParameter("page") == null) {
				paginaActual = 1;	
				NegocioCategoria negocioCategoria = new NegocioCategoria();
				categoria = negocioCategoria.getCategoria(idCategoria);
				if(categoria != null && !categoria.getLibros().isEmpty()){
					cantPaginas = (categoria.getLibros().size() / Constantes.CANT_LIBROS_POR_PAGINA + 1);	
				}
				else{
					cantPaginas = 0;
				}
				request.setAttribute("categoria", categoria);
			}
			else{
				paginaActual = Integer.parseInt(request.getParameter("page"));
				categoria = (Categoria)session.getAttribute("categoria");
			}
			
			if(categoria != null && !categoria.getLibros().isEmpty()){
				librosPagina = categoria.getLibros().subList(paginaActual - 1, paginaActual - 1 + Constantes.CANT_LIBROS_POR_PAGINA <= categoria.getLibros().size() ? paginaActual - 1 + Constantes.CANT_LIBROS_POR_PAGINA : categoria.getLibros().size());
			}
			else{
				librosPagina = new ArrayList<Libro>();
			}
			
			request.setAttribute("categoria", categoria);
		}
		else{
			List<Libro> resultadoLibrosList = new NegocioLibro().getLibroPorTituloYCategoria(tituloLibro,idCategoria);
			if (request.getParameter("page") == null) {
				paginaActual = 1;	
				if(!resultadoLibrosList.isEmpty()){
					cantPaginas = (resultadoLibrosList.size() / Constantes.CANT_LIBROS_POR_PAGINA + 1);	
				}
				else{
					cantPaginas = 0;
				}
			}
			else{
				paginaActual = Integer.parseInt(request.getParameter("page"));
			}
			
			if(!resultadoLibrosList.isEmpty()){
				librosPagina = resultadoLibrosList.subList(paginaActual - 1, paginaActual - 1 + Constantes.CANT_LIBROS_POR_PAGINA <= resultadoLibrosList.size() ? paginaActual - 1 + Constantes.CANT_LIBROS_POR_PAGINA : resultadoLibrosList.size());
			}
			else{
				librosPagina = new ArrayList<Libro>();
			}
		}
		
		request.setAttribute("titulo", request.getParameter("titulo"));
		request.setAttribute("idCategoria", request.getParameter("categoria"));
		request.setAttribute("librosPagina", librosPagina);
		request.setAttribute("paginaActual", paginaActual);
		request.setAttribute("cantPaginas", cantPaginas);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("libros.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String getCuerpo() {
    	
    	String html = "";
    	
	    html = html + "<div id=\"mainBody\">";
			html = html + "<div class=\"container\">";
				html = html + "<div class=\"row\">";
					
					html = html + this.getSideBar() + this.getContenido();
					
				html = html + "</div>";
			html = html + "</div>";
		html = html + "</div>";
		
		return html;
	}

	public String getContenido() {
		
		String html = "";
		
//		html = html + "<!-- Inicio contenido -->";
//		html = html + "<div class=\"span9\">";
//			html = html + "<ul class=\"breadcrumb\">";
//				html = html + "<li><a href=\"index\">Inicio</a> <span class=\"divider\">/</span></li>";
//				html = html + "<li class=\"active\">" + categoria.getNombre() + "</li>";
//			html = html + "</ul>";
//			html = html + "<h3>" + categoria.getNombre() + "<small class=\"pull-right\">" + String.valueOf(categoria.getLibros().size()) + " Libros disponibles</small></h3>	";
//			html = html + "<hr class=\"soft\"/>";
//			html = html + "<p>";
//				html = html + categoria.getDescripcion();
//			html = html + "</p>";
//			html = html + "<hr class=\"soft\"/>";
//			html = html + "<div class=\"row\">";
//				html = html + "<form class=\"form-horizontal span6\">";
//					html = html + "<div class=\"control-group\">";
//	  					html = html + "<label class=\"control-label alignL\">Ordenado por  </label>";
//						html = html + "<select>";
//          					html = html + "<option>Alfabético A - Z</option>";
//          					html = html + "<option>Alfabético Z - A</option>";
//          					html = html + "<option>Precio más bajo</option>";
//          				html = html + "</select>";
//					html = html + "</div>";
//  				html = html + "</form>";
//				html = html + "<div id=\"myTab\" class=\"pull-right\">";
//						html = html + "<a href=\"#listView\" data-toggle=\"tab\"><span class=\"btn btn-large\"><i class=\"icon-list\"></i></span></a>";
//						html = html + "<a href=\"#blockView\" data-toggle=\"tab\"><span class=\"btn btn-large btn-primary\"><i class=\"icon-th-large\"></i></span></a>";
//				html = html + "</div>";
//			html = html + "</div>";
//			html = html + "<br class=\"clr\"/>";
//			html = html + "<div class=\"tab-content\">";
//				html = html + "<div class=\"tab-pane\" id=\"listView\">";
//					
//					for (Libro libro : librosPagina) {
//					
//						html = html + "<div class=\"row\">";
//							html = html + "<div class=\"span2\">";
//								html = html + "<img src=\"" + libro.getUrlImagen() + "\" alt=\"\"/>";
//							html = html + "</div>";
//							html = html + "<div class=\"span4\">";
//								html = html + "<h3>" + libro.getTitulo() + "</h3>";
//								html = html + "<hr class=\"soft\"/>";
//								html = html + "<h5>" + libro.getEditorial() + "</h5>";
//								html = html + "<p>";
//									html = html + libro.getDescripcion();
//								html = html + "</p>";
//								html = html + "<br class=\"clr\"/>";
//							html = html + "</div>";
//							html = html + "<div class=\"span3 alignR\">";
//								html = html + "<form class=\"form-horizontal qtyFrm\">";
//									html = html + "<h3> $140.00</h3>";
//									html = html + "<a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>";
//								html = html + "</form>";
//							html = html + "</div>";
//						html = html + "</div>";
//						html = html + "<hr class=\"soft\"/>";
//					
//					}
//					
//				html = html + "</div>";
//				html = html + "<div class=\"tab-pane  active\" id=\"blockView\">";
//					html = html + "<ul class=\"thumbnails\">";
//						
//						for (Libro libro : librosPagina) {
//						
//							html = html + "<li class=\"span3\">";
//		  						html = html + "<div class=\"thumbnail\">";
//		  							html = html + "<a href=\"product_details.html\"><img src=\"" + libro.getUrlImagen() + "\" alt=\"\"/></a>";
//		  							html = html + "<div class=\"caption\">";
//			  							html = html + "<h5>" + libro.getTitulo() + "</h5>";
//			  							html = html + "<p> ";
//			  								html = html + libro.getDescripcion();
//			  							html = html + "</p>";
//			  							html = html + "<h4 style=\"text-align:center\"><a class=\"btn\" href=\"#\">Add to <i class=\"icon-shopping-cart\"></i></a> <a class=\"btn btn-primary\" href=\"#\">&euro;222.00</a></h4>";
//			  						html = html + "</div>";
//			  					html = html + "</div>";
//			  				html = html + "</li>";
//		  				
//						}
//						
//					html = html + "</ul>";
//					html = html + "<hr class=\"soft\"/>";
//				html = html + "</div>";
//			html = html + "</div>";
//			html = html + "<!-- Fin contenido -->";
//			html = html + "<div class=\"pagination\">";
//				html = html + "<ul>";
//					if (paginaActual != 1) {
//						html = html + "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "\">&lsaquo;</a></li>";
//					}
//					
//					html = html + (paginaActual - 3 > 0 ? "<li><a href=\"#\">...</a></li>" : "");
//					html = html + (paginaActual - 2 > 0 ? "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "?page=" + String.valueOf(paginaActual-2) + "\">" + String.valueOf(paginaActual-2) + "</a></li>" : "");
//					html = html + (paginaActual - 1 > 0 ? "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "?page=" + String.valueOf(paginaActual-1) + "\">" + String.valueOf(paginaActual-1) + "</a></li>" : "");
//					html = html + "<li><a href=\"#\">" + String.valueOf(paginaActual) + "</a></li>";
//					html = html + (paginaActual + 1 <= cantPaginas ? "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "?page=" + String.valueOf(paginaActual+1) + "\">" + String.valueOf(paginaActual+1) + "</a></li>" : "");
//					html = html + (paginaActual + 2 <= cantPaginas ? "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "?page=" + String.valueOf(paginaActual+2) + "\">" + String.valueOf(paginaActual+2) + "</a></li>" : "");
//					html = html + (paginaActual + 3 <= cantPaginas ? "<li><a href=\"#\">...</a></li>" : "");
//					
//					if (paginaActual != cantPaginas) {
//						html = html + "<li><a href=\"libros?categoria=" + String.valueOf(categoria.getId()) + "?page=" + String.valueOf(cantPaginas) + "\">&rsaquo;</a></li>";
//					}
//				html = html + "</ul>";
//			html = html + "</div>";
//			html = html + "<br class=\"clr\"/>";
		
		return html;
	}

}
