<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it">
<head>
	<jsp:include page="/jsp/generali/header.jsp" />
	<title>Inserisci nuovo tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/jsp/generali/navbar.jsp" />
	
	<main role="main" class="container">
	
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': 
		''}" role="alert">
		 <table class='table table-striped ' >
		  	<thead>
		  	<tr><th> Operazione non riuscita: </th></tr>
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
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuovo tavolo </h5> 
		    </div>
		    <div class='card-body'>
		    <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		    		<form method="post" 
		    		  action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/inserimento/ExecuteInsertTavoloServlet" 
		    		  novalidate="novalidate"
		    		>
				 	    
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Denominazione <span class="text-danger">*</span></label>
								<input type="text" name="denominazione" id="denominazione" 
								  placeholder="Inserisci la denominazione..." class="form-control" required
								>
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Esperienza minima richiesta<span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="esperienzaMinimaRichiesta" 
								  id="esperienzaMinimaRichiesta" placeholder="Inserisci l'esperienza minima richiesta..."
								  required>
							</div>		

							<div class="form-group col-md-6">
								<label>Puntata minima<span class="text-danger">*</span></label>
								<input type="number" name="puntataMinima" id="puntataMinima" 
								  placeholder="Inserisci la puntata minima richiesta..." class="form-control" 
								  required>
							</div>

						</div>						
																		
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
	
</body>
</html>