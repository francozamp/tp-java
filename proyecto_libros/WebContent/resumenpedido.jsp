<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<!-- Cabecera -->
<jsp:include page="plantilla/cabecera.jsp"/>
<!-- Fin cabecera -->
	<!-- Main body -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<c:choose>
					<c:when test="${sessionScope.usuario.getTipoUsuario().getId() == 1 && editarPedido }">
						<jsp:include page="plantilla/sidebaradministracion.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<jsp:include page="plantilla/sidebarusuario.jsp"></jsp:include>
					</c:otherwise>
				</c:choose>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<h3>Pedido #${pedido.getId() }</h3>	
					<hr class="soft"/>
					<h4>Fecha Pedido: <span class="dato">${pedido.getFecha() }</span></h4>
					<h4>Estado: <span class="dato">${pedido.getEstado().getNombre() }</span></h4>
					<c:if test="${pedido.getSeguimiento() != null && pedido.getSeguimiento() !='' }">
						<h4>Número de seguimiento: <span class="dato">${pedido.getSeguimiento() }</span></h4>
					</c:if>
					<hr class="soft"/>
					<h4>Libros [${pedido.getCantLibros() } Libro(s)]: </h4>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th></th>
								<th>Libro</th>
								<th>Cantidad</th>
								<th>Precio</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pedido.getLineasPedido() }" var="lineaPedido">
								<tr>
									<td> <img width="60" src="${lineaPedido.getLibro().getUrlImagen() }" alt=""/></td>
									<td><strong>${lineaPedido.getLibro().getTitulo() } </strong><br/>${lineaPedido.getLibro().getEditorial() }<br/>${lineaPedido.getLibro().getEdicion() }</td>
									<td>${lineaPedido.getCantidad() }</td>
									<td>$ ${lineaPedido.getLibro().getPrecioView() }</td>
									<td>$ ${lineaPedido.getMontoLineaView() } </td>
								</tr>
							</c:forEach>

							<tr>
								<td colspan="4" style="text-align:right">Precio Total:	</td>
								<td> $ ${pedido.getMontoTotalView() }</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:right">Descuento Total:	</td>
								<td> $ 0.00</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:right"><strong>TOTAL ($ ${pedido.getMontoTotalView() } - $ 0) =</strong></td>
								<td class="label label-important" style="display:block"> <strong> $ ${pedido.getMontoTotalView() } </strong></td>
							</tr>
						</tbody>
					</table>
	
	
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td> 
									<div class="control-group form-horizontal">
										<label class="control-label"><strong> Codigo de descuento: </strong> </label>
										<div class="controls">
											<input type="text" class="input-medium" placeholder="Codigo">
										</div>
									</div>
								</td>
							</tr>

						</tbody>
					</table>
						
					<c:if test="${sessionScope.usuario.getTipoUsuario().getId() == 1 && editarPedido }">
						<div>
							<form class="form-inline" action="actualizarpedido" method="post">
								<label>Estado:
									<select name="idEstado">
										<c:forEach items="${estadosList }" var="estado">
											<option value="${estado.getID() }">${estado.getNombre() }</option>
										</c:forEach>
									</select>
								</label>
								
								<c:choose>
									<c:when test="${pedido.getSeguimiento() != null && pedido.getSeguimiento() != '' }">
										<label>Código de seguimiento <input type="text" name="seguimiento" value="${pedido.getSeguimiento() }" disabled></label>
									</c:when>
									<c:otherwise>
										<label>Código de seguimiento <input type="text" name="seguimiento" placeholder="Código de seguimiento"></label>
									</c:otherwise>
								</c:choose>
								
								<input type="hidden" name="idPedido" value="${pedido.getId() }">
								<input type="submit" class="btn btn-primary" value="Guardar">
							</form>
						</div>
					</c:if>
	
					<!-- <table class="table table-bordered">
						<tr><th>ESTIMATE YOUR SHIPPING </th></tr>
						<tr> 
							<td>
								<form class="form-horizontal">
									<div class="control-group">
										<label class="control-label" for="inputCountry">Country </label>
										<div class="controls">
											<input type="text" id="inputCountry" placeholder="Country">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputPost">Post Code/ Zipcode </label>
										<div class="controls">
											<input type="text" id="inputPost" placeholder="Postcode">
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">ESTIMATE </button>
										</div>
									</div>
								</form>				  
							</td>
						</tr>
					</table>	 -->
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