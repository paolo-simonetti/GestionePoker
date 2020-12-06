package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.playManagement.partita;

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


@WebServlet("/accessoEffettuato/playManagement/partita/GiocaPartitaServlet")
public class GiocaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GiocaPartitaServlet() {
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
		// Controllo anche qui, lato back-end, che l'utente abbia l'esperienza e il credito necessari a giocare al tavolo
		// Recupero l'id del tavolo di gioco e lo valido
		TavoloDTO tavoloDTO=new TavoloDTO();
		if(tavoloDTO.errorId(request.getParameter("idTavoloDiGioco"))!=null) {
			request.setAttribute("errorMessage","Errore nel recupero del tavolo di gioco. Hai manomesso l'id dall'URL?");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		Tavolo tavoloDiGioco=tavoloService.trovaTramiteIdConInformazioniComplete(Long.parseLong(request.getParameter("idTavoloDiGioco")));
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
				
		// Se sono qui, l'utente può effettivamente giocare a questo tavolo. Simulo il gioco
		Double generatoreSegno=Math.random();
		Integer segno=null;
		if(generatoreSegno>=0.5) {
			segno=1;
		} else {
			segno=-1;
		}
		Integer somma=(int) (Math.random()*1000);
		somma=segno*somma;
		
		// Aggiungo la somma al credito disponibile dell'utente
		Integer creditoDisponibile=((Utente)request.getSession().getAttribute("utenteIdentificato")).getCreditoDisponibile();
		creditoDisponibile+=somma;
		if(creditoDisponibile<0) {
			creditoDisponibile=0;
		}
		// Aggiorno il credito disponibile in sessione e sul db;
		((Utente)request.getSession().getAttribute("utenteIdentificato")).setCreditoDisponibile(creditoDisponibile);
		request.getSession().setAttribute("utenteIdentificato",(Utente)request.getSession().getAttribute("utenteIdentificato"));
		utenteService.aggiorna((Utente)request.getSession().getAttribute("utenteIdentificato"));
		
		/* Se il credito disponibile è >0, ma inferiore alla puntata minima richiesta al tavolo, 
		 * scollego l'utente dal tavolo di gioco e lo mando alla schermata generale */
		if(creditoDisponibile>0 && creditoDisponibile<tavoloDiGioco.getPuntataMinima()) {
			tavoloDiGioco.getGiocatori().remove((Utente)request.getSession().getAttribute("utenteIdentificato"));
			((Utente)request.getSession().getAttribute("utenteIdentificato")).setTavoloDiGioco(null);
			utenteService.aggiorna((Utente)request.getSession().getAttribute("utenteIdentificato"));
			request.setAttribute("errorMessage","Il tuo credito attuale è sceso a "+creditoDisponibile+". Non è sufficiente per continuare a "
					+ "giocare al tavolo "+tavoloDiGioco);
			request.getServletContext().getRequestDispatcher("/jsp/playManagement/schermataGenerale.jsp").forward(request,response);
			return;
		}
		
		// Se il credito disponibile è sceso a 0, scollego l'utente dal tavolo di gioco e lo mando alla schermata di acquisto del credito
		if(creditoDisponibile==0) {
			tavoloDiGioco.getGiocatori().remove((Utente)request.getSession().getAttribute("utenteIdentificato"));
			((Utente)request.getSession().getAttribute("utenteIdentificato")).setTavoloDiGioco(null);
			utenteService.aggiorna((Utente)request.getSession().getAttribute("utenteIdentificato"));
			request.setAttribute("errorMessage","Il tuo credito è sceso a 0. Acquistane di nuovo, se vuoi giocare a un tavolo");
			request.getServletContext().getRequestDispatcher("/jsp/playManagement/credito/compraCredito.jsp").forward(request,response);
			return;
			
		}
		
		// Se sono qui, l'utente ha abbastanza credito disponibile per continuare a giocare al tavolo
		if(somma>0) {
			request.setAttribute("successMessage","Complimenti, hai vinto "+somma+" punti. Il tuo credito disponibile è di "+creditoDisponibile+" punti");
		} else {
			request.setAttribute("errorMessage","Oh no: hai perso "+(-somma)+" punti. Il tuo credito disponibile è di "+creditoDisponibile+" punti");
		}
		request.setAttribute("idTavoloDiGioco",request.getParameter("idTavoloDiGioco"));
		request.getServletContext().getRequestDispatcher("/jsp/playManagement/partita/partita.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
