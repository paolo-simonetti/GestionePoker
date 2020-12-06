package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.partita;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;


@WebServlet("/accessoEffettuato/playManagement/partita/RiprendiPartitaServlet")
public class RiprendiPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RiprendiPartitaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Controllo, lato back-end, che l'utente sia effettivamente in un tavolo di gioco
		if(((Utente)request.getSession().getAttribute("utenteIdentificato")).getTavoloDiGioco()==null) {
			request.setAttribute("errorMessage","Non hai i requisiti per accedere a questa funzionalità.");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		// Se sono qui, l'utente stava effettivamente giocando a un tavolo, quindi lo riporto alla pagina della partita
		request.setAttribute("idTavoloDiGioco",((Utente)request.getSession().getAttribute("utenteIdentificato")).getTavoloDiGioco().getIdTavolo());
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/partita/partita.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
