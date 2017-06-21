package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Categoria;
import Entidades.Libro;
import Negocio.NegocioCategoria;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class formularioLibro
 */
@WebServlet("/formularioLibro")
public class formularioLibro extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public formularioLibro() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if (request.getParameter("idLibro")!=null) {
			NegocioLibro negocioLibro = new NegocioLibro();
			Libro libro = negocioLibro.getLibroById(Integer.valueOf(request.getParameter("idLibro")));
			request.setAttribute("libro", libro);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String edicion = request.getParameter("edicion");
		String descripcion = request.getParameter("descripcion");
		String[] idsCategorias = request.getParameterValues("categorias");
		List<Categoria> categorias = new ArrayList<Categoria>();

		for (String idCategoria : idsCategorias) {
			int id = Integer.parseInt(idCategoria); 
			NegocioCategoria negocioCategoria = new NegocioCategoria();
			Categoria categoria = negocioCategoria.getCategoria(id);
			categorias.add(categoria);
		}
		
		Libro libro = new Libro(isbn, titulo, autor, editorial, edicion, descripcion, true, categorias);
		NegocioLibro negocioLibro = new NegocioLibro();
		Libro guardado = negocioLibro.guardarLibro(libro);
		if (guardado != null) {
			List<Libro> libros = negocioLibro.getLibros();
			request.setAttribute("libros", libros);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("administracion.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

}
