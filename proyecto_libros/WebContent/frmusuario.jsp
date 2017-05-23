<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<!-- Cabecera -->
<jsp:include page="plantilla/cabecera.jsp"/>
<!-- Fin cabecera -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebaradministracion.jsp"/>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class="span9">
		            <h3>Registro</h3>    
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
		                <form class="form-horizontal frm" action="nuevousuario" method="POST" >
		                    <h4>Información personal</h4>
		                    <div class="control-group">
		                        <label class="control-label" for="inputFname1">Nombre <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="text" id="inputFname1" placeholder="Nombre" name="nombre" value="${usuarioEditar.nombre }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="text" id="inputFname1" placeholder="Nombre" name="nombre" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="inputLnam">Apellido <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="text" id="inputLnam" placeholder="Apellido" name="apellido" value="${usuarioEditar.apellido }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="text" id="inputLnam" placeholder="Apellido" name="apellido" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="input_email">Email <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="email" id="input_email" placeholder="Email" name="email" value="${usuarioEditar.email }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="email" id="input_email" placeholder="Email" name="email" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>     
		                    <div class="control-group">
		                        <label class="control-label" for="inputPassword1">Contraseña <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="password" id="inputPassword1" placeholder="Contraseña" name="password" value="${usuarioEditar.password }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="password" id="inputPassword1" placeholder="Contraseña" name="password" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="inputPassword2">Repetir contraseña <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="password" id="inputPassword2" placeholder="Contraseña" value="${usuarioEditar.password }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="password" id="inputPassword2" placeholder="Contraseña" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <!-- <div class="alert alert-block alert-error fade in">
		                        <button type="button" class="close" data-dismiss="alert">×</button>
		                        <strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
		                    </div> -->
		                    <div class="control-group">
		                        <label class="control-label" for="address">Dirección <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${usuarioEditar != null }">
		                        			<input type="text" id="address" placeholder="Dirección" name="direccion" value="${usuarioEditar.direccion }" required/> <span>Calle y número</span>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" id="address" placeholder="Dirección" name="direccion" required/> <span>Calle y número</span>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="address2">Dirección (Adicional) </label>
		                        <div class="controls">
		                          <input type="text" id="address2" placeholder="Dirección (Adicional)" name="direccion2"/> <span>Piso, departamento, etc.</span>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="phone">Teléfono <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${usuarioEditar != null }">
		                            		<input type="text" id="phone" placeholder="Teléfono" name="telefono" value="${usuarioEditar.telefono }" required/>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="text" id="phone" placeholder="Teléfono" name="telefono" required/>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <div class="controls">
		                            <input class="btn btn-large btn-success" type="submit" value="Registrarse" />
		                        </div>
		                    </div>
		                </form>
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