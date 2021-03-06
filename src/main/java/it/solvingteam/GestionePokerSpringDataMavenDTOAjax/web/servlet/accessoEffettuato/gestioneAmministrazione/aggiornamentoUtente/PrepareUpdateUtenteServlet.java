package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.aggiornamentoUtente;

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
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/aggiornamentoUtente/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
  
    public PrepareUpdateUtenteServlet() {
        super();  
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Prendo i parametri dall'input e li valido
		String[] idUtentiRisultatoRicerca=request.getParameterValues("risultatoRicercaUtentePerGet");
		UtenteDTO utenteDTO=new UtenteDTO();
		if(utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare"))!=null) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage",
					utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare")));
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
		Utente utenteDaAggiornare=utenteService.trovaTramiteIdConInformazioniComplete
				(Long.parseLong(request.getParameter("idUtenteDaAggiornare")));
		if(utenteDaAggiornare== null) {
			request.setAttribute("errorMessage", "Aggiornamento fallito: hai manomesso l'id dell'utente?");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
			return;
		}
		// Carico l'utente, con tutte le sue informazioni
		request.setAttribute("utenteDaAggiornare", utenteDaAggiornare);
		/* Abbiamo stabilito che posso modificare lo stato e i ruoli solo se l'utente 
		 * � appena stato registrato, cio� solo se il suo ruolo � null e lo stato � 'creato' */
		if(utenteDaAggiornare.getStatoUtente().toString().equals("creato")) {
			request.setAttribute("listaRuoli",NomeRuolo.conversioneNomeRuolo.keySet());			
		}
		Set<Utente> risultatoRicercaUtente=Arrays.asList(idUtentiRisultatoRicerca).stream()
			.map(stringaId->utenteService.caricaSingoloUtente(Long.parseLong(stringaId)))
				.collect(Collectors.toSet());
		request.setAttribute("risultatoRicercaUtente",utenteDTO.generaRisultatoRicercaPerPost
				(risultatoRicercaUtente));
		request.getServletContext()
			.getRequestDispatcher("/jsp/gestioneAmministrazione/aggiornamentoUtente/update.jsp")
				.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
