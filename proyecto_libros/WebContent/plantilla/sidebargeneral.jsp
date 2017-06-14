<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="sidebar" class="span3">
	<div class="well well-small">		
		<c:choose>
			<c:when test="${sessionScope.carro != null && sessionScope.carro.getCantLibros() > 0 }">
				<a id="myCart" href="verCarro">
					<img src="themes/images/ico-cart.png" alt="cart"> <c:out value="${sessionScope.carro.getCantLibros() }"/> Libros en tu carrito<span class="badge badge-warning pull-right"><c:out value="$ ${sessionScope.carro.getMontoTotalView() }"/> </span>
				</a>
			</c:when>
			<c:otherwise>
				<img src="themes/images/ico-cart.png" alt="cart"> No hay libros en tu carrito
			</c:otherwise>
		</c:choose>	
	</div>
	<ul id="sideManu" class="nav nav-tabs nav-stacked">
		<li class="subMenu open"><a> Categorías</a>
			<ul>
				<c:forEach items="${categorias }" var="categoria">
					<li><a href="libros?categoria=${categoria.id }"><i class="icon-chevron-right"></i>${categoria.nombre }</a></li>
				</c:forEach>
			</ul>
		</li>
	</ul>
</div>