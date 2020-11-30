<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 	<jsp:include page="/jsp/generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>

	<title>Dettaglio Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
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
		
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Maggiori informazioni sull'utente richiesto</h5> 
		    </div>
		    <div class='card-body'>
					
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Campo</th>
		                        <th>Valore</th>
		                    </tr>
		                </thead>
		                <tbody>
		                  <tr>
		                    <td><c:out value="Id:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.idUtente}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Nome:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.nome}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Cognome:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.cognome}"/></td>
		                  </tr>
		                  
		                  <tr>
		                    <td><c:out value="Username:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.username}"/></td>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Data di registrazione:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.dataRegistrazione}"/></td>
		                  </tr>

		                  <tr >
		                    <td><c:out value="Stato utente:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.statoUtente}" /></td>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Credito disponibile:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.creditoDisponibile}" /></td>
		                  </tr>

		                  <tr >
		                    <td><c:out value="Esperienza accumulata:"/></td>
		                    <td><c:out value="${requestScope.utenteResult.esperienzaAccumulata}"/></td>
		                  </tr>

		                  <tr >
		                    <td><c:out value="Ruoli assegnati:"/></td>
		                    <c:forEach items="${requestScope.utenteResult.ruoli}" var="ruolo">
		                      <td><c:out value="${ruolo.descrizioneRuolo}"/></td>
		                    </c:forEach>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Tavoli creati:"/></td>
		                    <c:forEach items="${requestScope.utenteResult.tavoliCreati}" var="tavoloCreato">
		                      <td><c:out value="${tavoloCreato.idTavolo} ${tavoloCreato.denominazione}"/></td>
		                    </c:forEach>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Tavolo di gioco:"/></td>
		                    <c:forEach items="${requestScope.utenteResult.tavoloDiGioco}" var="tavoloDiGioco">
		                      <td><c:out value="id=${tavolo.idTavolo} denominazione=${tavoloDiGioco.denominazione}"/></td>
		                    </c:forEach>
		                  </tr>

		              </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	
<jsp:include page="/jsp/generali/footer.jsp" />

</body>
</html>