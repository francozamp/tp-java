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
					<!-- <ul class="breadcrumb">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li class="active"> SHOPPING CART</li>
					</ul> -->
					<h3>  Carrito de compras [${sessionScope.pedido.getCantLibros() } Libro(s)]<a href="index" class="btn btn-large pull-right"><i class="icon-arrow-left"></i> Continuar comprando </a></h3>	
					<hr class="soft"/>
					<c:if test="${errores != null}">
						<p style="color:red;">${errores}</p>
					</c:if>
					<form method="post" action="aplicarDescuento" name="frmDescuento" id="frmDescuento">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td> 
										<div class="control-group form-horizontal">
											<label class="control-label"><strong> Codigo de descuento: </strong> </label>
											<div class="controls">
												<c:choose>
													<c:when test="${sessionScope.descuento != null}">
														<input type="text" class="input-medium" placeholder="Codigo" name="codigoDescuento" id="codigoDescuento" value="${sessionScope.descuento.getCodigo() }">
													</c:when>
													<c:otherwise>
														<input type="text" class="input-medium" placeholder="Codigo" name="codigoDescuento" id="codigoDescuento">
													</c:otherwise>
												</c:choose>
												<button type="submit" class="btn"> Aplicar </button>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>

					<form action="pagoyenvio" method="post">
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
								<c:forEach items="${sessionScope.pedido.getLineasPedido() }" var="lineaPedido">
									<tr>
										<td> <img width="60" src="${lineaPedido.getLibro().getUrlImagen() }" alt=""/></td>
										<td><strong>${lineaPedido.getLibro().getTitulo() } </strong><br/>${lineaPedido.getLibro().getEditorial() }<br/>${lineaPedido.getLibro().getEdicion() }</td>
										<td>
											<div class="input-append">
												<input class="cantidadLibros" style="max-width:34px" size="16" name="cant${lineaPedido.getLibro().getId()}" type="text" value="${lineaPedido.getCantidad() }">
												<button class="btn restar" type="button"><i class="icon-minus"></i></button>
												<button class="btn sumar" type="button"><i class="icon-plus"></i></button>
												<button class="btn btn-danger borrar" type="button"><i class="icon-remove icon-white"></i></button>
											</div>
										</td>
										<td>$ ${lineaPedido.getLibro().getPrecioView() }</td>
										<td>$ ${lineaPedido.getMontoLineaView() } </td>
									</tr>
								</c:forEach>
	
								<tr>
									<td colspan="4" style="text-align:right">Precio Total:	</td>
									<td> $ ${sessionScope.pedido.getMontoTotalView() }</td>
								</tr>
								<tr>
									<td colspan="4" style="text-align:right">Descuento Total:	</td>
									<c:choose>
										<c:when test="${sessionScope.descuento != null }">
											<c:set var = "montoDescontado" scope = "session" value = "${sessionScope.pedido.getMontoTotalView()*sessionScope.descuento.getPorcDescuento()}"></c:set>
											<fmt:formatNumber var="formatMontoDescontado" type = "number" maxFractionDigits = "2" value = "${montoDescontado}" />
											<td> $ - <c:out value="${formatMontoDescontado}" /></td>
										</c:when>
										<c:otherwise>
										<c:set var = "formatMontoDescontado" scope = "session" value = "0.00"></c:set>
											<td> $ <c:out value="${formatMontoDescontado}" /></td>
										</c:otherwise>
									</c:choose>
									
								</tr>
								<tr>
									<td colspan="4" style="text-align:right"><strong>TOTAL ($ ${sessionScope.pedido.getMontoTotalView() } - $ <c:out value="${formatMontoDescontado}" />) =</strong></td>
									<td class="label label-important" style="display:block"> <strong> $ ${sessionScope.pedido.getMontoTotalView() - formatMontoDescontado} </strong></td>
								</tr>
							</tbody>
						</table>
	
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
						<a href="index" class="btn btn-large"><i class="icon-arrow-left"></i> Continuar comprando </a>
						<c:choose>
							<c:when test="${sessionScope.usuario != null }">
								<button type="submit" class="btn btn-large pull-right">Comprar <i class="icon-arrow-right"></i></button>
							</c:when>
							<c:otherwise>
	<!-- 							<a href="#login" role="button" data-toggle="modal" class="btn btn-large pull-right">Comprar <i class="icon-arrow-right"></i></a> -->
								<a href="#login" role="button" data-toggle="modal" class="pull-right"><span class="btn btn-large btn-success">Iniciar Sesi�n</span></a>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
		$('.restar').on('click', function() {		
			var cant = $('.cantidadLibros').val();
			if (cant > 0) {
				$('.cantidadLibros').val(--cant)
			}
			console.log(cant);
		})
		$('.sumar').on('click', function() {		
			var cant = $('.cantidadLibros').val();

			$('.cantidadLibros').val(++cant)

			console.log(cant);
		})
		
	</script>
	<!-- Fin Main Body -->
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp"/>
	<!-- Fin footer -->
</html>