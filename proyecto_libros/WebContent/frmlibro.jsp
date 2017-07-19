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
		                <form class="form-horizontal frm" action="formularioLibro" method="POST" enctype="multipart/form-data" >
		                    <h4>Información del libro</h4>
		                    <div class="control-group">
		                        <label class="control-label" for="isbn">ISBN <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${libro != null }">
		                        			<input type="text" id="isbn" placeholder="ISBN" name="isbn" value="${libro.ISBN }" required readonly>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" id="isbn" placeholder="ISBN" name="isbn" required>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="titulo">Titulo <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
			                        	<c:when test="${libro != null }">
			                        		<input type="text" id="titulo" placeholder="Titulo" name="titulo" value="${libro.titulo }" required>
			                        	</c:when>
			                        	<c:otherwise>
			                        		<input type="text" id="titulo" placeholder="Titulo" name="titulo" required>
			                        	</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="autor">Autor <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${libro != null }">
		                        			<input type="text" id="autor" placeholder="Autor" name="autor" value="${libro.autor }" required>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" id="autor" placeholder="Autor" name="autor" required>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>     
		                    <div class="control-group">
		                        <label class="control-label" for="editorial">Editorial <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${libro != null }">
		                        			<input type="text" id="editorial" placeholder="Editorial" name="editorial" value="${libro.editorial }" required>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" id="editorial" placeholder="Editorial" name="editorial" required>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="edicion">Edición <sup>*</sup></label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${libro != null }">
		                            		<input type="text" id="edicion" placeholder="Edición" name="edicion" value="${libro.edicion }" required>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="text" id="edicion" placeholder="Edición" name="edicion" required>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <!-- <div class="alert alert-block alert-error fade in">
		                        <button type="button" class="close" data-dismiss="alert">×</button>
		                        <strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
		                    </div> -->
		                    <div class="control-group">
		                        <label class="control-label" for="descripcion">Descripcion </label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${libro != null }">
		                            		<textarea id="descripcion" placeholder="Descripcion" name="descripcion" rows="5" cols="6"><c:out value="${libro.descripcion }"/></textarea>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<input type="text" id="descripcion" placeholder="Descripcion" name="descripcion"/>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="categorias">Categorias </label>
		                        <div class="controls">
									<select multiple name="categorias">
						  				<option value="">Seleccione una Categoría</option>
						  				<c:forEach items="${categorias }" var="categoria">
						  					<option value="${categoria.getId() }">${categoria.getNombre() }</option>
						  				</c:forEach>
									</select> 
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="imagen">Imagen  </label>
		                        <div class="controls">
		                            <input id="imagen" type="file" name="imgtapa">
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <div class="controls">
		                        	<c:if test="${libro != null && libro.getId() != null }">
		                        		<input type="hidden" name="idLibro" value="${libro.getId() }">
		                        	</c:if>
		                            <input class="btn btn-large btn-success" type="submit" value="Guardar" />
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