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
    
    <title>Conferma <c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() eq 'attivo' }"> disabilitazione </c:if> 
    	<c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() ne 'attivo' }"> attivazione </c:if> </title>
  </head>
  <body>
  
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Conferma <c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() eq 'attivo' }"> disabilitazione </c:if> 
    	<c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() ne 'attivo' }"> attivazione </c:if> </h1>
	      <p> <c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() eq 'attivo' }"> <strong>Attenzione:</strong>
	       Disabilitare l'utente staccherà quest'ultimo dai tavoli a cui sta giocando, ma non da quelli di cui è creatore. 
	       L'utente, inoltre, perderà il proprio credito disponibile e l'esperienza accumulata. </c:if> 
    	<c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() ne 'attivo' }"> <strong>Attenzione:</strong>
	       lo stato dell'utente viene impostato a 'creato', in modo da poter assegnare i ruoli col pulsante 'aggiorna'. 
	       Se l'utente era stato precedentemente disabilitato, non recuperà automaticamente né il credito né l'esperienza accumulate fino a 
	       quel momento, né il tavolo a cui stava giocando. </c:if> </p>
	      <p><a class="btn btn-primary btn-lg" 
	        href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/attivazioneEDisabilitazione/OperazioniConfermateServlet?${requestScope.risultatoRicercaUtentePerGet}idUtenteDaAttivareODisabilitare=${requestScope.utenteDaAttivareODisabilitare.idUtente}" role="button">
	          <c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() eq 'attivo' }"> Disabilita </c:if> 
    		  <c:if test="${utenteDaAttivareODisabilitare.statoUtente.toString() ne 'attivo' }"> Attiva </c:if>  
    		  &raquo;
	      </a></p>
	      <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/attivazioneEDisabilitazione/OperazioniNonConfermateServlet?${requestScope.risultatoRicercaUtentePerGet}idUtenteDaAttivareODisabilitare=${requestScope.utenteDaAttivareODisabilitare.idUtente}" role="button">
	          Torna alla pagina dei risultati della ricerca precedente &raquo;
	      </a></p>
	    </div>
	  </div>


	</main>
	
	<jsp:include page="/jsp/generali/footer.jsp" />
  </body>
</html>