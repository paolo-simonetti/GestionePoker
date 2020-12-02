<!doctype html>
<html lang="it">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    
    <jsp:include page="/jsp/generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione amministrazione</title>
  </head>
  <body>
  
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
  
  
	<main role="main">
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  <table class='table table-striped ' >
		  	<thead>
		  		<tr><th> Ricerca non riuscita: </th></tr>
		  	</thead>
		  	<tbody>
		  	<c:forEach items="${errorMessage}" var="item">
		  		<tr><td> ${item} </td></tr>
		  	</c:forEach>
		  	</tbody>
		  </table>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	
	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	    
	    <%--  trovaTuttiTramiteDenominazione - findAllByDenominazione --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/TestTavoloServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByDenominazione">
			</div>
			<input type="hidden" name="codop" value="findAllByDenominazione">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
		
		<%-- trovaTuttiTramiteCreatore - findAllByCreatore --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/TestTavoloServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByCreatore: username">
			</div>
			<input type="hidden" name="codop" value="findAllByCreatore">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteIdConCreatore - findByIdWithCreatore --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/TestTavoloServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithCreatore">
			</div>
			<input type="hidden" name="codop" value="findByIdWithCreatore">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteIdConGiocatori - findByIdWithGiocatori --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/TestTavoloServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithGiocatori">
			</div>
			<input type="hidden" name="codop" value="findByIdWithGiocatori">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    
	    </div>
	  </div>
	  
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
</body>
</html>