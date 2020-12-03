package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.ricerca;

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

@WebServlet("/accessoEffettuato/gestioneTavoli/ricerca/ExecuteRicercaTavoliServlet")
public class ExecuteRicercaTavoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired 
	private TavoloService tavoloService;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	
    public ExecuteRicercaTavoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero i campi immessi dallo user
		TavoloDTO tavoloDTO=TavoloDTOBuilder.nuovoBuilder(request.getParameter("denominazione"))	
			.dataCreazione(request.getParameter("dataCreazione"))
			.esperienzaMinimaRichiesta(request.getParameter("esperienzaMinimaRichiesta"))
			.puntataMinima(request.getParameter("puntataMinima")).build();
		
		// Li valido
		if (tavoloDTO.errorRicerca().size()!=0) {
			request.setAttribute("errorMessage",tavoloDTO.errorRicerca());
			request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/ricerca/ricercaTavoli.jsp")
				.forward(request,response);
			return;
		}
		
		// Costruisco il model
		Tavolo tavoloDaCercare=tavoloDTO.buildModelFromDTO();

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
		tavoliTrovatiDTO.stream().forEach(tDTO->System.out.println(tDTO.getUsernameGiocatori().size()));
		// Mando alla pagina dei risultati sia i tavoli trovati, sia i criteri della ricerca effettuata
		request.setAttribute("listaTavoli",tavoliTrovatiDTO);
		request.setAttribute("tavoloDTO",tavoloDTO);
		request.setAttribute("successMessage","Ricerca effettuata con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/ricerca/risultatiRicercaTavoli.jsp")
			.forward(request,response);
	}

}
