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
<!-- 					<div class="row"> -->
<!-- 						<form class="form-horizontal span6"> -->
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label alignL">Ordenado por</label>  -->
<!-- 								<select> -->
<!-- 									<option>Alfabético A - Z</option> -->
<!-- 									<option>Alfabético Z - A</option> -->
<!-- 									<option>Precio más bajo</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</form> -->
<!-- 						<div id="myTab" class="pull-right"> -->
<!-- 							<a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>  -->
<!-- 							<a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<br class="clr" />
					
					<c:choose>
						<c:when test="${cantPaginas != 0 }">
							<div class="tab-content">
								<div class="tab-pane" id="listView">
		
									<c:forEach items="${librosPagina }" var="libro">
		
										<div class="row">
											<div class="span2">
												<a href="detalleproducto?idLibro=${libro.getId() }"><img src="${libro.getUrlImagen() }" alt="" /></a>
											</div>
											<div class="span4">
												<a href="detalleproducto?idLibro=${libro.getId() }"><h3>${libro.getTitulo() }</h3></a>
												<hr class="soft" />
												<h5>${libro.getEditorial() }</h5>
												<p>${libro.getDescripcion() }</p>
												<br class="clr" />
											</div>
											<div class="span3 alignR">
												<form class="form-horizontal qtyFrm">
													<h3>$${libro.getPrecioView() }</h3>
													<a class="btn" href="agregarAlCarro?idLibro=${libro.getId() }"> Agregar al <i class=" icon-shopping-cart"></i></a>
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
													<a href="detalleproducto?idLibro=${libro.getId() }"><img src="${libro.getUrlImagen() }" alt=""/></a>
													<div class="caption">
														<a href="detalleproducto?idLibro=${libro.getId() }"><h5>${libro.getTitulo() }</h5></a>
														<p>${libro.getDescripcion() }</p>
														<div class="stars small">
															<form action="">
					 											<c:set var="puntaje" value="${libro.getPuntajePromedio()}"></c:set>
																<c:forEach var="i" begin="1" end="5">
					              									<c:choose>
					              										<c:when test="${6-i-0.5 < puntaje && 6-i+0.5 >= puntaje}">
					              											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star" checked="checked" disabled/>
					              										</c:when>
					              										<c:otherwise>
					              											<input class="star small star-${6-i}" id="star-${6-i}" type="radio" name="star" disabled/>
					              										</c:otherwise>
					              									</c:choose>
					            									<label class="star small star-${6-i}" for="star-${6-i}"></label>
					          									</c:forEach>
					          									<span>${libro.getPuntajePromedio()}</span>
					          								</form>
														</div>
														<h4 style="text-align: center">
															<a class="btn" href="agregarAlCarro?idLibro=${libro.getId() }">Agregar al <i class="icon-shopping-cart"></i></a> 
															<a class="btn btn-primary" href="agregarAlCarro?idLibro=${libro.getId() }">$${libro.getPrecioView() }</a>
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