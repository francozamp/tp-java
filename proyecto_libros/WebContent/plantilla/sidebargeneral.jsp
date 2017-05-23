<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="sidebar" class="span3">
	<div class="well well-small"><a id="myCart" href="product_summary.html"><img src="themes/images/ico-cart.png" alt="cart">3 Items en tu carrito <span class="badge badge-warning pull-right">$155.00</span></a></div>
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