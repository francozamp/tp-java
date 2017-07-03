<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<jsp:include page="plantilla/cabecera.jsp"/>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebarusuario.jsp"/>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class="span9">
					<h3>Mi cuenta</h3>
					<hr class="soft"/>
					<h4>Datos de la cuenta</h4>
					<div>
						<h5>Email: <span class="dato">${usuario.getEmail() }</span></h5>
						<h5>Contraseña: <span class="dato">${usuario.getPasswordView() }</span></h5>
						<h5>Usuario desde: <span class="dato">${usuario.getFechaAlta() }</span></h5>
					</div>
					<hr class="soft"/>
					<div>
						<h4>Datos personales</h4>
						<h5>Nombre y Apellido: <span class="dato">${usuario.getNombreApellido() }</span></h5>
						<h5>Teléfono: <span class="dato">${usuario.getTelefono() }</span></h5>
						<h5>Dirección: <span class="dato">${usuario.getDireccion() }</span></h5>
					</div>	
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp"/>
	<!-- Fin footer -->
</html>