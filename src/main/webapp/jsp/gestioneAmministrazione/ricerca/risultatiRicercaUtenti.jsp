<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Risultati della ricerca dell'utente</title>
	
	 <jsp:include page="/jsp/generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
</head>
<body>
	
	<jsp:include page="/jsp/generali/navbar.jsp"></jsp:include>
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
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
		        <h5>Lista dei risultati</h5> 
		    </div>
		    	<div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id Utente</th>
		                        <th>Nome</th>
		                        <th>Username</th>
		                        <th>Data di creazione</th>
								<th>Stato Utente</th>
								<th>Ruolo</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                  	<c:forEach items="${requestScope.listaUtenti}" var="item">
		                	  <tr >
		                        <td><c:out value="${item.idUtente}"></c:out></td>
		                        <td><c:out value="${item.nome}"></c:out></td>
		                        <td><c:out value="${item.cognome}"></c:out></td>
		                        <td><c:out value="${item.dataRegistrazione}"></c:out></td>
		                        <td><c:out value="${item.statoUtente}"></c:out></td>
		                        <td><c:forEach items="${item.ruoli}" var="ruolo">
		                          <c:out value="${ruolo}"/> 
		                        </c:forEach></td>
		                     
	               				<td>
								  <a class="btn  btn-sm btn-outline-secondary" 
								  	href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/visualizzazioneUtente/VisualizzazioneUtenteServlet?${requestScope.risultatoRicercaUtentePerGet}idUtenteDaVisualizzare=${item.idUtente}">
									Visualizza utente
								  </a>
								  <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" 
								    href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/aggiornamentoUtente/PrepareUpdateUtenteServlet?${requestScope.risultatoRicercaUtentePerGet}idUtenteDaAggiornare=${item.idUtente}">
								    Aggiorna utente
								  </a>
								  <c:if test="${item.statoUtente.toString() ne 'creato'}">
								  <a class="btn btn-outline-danger btn-sm" 
								    href="${pageContext.request.contextPath}/accessoEffettuato/gestioneAmministrazione/attivazioneEDisabilitazione/PrepareOperazioniServlet?${requestScope.risultatoRicercaUtentePerGet}idUtenteDaAttivareODisabilitare=${item.idUtente}">
								    <c:if test="${item.statoUtente.toString() eq 'attivo'}"> <c:out value="Disabilita"/> </c:if> <c:if test="${item.statoUtente.toString() eq 'disabilitato'}"> <c:out value="Reimposta a 'creato'"/> </c:if> utente
								  </a>
								  </c:if>
								
							  	</td>
		                      </tr>
		                	</c:forEach>		                   
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
			
	
	
	
	
	
	
	<!-- end container -->	 
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
</body>
</html>