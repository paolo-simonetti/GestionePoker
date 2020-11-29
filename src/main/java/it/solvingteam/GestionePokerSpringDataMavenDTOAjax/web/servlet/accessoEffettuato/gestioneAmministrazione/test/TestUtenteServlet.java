package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.test;

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

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.RuoloUtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;


@WebServlet("/accessoEffettuato/gestioneAmministrazione/test/TestUtenteServlet")
public class TestUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
   
    public TestUtenteServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceOperazione = request.getParameter("codop");
		String queryInput = request.getParameter("queryInput");

		Object resultFromQuery = null;

		switch (codiceOperazione) {
			case "findAllByRuoli":
				RuoloUtenteDTO ruoloUtenteDTO=new RuoloUtenteDTO();
				ruoloUtenteDTO.setDescrizioneRuolo(queryInput);
				if (ruoloUtenteDTO.errors().size()!=0) {
					request.setAttribute("errorMessage",ruoloUtenteDTO.errors());
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				if(NomeRuolo.conversioneNomeRuolo.get(queryInput)==null) {
					request.setAttribute("errorMessage","Ruolo non configurato");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
		
				resultFromQuery = utenteService.trovaTuttiTramiteRuoli(queryInput);
				break;
		
			case "findAllByStatoUtente":
				if(StatoUtente.conversioneStatoUtente.get(queryInput)==null) {
					request.setAttribute("errorMessage","Stato non configurato!");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTuttiTramiteStatoUtente(StatoUtente.conversioneStatoUtente.get(queryInput));
				break;
			
			case "findAllByTavoloDiGioco":
				TavoloDTO tavoloDTO=new TavoloDTO();
				tavoloDTO.setDenominazione(queryInput);
				tavoloDTO.setEsperienzaMinimaRichiesta(request.getParameter("queryInputEsperienza"));
				tavoloDTO.setUsernameCreatore(request.getParameter("queryInputCreatore"));
				tavoloDTO.setPuntataMinima(request.getParameter("queryInputPuntata"));
				if (tavoloDTO.errors().size()!=0) {
					request.setAttribute("errorMessage",tavoloDTO.errors());
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				Tavolo tavolo=tavoloDTO.buildModelFromDTO();
				Utente creatore=utenteService.trovaTramiteUsername(tavoloDTO.getUsernameCreatore());
				tavolo.setCreatore(creatore);
				resultFromQuery = utenteService.trovaTuttiTramiteTavoloDiGioco(tavolo);
				break;
				
			case "findByUsername":
				if(StringUtils.isBlank(queryInput)) {
					request.setAttribute("errorMessage","Inserire uno username!");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteUsername(queryInput);
				break;
				
			case "findAllByNomeAndCognome":
				if(StringUtils.isBlank(queryInput)||StringUtils.isBlank(request.getParameter("queryInputCognome"))) {
					request.setAttribute("errorMessage","Inserire un nome e un cognome!");
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTuttiTramiteNomeECognome(queryInput,request.getParameter("queryInputCognome"));
				break;
			
			case "findByTavoliCreati_IdTavolo":
				TavoloDTO tavoloDTO2=new TavoloDTO();
				if(tavoloDTO2.errorId(queryInput)!=null) {
					request.setAttribute("errorMessage",tavoloDTO2.errorId(queryInput));
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteTavoliCreati_IdTavolo(Long.parseLong(queryInput));
				break;
			
			case "findByIdWithRuoli":
				UtenteDTO utenteDTO=new UtenteDTO();
				if(utenteDTO.errorId(queryInput)!=null) {
					request.setAttribute("errorMessage",utenteDTO.errorId(queryInput));
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteIdConRuoli(Long.parseLong(queryInput));
				break;	
			
			case "findByIdWithTavoloDiGioco":
				UtenteDTO utenteDTO2=new UtenteDTO();
				if(utenteDTO2.errorId(queryInput)!=null) {
					request.setAttribute("errorMessage",utenteDTO2.errorId(queryInput));
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteIdConTavoloDiGioco(Long.parseLong(queryInput));
				break;	
			
			case "findByIdWithTavoliCreati":
				UtenteDTO utenteDTO3=new UtenteDTO();
				if(utenteDTO3.errorId(queryInput)!=null) {
					request.setAttribute("errorMessage",utenteDTO3.errorId(queryInput));
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteIdConTavoliCreati(Long.parseLong(queryInput));
				break;	
				
			case "findByIdWithCompleteInfo":
				UtenteDTO utenteDTO4=new UtenteDTO();
				if(utenteDTO4.errorId(queryInput)!=null) {
					request.setAttribute("errorMessage",utenteDTO4.errorId(queryInput));
					request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/test/testSpringData.jsp").forward(request,response);
					return;
				}
				resultFromQuery = utenteService.trovaTramiteIdConInformazioniComplete(Long.parseLong(queryInput));
				break;	
				
			default:
				break;
		}

		String result = resultFromQuery == null ? "" : resultFromQuery.toString();

		response.getWriter().append("Risultato: =====>>> " + codiceOperazione).append("\n").append(result);
		
	}

}
