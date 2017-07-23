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
				<jsp:include page="plantilla/sidebaradministracion.jsp"/>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class="span9">
					<h4>Reportes</h4>
					<table class="table table-striped">
						<tr>
							<th>Reporte</th>
							<th></th>
						</tr>
						<tr>
							<td>Ventas</td>
							<td><a href="filtrosreporte?reporte=ventas"><i class="fa fa-print" aria-hidden="true"></i></a></td>
						</tr>
					</table>
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp"/>
	<!-- Fin footer -->
</html>