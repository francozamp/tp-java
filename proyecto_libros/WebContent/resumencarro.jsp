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
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div id="contenido" class=span9>
					<!-- <ul class="breadcrumb">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li class="active"> SHOPPING CART</li>
					</ul> -->
					<h3>  Carrito de compras [${pedido.getCantLibros() } Libro(s)]<a href="products.html" class="btn btn-large pull-right"><i class="icon-arrow-left"></i> Continuar comprando </a></h3>	
					<hr class="soft"/>
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
									<td>
										<div class="input-append"><input class="span1" style="max-width:34px" placeholder="0" id="appendedInputButtons" size="16" type="text" value="${lineaPedido.getCantidad() }"><button class="btn" type="button"><i class="icon-minus"></i></button><button class="btn" type="button"><i class="icon-plus"></i></button><button class="btn btn-danger" type="button"><i class="icon-remove icon-white"></i></button>				</div>
									</td>
									<td>$ ${lineaPedido.getLibro().getPrecioView() }</td>
									<td>$ ${lineaPedido.getMontoLineaView() } </td>
								</tr>
							</c:forEach>

							<tr>
								<td colspan="6" style="text-align:right">Precio Total:	</td>
								<td> $ ${pedido.getMontoTotalView() }</td>
							</tr>
							<tr>
								<td colspan="6" style="text-align:right">Descuento Total:	</td>
								<td> $ 0.00</td>
							</tr>
							<tr>
								<td colspan="6" style="text-align:right"><strong>TOTAL ($ ${pedido.getMontoTotalView() } - $ 0) =</strong></td>
								<td class="label label-important" style="display:block"> <strong> $ ${pedido.getMontoTotalView() } </strong></td>
							</tr>
						</tbody>
					</table>


					<table class="table table-bordered">
						<tbody>
							<tr>
								<td> 
									<form class="form-horizontal">
										<div class="control-group">
											<label class="control-label"><strong> Codigo de descuento: </strong> </label>
											<div class="controls">
												<input type="text" class="input-medium" placeholder="Codigo">
												<button type="submit" class="btn"> Aplicar </button>
											</div>
										</div>
									</form>
								</td>
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
					<a href="products.html" class="btn btn-large"><i class="icon-arrow-left"></i> Continuar comprando </a>
					<a href="login.html" class="btn btn-large pull-right">Comprar <i class="icon-arrow-right"></i></a>
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