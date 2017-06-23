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
				<div class="span9">
					<h3> Login</h3>	
					<hr class="soft"/>
					<div class="row">
						<div id="loginCont" class="span4">
							<div class="well">
								<h4>Inicia Sesión</h4>
								<form action="login" method="post">
									<div class="control-group">
										<label class="control-label" for="inputEmail1">Email</label>
										<div class="controls">
											<input class="span3"  type="text" id="inputEmail1" placeholder="Email" name="email">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputPassword1">Contraseña</label>
										<div class="controls">
											<input type="password" class="span3"  id="inputPassword1" placeholder="Contraseña" name="password">
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<input type="hidden" name="paginaLlamado" value="index">
											<button type="submit" class="btn">Iniciar Sesión</button> <a href="forgetpass.html">Olvidaste la contraseña?</a>
										</div>
									</div>
								</form>
							</div>
						</div>
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