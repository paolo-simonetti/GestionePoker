package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.attivazioneEDisabilitazione;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/attivazioneEDisabilitazione/OperazioniNonConfermateServlet")
public class OperazioniNonConfermateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public OperazioniNonConfermateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero i dati dalla pagina di provenienza e li palleggio alla pagina di conferma
		String[] idUtentiRisultatoRicerca=request.getParameterValues("risultatoRicercaUtentePerGet");
		UtenteDTO utenteDTO=new UtenteDTO();
		if(utenteDTO.errorId(request.getParameter("idUtenteDaAttivareODisabilitare"))!=null) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage",
				utenteDTO.errorId(request.getParameter("idUtenteDaAttivareODisabilitare")));
				request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
					.forward(request,response);
				return;
		}
		for (int i=0; i<idUtentiRisultatoRicerca.length;i++) {
			if(utenteDTO.errorId(idUtentiRisultatoRicerca[i])!=null) {
				request.getSession().invalidate();
				request.setAttribute("errorMessage",
					utenteDTO.errorId(idUtentiRisultatoRicerca[i]));
				request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
					.forward(request,response);
				return;
			}
		}
				
		// Se sono qui, i dati provenienti dalla pagina non sono stati manomessi, quindi li trasmetto alla pagina di conferma
		Set<Utente> risultatoRicercaUtente=Arrays.asList(idUtentiRisultatoRicerca).stream()
			.map(stringaId->utenteService.caricaSingoloUtente(Long.parseLong(stringaId)))
				.collect(Collectors.toSet());
		request.setAttribute("listaUtenti",risultatoRicercaUtente);
		request.setAttribute("risultatoRicercaUtentePerGet",utenteDTO.generaRisultatoRicercaPerGet(risultatoRicercaUtente));
		request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/risultatiRicercaUtenti.jsp").forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
