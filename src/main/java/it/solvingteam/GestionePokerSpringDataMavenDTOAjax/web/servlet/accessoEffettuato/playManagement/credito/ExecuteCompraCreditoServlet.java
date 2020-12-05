package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.credito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;


@WebServlet("/accessoEffettuato/playManagement/credito/ExecuteCompraCreditoServlet")
public class ExecuteCompraCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteCompraCreditoServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Valido l'input
		UtenteDTO utenteDTO=new UtenteDTO();
		if(utenteDTO.errorCompraCredito(request.getParameter("creditoDesiderato"))!=null) {
			request.setAttribute("errorMessage",utenteDTO.errorCompraCredito(request.getParameter("creditoDesiderato")));
			request.getServletContext().getRequestDispatcher("/jsp/playManagement/credito/compraCredito.jsp").forward(request,response);
			return;
		}
		
		// Se sono qui, il credito inserito è effettivamente un numero positivo
		Integer creditoDesiderato=Integer.parseInt(request.getParameter("creditoDesiderato"));
		
		// Lo aggiungo al credito disponibile dell'utente
		((Utente) request.getSession().getAttribute("utenteIdentificato")).setCreditoDisponibile(creditoDesiderato+
				((Utente) request.getSession().getAttribute("utenteIdentificato")).getCreditoDisponibile());
		
		// Torno alla schermata generale con un messaggino di avvenuta operazione
		request.setAttribute("successMessage","Operazione avvenuta con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/schermataGenerale.jsp").forward(request,response);
	}

}
