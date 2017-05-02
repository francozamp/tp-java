package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Libro;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class libros
 */
@WebServlet("/libros")
public class libros extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
	
	private int idCategoria;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public libros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		idCategoria  = Integer.parseInt(request.getParameter("categoria"));
		
		responder(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public String getContenido() {
		
		NegocioLibro negocioLibro = new NegocioLibro();
		List<Libro> libros = negocioLibro.getLibrosPorCategoria(idCategoria);
		
		String html = "";
		
		html = html + "<!-- Inicio contenido -->";
		html = html + "<div id=\"contenido\" class=\"span9\">";
			html = html + "<h4>Ingresos recientes</h4>";
			html = html + "<ul class=\"thumbnails\">";
			
				for (Libro libro : libros) {
					
					html = html + "<li class=\"span3\">";
						html = html + "<div class=\"thumbnail\">";
							html = html + "<a  href=\"product_details.html\"><img src=\"" + libro.getUrlImagen() + "\" alt=\"\"/></a>";
							html = html + "<div class=\"caption\">";
								html = html + "<h5>" + libro.getTitulo() + "</h5>";
								html = html + "<p>" + libro.getDescripcion() + "</p>";
								html = html + "<h4 style=\"text-align:center\">";
									html = html + "<a class=\"btn\" href=\"#\">";
										html = html + "Agregar al";
										html = html + "<i class=\"icon-shopping-cart\"></i>";
									html = html + "</a>";
									html = html + "<a class=\"btn btn-primary\" href=\"#\">";
										html = html + "$222.00";
									html = html + "</a>";
								html = html + "</h4>";
							html = html + "</div>";
						html = html + "</div>";
					html = html + "</li>";
					
				}
			
			html = html + "</ul>";
		html = html + "</div>";
		html = html + "<!-- Fin contenido -->";
		
		return html;
	}

}
