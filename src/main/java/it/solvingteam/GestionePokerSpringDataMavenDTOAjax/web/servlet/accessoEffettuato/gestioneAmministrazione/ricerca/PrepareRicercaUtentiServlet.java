package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.ricerca;

import java.io.IOException;
import java.util.Set;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.RuoloUtenteService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/ricerca/PrepareRicercaUtentiServlet")
public class PrepareRicercaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
  
	@Autowired
	private TavoloService tavoloService;
	
    public PrepareRicercaUtentiServlet() {
        super();    
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		/* Ho scelto di portare informazioni sui tavoli: l'admin qui cerca tutti i tavoli presenti;
		 * nella sezione GestioneTavoli, cercherà solo quelli che ha creato */
		Set<RuoloUtente> listaRuoli=ruoloUtenteService.elenca();
		// Devo cercare anche gli utenti che ancora non hanno un ruolo, cioé quelli appena registrati,
		//altrimenti non posso mai assegnarlo!
		listaRuoli.add(null);
		request.setAttribute("listaRuoli",listaRuoli);
		request.setAttribute("listaStati",StatoUtente.conversioneStatoUtente.values());
		/* recupero la lista dei tavoli; per ora, non mi serve l'informazione sui loro creatori,  
		 * la prendo nell'executeRicerca */
		request.setAttribute("listaTavoli", tavoloService.elenca());
		request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/ricercaUtenti.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
