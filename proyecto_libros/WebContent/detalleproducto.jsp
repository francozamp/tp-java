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
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<div class="row">	  
						<div id="gallery" class="span3">
							<img src="${libro.getUrlImagen()}" style="width:100%" alt="${libro.getTitulo()} - ${libro.getAutor()}"/>
						</div>
						<div class="span6">
							<h3>${libro.getTitulo()}</h3>
							<small>- ${libro.getAutor()}</small>
							<hr class="soft"/>
							<div class="stars promedio">
								<form action="">
									<span id="promedio">${libro.getPuntajePromedio()}</span>
									<c:set var="puntaje" value="${libro.getPuntajePromedio()}"></c:set>
									<c:forEach var="i" begin="1" end="5">
       									<c:choose>
       										<c:when test="${6-i-0.5 < puntaje && 6-i+0.5 >= puntaje}">
       											<input class="star star-${6-i}" id="star-${6-i}" type="radio" name="star" checked="checked" disabled/>
       										</c:when>
       										<c:otherwise>
       											<input class="star star-${6-i}" id="star-${6-i}" type="radio" name="star" disabled/>
       										</c:otherwise>
       									</c:choose>
     									<label class="star star-${6-i}" for="star-${6-i}"></label>
   									</c:forEach>
   								</form>
							</div>
							<form  action="agregarAlCarro" class="form qtyFrm">
								<div class="control-group">
									<h3>$ ${libro.getPrecioView()}</h3>
									<div class="controls">
										<input id="idLibro" name="idLibro" type="hidden" value="${libro.getId()}">
										<input id="redir" name="redir" type="hidden" value="detalleproducto?idLibro=${libro.getId()}">
										<label class="control-label">Cantidad <input id="cantidad" name="cantidad" type="number" class="span1" placeholder="Cant." value="1" min="1" required/></label>
										<button type="submit" class="btn btn-large btn-primary"> Agregar al carrito <i class=" icon-shopping-cart"></i></button>
									</div>
								</div>
							</form>
						</div>
						<div class="span9">
							<hr class="soft"/>
							<ul id="productDetail" class="nav nav-tabs">
								<li class="active"><a href="#home" data-toggle="tab">Detalles</a></li>
								<li><a href="#profile" data-toggle="tab">Sinopsis</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="home">
									<table class="table table-bordered">
										<tbody>
											<tr class="techSpecRow"><th colspan="2">Detalles del producto:</th></tr>
											<tr class="techSpecRow"><td class="techSpecTD1">Titulo: </td><td class="techSpecTD2">${libro.getTitulo()}</td></tr>
											<tr class="techSpecRow"><td class="techSpecTD1">Autor:</td><td class="techSpecTD2">${libro.getAutor()}</td></tr>
											<tr class="techSpecRow"><td class="techSpecTD1">Editorial:</td><td class="techSpecTD2">${libro.getEditorial()}</td></tr>
											<tr class="techSpecRow"><td class="techSpecTD1">Edicion:</td><td class="techSpecTD2">${libro.getEdicion()}</td></tr>
											<tr class="techSpecRow"><td class="techSpecTD1">ISBN:</td><td class="techSpecTD2">${libro.getISBN()}</td></tr>
										</tbody>
									</table>
								</div>
								<div class="tab-pane fade" id="profile">
									<div class="tab-content">
										
										<div class="tab-pane active" id="blockView">
											<h5>Sinopsis</h5>
											<p>
												${libro.getDescripcion()}
											</p>
											<hr class="soft"/>
										</div>
									</div>
									<br class="clr">
								</div>
							</div>
						</div>
						<div class="span9">
							<h3>Valoraciones</h3>
							<c:forEach var="valoracion" items="${valoraciones }">
								<div class="span9 comentario">
									<h5>${valoracion.getUsuario().getNombreApellido() }</h5>
									<div class="stars small">
										<form action="">
											<c:set var="puntaje" value="${valoracion.getPuntaje()}"></c:set>
											<c:forEach var="i" begin="1" end="5">
		       									<c:choose>
		       										<c:when test="${6-i-0.5 < puntaje && 6-i+0.5 >= puntaje}">
		       											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star" checked="checked" disabled/>
		       										</c:when>
		       										<c:otherwise>
		       											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star" disabled/>
		       										</c:otherwise>
		       									</c:choose>
		     									<label class="star small star-${6-i}" for="star-${6-i}"></label>
		   									</c:forEach>
		   								</form>
									</div>
									<p>${valoracion.getComentario() }</p>
								</div>
							</c:forEach>
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
	<script type="text/javascript">
</script>
</html>