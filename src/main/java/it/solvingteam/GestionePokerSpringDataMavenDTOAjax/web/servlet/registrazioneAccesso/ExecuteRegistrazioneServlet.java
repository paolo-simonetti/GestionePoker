package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.registrazioneAccesso;

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

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.RuoloUtenteService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;



@WebServlet("/registrazioneAccesso/ExecuteRegistrazioneServlet")
public class ExecuteRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Autowired
	private UtenteService utenteService;
   
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
	
    public ExecuteRegistrazioneServlet() {
        super();
       
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UtenteDTO utenteDTO=new UtenteDTO();
		utenteDTO.setNome(request.getParameter("nome"));
		utenteDTO.setCognome(request.getParameter("cognome"));
		utenteDTO.setUsername(request.getParameter("username"));
		utenteDTO.setPassword(request.getParameter("password"));
		
		// Fiocchetto: mettere una lunghezza minima per username e password 
		// Se non hai riempito i campi richiesti, torni alla pagina di registrazione
		if(!utenteDTO.errors().isEmpty()) {
			request.setAttribute("errorMessage",utenteDTO.errors());
			request.setAttribute("utenteDTO",utenteDTO);
			request.getServletContext().getRequestDispatcher("/jsp/generali/registrazione.jsp").forward(request,response);
			return;
		} 
		 
		// Se sono qui, hai riempito i campi richiesti
		utenteDTO.setDataRegistrazione(LocalDate.now().toString());
		Utente utenteDaRegistrare=utenteDTO.buildModelFromDTO();
				
		// Recupero gli utenti iscritti, per poter verificare se l'utente si sta registrando con credenziali già usate
		Set<Utente> listaIscritti=null;
		try {
			listaIscritti= utenteService.elenca();			
		} catch(Exception e) {
			System.err.println("Problemi nel recupero degli utenti iscritti");
			e.printStackTrace();
			request.setAttribute("errorMessage","Problemi nel recupero degli utenti iscritti");
			request.setAttribute("utenteDTO",utenteDTO);
			request.getServletContext().getRequestDispatcher("/jsp/generali/registrazione.jsp").forward(request,response);
			return;
		}
		if (listaIscritti.isEmpty()) { 
			/* Do al primo iscritti i ruoli.
			Inserisco i ruoli ammessi sul db, se non già presenti */
			Set<RuoloUtente> ruoliPresenti=null;
			try {
				ruoloUtenteService.inserisciNuovo(new RuoloUtente("admin"));
				ruoloUtenteService.inserisciNuovo(new RuoloUtente("specialPlayer"));
				ruoloUtenteService.inserisciNuovo(new RuoloUtente("player"));
				ruoliPresenti=ruoloUtenteService.elenca();
			} catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage","Problemi nell'inserimento dei ruoli ammessi");
				request.setAttribute("utenteDTO",utenteDTO);
				request.getServletContext().getRequestDispatcher("/jsp/generali/registrazione.jsp").forward(request,response);
				return;
			}
			for(RuoloUtente r:ruoliPresenti) {
				utenteDaRegistrare.addToRuoli(r);
			}
			
			// Provo ad inserire sul db questo primo utente
			try {
				utenteService.inserisciNuovo(utenteDaRegistrare);
				request.setAttribute("successMessage","Registrazione effettuata!");
			} catch (Exception e) {
				request.setAttribute("errorMessage","Registrazione fallita: inserimento dell'utente nel database non riuscito");		
				request.setAttribute("utenteDTO",utenteDTO);
				e.printStackTrace();
			} 		
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request, response);				
			return;
			
		} else { 
			/* Verifico che lo username non sia già stato usato. Ignoro i whitespaces e il case, per 
			 * evitare problemi nelle ricerche che farà l'admin poi	*/
			boolean utenteGiaRegistrato=false;
			for (Utente u:listaIscritti) {
				if (utenteDTO.getUsername().trim().equalsIgnoreCase(u.getUsername().trim())) {
					utenteGiaRegistrato=true;
					break;
				}
			}
			
			//Se lo username è già stato usato, lo rimando alla pagina di partenza con un messaggio d'errore
			if (utenteGiaRegistrato) {
				request.setAttribute("errorMessage", "Username non disponibile. Forse ti sei già registrato e non ricordi le tue credenziali?");
				request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
				return;
			} else {
				// Altrimenti, il nuovo utente si registra; il ruolo e l'abilitazione glieli darà l'admin
				try {
					utenteService.inserisciNuovo(utenteDaRegistrare);
					request.setAttribute("successMessage","Registrazione effettuata!");
				} catch (Exception e) {
					request.setAttribute("errorMessage","Registrazione fallita: inserimento dell'utente nel database non riuscito");		
					request.setAttribute("utenteDTO",utenteDTO);
					e.printStackTrace();
				} finally {
					request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request, response);					
				}
			}
			
		}
	}

}
