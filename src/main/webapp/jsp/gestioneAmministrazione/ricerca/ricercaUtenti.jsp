<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it">
<head>
	<title>Ricerca utente</title>
	
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
				action="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/ricerca/ExecuteRicercaUtentiServlet" novalidate="novalidate">
					
				<div class="form-row">
							
				  <div class="form-group col-md-6">
				    <label>Nome dell'utente</label>
					<input type="text" name="nome" id="nome" class="form-control">
				  </div>
				  <div class="form-group col-md-3">
				    <label>Cognome dell'utente</label>
					<input type="text" class="form-control" name="cognome" id="cognome">
				  </div>		
							
				</div>
						
				<div class="form-row">
				  <div class="form-group col-md-6">
				    <label>Username</label>
				    <input type="text" name="username" id="username" class="form-control">
				  </div>	
				  <div class="form-group col-md-3">
				    <label>Data di creazione dell'account</label> 
				    <input type="date" class="form-control" name="dataRegistrazione" id="dataRegistrazione">
				  </div>		 
				</div>
						
				<div class="form-row">
				  <div class="form-group">
  				  <label for="selectRuolo">Ruolo:</label>
  				  <select class="form-control" id="selectRuolo" name="descrizioneRuolo">
     			    <c:forEach items="${requestScope.listaRuoli}" var="ruoloDisponibile">
    				  <option value="${ruoloDisponibile}">
    					${ruoloDisponibile}
    				  </option>
    				</c:forEach>
  				  </select>
				  </div>
					      
				  <div class="form-group">
  				  <label for="selectStato">Stato utente:</label>
  				  <select class="form-control" id="selectStato" name="statoUtente">
     			    <c:forEach items="${requestScope.listaStati}" var="statoPossibile">
    			    <option value="${statoPossibile}">
    			      ${statoPossibile}
    			    </option>
    			    </c:forEach>
  				  </select>
				  </div>
				  
				  
				  <div class="form-group">
  				  <label for="selectTavolo">Tavolo:</label>
  				  <select class="form-control" id="selectTavolo" name="tavolo">
     			    <c:forEach items="${requestScope.listaTavoli}" var="tavoloDisponibile">
    			    <option value="${tavoloDisponibile.idTavolo}" disabled>
    			      ${tavoloDisponibile}
    			    </option>
    			    </c:forEach>
  				  </select>
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