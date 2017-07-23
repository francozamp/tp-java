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
		            <h3>Valoracion</h3>    
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
		                <form class="form-horizontal frm" action="guardarValoracion" method="POST" >
		                    <h4>Ingresa un puntaje y un comentario</h4>
		                    <div class="control-group">
		                        <label class="control-label" for="isbn">Puntaje</label>
		                        <div class="controls">
		                        	<div class="stars">
		                        	<c:choose>
		                        		<c:when test="${valoracion != null}">
		                        			<c:set var="puntaje" value="${valoracion.getPuntaje()}"></c:set>
		                        		</c:when>
		                        		<c:otherwise>
		                        		<c:set var="puntaje" value="0"></c:set>
		                        		</c:otherwise>
		                        	</c:choose>
											<c:forEach var="i" begin="1" end="5">
              									<c:choose>
              										<c:when test="${6-i == puntaje}">
              											<input class="star star-${6-i}" id="star-${6-i}" type="radio" name="puntaje" value="${6-i}" checked="checked"/>
              										</c:when>
              										<c:otherwise>
              											<input class="star star-${6-i}" id="star-${6-i}" type="radio" name="puntaje" value="${6-i}"/>
              										</c:otherwise>
              									</c:choose>
            									<label class="star star-${6-i}" for="star-${6-i}"></label>
          									</c:forEach>
									</div>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="descripcion">Comentario </label>
		                        <div class="controls">
		                            <c:choose>
		                            	<c:when test="${valoracion != null }">
		                            		<textarea id="descripcion" placeholder="Comentario" name="comentario" rows="5" cols="6"><c:out value="${valoracion.getComentario() }"/></textarea>
		                            	</c:when>
		                            	<c:otherwise>
		                            		<textarea id="descripcion" placeholder="Comentario" name="comentario" rows="5" cols="6"></textarea>
		                            	</c:otherwise>
		                            </c:choose>
		                        </div>
		                    </div>
		                    <!-- <div class="alert alert-block alert-error fade in">
		                        <button type="button" class="close" data-dismiss="alert">×</button>
		                        <strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
		                    </div> -->
		                    <div class="control-group">
		                        <div class="controls">
		                        	<c:if test="${valoracion != null && valoracion.getId() != null }">
		                        		<input type="hidden" name="idValoracion" value="${valoracion.getId() }">
		                        	</c:if>
		                            <input class="btn btn-large btn-success" type="submit" value="Guardar" />
		                        </div>
		                    </div>
		                </form>
		                <c:if test="${errores != null}">
							<p style="color:red;">${errores}</p>
						</c:if>
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