package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.ricerca;

import java.io.IOException;

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
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/playManagement/partita/UniscitiAllaPartitaServlet")
public class UniscitiAllaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UniscitiAllaPartitaServlet() {
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
		// TODO: fare anche un filtro che impedisca di accedere alla sezione di ricerca se stai giocando a un tavolo
		// TODO: fare anche un filtro che impedisca di accedere alla sezione di partita se non stai giocando a nessun tavolo
		
		// Controllo anche qui, lato back-end, che l'utente abbia l'esperienza e il credito necessari a giocare al tavolo
		// Recupero l'id del tavolo di gioco e lo valido
		TavoloDTO tavoloDTO=new TavoloDTO();
		if(tavoloDTO.errorId(request.getParameter("idTavoloDiGioco"))!=null) {
			request.setAttribute("errorMessage","Errore nel recupero del tavolo di gioco. Hai manomesso l'id dall'URL?");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		 Tavolo tavoloDiGioco=tavoloService.trovaTramiteIdConGiocatori(Long.parseLong(request.getParameter("idTavoloDiGioco")));
		// Se sono qui, l'id del gioco è un numero. Controllo che sia relativo a un tavolo esistente e in cui lo user possa giocare
		if (tavoloDiGioco==null) {
			request.setAttribute("errorMessage","Il tavolo a cui stai tentando di giocare non esiste. Hai manomesso l'id dall'URL?");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		} else if(tavoloDiGioco.getEsperienzaMinimaRichiesta()>((Utente)request.getSession().getAttribute("utenteIdentificato")).getEsperienzaAccumulata() 
				|| tavoloDiGioco.getPuntataMinima()>((Utente)request.getSession().getAttribute("utenteIdentificato")).getCreditoDisponibile()) {
			request.setAttribute("errorMessage","Non hai i requisiti di esperienza o credito per giocare a questo tavolo. Hai manomesso l'id dall'URL?");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		// Se sono qui, l'utente può effettivamente giocare a questo tavolo
		// Collego utente e tavolo di gioco dal lato del model
		tavoloDiGioco.getGiocatori().add((Utente)request.getSession().getAttribute("utenteIdentificato"));
		((Utente)request.getSession().getAttribute("utenteIdentificato")).setTavoloDiGioco(tavoloDiGioco);
		
		// Aggiorno le informazioni in sessione e sul db
		request.getSession().setAttribute("utenteIdentificato",(Utente)request.getSession().getAttribute("utenteIdentificato"));
		utenteService.aggiorna((Utente)request.getSession().getAttribute("utenteIdentificato"));
		tavoloService.aggiorna(tavoloDiGioco);
		
		// Vado alla pagina della partita
		request.setAttribute("idTavoloDiGioco",tavoloDiGioco.getIdTavolo());
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/partita/partita.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
