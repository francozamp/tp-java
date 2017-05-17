package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Categoria;
import Entidades.Constantes;
import Entidades.Libro;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class administracionLibros
 */
@WebServlet("/admlibros")
public class administracionLibros extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
    
	private List<Libro> libros;
	
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public administracionLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if (this.getUsuario() != null && this.getUsuario().getTipousu() == Constantes.ID_TIPO_ADMINISTRADOR) {
			
			NegocioLibro negocioLibro = new NegocioLibro();
			libros = negocioLibro.getLibros();
			
			request.setAttribute("libros", libros);
			
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
	
	@Override
	public String getSideBar(){
		
		String html = "";
    	
    	html = html + "<!-- Sidebar ================================================== -->";
		html = html + "<div id=\"sidebar\" class=\"span3\">";
			html = html + "<div class=\"well well-small\"><a id=\"myCart\" href=\"product_summary.html\"><img src=\"themes/images/ico-cart.png\" alt=\"cart\">3 Items en tu carrito <span class=\"badge badge-warning pull-right\">$155.00</span></a></div>";
			html = html + "<ul id=\"sideManu\" class=\"nav nav-tabs nav-stacked\">";
				html = html + "<li class=\"subMenu open\"><a>Administración</a>";
					html = html + "<ul>";
						html += "<li><a href=\"products.html\"><i class=\"icon-chevron-right\"></i>Libros</a></li>";
						html += "<li><a href=\"products.html\"><i class=\"icon-chevron-right\"></i>Categorías</a></li>";
						html += "<li><a href=\"products.html\"><i class=\"icon-chevron-right\"></i>Usuarios</a></li>";
					html = html + "</ul>";
				html = html + "</li>";
			html = html + "</ul>";
		html = html + "</div>";
		html = html + "<!-- Sidebar end=============================================== -->";
		
		return html;
	}

	@Override
	public String getContenido() {
		
		String html = "";
		
		html += "<!-- Inicio contenido -->";
		html += "<div id=\"contenido\" class=\"span9\">";
			html += "<h4>Administración de libros</h4>";
			html += "<div class=\"span6\">";
				html += "<form class=\"form-inline\" method=\"get\" action=\"products.html\" >";
					html += "<input class=\"srchTxt\" type=\"text\" placeholder=\"Buscar\" />";
					html += "<button type=\"submit\" id=\"submitButton\" class=\"btn btn-primary\">Buscar</button>";
				html += "</form>	";
			html += "</div>";
			html += "<div class=\"span2\">";
				html += "<a class=\"btn btn-default\" href=\"#\" role=\"button\"><i class=\"fa fa-plus\" aria-hidden=\"true\"></i> Agregar</a>";
				html += "<!-- <button>Agregar</button> -->";
			html += "</div>";
			html += "<table class=\"table table-striped\">";
				html += "<tr>";
					html += "<th>Img</th>";
					html += "<th>ISBN</th>";
					html += "<th>Título</th>";
					html += "<th>Autor</th>";
					html += "<th>Editorial</th>";
					html += "<th>Edición</th>";
					html += "<th>Otro</th>";
				html += "</tr>";
				for (Libro libro : libros) {
					html += "<tr>";
						html += "<td class=\"tapachica\"><img src=\"" + libro.getUrlImagen() + "\"></td>";
						html += "<td>" + libro.getISBN() + "</td>";
						html += "<td>" + libro.getTitulo() + "</td>";
						html += "<td>" + libro.getAutor() + "</td>";
						html += "<td>" + libro.getEditorial() + "</td>";
						html += "<td>" + libro.getEdicion() + "</td>";
						html += "<td><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i> <i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i></td>";
					html += "</tr>";
				}
			html += "</table>";
			html += "<div class=\"pagination\">";
			html += "<ul>";
				html += "<li><a href=\"#\">&lsaquo;</a></li>";
				html += "<li><a href=\"#\">1</a></li>";
				html += "<li><a href=\"#\">2</a></li>";
				html += "<li><a href=\"#\">3</a></li>";
				html += "<li><a href=\"#\">4</a></li>";
				html += "<li><a href=\"#\">...</a></li>";
				html += "<li><a href=\"#\">&rsaquo;</a></li>";
			html += "</ul>";
		html += "</div>";
		html += "</div>";
		html += "<!-- Fin contenido -->";
		
		return html;
	}

}
