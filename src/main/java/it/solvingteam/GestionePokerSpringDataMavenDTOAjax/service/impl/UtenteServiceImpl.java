package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.impl;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository.UtenteRepository;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.UtenteService;

@Component
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	@Transactional(readOnly = true)
	public Set<Utente> elenca() {
		ArrayList<Utente> temporaryList=(ArrayList<Utente>) utenteRepository.findAll();
		return temporaryList.stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long idUtente) {
		return utenteRepository.findById(idUtente).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Utente utenteInstance) {
		utenteRepository.delete(utenteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Utente> findByExample(Utente example) {
		/* Non abbiamo detto nulla sul form di ricerca degli utenti. Ho deciso di fare una ricerca solo 
		 * sui parametri dell'utente (cio�, niente tavoli n� ruoli). Nella pagina di risultati, avr�
		 * una lista di utenti, e su "Dettaglio Utente" vedr� anche Ruolo e Tavolo */
		String query = "select u from Utente u where u.idUtente = u.idUtente ";	
		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and u.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and u.cognome = " + example.getCognome() + " ";
		if (StringUtils.isNotEmpty(example.getUsername()))
			query += " and u.username = " + example.getCognome() + " ";
		if (StringUtils.isNotEmpty(example.getPassword()))
			query += " and u.password = " + example.getPassword() + " ";
		if (example.getDataRegistrazione()!=null) { 
			query += " and u.dataRegistrazione = " + example.getDataRegistrazione() + " ";
		}
		
		// Trovo pi� sensato cercare gli utenti con almeno un tot di esperienza
		if(example.getEsperienzaAccumulata()!=null) {	
			query += " and u.esperienzaAccumulata >= " + example.getEsperienzaAccumulata() + " ";
		}
		
		/* Considero un admin come super admin: pu� vedere tutto, cambiare tutto, disabilitare chiunque,
		 * quindi la ricerca copre anche il credito accumulato */
		if(example.getCreditoDisponibile()!=null) {	
			query += " and u.creditoDisponibile >= " + example.getCreditoDisponibile() + " ";
		}
		
		if(example.getStatoUtente()!=null) {
			query += " and u.statoUtente = "+example.getStatoUtente() + " ";
		}
		
		return entityManager.createQuery(query, Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Utente> trovaTuttiTramiteRuoli(RuoloUtente ruoloUtente) {
		return utenteRepository.findAllByRuoli(ruoloUtente);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Utente> trovaTuttiTramiteStatoUtente(StatoUtente statoUtente) {
		return utenteRepository.findAllByStatoUtente(statoUtente);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Utente> trovaTuttiTramiteTavoloDiGioco(Tavolo tavoloDiGioco) {
		return utenteRepository.findAllByTavoloDiGioco(tavoloDiGioco);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteUsername(String username) {
		return utenteRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Utente> trovaTuttiTramiteNomeECognome(String nome, String cognome) {
		return utenteRepository.findAllByNomeAndCognome(nome,cognome);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteTavoliCreati_IdTavolo(Long idTavolo) {
		return utenteRepository.findByTavoliCreati_IdTavolo(idTavolo);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteIdConRuoli(Long idUtente) {
		return utenteRepository.findByIdWithRuoli(idUtente);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteIdConTavoloDiGioco(Long idUtente) {
		return utenteRepository.findByIdWithTavoloDiGioco(idUtente);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteIdConTavoliCreati(Long idUtente) {
		return utenteRepository.findByIdWithTavoliCreati(idUtente);
	}

	@Override
	@Transactional(readOnly = true)
	public Utente trovaTramiteIdConInformazioniComplete(Long idUtente) {
		return utenteRepository.findByIdWithCompleteInfo(idUtente);
	}

}
