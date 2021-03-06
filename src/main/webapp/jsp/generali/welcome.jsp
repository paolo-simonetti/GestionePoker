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
    
<title>Login</title>
</head>
<body>

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
		        <h5>Effettua il login</h5> 
		    </div>
		    <div class='card-body'>
				<form method="post" action="${pageContext.request.contextPath}/registrazioneAccesso/LoginServlet" novalidate="novalidate">
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Username </label>
							<input type="text" name="username" id="username" class="form-control" placeholder="Inserire lo username" required value="${utenteDTO.username}">
						</div>
						
						<!-- Fiocchetto: mettere un pulsantino per vedere in chiaro la password -->	
						<div class="form-group col-md-6">
							<label>Password</label>
							<input type="password" name="password" id="password" class="form-control" placeholder="Inserire la password" required value="${utenteDTO.password}">
						</div>
					</div>
											
					<button type="submit" name="login" value="login" id="login" class="btn btn-primary">Accedi</button>
						
				</form>
			<p><a class="btn btn-secondary" href="${pageContext.request.contextPath}/registrazioneAccesso/PrepareRegistrazioneServlet" role="button">Non sei ancora registrato? &raquo;</a></p>				
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
		
	</main>
	<jsp:include page="footer.jsp" />
</body>
</html>