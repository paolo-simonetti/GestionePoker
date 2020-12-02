package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.web.servlet.accessoEffettuato.gestioneAmministrazione.aggiornamentoUtente;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.RuoloUtenteService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;



@WebServlet("/accessoEffettuato/gestioneAmministrazione/aggiornamentoUtente/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
  
    public ExecuteUpdateUtenteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero i dati immessi nella form
		// Parto con gli utenti che risultavano dalla ricerca iniziale, e li valido
		UtenteDTO utenteDTO=new UtenteDTO();
		String[] stringaIdRisultatiRicerca=request.getParameter("risultatoRicercaUtente").split(", ");
		Set<String> erroriNegliIdRisultatiRicerca=Arrays.asList(stringaIdRisultatiRicerca).stream()
			.filter(idStringa->utenteDTO.errorId(idStringa)!=null)
				.map(idStringa->utenteDTO.errorId(idStringa)).collect(Collectors.toSet());
		if(erroriNegliIdRisultatiRicerca.size()!=0) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage", erroriNegliIdRisultatiRicerca);
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
		}
		
		// Prendo l'utente da aggiornare, validandone prima l'id  
		if(utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare"))!=null) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage",
					utenteDTO.errorId(request.getParameter("idUtenteDaAggiornare")));
			request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
				.forward(request,response);
		}
			
		// Recupero i campi immessi dallo user nella form
		utenteDTO.setNome(request.getParameter("nome"));
		utenteDTO.setCognome(request.getParameter("cognome"));
		utenteDTO.setDataRegistrazione(request.getParameter("dataRegistrazione"));
		utenteDTO.setUsername(request.getParameter("username"));
		utenteDTO.setEsperienzaAccumulata(request.getParameter("esperienzaAccumulata"));
		utenteDTO.setCreditoDisponibile(request.getParameter("creditoDisponibile"));
		// Valido i dati sull'utente appena recuperati
		if(utenteDTO.errorAggiorna().size()!=0) {
			request.setAttribute("errorMessage",utenteDTO.errorAggiorna());
			request.setAttribute("risultatoRicercaUtente",request.getParameter("risultatoRicercaUtente"));
			request.getServletContext()
				.getRequestDispatcher("/jsp/gestioneAmministrazione/aggiornamentoUtente/update.jsp").forward(request,response);
			return;
		}
		
		Set<RuoloUtenteDTO> ruoliUtenteDTO=new TreeSet<>(); 
		Set<RuoloUtente> ruoli=new TreeSet<>();
		// Lo stato dell'utente deve essere automaticamente impostato ad attivo, se è attualmente a "creato". 
		// Altrimenti, viene preservato.
		switch(utenteService.caricaSingoloUtente(Long.parseLong(request.getParameter("idUtenteDaAggiornare")))
				.getStatoUtente().toString()) {
				case "creato": 
					utenteDTO.setStatoUtente("attivo");
					// Quando attivo l'utente, imposto anche i ruoli
					if(request.getParameterValues("selectRuoli")!=null&&
							request.getParameterValues("selectRuoli").length!=0) {
						// Impostare i ruoli vuol dire settarli anche all'utenteDTO da cui poi voglio ricostruire il model
						Arrays.asList(request.getParameterValues("selectRuoli")).stream()
							.forEach(descrizioneRuolo-> {
								RuoloUtenteDTO ruoloUtenteDTO=new RuoloUtenteDTO();
								ruoloUtenteDTO.setDescrizioneRuolo(descrizioneRuolo); 
								ruoliUtenteDTO.add(ruoloUtenteDTO);});
						ruoliUtenteDTO.stream().forEach(ruoloDTO->ruoli.add(ruoloUtenteService
								.trovaTramiteNomeRuoloConUtenti(NomeRuolo.conversioneNomeRuolo
										.get(ruoloDTO.getDescrizioneRuolo()))));
					}
					
					break;
						
				case "attivo":
					// In questo caso, voglio che i ruoli siano preservati
					utenteService.trovaTramiteIdConRuoli(Long.parseLong(request.getParameter("idUtenteDaAggiornare")))
						.getRuoli().stream().forEach(ruolo-> { 
							ruoli.add(ruolo);
							// Preservare i ruoli vuol dire settarli anche all'utenteDTO da cui poi voglio ricostruire il model
							RuoloUtenteDTO ruoloUtenteDTO=new RuoloUtenteDTO();
							ruoloUtenteDTO.setDescrizioneRuolo(ruolo.getDescrizioneRuolo());
							ruoliUtenteDTO.add(ruoloUtenteDTO);
						});
					utenteDTO.setStatoUtente("attivo");
					break;
							
				case "disabilitato":
					utenteDTO.setStatoUtente("disabilitato");
					// Non serve che dica nulla sui ruoli: se lo stato è disabilitato, l'utente non ha ruoli
					break;
						
				default: 
					request.getSession().invalidate();
					request.setAttribute("errorMessage","Hai provato a manomettere gli stati dell'utente?");
					request.getServletContext().getRequestDispatcher("/jsp/generali/welcome.jsp")
						.forward(request,response);
			}
		// Creo l'utente aggiornato a partire dai dati immessi
				Utente utenteAggiornato=utenteDTO.buildModelFromDTO();
				
				// Se l'utente ha selezionato tavoli nel campo Tavoli creati, ne valido gli id e li recupero dal db
				TavoloDTO tavoloDTO=new TavoloDTO();
				Set<String> erroriNegliId=new TreeSet<>();
				Set<Tavolo> tavoliSelezionati=new TreeSet<>();
				if(request.getParameterValues("selectTavoli")!=null &&
						request.getParameterValues("selectTavoli").length!=0) {
					// valido gli id
					erroriNegliId=Arrays.asList(request.getParameterValues("selectTavoli")).stream()
							.map(stringaId->tavoloDTO.errorId(stringaId))
								.filter(stringaErrore->!StringUtils.isBlank(stringaErrore)).collect(Collectors.toSet());
					if(erroriNegliId.size()!=0) {
						request.getSession().invalidate();
						request.setAttribute("errorMessage", erroriNegliId);
						request.getServletContext().getRequestDispatcher("/jsp/generali/menu.jsp").forward(request,response);
					}
					// se sono qui, gli id sono validi, quindi recupero i tavoli dal db e li metto in tavoliSelezionati
					Arrays.asList(request.getParameterValues("selectTavoli")).stream()
						.forEach(stringaId->tavoliSelezionati.add(tavoloService
							.trovaTramiteIdConCreatore(Long.parseLong(stringaId))));
				}
				
				// Se l'utente ha selezionato un tavolo nel campo Tavolo di gioco, ne valido l'id e lo recupero dal db
				Tavolo tavoloDiGioco=null;
				if(!StringUtils.isBlank(request.getParameter("tavoloDiGioco")))  {
					if (tavoloDTO.errorId(request.getParameter("tavoloDiGioco"))!=null) {
						request.getSession().invalidate();
						request.setAttribute("errorMessage", erroriNegliId);
						request.getServletContext().getRequestDispatcher("/jsp/generali/menu.jsp").forward(request,response);
					}
					// Se sono qui, l'id è valido e recupero il tavolo dal db
					tavoloDiGioco=tavoloService.trovaTramiteIdConGiocatori(
							Long.parseLong(request.getParameter("tavoloDiGioco")));	
				}
				
				/* Ora che ho recuperato e validato tutto, procedo con l'aggiornamento
				 Imposto l'utente aggiornato come creatore dei tavoliSelezionati. Se non ho selezionato tavoli,
				 considero che l'admin voglia che lo user sia sganciato da ogni tavolo
				 */
				utenteAggiornato.setTavoliCreati(tavoliSelezionati);
				if(tavoliSelezionati!=null && tavoliSelezionati.size()!=0) {
					tavoliSelezionati.stream().forEach(tavolo->tavolo.setCreatore(utenteAggiornato));			
				}
				
				// Imposto l'utente aggiornato come giocatore al tavoloDiGioco, con la stessa considerazione di prima
				utenteAggiornato.setTavoloDiGioco(tavoloDiGioco);
				if(tavoloDiGioco!=null) {
					tavoloDiGioco.addToGiocatori(utenteAggiornato);
				}
				
				// Se l'admin ha immesso dei ruoli e uno stato, li metto nell'utente
				if(ruoliUtenteDTO!=null&&ruoliUtenteDTO.size()!=0) {
					utenteAggiornato.setRuoli(ruoli);
					ruoli.stream().forEach(ruolo-> {
						ruolo=ruoloUtenteService.trovaTramiteNomeRuoloConUtenti(ruolo.getNomeRuolo());
						ruolo.addToUtenti(utenteAggiornato);
					});
				}

				if(!StringUtils.isBlank(utenteDTO.getStatoUtente())) {
					utenteAggiornato.setStatoUtente(StatoUtente.conversioneStatoUtente.get(utenteDTO.getStatoUtente()));
				}
				
				// Ora posso fare l'update dell'utente
				utenteService.aggiorna(utenteAggiornato);
				
				// Recupero i risultati della ricerca originaria e torno alla pagina dei risultati della ricerca
				Set<Utente> utentiRisultantiDaRicerca=Arrays.asList(stringaIdRisultatiRicerca).stream()
					.map(stringaId->utenteService.caricaSingoloUtente(Long.parseLong(stringaId)))
						.collect(Collectors.toSet());
				request.setAttribute("listaUtenti",utentiRisultantiDaRicerca);
				request.setAttribute("risultatoRicercaUtentePerGet",utenteDTO.generaRisultatoRicercaPerGet(utentiRisultantiDaRicerca)); 
				request.getServletContext().getRequestDispatcher("/jsp/gestioneAmministrazione/ricerca/risultatiRicercaUtenti.jsp")
					.forward(request,response);
				 
			}

		}

		