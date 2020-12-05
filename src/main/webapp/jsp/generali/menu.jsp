<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="it">
  <head>
    
    <jsp:include page="header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Pokerone online!</title>
  </head>
  <body>
  
	<jsp:include page="navbar.jsp"></jsp:include>
  
  
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
		
	      <h1 class="display-3">Benvenuto nel fantastico casinò di Pagliare del Tronto!</h1>
	      <h3>Cosa vuoi fare oggi?</h3>
	      <c:if test="${sessionScope.isAdmin eq 'true'}">
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/SchermataGeneraleGestioneAmministrazioneServlet" role="button">
	      	    Vai alla gestione degli utenti &raquo;</a></p>
	      </c:if>
	      <c:if test="${sessionScope.isAdmin eq 'true' or sessionScope.isSpecialPlayer eq 'true'}">
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/SchermataGeneraleGestioneTavoliServlet" role="button">
	      	    Vai alla gestione dei tavoli &raquo;</a></p>
	      </c:if>
	      
	      <c:if test="${sessionScope.isAdmin eq 'true' or sessionScope.isSpecialPlayer eq 'true' or sessionScope.isPlayer eq 'true'}">
	        <p><a class="btn btn-primary btn-lg" 
	      	  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/SchermataGeneralePlayManagementServlet" role="button">
	      	    Vai al play management &raquo;</a></p>
	      </c:if>
	    </div>
	  </div>
	  
	  <div class="container">
	    <!-- Example row of columns -->
	    <div class="row">
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	    </div>
	
	    <hr>
	
	  </div> <!-- /container -->
	
	</main>
	
	<jsp:include page="footer.jsp" />
  </body>
</html>