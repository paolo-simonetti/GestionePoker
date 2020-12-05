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

	<title>Dettaglio Tavolo</title>
	
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
		        <h5>Maggiori informazioni sul tavolo richiesto</h5> 
		    </div>
		    <div class='card-body'>
				<form method="get">
				<input type="hidden" name="criteriDiRicerca" value="${requestScope.criteriDiRicerca}">
				<a class="btn  btn-sm btn-outline-danger" 
				  	href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/visualizzazioneTavolo/TornaAiRisultatiServlet${requestScope.criteriDiRicerca}">
					Torna ai risultati della ricerca
				</a>
				</form>
				
		    	
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
		                    <td><c:out value="${requestScope.tavoloResult.idTavolo}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Denominazione:"/></td>
		                    <td><c:out value="${requestScope.tavoloResult.denominazione}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Data creazione:"/></td>
		                    <td><c:out value="${requestScope.tavoloResult.dataCreazione}"/></td>
		                  </tr>
		                  
		                  <tr>
		                    <td><c:out value="Esperienza minima richiesta:"/></td>
		                    <td><c:out value="${requestScope.tavoloResult.esperienzaMinimaRichiesta}"/></td>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Puntata minima:"/></td>
		                    <td><c:out value="${requestScope.tavoloResult.puntataMinima}"/></td>
		                  </tr>

		                  <tr >
		                    <td><c:out value="Giocatori:"/></td>
		                    <td>
		                      <c:forEach items="${requestScope.tavoloResult.usernameGiocatori}" var="giocatore">
		                    	<c:out value="${giocatore}" />
		                      </c:forEach>
		                    </td>
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