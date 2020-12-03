<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it">
<head>
	<title>Ricerca tavolo</title>
	
	<jsp:include page="/jsp/generali/header.jsp" />
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
	
	<main role="main" class="container">
	
		
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
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci campi di ricerca</h5> 
		    </div>
		    <div class='card-body'>

			  <form method="post" 
				action="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/ricerca/ExecuteRicercaTavoliServlet" novalidate="novalidate">
					
				<div class="form-row">
							
				  <div class="form-group col-md-6">
				    <label>Denominazione</label>
					<input type="text" name="denominazione" id="denominazione" class="form-control">
				  </div>
				  <div class="form-group col-md-3">
				    <label>Data creazione</label>
					<input type="date" class="form-control" name="dataCreazione" id="dataCreazione">
				  </div>		
							
				</div>
						
				<div class="form-row">
				  <div class="form-group col-md-6">
				    <label>Esperienza minima richiesta</label>
				    <input type="number" name="esperienzaMinimaRichiesta" id="esperienzaMinimaRichiesta" class="form-control">
				  </div>	
				  <div class="form-group col-md-3">
				    <label>Puntata minima</label> 
				    <input type="number" class="form-control" name="puntataMinima" id="puntataMinima">
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