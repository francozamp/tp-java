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
						<c:when test="${pedidos != null }">
							<h4>Administración de pedidos</h4>
							<div class="span6">
								<form class="form-inline" method="post" action="buscar" >
									<input class="srchTxt" type="text" name="descripcion" placeholder="Buscar" />
									<input type="hidden" name="objeto" value="pedido">
									<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
								</form>
							</div>
							<table class="table table-striped">
								<tr>
									<th>Pedido</th>
									<th>Usuario</th>
									<th>Cant Libros</th>
									<th>Fecha</th>
									<th>Estado</th>
									<th>Acciones</th>
								</tr>
								<c:forEach items="${pedidos }" var="pedido">
									<tr>
										<td>${pedido.getId() }</td>
										<td>${pedido.getUsuario().getEmail() }</td>
										<td>${pedido.getCantLibros() }</td>
										<td>${pedido.getFecha() }</td>
										<td>${pedido.getEstado().getNombre() }</td>
										<td><a href="editarpedido?idPedido=${pedido.getId() }"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:when test="${libros != null }">
							<h4>Administración de libros</h4>
							<div class="span6">
								<form class="form-inline" method="post" action="buscar" >
									<input class="srchTxt" type="text" name="descripcion" placeholder="Buscar" />
									<input type="hidden" name="objeto" value="libro">
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
									<th>Título</th>
									<th>Autor</th>
									<th>Editorial</th>
									<th>Edición</th>
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
										<td><a href="editarLibro?idLibro=${libro.id }"><i class="fa fa-pencil" aria-hidden="true"></i></a> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:when test="${usuarios != null }">
							<h4>Administración de usuarios</h4>
							<div class="span6">
								<form class="form-inline" method="post" action="buscar" >
									<input class="srchTxt" type="text" name="descripcion" placeholder="Buscar" />
									<input type="hidden" name="objeto" value="usuario">
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
						<c:when test="${descuentos != null }">
							<h4>Administración de usuarios</h4>
							<div class="span6">
								<form class="form-inline" method="post" action="buscar" >
									<input class="srchTxt" type="text" name="descripcion" placeholder="Buscar" />
									<input type="hidden" name="objeto" value="descuento">
									<button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
								</form>
							</div>
							<div class="span2">
								<a class="btn btn-default" href="formularioDescuento" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</a>
								<!-- <button>Agregar</button> -->
							</div>
							<table class="table table-striped">
								<tr>
									<th>Código</th>
									<th>% de Descuento</th>
									<th>Fecha desde</th>
									<th>Fecha hasta</th>
								</tr>
								<c:forEach items="${descuentos }" var="descuento">
									<tr>
										<td>${descuento.getCodigo()}</td>
										<td>${descuento.getPorcDescuento() }</td>
										<td>${descuento.getFechaDesde() }</td>
										<td>${descuento.getFechaHasta() }</td>
										<td><a href="formularioDescuento?idDescuento=${descuento.getId() }"><i class="fa fa-pencil" aria-hidden="true"></i></a> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:when test="${categorias != null }">
							<h4>Administración de categorias</h4>
							<div class="span6">
								<form class="form-inline" method="post" action="buscar" >
									<input class="srchTxt" type="text" name="descripcion" placeholder="Buscar" />
									<input type="hidden" name="objeto" value="categoria">
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
										<td>${categoria.getId() }</td>
										<td>${categoria.getNombre() }</td>
										<td>${categoria.getDescripcion() }</td>
										<td><a href="editarcategoria?idCategoria=${categoria.getId() }"><i class="fa fa-pencil" aria-hidden="true"></i></a> <i class="fa fa-trash-o" aria-hidden="true"></i></td>
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