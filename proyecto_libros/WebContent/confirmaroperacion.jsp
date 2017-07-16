<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<h3>Confirmar datos</h3>	
					<div class="well">
						<h4>Pedido</h4>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Título</th>
									<th>Edición</th>
									<th>Editorial</th>
									<th>Precio</th>
									<th>Cantidad</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sessionScope.pedido.getLineasPedido() }" var="lineaPedido">
									<tr>
										<td>${lineaPedido.getLibro().getTitulo() }</td>
										<td>${lineaPedido.getLibro().getEditorial() }</td>
										<td>${lineaPedido.getLibro().getEdicion() }</td>
										<td>$${lineaPedido.getLibro().getPrecioView() }</td>
										<td>${lineaPedido.getCantidad()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<hr class="soft"/>
						<h4>Pago</h4>
						<p>Paga con tarjeta de crédito terminada en <strong>${nroTarjeta }</strong></p>
						<hr class="soft"/>
						<h4>Dirección de envío</h4>
						<p><strong>Dirección:</strong> ${direccion }</p>
						<p><strong>Localidad:</strong> ${localidad }</p>
						<p><strong>Código postal:</strong> ${codPostal }</p>
						<p><strong>Provincia:</strong> ${provincia }</p>
						<hr class="soft"/>
						<h4>Monto</h4>
						<p class="monto">
							Total libros: $${sessionScope.pedido.getMontoTotalView() }<br>
							<c:choose>
								<c:when test="${sessionScope.descuento != null}">
									<c:set var = "montoDescontado" scope = "session" value = "${sessionScope.pedido.getMontoTotalView()*sessionScope.descuento.getPorcDescuento()}"></c:set>
									<fmt:formatNumber var="formatMontoDescontado" type = "number" maxFractionDigits = "2" value = "${montoDescontado}" />
								</c:when>
								<c:otherwise>
									<c:set var = "formatMontoDescontado" scope = "session" value = "0.00"></c:set>
								</c:otherwise>
							</c:choose>	
							Descuento: $<c:out value="${formatMontoDescontado}" /><br>
							<span class="total"><strong>Precio final: $${sessionScope.pedido.getMontoTotalView() - formatMontoDescontado}</strong></span>
						</p>
						<hr class="soft"/>
						<a href="cancelarpedido"><button class="btn btn-large">Cancelar </button></a>
						<a href="resultadooperacion"><button class="btn btn-large btn-success pull-right">Pagar </button></a>
					</div>
					<!--
					<div class="alert alert-info fade in">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
					</div>
					<div class="alert fade in">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
					</div>
					<div class="alert alert-block alert-error fade in">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
					</div> -->
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