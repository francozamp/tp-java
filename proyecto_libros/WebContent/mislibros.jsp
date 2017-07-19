<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	
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
					<h4>Mis Libros</h4>
					<table class="table table-striped">
						<tr>
							<th>ISBN</th>
							<th>Titulo</th>
							<th>Autor</th>
							<th>Editorial</th>
							<th>Edicion</th>
							<th>Valoracion</th>
							<th>Comentario</th>
							<th>Acciones</th>
						</tr>
						
						<c:forEach items="${valoraciones }" var="valoracion">
						
							<tr>
								<td>${valoracion.getLibro().getISBN() }</td>
								<td>${valoracion.getLibro().getTitulo() }</td>
								<td>${valoracion.getLibro().getAutor() }</td>
								<td>${valoracion.getLibro().getEditorial() }</td>
								<td>${valoracion.getLibro().getEdicion() }</td>
								<td>
									<div class="stars small">
										<form action="">
 											<c:set var="puntaje" value="${valoracion.getPuntaje()}"></c:set>
											<c:forEach var="i" begin="1" end="5">
              									<c:choose>
              										<c:when test="${6-i == puntaje}">
              											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star" checked="checked"/>
              										</c:when>
              										<c:otherwise>
              											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star"/>
              										</c:otherwise>
              									</c:choose>
            									<label class="star small star-${6-i}" for="star-${6-i}"></label>
          									</c:forEach>
										</form>
									</div>
								</td>
								<c:set var = "comentario" value = "${valoracion.getComentario()}"></c:set>
								<c:set var = "comentarioParcial" value = "${fn:substring(comentario, 0, 10)}"></c:set>
								
								<td><c:out value="${comentarioParcial}"/>...</td>
								<td><a href="editarvaloracion?idValoracion=${valoracion.getId() }"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
							</tr>
						
						</c:forEach>
						
					</table>
					<div class="pagination">
						<ul>
							<li><a href="#">&lsaquo;</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">...</a></li>
							<li><a href="#">&rsaquo;</a></li>
						</ul>
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