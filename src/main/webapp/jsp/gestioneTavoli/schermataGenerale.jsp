<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="../generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione amministrazione</title>
  </head>
  <body>
  
	<jsp:include page="../generali/navbar.jsp"></jsp:include>
  
  
	<main role="main">
	
	
	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h3>Vuoi testare le funzionalità del sito, per quanto riguarda i tavoli, o effettuare una ricerca su di essi?</h3>

	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/PrepareTestTavoliServlet" role="button">
	      	    Testa le funzionalità &raquo;
	      	</a></p>
	      	
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/ricerca/PrepareRicercaTavoliServlet" role="button">
	      	    Effettua una ricerca &raquo;
	      	</a></p>
	      
	
	  	</div>
	  </div>
	</main>
	
	<jsp:include page="../generali/footer.jsp" />
</body>
</html>