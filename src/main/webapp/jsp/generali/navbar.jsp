<!-- navbar -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
   
  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
  	  <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/jsp/generali/menu.jsp"> Home <span class="sr-only">(current)</span></a>
      	  </li>
     	  
     	  <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	Benvenuto, ${sessionScope.utenteIdentificato.nome} ${sessionScope.utenteIdentificato.cognome} 
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdown03">
          	<a class="dropdown-item" href="${pageContext.request.contextPath}/registrazioneAccesso/LogoutServlet">Logout</a>
          </div>
          </li>
	      
    </ul>
    
  </div>
</nav>
