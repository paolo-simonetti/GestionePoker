<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Risultati della ricerca della partita</title>
	
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
		    <div class='card-body'>
		    
		    <a class="btn  btn-sm btn-outline-danger" 
			  href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/ricerca/TornaAlFormServlet"
			> Torna al form di ricerca</a>
			
		    	<div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id Tavolo</th>
		                        <th>Denominazione</th>
		                        <th>Data creazione</th>
		                        <th>Esperienza minima richiesta</th>
								<th>Puntata minima</th>
								<th>Creatore</th>
								<th>Giocatori</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                  	<c:forEach items="${requestScope.listaTavoli}" var="item">
		                	  <tr >
		                        <td><c:out value="${item.idTavolo}"></c:out></td>
		                        <td><c:out value="${item.denominazione}"></c:out></td>
		                        <td><c:out value="${item.dataCreazione}"></c:out></td>
		                        <td><c:out value="${item.esperienzaMinimaRichiesta}"></c:out></td>
		                        <td><c:out value="${item.puntataMinima}"></c:out></td>
		                    	<td><c:out value="${item.usernameCreatore}"></c:out></td>
		                    	<td>
		                    	  <c:forEach items="${item.usernameGiocatori}" var="usernameGiocatore">
		                    	    <c:out value="${usernameGiocatore} "></c:out>
		                    	  </c:forEach>
		                    	</td>
	               				<td>
	               				  <c:if test="${sessionScope.utenteIdentificato.esperienzaAccumulata>=item.esperienzaMinimaRichiesta and 
	               				    sessionScope.utenteIdentificato.creditoDisponibile>=item.puntataMinima }">
								  <a class="btn  btn-sm btn-outline-primary" 
								  	href="${pageContext.request.contextPath}/accessoEffettuato/playManagement/partita/UniscitiAllaPartitaServlet">
									Unisciti alla partita!
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
		    </div>
			
	
	
	
	
	
	
	<!-- end container -->	 
	</main>
	<jsp:include page="/jsp/generali/footer.jsp" />
</body>
</html>