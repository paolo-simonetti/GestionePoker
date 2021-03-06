package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.eliminazioneTavolo;

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


@WebServlet("/accessoEffettuato/gestioneTavoli/eliminazione/ExecuteDeleteTavoloServlet")
public class ExecuteDeleteTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExecuteDeleteTavoloServlet() {
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
		
		// Ora posso occuparmi delle operazioni di business
		// Tolgo il tavolo dall'elenco di quelli creati dall'utente in sessione
		((Utente) request.getSession().getAttribute("utenteIdentificato")).getTavoliCreati().remove(tavoloDaEliminare);
		
		// Elimino il tavolo dal db
		tavoloService.aggiorna(tavoloDaEliminare);
		tavoloService.rimuovi(tavoloDaEliminare);
		
		// Devo ricostruire i risultati della ricerca e tornare alla pagina a questi dedicata
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
		request.setAttribute("successMessage","Ricerca effettuata con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/ricerca/risultatiRicercaTavoli.jsp")
			.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
