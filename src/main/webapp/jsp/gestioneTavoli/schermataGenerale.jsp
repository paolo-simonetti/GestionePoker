<!doctype html>
<html lang="it">
  <head>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	  <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
	  </div>
	
	  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  <table class='table table-striped ' >
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
	      <h3>Vuoi testare le funzionalità del sito, per quanto riguarda i tavoli, effettuare una ricerca su di essi, o inserirne uno nuovo?</h3>

	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/test/PrepareTestTavoliServlet" role="button">
	      	    Testa le funzionalità &raquo;
	      	</a></p>
	      	
	      	<c:if test="${sessionScope.haCreatoTavoli eq 'true'}">
	          <p><a class="btn btn-primary btn-lg" 
	      	    href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/ricerca/PrepareRicercaTavoliServlet" role="button">
	      	      Effettua una ricerca &raquo;
	      	  </a></p>
	      	</c:if>
	      	<p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/inserimento/PrepareInsertTavoliServlet" role="button">
	      	    Inserisci nuovo &raquo;
	      	</a></p>
	
	  	</div>
	  </div>
	</main>
	
	<jsp:include page="../generali/footer.jsp" />
</body>
</html>