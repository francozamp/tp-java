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
						<a href="register.html"><img style="width:100%" src="imagenes/imagen1.jpeg" alt="special offers"/></a>
						<div class="carousel-caption">
					  		<h4>Second Thumbnail label</h4>
					  		<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<a href="register.html"><img style="width:100%" src="imagenes/imagen2.jpeg" alt=""/></a>
						<div class="carousel-caption">
							<h4>Second Thumbnail label</h4>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<a href="register.html"><img src="imagenes/imagen3.jpg" alt=""/></a>
						<div class="carousel-caption">
							<h4>Second Thumbnail label</h4>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="container">
						<a href="register.html"><img src="imagenes/imagen4.jpeg" alt=""/></a>
						<div class="carousel-caption">
							<h4>Second Thumbnail label</h4>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
						</div>
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
									<a  href="product_details.html"><img src="${libro.urlImagen }" alt=""/></a>
									<div class="caption">
										<h5>${libro.titulo }</h5>
										<p>${libro.descripcion }</p>
										<h4 style="text-align:center">
											<a class="btn" href="agregarAlCarro?idLibro=${libro.id }">
												Agregar al
												<i class="icon-shopping-cart"></i>
											</a>
											<a class="btn btn-primary" href="agregarAlCarro?idLibro=${libro.id }">
												$222.00
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