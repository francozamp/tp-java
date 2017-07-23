<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<!-- Cabecera -->
	<jsp:include page="plantilla/cabecera.jsp" />
	<!-- Fin cabecera -->
	<!-- Main body -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div class="span9">
					<hr class="soften">
					<h1>Visitanos</h1>
					<hr class="soften">
					<div class="row">
						<div class="span3">
							<h4>Detalles de contacto</h4>
							<p>
								Zeballos 1341,<br> 2000 Rosario, Santa Fe <br> <br>
								info@mrbookman.com<br> Tel 123-456-6780<br> Fax
								123-456-5679<br> web:mrbookman.com
							</p>
						</div>
	
						<div class="span3">
							<h4>Abierto:</h4>
							<h5>Lunes - Viernes</h5>
							<p>
								09:00am - 09:00pm<br> <br>
							</p>
							<h5>Sabado</h5>
							<p>
								09:00am - 07:00pm<br> <br>
							</p>
							<h5>Domingo</h5>
							<p>
								12:30pm - 06:00pm<br> <br>
							</p>
						</div>
						<div class="span3">
							<h4>Email</h4>
							<form action="enviaMail" method="post" id="mailcontacto" class="form-horizontal">
								<fieldset>
									<div class="control-group">
	
										<input name="nombre" placeholder="Nombre" class="input-xlarge" type="text">
	
									</div>
									<div class="control-group">
	
										<input name="email" placeholder="Email" class="input-xlarge" type="text">
	
									</div>
									<div class="control-group">
	
										<input name="asunto" placeholder="Asunto" class="input-xlarge" type="text">
	
									</div>
									<div class="control-group">
										<textarea name="mensaje" rows="3" id="textarea" placeholder="Mensaje..."
											class="input-xlarge"></textarea>
	
									</div>
	
									<button class="btn btn-large" type="submit">Enviar mensaje</button>
	
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<!-- Fin Main Body -->
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp" />
	<!-- Fin footer -->
</html>