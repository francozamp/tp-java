package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Entidades.Categoria;
import Entidades.Libro;
import Negocio.NegocioCategoria;
import Negocio.NegocioLibro;

/**
 * Servlet implementation class formularioLibro
 */
@WebServlet("/formularioLibro")
@MultipartConfig
public class formularioLibro extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
	private static final String BASE_PATH = System.getProperty("user.dir");
       
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		//String path = BASE_PATH + "\\WebContent\\imagenes\\libros";
		String path = "D:/Marianela/Documentos/Facultad/Java/Nuestro TP/tp-java/proyecto_libros/WebContent/imagenes/libros";

		Integer idLibro = null;
		if(request.getParameter("idLibro") != null){
			idLibro = Integer.valueOf(request.getParameter("idLibro"));
		}
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String edicion = request.getParameter("edicion");
		String descripcion = request.getParameter("descripcion");
		

		
		//Subir foto
		Part filePart = request.getPart("imgtapa"); 
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();
	    File uploads = new File(path);
	    uploads.mkdirs();
	    File file = new File(uploads, fileName);
	    String urlImagen = "imagenes/libros/" + fileName;

	    try (InputStream input = filePart.getInputStream()) {
	        Files.copy(input, file.toPath());
	    }
	    
		String[] idsCategorias = request.getParameterValues("categorias");
		List<Categoria> categorias = new ArrayList<Categoria>();

		for (String idCategoria : idsCategorias) {
			int id = Integer.parseInt(idCategoria); 
			NegocioCategoria negocioCategoria = new NegocioCategoria();
			Categoria categoria = negocioCategoria.getCategoria(id);
			categorias.add(categoria);
		}
		
		Libro libro = new Libro(isbn, titulo, autor, editorial, edicion, descripcion, true, categorias, urlImagen);
		if(idLibro != null){
			libro.setId(idLibro);
		}
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
