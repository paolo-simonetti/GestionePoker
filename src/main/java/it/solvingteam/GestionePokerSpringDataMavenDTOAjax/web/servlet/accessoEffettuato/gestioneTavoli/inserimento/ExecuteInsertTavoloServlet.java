package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.inserimento;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

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
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;

@WebServlet("/accessoEffettuato/gestioneTavoli/inserimento/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteInsertTavoloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero lo username dell'utente in sessione, per metterlo nel DTO del tavolo
		Utente utenteInSessione=(Utente) request.getSession().getAttribute("utenteIdentificato");
		String usernameUtenteInSessione=utenteInSessione.getUsername();
		
		// Recupero i dati dal form di inserimento
		TavoloDTO tavoloDTO=TavoloDTOBuilder.nuovoBuilder(request.getParameter("denominazione"))
					.esperienzaMinimaRichiesta(request.getParameter("esperienzaMinimaRichiesta"))
					.puntataMinima(request.getParameter("puntataMinima"))
					.dataCreazione(LocalDate.now().toString())
					.usernameCreatore(usernameUtenteInSessione).build();
		
		// Li valido
		Set<String> erroriNellInput=null;
		try {
			erroriNellInput=tavoloDTO.errors();
		} catch(NumberFormatException e) {
			request.setAttribute("errorMessage",erroriNellInput);
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		if(erroriNellInput.size()!=0) {
			request.setAttribute("errorMessage",tavoloDTO.errors());
			request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/inserimento/inserisciNuovo.jsp")
				.forward(request,response);
			return;
		}
		
		// Costruisco il tavolo
		Tavolo tavoloDaInserire=tavoloDTO.buildModelFromDTO();
		
		// Lo inserisco nel db e aggiorno l'utente
		tavoloDaInserire.setCreatore(utenteInSessione);
		utenteInSessione.addToTavoliCreati(tavoloDaInserire);
		tavoloService.inserisciNuovo(tavoloDaInserire);
		utenteService.aggiorna(utenteInSessione);
		// Aggiorno l'utente in sessione
		request.getSession().setAttribute("utenteIdentificato",utenteService
				.trovaTramiteIdConInformazioniComplete(utenteInSessione.getIdUtente()));
		
		request.setAttribute("successMessage","Inserimento effettuato con successo!");
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/schermataGenerale.jsp")
			.forward(request,response);
	}

}
