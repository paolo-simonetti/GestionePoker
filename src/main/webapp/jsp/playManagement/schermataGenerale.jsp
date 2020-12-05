<!doctype html>
<html lang="it">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    
    <jsp:include page="../generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Play management</title>
  </head>
  <body>
  
	<jsp:include page="../generali/navbar.jsp"></jsp:include>
  
  
	<main role="main">
	
	
	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		    ${successMessage}
		    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		      <span aria-hidden="true">&times;</span>
		    </button>
		  </div>
		  
	      <c:if test="${not empty sessionScope.utenteIdentificato.tavoloDiGioco}">  
	        <h3>Vuoi comprare il credito o tornare all'ultima partita lasciata in sospeso?</h3>
	      </c:if>
	      <c:if test="${empty sessionScope.utenteIdentificato.tavoloDiGioco}">  
	        <h3>Vuoi comprare il credito o cercare un tavolo in cui giocare?</h3>
	      </c:if>

	        
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/credito/PrepareCompraCreditoServlet" role="button">
	      	    Compra credito &raquo;
	      	</a></p>
	      	
	      	<c:if test="${not empty sessionScope.utenteIdentificato.tavoloDiGioco}">
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/partita/RiprendiPartitaServlet" role="button">
	      	    Riprendi la partita lasciata in sospeso &raquo;
	      	</a></p>
	        </c:if>
	        
	        <c:if test="${empty sessionScope.utenteIdentificato.tavoloDiGioco}">
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/ricerca/PrepareRicercaPartitaServlet" role="button">
	      	    Ricerca tavolo in cui giocare &raquo;
	      	</a></p>
	        </c:if>
	
	  	</div>
	  </div>
	</main>
	
	<jsp:include page="../generali/footer.jsp" />
</body>
</html>