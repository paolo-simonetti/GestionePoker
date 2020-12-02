<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it">
<head>
	<jsp:include page="/jsp/generali/header.jsp" />
	<title>Aggiornamento utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/jsp/generali/navbar.jsp" />
	
	<main role="main" class="container">
	
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': 
		''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Aggiorna i dati dell'utente </h5> 
		    </div>
		    <div class='card-body'>
		    <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		    		<form method="post" 
		    		  action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/aggiornamentoUtente/ExecuteUpdateUtenteServlet" 
		    		  novalidate="novalidate"
		    		>
				 	    <div class="form-group col-md-6">
						  <label></label>
						  <input type="hidden" name="risultatoRicercaUtente" id="risultatoRicercaUtente" 
						    value="${requestScope.risultatoRicercaUtente}" class="form-control"
						  >
				  		  <input type="hidden" name="idUtenteDaAggiornare" id="idUtenteDaAggiornare" 
				  		    value="${requestScope.utenteDaAggiornare.idUtente}" class="form-control"
				  		  >
				  		</div>
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" 
								  value="${requestScope.utenteDaAggiornare.nome}" class="form-control" required
								>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome<span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" 
								  value="${requestScope.utenteDaAggiornare.cognome}" class="form-control" required
								>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Data di registrazione<span class="text-danger">*</span></label>
								<input type="date" class="form-control" name="dataRegistrazione" 
								  value="${requestScope.utenteDaAggiornare.dataRegistrazione}" id="dataRegistrazione" required
								>
							</div>		

							<div class="form-group col-md-6">
								<label>Username<span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" 
								  value="${requestScope.utenteDaAggiornare.username}" class="form-control" required
								>
							</div>

						</div>
						<c:if test="${requestScope.utenteDaAggiornare.statoUtente.toString() ne 'creato'}">
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Esperienza accumulata</label>
								<input type="text" class="form-control" name="esperienzaAccumulata" 
								  value="${requestScope.utenteDaAggiornare.esperienzaAccumulata}" id="esperienzaAccumulata" required
								>
							</div>		

							<div class="form-group col-md-6">
								<label>Credito disponibile</label>
								<input type="text" name="creditoDisponibile" id="creditoDisponibile" 
								  value="${requestScope.utenteDaAggiornare.creditoDisponibile}" class="form-control" required
								>
							</div>
							
							<div class="form-group col-md-6">
							  <label for="selectTavoli">Tavoli creati: </label>
  					      	  <select class="form-control" id="selectTavoli" name="selectTavoli" multiple>
     					        <c:forEach items="${requestScope.utenteDaAggiornare.tavoliCreati}" var="tavolo"> 
    					    	  <option value="${tavolo.idTavolo}">
    					    	    id= ${tavolo.idTavolo}, denominazione= ${tavolo.denominazione} 
    					    	  </option>
    					    	</c:forEach>
  					      	  </select>
							</div>

							<div class="form-group col-md-3">
								<label>Tavolo di gioco</label>
								<input type="text" class="form-control" name="tavoloDiGioco" 
								  value="${requestScope.utenteDaAggiornare.tavoloDiGioco.idTavolo}" id="tavoloDiGioco" required
								>
							</div>	

						</div>
						</c:if>
						
						<div class="form-row">	
							<c:if test="${not empty requestScope.listaRuoli}">	

							<div class="form-group col-md-6">
							  <label for="selectRuoli">Ruoli: </label>
  					      	  <select class="form-control" id="selectRuoli" name="selectRuoli" multiple>
     					        <c:forEach items="${requestScope.listaRuoli}" var="ruolo"> 
    					    	  <option value="${ruolo}">
    					    	    ${ruolo}
    					    	  </option>
    					    	</c:forEach>
  					      	  </select>
							</div>
							</c:if>

								


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