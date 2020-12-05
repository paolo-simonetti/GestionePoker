package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.visualizzazioneTavolo;

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


@WebServlet("/accessoEffettuato/gestioneTavoli/visualizzazioneTavolo/VisualizzazioneTavoloServlet")
public class VisualizzazioneTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzazioneTavoloServlet() {
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
		if(tavoloDTO.errorId(request.getParameter("idTavoloDaVisualizzare"))!=null) {
			request.setAttribute("errorMessage",request.getParameter("idTavoloDaVisualizzare"));
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		} 
		
		// Se sono qui, vuol dire che l'id è valido e lo posso parsare
		Tavolo tavoloDaVisualizzare=tavoloService.trovaTramiteIdConGiocatori(Long.parseLong(request.getParameter("idTavoloDaVisualizzare")));
		tavoloDaVisualizzare.setCreatore((Utente) request.getSession().getAttribute("utenteIdentificato"));
		// Lo trasformo in DTO e lo passo alla pagina di visualizzazione
		tavoloDTO.buildDTOFromModel(tavoloDaVisualizzare);
		request.setAttribute("tavoloResult", tavoloDTO);
		request.setAttribute("criteriDiRicerca",criteriDiRicerca);
		request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/visualizzazioneTavolo/get.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
