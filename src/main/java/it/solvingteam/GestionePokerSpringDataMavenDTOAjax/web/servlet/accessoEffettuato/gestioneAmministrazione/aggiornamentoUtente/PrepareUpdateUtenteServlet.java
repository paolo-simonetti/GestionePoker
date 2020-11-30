package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.aggiornamentoUtente;

import java.io.IOException;

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
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
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
		utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare"));
		for (int i=0; i<idUtentiRisultatoRicerca.length;i++) {
			if(utenteDTO.errorId(idUtentiRisultatoRicerca[i])!=null) {
				request.getSession().invalidate();
				request.setAttribute("errorMessage",
						utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare")));
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
		request.setAttribute("listaStati",StatoUtente.conversioneStatoUtente.values());
		request.setAttribute("listaRuoli",NomeRuolo.conversioneNomeRuolo.keySet());
		request.setAttribute("risultatoRicercaUtente",idUtentiRisultatoRicerca);
		request.getServletContext()
			.getRequestDispatcher("/jsp/gestioneAmministrazione/aggiornamentoUtente/update.jsp")
				.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
