package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.eliminazioneTavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;

@WebServlet("/accessoEffettuato/gestioneTavoli/eliminazioneTavolo/PrepareDeleteTavoloServlet")
public class PrepareDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareDeleteTavoloServlet() {
        super();
    }

    @Autowired
   	private TavoloService tavoloService;
       
       @Override
   	public void init(ServletConfig config) throws ServletException {
   		super.init(config);
   		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
   	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Palleggio i risultati della ricerca
		TavoloDTO criteriDiRicerca=new TavoloDTO();
		criteriDiRicerca.ricostruisciCriteriDiRicercaDaDTO(request);
		// Valido l'id passato in get
		TavoloDTO tavoloDTO=new TavoloDTO();
		if(tavoloDTO.errorId(request.getParameter("idTavoloDaEliminare"))!=null) {
			request.setAttribute("errorMessage",request.getParameter("idTavoloDaEliminare"));
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		} 
		// Ricostruisco il tavolo da eliminare
		Tavolo tavoloDaEliminare=tavoloService.trovaTramiteIdConInformazioniComplete(Long.parseLong(request.getParameter("idTavoloDaEliminare")));
		// Blocco, lato back-end, il tentativo di eliminare un tavolo se ha giocatori
		if(tavoloDaEliminare.getGiocatori().size()>0) {
			request.setAttribute("errorMessage","Hai tentato di eliminare un tavolo in cui stanno giocando degli utenti.");
			request.getSession().invalidate();
			// Se hai attivato questa servlet, nonostante io abbia nascosto il pulsante nei risultati di ricerca, sei una brutta persona
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		// Blocco, lato back-end, il tentativo di eliminare un tavolo non appartenente all'utente in sessione
		if(!tavoloDaEliminare.getCreatore().getUsername().equals(((Utente) request.getSession().getAttribute("utenteIdentificato")).getUsername())) {
			request.setAttribute("errorMessage","Hai tentato di eliminare un tavolo non tuo.");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		// Palleggio i criteri della ricerca precedente e vado alla pagina di eliminazione
		request.setAttribute("criteriDiRicerca",criteriDiRicerca);
		request.setAttribute("idTavoloDaEliminare",request.getParameter("idTavoloDaEliminare"));
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/eliminazione/delete.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
