package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.aggiornamentoTavolo;

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


@WebServlet("/accessoEffettuato/gestioneTavoli/aggiornamentoTavolo/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareUpdateTavoloServlet() {
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
		// Recupero l'id del tavolo da aggiornare e lo valido
		TavoloDTO tavoloDTO=new TavoloDTO();
		if(tavoloDTO.errorId(request.getParameter("idTavoloDaAggiornare"))!=null) {
			request.setAttribute("errorMessage",tavoloDTO.errorId(request.getParameter("idTavoloDaAggiornare")));
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		} 
		
		// Ricostruisco il tavolo da aggiornare
		Tavolo tavoloDaAggiornare=tavoloService.trovaTramiteIdConInformazioniComplete(Long.parseLong(request.getParameter("idTavoloDaAggiornare")));
		// Blocco, lato back-end, il tentativo di aggiornare un tavolo se ha giocatori
		if(tavoloDaAggiornare.getGiocatori().size()>0) {
			request.setAttribute("errorMessage","Hai tentato di aggiornare un tavolo in cui stanno giocando degli utenti.");
			request.getSession().invalidate();
			// Se hai attivato questa servlet, nonostante io abbia nascosto il pulsante nei risultati di ricerca, sei una brutta persona
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		// Blocco, lato back-end, il tentativo di aggiornare un tavolo non appartenente all'utente in sessione
		if(!tavoloDaAggiornare.getCreatore().getUsername().equals(((Utente) request.getSession().getAttribute("utenteIdentificato")).getUsername())) {
			request.setAttribute("errorMessage","Hai tentato di aggiornare un tavolo non tuo.");
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		// Trasformo il tavolo in DTO 
		tavoloDTO.buildDTOFromModel(tavoloDaAggiornare);
		
		// Palleggio i criteri della ricerca precedente e vado in pagina
		request.setAttribute("criteriDiRicerca",criteriDiRicerca);
		request.setAttribute("tavoloDaAggiornare",tavoloDTO);
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/aggiornamentoTavolo/update.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
