package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.visualizzazioneUtente;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;

@WebServlet("/accessoEffettuato/gestioneAmministrazione/visualizzazioneUtente/VisualizzazioneUtenteServlet")
public class VisualizzazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzazioneUtenteServlet() {
        super();
    }
    
    @Autowired
    private UtenteService utenteService;
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Valido gli input dalla jsp
		String[] idUtentiRisultatoRicerca=request.getParameterValues("risultatoRicercaUtentePerGet");
		UtenteDTO utenteDTO=new UtenteDTO();
		if(utenteDTO.errorId(request.getParameter("idUtenteDaVisualizzare"))!=null) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage",
					utenteDTO.errorId(request.getParameter("idUtenteDaVisualizzare")));
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
		}
		utenteDTO.errorId(request.getParameter("idUtenteDaVisualizzare"));
		for (int i=0; i<idUtentiRisultatoRicerca.length;i++) {
			if(utenteDTO.errorId(idUtentiRisultatoRicerca[i])!=null) {
				request.getSession().invalidate();
				request.setAttribute("errorMessage",
						utenteDTO.errorId(request.getParameter("idUtenteDaVisualizzare")));
				request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
					.forward(request,response);
			}
		}
		
		// Se sono qui, vuol dire che gli input in get non sono stati manomessi
		// Recupero l'utente dal db, con tutte le informazioni (ruoli, tavoli)
		request.setAttribute("utenteResult",utenteService.trovaTramiteIdConInformazioniComplete
				(Long.parseLong(request.getParameter("idUtenteDaVisualizzare"))));
		request.getServletContext()
			.getRequestDispatcher("/jsp/gestioneAmministrazione/visualizzazioneUtente/get.jsp")
			.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
