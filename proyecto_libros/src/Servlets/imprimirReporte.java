package Servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Entidades.LineaPedido;
import Entidades.Pedido;
import Negocio.NegocioPedido;

/**
 * Servlet implementation class imprimirReporte
 */
@WebServlet("/imprimirReporte")
public class imprimirReporte extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
	
	private ServletContext servletContext;
	private File directorioTemporal;
	private String rutaArchivo;
	private String nombreArchivo;
	private ByteArrayOutputStream baos;
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public imprimirReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		try {
			this.validarAdministrador();
			
			servletContext = request.getSession().getServletContext();
			directorioTemporal = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			rutaArchivo = directorioTemporal.getAbsolutePath();
			
			String nombreArchivo = null;
			String reporte = request.getParameter("reporte");
			
			try {
				switch (reporte) {
				case "ventas":
					this.reporteVentas();
					break;

				default:
					break;
				}
				
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "max-age=0");
				response.setHeader("Content-dispositon", "attachment; filename" + nombreArchivo);
				
				OutputStream os = response.getOutputStream();
				baos.writeTo(os);
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			response.sendRedirect(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void convertirPdfAByteArrayOutputStream(String nombreArchivo){
		
		InputStream inputStream = null;
		this.baos = new ByteArrayOutputStream();
		try {
			inputStream = new FileInputStream(nombreArchivo);
			
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
			
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				this.baos.write(buffer, 0, bytesRead);	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null){
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void reporteVentas() throws FileNotFoundException {
		
		this.nombreArchivo = "Ventas_" + System.currentTimeMillis() + ".pdf";
		
		String nombreCompleto = this.rutaArchivo + "\\" + this.nombreArchivo;
		
		Document documento = null;
		
		try {
			PdfWriter pdfWriter = new PdfWriter(nombreCompleto);
			PdfDocument documentoPdf = new PdfDocument(pdfWriter);
			documento = new Document(documentoPdf, PageSize.A4);
			documento.setMargins(20, 20, 20, 20);
			
			Table tabla = new Table(new float[]{1,1,1,1,1,1,1});
			tabla.setWidthPercent(100);
			
			tabla.addHeaderCell(new Cell().add(new Paragraph("Nro Pedido")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("Fecha Pedido")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("ISBN")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("Titulo")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("Precio")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("Cantidad")));
			tabla.addHeaderCell(new Cell().add(new Paragraph("Sub total")));
			
			List<Pedido> pedidosList = new NegocioPedido().getPedidosList();
			
			for (Pedido pedido : pedidosList) {
				for (LineaPedido lineaPedido : pedido.getLineasPedido()) {
					tabla.addCell(new Cell().add(new Paragraph(Integer.toString(pedido.getId()))));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					tabla.addCell(new Cell().add(new Paragraph(df.format(pedido.getFecha()))));
					tabla.addCell(new Cell().add(new Paragraph(lineaPedido.getLibro().getISBN())));
					tabla.addCell(new Cell().add(new Paragraph(lineaPedido.getLibro().getTitulo())));
					tabla.addCell(new Cell().add(new Paragraph(lineaPedido.getLibro().getPrecioView())));
					tabla.addCell(new Cell().add(new Paragraph(Integer.toString(lineaPedido.getCantidad()))));
					tabla.addCell(new Cell().add(new Paragraph(lineaPedido.getMontoLineaView())));
				}
				tabla.addCell(new Cell().add(new Paragraph()));
				tabla.addCell(new Cell().add(new Paragraph()));
				tabla.addCell(new Cell().add(new Paragraph()));
				tabla.addCell(new Cell().add(new Paragraph()));
				tabla.addCell(new Cell().add(new Paragraph()));
				tabla.addCell(new Cell().add(new Paragraph("TOTAL:")));
				tabla.addCell(new Cell().add(new Paragraph(pedido.getMontoTotalView())));
			}
			
			documento.add(tabla);
			documento.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.convertirPdfAByteArrayOutputStream(nombreCompleto);
		
	}

}
