package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.ricerca;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.RuoloUtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/ricerca/PrepareRicercaUtentiServlet")
public class PrepareRicercaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
  
    public PrepareRicercaUtentiServlet() {
        super();    
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setAttribute("listaRuoli",ruoloUtenteService.elenca());
		request.setAttribute("listaStati",StatoUtente.conversioneStatoUtente.values());
		request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/ricercaUtenti.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
