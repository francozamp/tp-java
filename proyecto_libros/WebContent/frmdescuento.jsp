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
		                <form class="form-horizontal frm" action="guardarDescuento" method="POST" >
		                    <h4>Información del descuento</h4>
		                    <div class="control-group">
		                        <label class="control-label" for="isbn">Código de descuento <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${descuento != null }">
		                        			<input type="text" placeholder="Código de descuento" name="codigo" value="${descuento.getCodigo() }" required readonly>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="text" placeholder="Código de descuento" name="codigo" required>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="titulo">% de descuento <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
			                        	<c:when test="${descuento != null }">
			                        		<input type="text" placeholder="% de descuento" name="porcDesc" value="${descuento.getFechaHasta() }" required>
			                        	</c:when>
			                        	<c:otherwise>
			                        		<input type="text" placeholder="% de descuento" name="porcDesc" required>
			                        	</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>
		                    <div class="control-group">
		                        <label class="control-label" for="autor">Fecha desde <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${descuento != null }">
		                        			<input type="date" name="fechaDesde" required>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="date" name="fechaDesde" required>
		                        		</c:otherwise>
		                        	</c:choose>
		                        </div>
		                    </div>     
		                    <div class="control-group">
		                        <label class="control-label" for="editorial">Fecha hasta <sup>*</sup></label>
		                        <div class="controls">
		                        	<c:choose>
		                        		<c:when test="${descuento != null }">
		                        			<input type="date" name="fechaDesde" required>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<input type="date" name="fechaDesde" required>
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
		                        	<c:if test="${descuento != null && descuento.getId() != null }">
		                        		<input type="hidden" name="idLibro" value="${descuento.getId() }">
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