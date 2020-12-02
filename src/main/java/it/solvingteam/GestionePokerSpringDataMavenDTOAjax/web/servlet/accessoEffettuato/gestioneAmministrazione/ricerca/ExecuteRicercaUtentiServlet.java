package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.ricerca;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.RuoloUtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/ricerca/ExecuteRicercaUtentiServlet")
public class ExecuteRicercaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired 
	private UtenteService utenteService;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
  
    public ExecuteRicercaUtentiServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Per ora, lasciamo in sospeso la ricerca per tavolo. Quando abbiamo finito tutto il 
		 * resto, ne riparliamo * */
		
		// prendo i parametri da input
		UtenteDTO utenteDTO=new UtenteDTO();
		RuoloUtenteDTO ruoloDTO=new RuoloUtenteDTO();
		//TavoloDTO tavoloDTO=new TavoloDTO();
		utenteDTO.setNome(request.getParameter("nome"));
		utenteDTO.setCognome(request.getParameter("cognome"));
		utenteDTO.setUsername(request.getParameter("username"));
		utenteDTO.setDataRegistrazione(request.getParameter("dataRegistrazione"));
		utenteDTO.setStatoUtente(request.getParameter("statoUtente"));
		ruoloDTO.setDescrizioneRuolo(request.getParameter("descrizioneRuolo"));
		// tavoloDTO.setDenominazione(request.getParameter("tavoloDisponibile"));
		// valido l'input di ricerca
		if(utenteDTO.errorRicerca().size()!=0) {
			request.setAttribute("errorMessage",utenteDTO.errorRicerca());
			request.getServletContext()
				.getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/ricercaUtenti.jsp")
					.forward(request,response);
			return;
		} 
		
		// effettuo la ricerca
		Utente utenteDaCercare=utenteDTO.buildModelFromDTO();
		Set<Utente> listaUtenti=utenteService.findByExample(utenteDaCercare);
		
		// Porto i risultati in pagina
		request.setAttribute("criteriDiRicerca",utenteDTO);
		request.setAttribute("listaUtenti",listaUtenti);
		if(!listaUtenti.isEmpty()) {
			request.setAttribute("risultatoRicercaUtentePerGet",
					utenteDTO.generaRisultatoRicercaPerGet(listaUtenti));
			request.setAttribute("successMessage","Ricerca eseguita con successo");			
		} else {
			request.setAttribute("errorMessage","Non è stato trovato alcun risultato");
		}
		request.getServletContext()
			.getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/risultatiRicercaUtenti.jsp")
				.forward(request,response);
	}

}
