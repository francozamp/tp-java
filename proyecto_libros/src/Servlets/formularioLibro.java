package Servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Entidades.Categoria;
import Entidades.Constantes;
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
	
	private boolean esMultipart;
	private String filePath;
	private String extension;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public formularioLibro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		//String path = BASE_PATH + "\\WebContent\\imagenes\\libros";
//		String path = "D:/Marianela/Documentos/Facultad/Java/Nuestro TP/tp-java/proyecto_libros/WebContent/imagenes/libros";

		boolean continua = true;
		
		ServletContext context = getServletContext();
//		filePath = context.getInitParameter("file-upload");
		
		filePath = Constantes.RUTA_IMAGEN_TAPA_LIBRO;
		
		esMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		
		try {		
			// Subo guardo la imagen
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			Path temp = Paths.get(context.getRealPath("temp"));
			if(!Files.exists(temp)){
				Files.createDirectories(temp);
			}
			File temporal = new File(temp.toString());
			
			factory.setRepository(temporal);

			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> fileItems = upload.parseRequest(request);
			
			Iterator<FileItem> i = fileItems.iterator();
			
			Integer idLibro = null;
			String isbn = null, titulo = null, autor = null, editorial = null, edicion = null, descripcion = null, idCategoria = null;
			List<Categoria> categorias = new ArrayList<Categoria>();
			
			while(i.hasNext()){
				FileItem fi = (FileItem)i.next();
				
				switch (fi.getFieldName()) {
				
				case "idLibro":
					idLibro = Integer.valueOf(fi.getString());
					break;
				
				case "isbn":
					isbn = fi.getString();
					break;
					
				case "titulo":
					titulo = fi.getString();
					break;
					
				case "autor":
					autor = fi.getString();
					break;
					
				case "editorial":
					editorial = fi.getString();
					break;
				
				case "edicion":
					edicion = fi.getString();
					break;
					
				case "descripcion":
					descripcion = fi.getString();
					break;
					
				case "categorias":
					idCategoria = fi.getString();
					
					int id = Integer.parseInt(idCategoria); 
					NegocioCategoria negocioCategoria = new NegocioCategoria();
					Categoria categoria = negocioCategoria.getCategoria(id);
					categorias.add(categoria);
					break;
					
				case "imgtapa":
					if(!fi.isFormField()){
						String nombreCampo = fi.getFieldName();
						String nombreArchivo = fi.getName();
						String tipoContenido = fi.getContentType();
						boolean enMemoria = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						
						
						Path path = Paths.get(context.getRealPath(filePath));
						
						
						if(!Files.exists(path)){
							Files.createDirectories(path);
						}
						
						file = new File(path.toString() + "\\" + nombreArchivo);
						
						Files.deleteIfExists(file.toPath());
						
						fi.write(file);
						extension = filePath + nombreArchivo;
					}
					break;
					
				default:
					break;
				}
			}
			//Fin guardar imagen
			
			// Integer idLibro = null;
			//
			// String isbn = request.getParameter("isbn");
			// String titulo = request.getParameter("titulo");
			// String autor = request.getParameter("autor");
			// String editorial = request.getParameter("editorial");
			// String edicion = request.getParameter("edicion");
			// String descripcion = request.getParameter("descripcion");
			//
			// String[] idsCategorias =
			// request.getParameterValues("categorias");
			
			
			Libro libro = new Libro(isbn, titulo, autor, editorial, edicion, descripcion, true, categorias, extension);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
//		//Subir foto
//		Part filePart = request.getPart("imgtapa"); 
//	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
//	    InputStream fileContent = filePart.getInputStream();
//	    File uploads = new File(path);
//	    uploads.mkdirs();
//	    File file = new File(uploads, fileName);
//	    String urlImagen = "imagenes/libros/" + fileName;
//
//	    try (InputStream input = filePart.getInputStream()) {
//	        Files.copy(input, file.toPath());
//	    }
//	    
//		String[] idsCategorias = request.getParameterValues("categorias");
//		List<Categoria> categorias = new ArrayList<Categoria>();
//
//		for (String idCategoria : idsCategorias) {
//			int id = Integer.parseInt(idCategoria); 
//			NegocioCategoria negocioCategoria = new NegocioCategoria();
//			Categoria categoria = negocioCategoria.getCategoria(id);
//			categorias.add(categoria);
//		}
//		
//		Libro libro = new Libro(isbn, titulo, autor, editorial, edicion, descripcion, true, categorias, urlImagen);
//		if(idLibro != null){
//			libro.setId(idLibro);
//		}
//		NegocioLibro negocioLibro = new NegocioLibro();
//		Libro guardado = negocioLibro.guardarLibro(libro);
//		if (guardado != null) {
//			List<Libro> libros = negocioLibro.getLibros();
//			request.setAttribute("libros", libros);
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("administracion.jsp");
//			requestDispatcher.forward(request, response);
//		}
		
	}

}
