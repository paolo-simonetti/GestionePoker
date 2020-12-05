<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it">
<head>
	<jsp:include page="/jsp/generali/header.jsp" />
	<title>Compra credito</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
    
   
	<jsp:include page="/jsp/generali/navbar.jsp" />
	
	<main role="main" class="container">
		
		
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Quanto credito vuoi acquistare? </h5> 
		    </div>
		    
		    <div class='card-body'>
     		
     		  <form method="post" 
    		    action="${pageContext.request.contextPath}/accessoEffettuato/playManagement/credito/ExecuteCompraCreditoServlet" 
    		    novalidate="novalidate"
    		  >
				 	    
			  <div class="form-row">
			    <div class="form-group col-md-6">
  			      <label>Credito desiderato <span class="text-danger">*</span></label>
			      <input type="number" name="creditoDesiderato" id="creditoDesiderato" 
			        placeholder="Inserisci il credito desiderato..." class="form-control" required
			      >
			    </div>
			  </div>
											
		  	  <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Acquista</button>
		
			  </form>	    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
	
</body>
</html>