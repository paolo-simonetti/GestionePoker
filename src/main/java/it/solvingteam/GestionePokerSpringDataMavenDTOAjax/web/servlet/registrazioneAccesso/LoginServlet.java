package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.registrazioneAccesso;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/registrazioneAccesso/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	@Autowired
	private UtenteService utenteService;
	
    public LoginServlet() {
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
		utenteDTO.setUsername(request.getParameter("username"));
		utenteDTO.setPassword(request.getParameter("password"));
		// Se ho lasciato vuoti user e/o password, torno in pagina
		if (!utenteDTO.errorLogin().isEmpty()) {
			request.setAttribute("utenteDTO",utenteDTO);
			request.setAttribute("errorMessage",utenteDTO.errorLogin());
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		
		// Controllo se l'utente è registrato e se ha inserito le credenziali corrette
		Set<Utente> listaIscritti=null;
		try {
			// qui non serve caricare tutte le loro informazioni (tavoli creati, tavolo di gioco, ruolo)
			listaIscritti= utenteService.elenca();			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("utenteDTO",utenteDTO);

			request.setAttribute("errorMessage","Problemi nel recupero degli utenti iscritti");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
			return;
		}
		boolean utenteIsRegistrato=false;
		Utente utenteIdentificato=null;
		for (Utente u:listaIscritti) {
			if (utenteDTO.getUsername().equals(u.getUsername()) && utenteDTO.getPassword().equals(u.getPassword())) {
				utenteIsRegistrato=true;
				utenteIdentificato=u;
				break;
			}
		}
		
		/* Se il controllo è andato a buon fine, butto l'utente in sessione, con tutte le sue informazioni
		 * (i ruoli che ha, i tavoli che ha creato e quelli in cui sta giocando)
		 */
		if (utenteIsRegistrato) {
			HttpSession session=request.getSession(true);
			Utente utenteConInformazioniComplete=utenteService
					.trovaTramiteIdConInformazioniComplete(utenteIdentificato.getIdUtente());
			session.setAttribute("utenteIdentificato",utenteConInformazioniComplete);

			// Non è necessario, perché sono info che ho già dall'utente che ho messo in sessione,
			// ma è comodo salvare in sessione dei booleani che verifichino se l'utente ha un certo ruolo
			boolean isAdmin=false;
			boolean isSpecialPlayer=false;
			boolean isPlayer=false;
			for (RuoloUtente r:utenteConInformazioniComplete.getRuoli()) {
				if (r.equals(new RuoloUtente("admin"))) {
					isAdmin=true;
				}
				
				if (r.equals(new RuoloUtente("specialPlayer"))) {
					isSpecialPlayer=true;
				}
				
				if (r.equals(new RuoloUtente("player"))) {
					isPlayer=true;
				}
			}
			session.setAttribute("isAdmin",isAdmin);
			session.setAttribute("isSpecialPlayer",isSpecialPlayer);
			session.setAttribute("isPlayer",isPlayer);
			
			// Mi rimane comodo, per la schermata di gestione tavoli, portare in sessione un booleano che indichi se l'utente ha creato tavoli
			if (utenteConInformazioniComplete.getTavoliCreati()==null||utenteConInformazioniComplete.getTavoliCreati().size()==0) {
				session.setAttribute("haCreatoTavoli",false);
			} else {
				session.setAttribute("haCreatoTavoli",true);
			}
			
			request.getServletContext().getRequestDispatcher("/jsp/generali/menu.jsp").forward(request,response);
		} else {
			request.setAttribute("utenteDTO",utenteDTO);
			request.setAttribute("errorMessage","Le credenziali inserite sono errate o non sei registrato");
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp").forward(request,response);
		}
	}

}
