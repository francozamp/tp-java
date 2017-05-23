<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<jsp:include page="plantilla/cabecera.jsp"/>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebaradministracion.jsp"/>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class="span9">
					<c:choose>
						<c:when test="${libros != null }">
							<h4>Administraci�n de libros</h4>
							<div class="span6">
								<form class="form-inline" method="get" action="products.html" >
									<input class="srchTxt" type="text" placeholder="Buscar" />
									<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
								</form>	
							</div>
							<div class="span2">
								<a class="btn btn-default" href="frmlibro.jsp" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</a>
								<!-- <button>Agregar</button> -->
							</div>
							<table class="table table-striped">
								<tr>
									<th>Img</th>
									<th>ISBN</th>
									<th>T�tulo</th>
									<th>Autor</th>
									<th>Editorial</th>
									<th>Edici�n</th>
									<th>Acciones</th>
								</tr>
								<c:forEach items="${libros }" var="libro">
									<tr>
										<td class="tapachica"><img src="${libro.urlImagen }"></td>
										<td>${libro.ISBN }</td>
										<td>${libro.titulo }</td>
										<td>${libro.autor }</td>
										<td>${libro.editorial }</td>
										<td>${libro.edicion }</td>
										<td><a href="formularioLibro?idLibro=${libro.id }"><i class="fa fa-pencil" aria-hidden="true"></i></a> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:when test="${usuarios != null }">
							<h4>Administraci�n de usuarios</h4>
							<div class="span6">
								<form class="form-inline" method="get" action="products.html" >
									<input class="srchTxt" type="text" placeholder="Buscar" />
									<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
								</form>	
							</div>
							<div class="span2">
								<a class="btn btn-default" href="formularioUsuario" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</a>
								<!-- <button>Agregar</button> -->
							</div>
							<table class="table table-striped">
								<tr>
									<th>Id</th>
									<th>Apellido y Nombre</th>
									<th>Telefono</th>
									<th>Direccion</th>
									<th>Estado</th>
									<th>Admin</th>
									<th>Acciones</th>
								</tr>
								<c:forEach items="${usuarios }" var="usuario">
									<tr>
										<td>${usuario.id }</td>
										<td>${usuario.apellido} ${usuario.nombre }</td>
										<td>${usuario.telefono }</td>
										<td>${usuario.direccion }</td>
										<td>${usuario.estado.nombre }</td>
										<c:choose>
											<c:when test="${usuario.tipoUsuario.id == 1 }">
												<td><input type="checkbox" class="chk-admin" checked disabled/></td>
											</c:when>
											<c:otherwise>
												<td><input type="checkbox" class="chk-admin" disabled/></td>
											</c:otherwise>
										</c:choose>
										<td><a href="formularioUsuario?idUsuario=${usuario.id }"><i class="fa fa-pencil" aria-hidden="true"></i></a> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:when test="${categorias != null }">
							<h4>Administraci�n de categorias</h4>
							<div class="span6">
								<form class="form-inline" method="get" action="products.html" >
									<input class="srchTxt" type="text" placeholder="Buscar" />
									<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
								</form>	
							</div>
							<div class="span2">
								<a class="btn btn-default" href="#" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</a>
								<!-- <button>Agregar</button> -->
							</div>
							<table class="table table-striped">
								<tr>
									<th>Id</th>
									<th>Nombre</th>
									<th>Detalle</th>
									<th>Acciones</th>
								</tr>
								<c:forEach items="${categorias }" var="categoria">
									<tr>
										<td>${categoria.id }</td>
										<td>${categoria.nombre }</td>
										<td>${categoria.descripcion }</td>
										<td><i class="fa fa-pencil" aria-hidden="true"></i> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
					</c:choose>
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