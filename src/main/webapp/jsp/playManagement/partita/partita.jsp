<!doctype html>
<html lang="it">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    
    <jsp:include page="/jsp/generali/header.jsp" />
    
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
  
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
  
  
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
		  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		    ${errorMessage}
		    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		      <span aria-hidden="true">&times;</span>
		    </button>
		  </div>
		 	<h3> You're playing now!</h3>
	        
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/partita/GiocaPartitaServlet?idTavoloDiGioco=${requestScope.idTavoloDiGioco}" role="button">
	      	    Gioca &raquo;
	      	</a></p>
	      	
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/partita/AbbandonaPartitaServlet" role="button">
	      	    Abbandona la partita &raquo;
	      	</a></p>
	     
	
	  	</div>
	  </div>
	</main>
	
	<jsp:include page="/jsp/generali/footer.jsp" />
</body>
</html>