<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
					<form class="form-inline navbar-search nav-element" method="get" action="libros" >
						<input id="" class="srchTxt" type="text" placeholder="Buscar" name="titulo"/>
			  			<select class="srchTxt" name="idCategoria">
			  				<option value="0">Todas las categorías</option>
			  				<c:forEach items="${categorias }" var="categoria">
			  					<option value="${categoria.getId() }">${categoria.getNombre() }</option>
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
			            				<c:if test="${sessionScope.usuario.getTipoUsuario().getId() == 1 }">
			            					<li><a href="admlibros">Administracion</a></li>
			            				</c:if>
			            				<li role="separator" class="divider"></li>
			            				<li><a href="logout?paginaLlamado=index">Cerrar sesión</a></li>
			          				</ul>
			        			</li>
 		 					</c:when>
 		 					<c:otherwise>
 		 						<li><a id="btnLogin" href="#login" role="button" data-toggle="modal"><span class="btn btn-large btn-success">Iniciar Sesión</span></a>
 		 						
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