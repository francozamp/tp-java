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
				<jsp:include page="plantilla/sidebarusuario.jsp"/>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class="span9">
					<h4>Historial de pedidos</h4>
					<table class="table table-striped">
						<tr>
							<th>Número</th>
							<th>Libros</th>
							<th>Fecha</th>
							<th>Monto</th>
							<th>Estado</th>
							<th></th>
						</tr>
						
						<c:forEach items="${pedidosList }" var="pedido">
						
							<tr>
								<td>${pedido.getId() }</td>
								<td>
									<ul>
										
										<c:forEach items="${pedido.getLineasPedido() }" var="lineaPedido">
										
											<li>${lineaPedido.getCantidad() }x ${lineaPedido.getLibro().getTitulo() }</li>
										
										</c:forEach>
										
									</ul>
								</td>
								<td>${pedido.getFecha() }</td>
								<td>$${pedido.getMontoTotalView() }</td>
								<td>${pedido.getEstado().getNombre() }</td>
								<td><a href="resumenpedido?idPedido=${pedido.getId() }" class="btn btn-primary">Ver</a></td>
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