package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.aggiornamentoTavolo;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.builder.TavoloDTOBuilder;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;


@WebServlet("/accessoEffettuato/gestioneTavoli/aggiornamento/ExecuteUpdateTavoloServlet")
public class ExecuteUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteUpdateTavoloServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Palleggio i risultati della ricerca
		TavoloDTO criteriDiRicerca=new TavoloDTO();
		criteriDiRicerca.ricostruisciCriteriDiRicercaDaDTO(request);
					
		// Recupero i campi inseriti dall'utente nel form di aggiornamento e li valido
		TavoloDTO tavoloDTO=TavoloDTOBuilder.nuovoBuilder(request.getParameter("denominazioneDaAggiornare"))
				.esperienzaMinimaRichiesta(request.getParameter("esperienzaMinimaRichiestaDaAggiornare"))
				.puntataMinima(request.getParameter("puntataMinimaDaAggiornare"))
				.dataCreazione(request.getParameter("dataCreazioneDaAggiornare"))
				.usernameCreatore(((Utente) request.getSession().getAttribute("utenteIdentificato")).getUsername()).build();
		if(tavoloDTO.errorAggiorna(request.getParameter("idTavoloDaAggiornare")).size()>0) {
			// se hai commesso errori nei campi, ti riporto al form di aggiornamento e palleggio i criteri della ricerca
			request.setAttribute("errorMessage",tavoloDTO.errors());
			request.setAttribute("tavoloDTO",criteriDiRicerca);
			request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/aggiornamentoTavolo/update.jsp").forward(request,response);
			return;
		}
		tavoloDTO.setIdTavolo(Long.parseLong(request.getParameter("idTavoloDaAggiornare")));
		// Se sono qui, i dati inseriti sono validi, quindi aggiorno il tavolo
		Tavolo tavoloAggiornato=tavoloDTO.buildModelFromDTO();
		tavoloAggiornato.setCreatore((Utente) request.getSession().getAttribute("utenteIdentificato"));
		tavoloAggiornato.setDataCreazione(tavoloService.caricaSingoloTavolo(
				Long.parseLong(request.getParameter("idTavoloDaAggiornare"))).getDataCreazione());
		tavoloService.aggiorna(tavoloAggiornato);
		
		// Rieffettuo la ricerca, in base ai nuovi parametri, e torno in pagina 
		// Costruisco il model
		
		
		Tavolo tavoloDaCercare=criteriDiRicerca.buildModelFromDTO();

		// Effettuo la ricerca
		Set<Tavolo> tavoliTrovati=tavoloService.findByExample(tavoloDaCercare).stream()
			.filter(tavolo->tavolo.getCreatore().getIdUtente()
				==((Utente) request.getSession().getAttribute("utenteIdentificato")).getIdUtente())
			.collect(Collectors.toSet());
				
		// Converto i risultati in DTO
		Set<TavoloDTO> tavoliTrovatiDTO=new TreeSet<>();
		tavoliTrovati.stream().forEach(tavolo-> {
		TavoloDTO tavoloDTOItem=new TavoloDTO();
		tavoloDTOItem.buildDTOFromModel(tavolo);
		tavoloDTOItem.setUsernameGiocatori(tavolo.getGiocatori()
			.stream().map(giocatore->giocatore.getUsername()).collect(Collectors.toSet()));
			tavoliTrovatiDTO.add(tavoloDTOItem);
		});
		// Mando alla pagina dei risultati sia i tavoli trovati, sia i criteri della ricerca effettuata
		request.setAttribute("listaTavoli",tavoliTrovatiDTO);
		request.setAttribute("criteriDiRicerca",criteriDiRicerca);
		request.setAttribute("successMessage","Aggiornamento effettuato con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/ricerca/risultatiRicercaTavoli.jsp")
			.forward(request,response);
		
	}

}
