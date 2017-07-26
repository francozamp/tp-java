<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<!-- Cabecera -->
<jsp:include page="plantilla/cabecera.jsp"/>
<!-- Fin cabecera -->
	<!-- Carousel -->
	<div id="carouselBlk">
		<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">
				<div class="item active">
					<div class="container">
						<img style="width:100%" src="imagenes/imagen1.jpeg" alt="special offers"/>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<img style="width:100%" src="imagenes/imagen2.jpeg" alt=""/>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<img src="imagenes/imagen3.jpg" alt=""/>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<img src="imagenes/imagen4.jpeg" alt=""/>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div> 
	</div>
	<!-- Fin carousel -->
	<!-- Main body -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<h4>Ingresos recientes</h4>
					<ul class="thumbnails">
						<c:forEach items="${librosRecientes}" var="libro">
							<li class="span3">
								<div class="thumbnail">
									<a href="detalleproducto?idLibro=${libro.getId() }"><img src="${libro.getUrlImagen() }" alt=""/></a>
									<div class="caption">
										<a href="detalleproducto?idLibro=${libro.getId() }"><h5>${libro.getTitulo() }</h5></a>
										<p>${libro.getDescripcion() }</p>
										<div class="stars small">
											<form action="">
	 											<c:set var="puntaje" value="${libro.getPuntajePromedio()}"></c:set>
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
	          									<span>${libro.getPuntajePromedio()}</span>
	          								</form>
										</div>
										<h4 style="text-align:center">
											<a class="btn" href="agregarAlCarro?idLibro=${libro.getId() }">
												Agregar al
												<i class="icon-shopping-cart"></i>
											</a>
											<a class="btn btn-primary" href="agregarAlCarro?idLibro=${libro.getId() }">
												$${libro.getPrecioView() }
											</a>
										</h4>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
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