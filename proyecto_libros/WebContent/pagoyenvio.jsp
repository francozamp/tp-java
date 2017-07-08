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
					<h3>Pago y envío</h3>	
					<div class="well">
						<!--
						<div class="alert alert-info fade in">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
						</div>
						<div class="alert fade in">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
						</div>
						<div class="alert alert-block alert-error fade in">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
						</div> -->
						<form class="form-horizontal" action="confirmaroperacion" method="post" >
							<h4>Información de pago</h4>
							<div class="control-group">
								<label class="control-label" for="inputFname1">Número de tarjeta </label>
								<div class="controls">
									<input type="text" id="inputFname1" name="nroTarjeta" pattern=".{16}" title="La tajeta debe contener 16 digitos" required>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputLnam">Nombre y Apellido </label>
								<div class="controls">
									<input type="text" id="inputLnam" name="nombYAp" required>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Fecha de vencimiento </label>
								<div class="controls">
									<!-- <input type="text" id="input_email" name="mesVto"> <input type="password" id="inputPassword1" name="anioVto"> -->
									<select name="mesVto" class="fechaPick" required>
										<option value="0">Mes</option>
										<c:forEach var="i" begin="01" end="12">
											<option value="${i }">${i }</option>
										</c:forEach>
									</select>/
									<select name="anioVto" class="fechaPick" required>
										<option value="0">Año</option>
										<c:forEach var="i" begin="17" end="25">
											<option value="${2000 + i }">${2000 + i }</option>
										</c:forEach>
									</select>
								</div>
							</div>	  
							<div class="control-group">
								<label class="control-label" for="inputPassword2">Cód. seguridad </label>
								<div class="controls">
									<input type="text" id="inputPassword2" name="codigo" class="codSeguridad" pattern=".{3}" title="El código de seguridad debe tener 3 dígitos" required>
								</div>
							</div>

							<hr class="soft"/>

							<h4>Información de envío</h4>
							<div class="control-group">
								<label class="control-label" for="address">Dirección </label>
								<div class="controls">
									<input type="text" id="address" placeholder="Dirección" name="dir1" required/> <span>Calle y número</span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="address2">Dirección (Adicional) </label>
								<div class="controls">
									<input type="text" id="address2" placeholder="Dirección (Adicional)" name="dir2" /> <span>Piso, departamento, etc.</span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="codpos">Código postal </label>
								<div class="controls">
									<input type="text" id="codpos" placeholder="Cod. Postal" name="codpos" required/>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="localidad">Localidad </label>
								<div class="controls">
									<input type="text" id="localidad" placeholder="Localidad" name="localidad" required/>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="provincia">Provincia </label>
								<div class="controls">
									<input type="text" id="provincia" placeholder="Provincia" name="provincia" required/>
								</div>
							</div>

							<hr class="soft"/>

							<!-- <div class="alert alert-block alert-error fade in">
								<button type="button" class="close" data-dismiss="alert">×</button>
								<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
							</div> -->
							<!-- <div class="controls"> -->
							<button class="btn btn-large">Cancelar </button>
							<input class="btn btn-large btn-success pull-right" type="submit" value="Continuar" />
							<!-- </div> -->
						</form>
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