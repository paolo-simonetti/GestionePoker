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
	    
	    <%-- trovaTuttiTramiteRuoli - findAllByRuoli --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByRuoli">
			</div>
			<input type="hidden" name="codop" value="findAllByRuoli">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
		
		<%-- trovaTuttiTramiteStatoUtente - findAllByStatoUtente --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByStatoUtente">
			</div>
			<input type="hidden" name="codop" value="findAllByStatoUtente">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTuttiTramiteTavoloDiGioco - findAllByTavoloDiGioco --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByTavoloDiGioco -> denominazione">
				<input
					type="text" class="form-control" id="queryInput" name="queryInputEsperienza"
					placeholder="esperienza minima richiesta">
				<input
					type="text" class="form-control" id="queryInput" name="queryInputPuntata"
					placeholder="puntata minima">
				<input
					type="text" class="form-control" id="queryInput" name="queryInputCreatore"
					placeholder="username del creatore">
			</div>
			<input type="hidden" name="codop" value="findAllByTavoloDiGioco">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteUsername - findByUsername --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByUsername">
			</div>
			<input type="hidden" name="codop" value="findByUsername">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTuttiTramiteNomeECognome - findAllByNomeAndCognome --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findAllByNomeAndCognome">
				<input
					type="text" class="form-control" id="queryInput" name="queryInputCognome"
					placeholder="cognome">
			</div>
			<input type="hidden" name="codop" value="findAllByNomeAndCognome">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteTavoliCreati_IdTavolo - findByTavoliCreati_IdTavolo --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByTavoliCreati_IdTavolo">
			</div>
			<input type="hidden" name="codop" value="findByTavoliCreati_IdTavolo">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteIdConRuoli - findByIdWithRuoli --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithRuoli">
			</div>
			<input type="hidden" name="codop" value="findByIdWithRuoli">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
		
		<%-- trovaTramiteIdConTavoloDiGioco - findByIdWithTavoloDiGioco --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithTavoloDiGioco">
			</div>
			<input type="hidden" name="codop" value="findByIdWithTavoloDiGioco">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
		
		<%-- trovaTramiteIdConTavoliCreati - findByIdWithTavoliCreati --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithTavoliCreati">
			</div>
			<input type="hidden" name="codop" value="findByIdWithTavoliCreati">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
	    
	    <%-- trovaTramiteIdConInformazioniComplete - findByIdWithCompleteInfo --%>
		<form class="form-inline" action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet" method="post">
			<div class="form-group mx-sm-3 mb-2">
				<input
					type="text" class="form-control" id="queryInput" name="queryInput"
					placeholder="findByIdWithCompleteInfo">
			</div>
			<input type="hidden" name="codop" value="findByIdWithCompleteInfo">
			<button type="submit" class="btn btn-primary mb-2">Esegui Test!</button>
		</form>
		
	    </div>
	  </div>
	  
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
</body>
</html>