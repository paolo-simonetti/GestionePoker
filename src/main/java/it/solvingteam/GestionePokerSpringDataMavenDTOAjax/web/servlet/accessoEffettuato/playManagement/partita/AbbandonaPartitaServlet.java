package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/playManagement/partita/AbbandonaPartitaServlet")
public class AbbandonaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AbbandonaPartitaServlet() {
        super();
    }
    
    @Autowired 
   	private TavoloService tavoloService;
    
    @Autowired 
   	private UtenteService utenteService;
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Rimuovo l'utente in sessione dalla lista dei giocatori del tavolo
		Long idTavoloDiGioco=((Utente)request.getSession().getAttribute("utenteIdentificato")).getTavoloDiGioco().getIdTavolo();
		Tavolo tavoloDiGioco=tavoloService.trovaTramiteIdConGiocatori(idTavoloDiGioco);
		tavoloDiGioco.getGiocatori().remove((Utente)request.getSession().getAttribute("utenteIdentificato"));
		
		// Setto a null il tavolo di gioco dell'utente in sessione
		((Utente)request.getSession().getAttribute("utenteIdentificato")).setTavoloDiGioco(null);
		
		// Aumento di un'unità l'esperienza del giocatore
		((Utente)request.getSession().getAttribute("utenteIdentificato")).setEsperienzaAccumulata(
				((Utente)request.getSession().getAttribute("utenteIdentificato")).getEsperienzaAccumulata()+1);
		
		// Aggiorno le informazioni in sessione e sul db 
		request.getSession().setAttribute("utenteIdentificato",(Utente)request.getSession().getAttribute("utenteIdentificato"));
		utenteService.aggiorna((Utente)request.getSession().getAttribute("utenteIdentificato"));
		tavoloService.aggiorna(tavoloDiGioco);
		
		// Torno alla schermata generale
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/schermataGenerale.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
