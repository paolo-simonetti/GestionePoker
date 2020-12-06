<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 	<jsp:include page="../generali/header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>

	<title>Dati personali</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	
	<jsp:include page="../generali/navbar.jsp"></jsp:include>
	<main role="main" class="container">
	
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Visualizza i tuoi dati</h5> 
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
		                    <td><c:out value="${sessionScope.utenteIdentificato.idUtente}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Nome:"/></td>
		                    <td><c:out value="${sessionScope.utenteIdentificato.nome}"/></td>
		                  </tr>
		                      
		                  <tr>
		                    <td><c:out value="Cognome:"/></td>
		                    <td><c:out value="${sessionScope.utenteIdentificato.cognome}"/></td>
		                  </tr>
		                  
		                  <tr>
		                    <td><c:out value="Data di registrazione:"/></td>
		                    <td><c:out value="${sessionScope.utenteIdentificato.dataRegistrazione}"/></td>
		                  </tr>
		                  
		                  <tr >
		                    <td><c:out value="Credito disponibile:"/></td>
		                    <td><c:out value="${sessionScope.utenteIdentificato.creditoDisponibile}"/></td>
		                  </tr>

		                  <tr>
		                    <td><c:out value="Esperienza accumulata:"/></td>
		                    <td><c:out value="${sessionScope.utenteIdentificato.esperienzaAccumulata}"/></td>
		                  </tr>
		                  
		              </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	
<jsp:include page="../generali/footer.jsp" />

</body>
</html>