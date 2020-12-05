package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.ricerca;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.builder.TavoloDTOBuilder;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/playManagement/ricerca/ExecuteRicercaPartitaServlet")
public class ExecuteRicercaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteRicercaPartitaServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Raccolgo i criteri di ricerca
		TavoloDTO criteriDiRicerca=TavoloDTOBuilder.nuovoBuilder(request.getParameter("denominazione"))
				.dataCreazione(request.getParameter("dataCreazione"))
				.esperienzaMinimaRichiesta(request.getParameter("esperienzaMinimaRichiesta"))
				.puntataMinima(request.getParameter("puntataMinima"))
				.usernameCreatore(request.getParameter("usernameCreatore")).build();
		criteriDiRicerca.getUsernameGiocatori().add(request.getParameter("usernameGiocatore"));
		
		// Valido gli input di ricerca
		if(criteriDiRicerca.errorRicerca().size()>0) {
			request.setAttribute("errorMessage",criteriDiRicerca.errorRicerca());
			request.getServletContext().getRequestDispatcher("/jsp/playManagement/ricerca/ricercaPartita.jsp").forward(request,response);
			return;
		}
		
		// Costruisco il model
		Tavolo tavoloDaCercare=criteriDiRicerca.buildModelFromDTO();
		// Recupero il creatore del tavolo e il giocatore richiesti dall'utente
		if(!StringUtils.isBlank(request.getParameter("usernameCreatore"))) {
			Utente creatore=utenteService.trovaTramiteUsername(request.getParameter("usernameCreatore"));
			if(creatore==null) {
				request.setAttribute("errorMessage","Creatore non trovato!");
				request.getServletContext().getRequestDispatcher("/jsp/playManagement/ricerca/ricercaPartita.jsp").forward(request,response);
				return;
			} else {
				tavoloDaCercare.setCreatore(creatore);
			}			
		}
		if(!StringUtils.isBlank(request.getParameter("usernameGiocatore"))) {
			Utente giocatore=utenteService.trovaTramiteUsername(request.getParameter("usernameGiocatore"));
			if(giocatore==null) {
				request.setAttribute("errorMessage","Giocatore non trovato!");
				request.getServletContext().getRequestDispatcher("/jsp/playManagement/ricerca/ricercaPartita.jsp").forward(request,response);
				return;
			} else {
				tavoloDaCercare.addToGiocatori(giocatore);
				giocatore.setTavoloDiGioco(tavoloDaCercare);
			}
		}
		/* Effettuo la ricerca. Ho deciso di non far risultare tavoli per i quali l'utente non abbia abbastanza esperienza o credito 
		 Se l'utente ha immesso nel form dei valori di puntata minima/esperienza minima maggiori rispetto a quelli che possiede,
		 è la validazione iniziale stessa a bloccarlo */
		Set<Tavolo> tavoliTrovati=tavoloService.findByExample(tavoloDaCercare).stream()
			.filter(tavolo->tavolo.getEsperienzaMinimaRichiesta()<=
				((Utente) request.getSession().getAttribute("utenteIdentificato")).getEsperienzaAccumulata() &&
				tavolo.getPuntataMinima()<=
				((Utente) request.getSession().getAttribute("utenteIdentificato")).getCreditoDisponibile())
			.collect(Collectors.toSet());
			
		// Converto i risultati in DTO
		Set<TavoloDTO> tavoliTrovatiDTO=new TreeSet<>();
		tavoliTrovati.stream().forEach(tavolo-> {
			TavoloDTO tavoloDTOItem=new TavoloDTO();
			tavoloDTOItem.buildDTOFromModel(tavolo);
			tavoloDTOItem.setUsernameGiocatori(tavolo.getGiocatori()
					.stream().map(usernameGiocatore->usernameGiocatore.getUsername()).collect(Collectors.toSet()));
			tavoliTrovatiDTO.add(tavoloDTOItem);
		});
				
		// Mando alla pagina dei risultati i tavoli trovati
		// Palleggiare i risultati della ricerca non serve, dato che nella pagina di atterraggio c'è solo il pulsante 'unisciti alla partita'
		request.setAttribute("listaTavoli",tavoliTrovatiDTO);
		request.setAttribute("successMessage","Ricerca effettuata con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/ricerca/risultatiRicercaPartita.jsp")
			.forward(request,response);
	}

}
