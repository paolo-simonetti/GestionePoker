<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="/jsp/generali/header.jsp" />
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Conferma eliminazione </title>
  </head>
  <body>
  
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Conferma eliminazione del tavolo </h1>
			<h4> Pazzo scatenato, vuoi davvero eliminare questo tavolo? </h4>
	      <p><a class="btn btn-primary btn-lg" 
	        href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/eliminazione/ExecuteDeleteTavoloServlet${requestScope.criteriDiRicerca}idTavoloDaEliminare=${requestScope.idTavoloDaEliminare}" role="button">
	          Yes_chad.jpg &raquo;
	      </a></p>
	      <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/eliminazione/EliminazioneNonConfermataServlet${requestScope.criteriDiRicerca}" role="button">
	          Torna alla pagina dei risultati della ricerca precedente &raquo;
	      </a></p>
	    </div>
	  </div>


	</main>
	
	<jsp:include page="/jsp/generali/footer.jsp" />
  </body>
</html>