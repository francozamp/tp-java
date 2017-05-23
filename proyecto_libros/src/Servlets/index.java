package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidades.Libro;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		List<Libro> librosRecientes = null;
		NegocioLibro negocioLibro = new NegocioLibro();
		librosRecientes = negocioLibro.getRecientes();
		
		request.setAttribute("librosRecientes", librosRecientes);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(request, response);
//		response.sendRedirect("index.jsp");
		
		
//		responder(response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void responder(HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String html = this.getHead() + this.getCabecera() + this.getCarousel() + this.getCuerpo() + this.getPie();
		
		out.print(html);

	}
	
	private String getCarousel(){
		
		String html = "";
		
		html = html + "<div id=\"carouselBlk\">";
			html = html + "<div id=\"myCarousel\" class=\"carousel slide\">";
				html = html + "<div class=\"carousel-inner\">";
					html = html + "<div class=\"item active\">";
						html = html + "<div class=\"container\">";
							html = html + "<a href=\"register.html\"><img style=\"width:100%\" src=\"imagenes/imagen1.jpeg\" alt=\"special offers\"/></a>";
							html = html + "<div class=\"carousel-caption\">";
						  		html = html + "<h4>Second Thumbnail label</h4>";
						  		html = html + "<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>";
							html = html + "</div>";
						html = html + "</div>";
					html = html + "</div>";
					html = html + "<div class=\"item\">";
						html = html + "<div class=\"container\">";
							html = html + "<a href=\"register.html\"><img style=\"width:100%\" src=\"imagenes/imagen2.jpeg\" alt=\"\"/></a>";
							html = html + "<div class=\"carousel-caption\">";
								html = html + "<h4>Second Thumbnail label</h4>";
								html = html + "<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>";
							html = html + "</div>";
						html = html + "</div>";
					html = html + "</div>";
					html = html + "<div class=\"item\">";
						html = html + "<div class=\"container\">";
							html = html + "<a href=\"register.html\"><img src=\"imagenes/imagen3.jpg\" alt=\"\"/></a>";
							html = html + "<div class=\"carousel-caption\">";
								html = html + "<h4>Second Thumbnail label</h4>";
								html = html + "<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>";
							html = html + "</div>";
						html = html + "</div>";
					html = html + "</div>";
					html = html + "<div class=\"item\">";
						html = html + "<div class=\"container\">";
							html = html + "<a href=\"register.html\"><img src=\"imagenes/imagen4.jpeg\" alt=\"\"/></a>";
							html = html + "<div class=\"carousel-caption\">";
								html = html + "<h4>Second Thumbnail label</h4>";
								html = html + "<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>";
							html = html + "</div>";
						html = html + "</div>";
					html = html + "</div>";
				html = html + "</div>";
				html = html + "<a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">&lsaquo;</a>";
				html = html + "<a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">&rsaquo;</a>";
			html = html + "</div> ";
		html = html + "</div>";
		
		return html;
	}
	
	public String getContenido() {
		
		List<Libro> librosRecientes = null;
		NegocioLibro negocioLibro = new NegocioLibro();
		librosRecientes = negocioLibro.getRecientes();
		
		String html = "";
		
		html = html + "<!-- Inicio contenido -->";
		html = html + "<div id=\"contenido\" class=\"span9\">";
			html = html + "<h4>Ingresos recientes</h4>";
			html = html + "<ul class=\"thumbnails\">";
			
				for (Libro libro : librosRecientes) {
					
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
