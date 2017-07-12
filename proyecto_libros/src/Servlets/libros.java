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
		NegocioCategoria negocioCategoria = new NegocioCategoria();
		
		if(tituloLibro == null || tituloLibro.isEmpty()){
			if (request.getParameter("page") == null) {
				paginaActual = 1;	
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
				categoria = negocioCategoria.getCategoria(idCategoria);
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
		
		request.setAttribute("titulo", tituloLibro);
		request.setAttribute("idCategoria", idCategoria);
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

}
