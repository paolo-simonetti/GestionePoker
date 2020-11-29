<!doctype html>
<html lang="it">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    
    <jsp:include page="header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>

<title>Registrazione</title>
</head>
<body>

	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  <table class='table table-striped ' >
		  	<thead>
		  		<tr><th> Registrazione non riuscita: </th></tr>
		  	</thead>
		  	<tbody>
		  	<c:forEach items="${requestScope.errorMessage}" var="item">
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
		        <h5>Crea il tuo account!</h5> 
		    </div>
		    <div class='card-body'>
		    <h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
				<form method="post" action="${pageContext.request.contextPath}/registrazioneAccesso/ExecuteRegistrazioneServlet" novalidate="novalidate">
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger">*</span> </label>
							<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserisci il tuo nome" required value="${requestScope.utenteDTO.nome}">
						</div>

						<div class="form-group col-md-6">
							<label>Cognome <span class="text-danger">*</span></label>
							<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserisci il tuo cognome" required value="${requestScope.utenteDTO.cognome}">
						</div>

	
						<div class="form-group col-md-6">
							<label>Username <span class="text-danger">*</span></label>
							<input type="text" name="username" id="username" class="form-control" placeholder="Inserire lo username" required value="${requestScope.utenteDTO.username}">
						</div>
						
						<!-- Fiocchetto: mettere un pulsantino per vedere in chiaro la password -->	
						<div class="form-group col-md-6">
							<label>Password <span class="text-danger">*</span></label>
							<input type="password" name="password" id="password" class="form-control" placeholder="Inserire la password" required value="${requestScope.utenteDTO.password}">
						</div>
					</div>
											
					<button type="submit" name="signup" value="signup" id="signup" class="btn btn-primary">Registrati</button>
						
				</form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
		
	</main>

	<jsp:include page="footer.jsp" />
</body>
</html>