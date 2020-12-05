package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.ricerca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;


@WebServlet("/accessoEffettuato/playManagement/ricerca/PrepareRicercaPartitaServlet")
public class PrepareRicercaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PrepareRicercaPartitaServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Controllo, lato back-end, che l'utente non abbia una partita in corso
		if(((Utente) request.getSession().getAttribute("utenteIdentificato")).getTavoloDiGioco()!=null) {
			request.setAttribute("errorMessage","Non hai i permessi per effettuare questa operazione");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			request.getSession().invalidate();
			return;
		}
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/ricerca/ricercaPartita.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
