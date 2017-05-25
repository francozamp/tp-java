package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registrate
 */
@WebServlet("/registrate")
public class registrate extends MiServletPlantilla {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see MiServletPlantilla#MiServletPlantilla()
     */
    public registrate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String getContenido() {
		
		String html = "";
		
		html = html + "<div class=\"span9\">";
			html = html + "<h3>Registro</h3>	";
			html = html + "<div class=\"well\">";
			html = html + "<!--";
			html = html + "<div class=\"alert alert-info fade in\">";
				html = html + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>";
				html = html + "<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";
			html = html + "</div>";
			html = html + "<div class=\"alert fade in\">";
				html = html + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>";
				html = html + "<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";
			html = html + "</div>";
			html = html + "<div class=\"alert alert-block alert-error fade in\">";
				html = html + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>";
				html = html + "<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";
			html = html + "</div> -->";
			html = html + "<form class=\"form-horizontal\" action=\"nuevousuario\" method=\"POST\" >";
				html = html + "<h4>Información personal</h4>";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"inputFname1\">Nombre <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
			  			html = html + "<input type=\"text\" id=\"inputFname1\" placeholder=\"Nombre\" name=\"nombre\" required>";
					html = html + "</div>";
			 	html = html + "</div>";
			 	html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"inputLnam\">Apellido <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
				  		html = html + "<input type=\"text\" id=\"inputLnam\" placeholder=\"Apellido\" name=\"apellido\" required>";
					html = html + "</div>";
			 	html = html + "</div>";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"input_email\">Email <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
			  			html = html + "<input type=\"email\" id=\"input_email\" placeholder=\"Email\" name=\"email\" required>";
					html = html + "</div>";
		  		html = html + "</div>	  ";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"inputPassword1\">Contraseña <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
			  			html = html + "<input type=\"password\" id=\"inputPassword1\" placeholder=\"Contraseña\" name=\"password\" required>";
					html = html + "</div>";
		  		html = html + "</div>";
		  		html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"inputPassword2\">Repetir contraseña <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
			  			html = html + "<input type=\"password\" id=\"inputPassword2\" placeholder=\"Contraseña\" required>";
					html = html + "</div>";
		  		html = html + "</div>";
				html = html + "<!-- <div class=\"alert alert-block alert-error fade in\">";
					html = html + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>";
					html = html + "<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";
				html = html + "</div> -->";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"address\">Dirección <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
					  html = html + "<input type=\"text\" id=\"address\" placeholder=\"Dirección\" name=\"direccion\" required/> <span>Calle y número</span>";
					html = html + "</div>";
				html = html + "</div>";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"address2\">Dirección (Adicional) </label>";
					html = html + "<div class=\"controls\">";
					  html = html + "<input type=\"text\" id=\"address2\" placeholder=\"Dirección (Adicional)\" name=\"direccion2\"/> <span>Piso, departamento, etc.</span>";
					html = html + "</div>";
				html = html + "</div>";
				html = html + "<div class=\"control-group\">";
					html = html + "<label class=\"control-label\" for=\"phone\">Teléfono <sup>*</sup></label>";
					html = html + "<div class=\"controls\">";
				  		html = html + "<input type=\"text\" id=\"phone\" placeholder=\"Teléfono\" name=\"telefono\" required/>";
					html = html + "</div>";
				html = html + "</div>";
				html = html + "<div class=\"control-group\">";
					html = html + "<div class=\"controls\">";
						html = html + "<input type=\"hidden\" name=\"email_create\" value=\"1\">";
						html = html + "<input type=\"hidden\" name=\"is_new_customer\" value=\"1\">";
						html = html + "<input class=\"btn btn-large btn-success\" type=\"submit\" value=\"Registrarse\" />";
					html = html + "</div>";
				html = html + "</div>";
			html = html + "</form>";
		html = html + "</div>";
	
		return html;
	}

}
