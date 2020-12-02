package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneTavoli.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;

@WebServlet("/accessoEffettuato/gestioneTavoli/test/TestTavoloServlet")
public class TestTavoloServlet extends HttpServlet {
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
	
    public TestTavoloServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceOperazione = request.getParameter("codop");
		String queryInput = request.getParameter("queryInput");

		Object resultFromQuery = null;

		switch (codiceOperazione) {
			case "findAllByDenominazione": 
				if(StringUtils.isBlank(queryInput)) {
					request.setAttribute("errorMessage","Il campo relativo alla descrizione non può essere vuoto");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery=tavoloService.trovaTuttiTramiteDenominazione(queryInput);
				break;
			
			case "findAllByCreatore":
				if(StringUtils.isBlank(queryInput)) {
					request.setAttribute("errorMessage","Il campo relativo allo username del creatore non può essere vuoto");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/test/testSpringData.jsp").forward(request,response);
					return;
				}
				if(utenteService.trovaTramiteUsername(queryInput)==null) {
					request.setAttribute("errorMessage","Lo username inserito non corrisponde ad alcun utente");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery=tavoloService.trovaTuttiTramiteCreatore(utenteService.trovaTramiteUsername(queryInput));
				break;
				
			case "trovaTramiteIdConCreatore": 
				if(StringUtils.isBlank(queryInput)) {
					request.setAttribute("errorMessage","Il campo relativo all'id del tavolo non può essere vuoto");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery=tavoloService.trovaTramiteIdConCreatore(Long.parseLong(queryInput));
				break;
			
			case "trovaTramiteIdConGiocatori": 
				if(StringUtils.isBlank(queryInput)) {
					request.setAttribute("errorMessage","Il campo relativo all'id del tavolo non può essere vuoto");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneTavoli/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery=tavoloService.trovaTramiteIdConGiocatori(Long.parseLong(queryInput));
				break;
		}
		
		String result = resultFromQuery == null ? "" : resultFromQuery.toString();
		response.getWriter().append("Risultato: =====>>> " + codiceOperazione).append("\n").append(result);
	}

}
