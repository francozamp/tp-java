<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<!-- Cabecera -->
<jsp:include page="plantilla/cabecera.jsp" />
<!-- Fin cabecera -->
	<!-- Main body -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="plantilla/sidebargeneral.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<!-- Inicio contenido -->
				<div class="span9">
					<c:choose>
						<c:when test="${categoria != null }">
							<h3>${categoria.getNombre() }<small class="pull-right">${categoria.getLibros().size() } Libros disponibles</small></h3>
							<hr class="soft" />
							<p>${categoria.getDescripcion() }</p>
						</c:when>
						
						<c:otherwise>
							<h3>Resultados </h3>						
						</c:otherwise>
					
					</c:choose>
					
					<hr class="soft" />
					<div class="row">
						<form class="form-horizontal span6">
							<div class="control-group">
								<label class="control-label alignL">Ordenado por</label> 
								<select>
									<option>Alfabético A - Z</option>
									<option>Alfabético Z - A</option>
									<option>Precio más bajo</option>
								</select>
							</div>
						</form>
						<div id="myTab" class="pull-right">
							<a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a> 
							<a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
						</div>
					</div>
					<br class="clr" />
					
					<c:choose>
						<c:when test="${cantPaginas != 0 }">
							<div class="tab-content">
								<div class="tab-pane" id="listView">
		
									<c:forEach items="${librosPagina }" var="libro">
		
										<div class="row">
											<div class="span2">
												<img src="${libro.getUrlImagen() }" alt="" />
											</div>
											<div class="span4">
												<h3>${libro.getTitulo() }</h3>
												<hr class="soft" />
												<h5>${libro.getEditorial() }</h5>
												<p>${libro.getDescripcion() }</p>
												<br class="clr" />
											</div>
											<div class="span3 alignR">
												<form class="form-horizontal qtyFrm">
													<h3>$${libro.getPrecioView() }</h3>
													<a href="product_details.html" class="btn btn-large btn-primary"> Agregar al <i class=" icon-shopping-cart"></i></a>
												</form>
											</div>
										</div>
										<hr class="soft" />
				
									</c:forEach>
				
								</div>
								<div class="tab-pane  active" id="blockView">
									<ul class="thumbnails">
				
										<c:forEach items="${librosPagina }" var="libro">
			
											<li class="span3">
												<div class="thumbnail">
													<a href="product_details.html"><img src="${libro.getUrlImagen() }" alt=""/></a>
													<div class="caption">
														<h5>${libro.getTitulo() }</h5>
														<p>${libro.getDescripcion() }</p>
														<h4 style="text-align: center">
															<a class="btn" href="#">Agregar al <i class="icon-shopping-cart"></i></a> 
															<a class="btn btn-primary" href="#">$${libro.getPrecioView() }</a>
														</h4>
													</div>
												</div>
											</li>
				
										</c:forEach>
										
									</ul>
									<hr class="soft" />
								</div>
							</div>
							<div class="pagination">
								<ul>
									<c:choose>
										<c:when test="${categoria != null }">
											<li><a href="libros?idCategoria=${categoria.getId() }">&lsaquo;</a></li>
									
											<c:set var="inicio" value="${paginaActual-3>0?paginaActual-3:paginaActual-2>0?paginaActual-2:paginaActual-1>0?paginaActual-1:1 }"/>
											<c:set var="fin" value="${paginaActual+3<cantPaginas?paginaActual+3:paginaActual+2<cantPaginas?paginaActual+2:paginaActual+1<cantPaginas?paginaActual+1:paginaActual }"/>
																
											<c:forEach var="pagina" begin="${inicio }" end="${fin }">
												
												<c:choose>
													<c:when test="${pagina eq (paginaActual-3) }">
														<li><a href="#">...</a></li>
													</c:when>
													
													<c:when test="${pagina gt (paginaActual-3) && pagina lt (paginaActual+3) }">
														<li><a href="libros?idCategoria=${categoria.getId() }&page=${pagina }">${pagina }</a></li>
													</c:when>
													
													<c:when test="${pagina eq (paginaActual+3) }">
														<li><a href="#">...</a></li>
													</c:when>
												</c:choose>
												
											</c:forEach>
											
											<li><a href="libros?idCategoria=${categoria.getId() }&page=${cantPaginas }">&rsaquo;</a></li>	
										</c:when>
										
										<c:otherwise>
										
											<li><a href="libros?titulo=${titulo }&idCategoria=${idCategoria }">&lsaquo;</a></li>
									
											<c:set var="inicio" value="${paginaActual-3>0?paginaActual-3:paginaActual-2>0?paginaActual-2:paginaActual-1>0?paginaActual-1:1 }"/>
											<c:set var="fin" value="${paginaActual+3<cantPaginas?paginaActual+3:paginaActual+2<cantPaginas?paginaActual+2:paginaActual+1<cantPaginas?paginaActual+1:paginaActual }"/>
																
											<c:forEach var="pagina" begin="${inicio }" end="${fin }">
												
												<c:choose>
													<c:when test="${pagina eq (paginaActual-3) }">
														<li><a href="#">...</a></li>
													</c:when>
													
													<c:when test="${pagina gt (paginaActual-3) && pagina lt (paginaActual+3) }">
														<li><a href="libros?titulo=${titulo }&idCategoria=${idCategoria }&page=${pagina }">${pagina }</a></li>
													</c:when>
													
													<c:when test="${pagina eq (paginaActual+3) }">
														<li><a href="#">...</a></li>
													</c:when>
												</c:choose>
												
											</c:forEach>
											
											<li><a href="libros?titulo=${titulo }&idCategoria=${idCategoria }&page=${cantPaginas }">&rsaquo;</a></li>
										
										</c:otherwise>
									</c:choose>
									
								</ul>
							</div>
						</c:when>
						<c:otherwise>
						
							<p>No se encontraron libros</p>
						
						</c:otherwise>
					</c:choose>
					
				</div>
				<!-- Fin contenido -->
			</div>
		</div>
	</div>
	<!-- Fin Main Body -->
	<!-- Footer ================================================================== -->
	<jsp:include page="plantilla/footer.jsp" />
	<!-- Fin footer -->
</html>