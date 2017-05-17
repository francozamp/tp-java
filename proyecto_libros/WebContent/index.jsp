<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mister Bookman</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<!-- Bootstrap style --> 
	<link id="callCss" rel="stylesheet" href="themes/cerulean/bootstrap.min.css" media="screen"/>
	<!-- <link href="themes/css/base.css" rel="stylesheet" media="screen"/>  BASE -->
	<!-- Bootstrap style responsive -->
	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
  	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- Google-code-prettify -->
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
	<!-- fav and touch icons -->
	<link rel="shortcut icon" href="themes/images/ico/favicon.ico">
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<link rel="stylesheet" type="text/css" href="micss/micss.css">
	<style type="text/css" id="enject"></style>
</head>
<body>
	<div id="header">
		<div class="container">
			<!-- Navbar ================================================== -->
			<div id="logoArea" class="navbar">
				<a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
	  			<div class="navbar-inner">
	    			<a class="brand" href="index"><img id="logo" src="themes/images/logo.png" alt="Bootsshop"/></a>
					<form class="form-inline navbar-search nav-element" method="post" action="products.html" >
						<input id="" class="srchTxt" type="text" placeholder="Buscar" />
			  			<select class="srchTxt">
			  				<option>Todas las categorías</option>
			  				<c:forEach items="${categorias }" var="categoria">
			  					<option>${categoria.nombre }</option>
			  				</c:forEach>
						</select> 
			  			<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
	    			</form>
	    			<ul id="topMenu" class="nav pull-right">
		 				<li class=""><a href="contact.html">Contacto</a></li>
 		 				
 		 				<c:choose>
 		 					<c:when test="${sessionScope.usuario != null }">
 		 						<li class="dropdown">
			          				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.usuario.nombre} ${sessionScope.usuario.apellido} <span class="caret"></span></a>
			          				<ul class="dropdown-menu">
			            				<li><a href="#">Mi cuenta</a></li>
			            				<li><a href="#">Historial de pedidos</a></li>
			            				<c:if test="${sessionScope.usuario.tipoUsuario.id == 1 }">
			            					<li><a href="admlibros">Administracion</a></li>
			            				</c:if>
			            				<li role="separator" class="divider"></li>
			            				<li><a href="logout?paginaLlamado=index">Cerrar sesión</a></li>
			          				</ul>
			        			</li>
 		 					</c:when>
 		 					<c:otherwise>
 		 						<li class=""><a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span class="btn btn-large btn-success">Iniciar Sesión</span></a>
 		 						
 		 						<c:set var="paginaActual" value="index" scope="request"/>
 		 						<div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
				  					<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h3>Iniciar Sesión</h3>
				  					</div>
				  					<div class="modal-body">
										<form class="form-horizontal loginFrm" action="login" method="post">
											<div class="control-group">
												<input type="text" id="inputEmail" placeholder="Email" name="email">
					  						</div>
					  						<div class="control-group">
					  							<input type="password" id="inputPassword" placeholder="Contraseña" name="password">
					  						</div>
					  						<div class="control-group">
												<label class="checkbox">
													<input type="checkbox"> Recuerdame
												</label>
					  						</div>
					  						<input type="hidden" name="paginaLlamado" value="index">
					  						<button type="submit" class="btn btn-success">Iniciar Sesión</button>
											<button class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</button>
										</form>
									</div>
								</div>
 		 					</c:otherwise>
 		 				</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
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
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<div id="sidebar" class="span3">
					<div class="well well-small"><a id="myCart" href="product_summary.html"><img src="themes/images/ico-cart.png" alt="cart">3 Items en tu carrito <span class="badge badge-warning pull-right">$155.00</span></a></div>
					<ul id="sideManu" class="nav nav-tabs nav-stacked">
						<li class="subMenu open"><a> Categorías</a>
							<ul>
								<c:forEach items="${categorias }" var="categoria">
									<li><a href="products.html"><i class="icon-chevron-right"></i>${categoria.nombre }</a></li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</div>
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
											<a class="btn" href="#">
												Agregar al
												<i class="icon-shopping-cart"></i>
											</a>
											<a class="btn btn-primary" href="#">
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
	<!-- Footer ================================================================== -->
	<div id="footerSection">
		<div class="container">
			<div class="row">
				<div class="span4">
					<h5>CUENTA</h5>
					<a href="login.html">Tu cuenta</a>
					<a href="login.html">Informacion personal</a> 
					<a href="login.html">Historial de pedidos</a>
					<a href="register.html">Registrate</a>
				</div>
				<div class="span4">
					<h5>Información</h5>
					<a href="contact.html">Contacto</a>    
				</div>
				<div class="span4">
					<h5>Nuestras ofertas</h5>
					<a href="#">Nuevos productos</a>
					<a href="#">Los mas vendidos</a>
				</div>
				<!-- 
				<div id="socialMedia" class="span3 pull-right">
					<h5>SOCIAL MEDIA </h5>
					<a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
					<a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
					<a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
				</div>  -->
			</div>
		</div><!-- Container End -->
	</div>
	<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>
	<script src="themes/js/bootshop.js"></script>
	<script src="themes/js/jquery.lightbox-0.5.js"></script>
</body>
</html>