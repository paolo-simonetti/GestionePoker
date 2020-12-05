package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.visualizzazioneTavolo;

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



import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;


@WebServlet("/accessoEffettuato/gestioneTavoli/visualizzazioneTavolo/TornaAiRisultatiServlet")
public class TornaAiRisultatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired 
	private TavoloService tavoloService;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public TornaAiRisultatiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero i criteri della ricerca originari
		TavoloDTO criteriDiRicerca=new TavoloDTO();
		criteriDiRicerca.ricostruisciCriteriDiRicercaDaDTO(request);
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
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/ricerca/risultatiRicercaTavoli.jsp")
			.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
