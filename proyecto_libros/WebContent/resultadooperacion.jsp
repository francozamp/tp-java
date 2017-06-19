<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<!-- Cabecera -->
<jsp:include page="plantilla/cabecera.jsp"/>
<!-- Fin cabecera -->
	<!-- Main body -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<h3>Resultado de la operación</h3>	
					<div class="well">
						<h4 class="exito">Pedido procesado exitosamente</h4>
						<p>¡Felicitaciones! Su pedido fue procesado exitosamente.<br>
						Su número de pedido es: <span class="exito numero-pedido"><strong>${pedido.getId() }</strong></span></p>
						<p>Será notificado cuando el pedido esté listo y sea enviado a la dirección:</p>
						<p><strong>Dirección:</strong> ${direccion } <br>
						<strong>Localidad:</strong> ${localidad } <br>
						<strong>Código postal:</strong> ${codPostal } <br>
						<strong>Provincia:</strong> ${provincia }</p>
						<hr class="soft"/>
						<button class="btn btn-large">Salir </button>
					</div>
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<!-- Fin Main Body -->
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp"/>
	<!-- Fin footer -->
</html>