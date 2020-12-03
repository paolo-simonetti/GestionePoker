package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.attivazioneEDisabilitazione;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/attivazioneEDisabilitazione/OperazioniConfermateServlet")
public class OperazioniConfermateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
   
    public OperazioniConfermateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idUtentiRisultatoRicerca=request.getParameterValues("risultatoRicercaUtentePerGet");
		UtenteDTO utenteDTO=new UtenteDTO();
		if(utenteDTO.errorId(request.getParameter("idUtenteDaAttivareODisabilitare"))!=null) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage",
					utenteDTO.errorId(request.getParameter("idUtenteDaAttivareODisabilitare")));
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
		}
		for (int i=0; i<idUtentiRisultatoRicerca.length;i++) {
			if(utenteDTO.errorId(idUtentiRisultatoRicerca[i])!=null) {
				request.getSession().invalidate();
				request.setAttribute("errorMessage",
						utenteDTO.errorId(idUtentiRisultatoRicerca[i]));
				request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
					.forward(request,response);
			}
		}
		
		// Se sono qui, vuol dire che gli id non sono stati manomessi; oppure che, nel manometterli 
		// lo user ha inserito altri numeri. 
		Utente utenteDaAttivareODisabilitare=utenteService.trovaTramiteIdConInformazioniComplete
				(Long.parseLong(request.getParameter("idUtenteDaAttivareODisabilitare")));
		if(utenteDaAttivareODisabilitare== null) {
			request.setAttribute("errorMessage", "Aggiornamento fallito: hai manomesso l'id dell'utente?");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
			return;
		}
		
		// Adesso mi occupo delle operazioni di business. Se l'utente è da attivare, cambio semplicemente il suo stato:
		if(!utenteDaAttivareODisabilitare.getStatoUtente().toString().equals("attivo")) {
			// Metto lo stato a "creato", così da poter riassegnare i ruoli poi
			utenteDaAttivareODisabilitare.setStatoUtente(StatoUtente.conversioneStatoUtente.get("creato"));
			// Aggiorno il db
			utenteService.aggiorna(utenteDaAttivareODisabilitare);
			// Recupero i risultati (aggiornati) della ricerca originaria e torno in pagina
			Set<Utente> risultatoRicercaUtente=Arrays.asList(idUtentiRisultatoRicerca).stream()
					.map(stringaId->utenteService.caricaSingoloUtente(Long.parseLong(stringaId)))
						.collect(Collectors.toSet());
			request.setAttribute("listaUtenti",risultatoRicercaUtente);
			request.setAttribute("risultatoRicercaUtentePerGet",utenteDTO.generaRisultatoRicercaPerGet(risultatoRicercaUtente));
			request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/risultatiRicercaUtenti.jsp")
				.forward(request,response);
			return;
		}
		
		// Se sono qui, l'utente è da disattivare		
		// Riporto a 0 il credito e l'esperienza accumulati
		utenteDaAttivareODisabilitare.setCreditoDisponibile(0);
		utenteDaAttivareODisabilitare.setEsperienzaAccumulata(0);
		// Stacco il giocatore dal tavolo, se sta giocando una partita (non occorre un controllo: se non sta giocando, il tavoloDiGioco è già null)
		utenteDaAttivareODisabilitare.setTavoloDiGioco(null);
		// Tolgo i ruoli 
		utenteDaAttivareODisabilitare.setRuoli(null);
		// Disattivo l'utente
		utenteDaAttivareODisabilitare.setStatoUtente(StatoUtente.conversioneStatoUtente.get("disabilitato"));
		// Aggiorno il db
		utenteService.aggiorna(utenteDaAttivareODisabilitare);
		// Aggiorno l'utente in sessione, se è lo stesso che ho aggiornato
		if(utenteDaAttivareODisabilitare.getIdUtente()==((Utente) request.getSession().getAttribute("utenteIdentificato")).getIdUtente()) {
			request.getSession().setAttribute("utenteIdentificato",utenteDaAttivareODisabilitare);					
		}
		// Recupero i risultati (aggiornati) della ricerca originaria e torno in pagina
		Set<Utente> risultatoRicercaUtente=Arrays.asList(idUtentiRisultatoRicerca).stream()
				.map(stringaId->utenteService.caricaSingoloUtente(Long.parseLong(stringaId)))
					.collect(Collectors.toSet());
		request.setAttribute("listaUtenti",risultatoRicercaUtente);
		request.setAttribute("risultatoRicercaUtentePerGet",utenteDTO.generaRisultatoRicercaPerGet(risultatoRicercaUtente));
		request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/risultatiRicercaUtenti.jsp")
			.forward(request,response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
