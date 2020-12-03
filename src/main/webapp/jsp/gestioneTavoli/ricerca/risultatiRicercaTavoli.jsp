<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="it.solvingteam.GestionePokerSpringDataMavenDTOAjax.beans.TavoloDTOBean"%>
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
	
	<!--  palleggio i criteri di ricerca -->
	<jsp:useBean id="criteriDiRicerca" class="it.solvingteam.GestionePokerSpringDataMavenDTOAjax.beans.TavoloDTOBean" scope="request" />
	<jsp:setProperty name="criteriDiRicerca" property="denominazione" value="${requestScope.tavoloDTO.denominazione}"/>
	<jsp:setProperty name="criteriDiRicerca" property="dataCreazione" value="${requestScope.tavoloDTO.dataCreazione}"/>
	<jsp:setProperty name="criteriDiRicerca" property="esperienzaMinimaRichiesta" value="${requestScope.tavoloDTO.esperienzaMinimaRichiesta}"/>
	<jsp:setProperty name="criteriDiRicerca" property="puntataMinima" value="${requestScope.tavoloDTO.puntataMinima}"/>
	
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
		  	<c:forEach items="${errorMessage}" var="errorItem">
		  		<tr><td> ${errorItem} </td></tr>
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
		                        <th>Id Tavolo</th>
		                        <th>Denominazione</th>
		                        <th>Data creazione</th>
		                        <th>Esperienza minima richiesta</th>
								<th>Puntata minima</th>
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
		                     
	               				<td>
								  <a class="btn  btn-sm btn-outline-secondary" 
								  	href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/visualizzazioneTavolo/VisualizzazioneTavoloServlet?idTavoloDaVisualizzare=${item.idTavolo}">
									Visualizza tavolo
								  </a>
								  <c:if test="${item.usernameGiocatori.size()==0}">
								  <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" 
								    href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/aggiornamentoTavolo/PrepareUpdateTavoloServlet?idTavoloDaAggiornare=${item.idTavolo}">
								    Aggiorna tavolo
								  </a>
								  <a class="btn  btn-sm btn-outline-danger ml-2 mr-2" 
								    href="${pageContext.request.contextPath}/accessoEffettuato/gestioneTavoli/eliminazioneTavolo/PrepareDeleteTavoloServlet?idTavoloDaEliminare=${item.idTavolo}">
								    Elimina tavolo
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