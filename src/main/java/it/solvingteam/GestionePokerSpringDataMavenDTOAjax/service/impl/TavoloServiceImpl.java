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

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository.TavoloRepository;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.TavoloService;

@Component
public class TavoloServiceImpl implements TavoloService {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Set<Tavolo> elenca() {
		ArrayList<Tavolo> temporaryList=(ArrayList<Tavolo>) tavoloRepository.findAll();
		return temporaryList.stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavolo(Long idTavolo) {
		return tavoloRepository.findById(idTavolo).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Tavolo tavoloInstance) {
		tavoloRepository.delete(tavoloInstance);
	}

	@Override
	@Transactional(readOnly = true)
	/*Ho deciso di tenere una sola "search", sia per gestione tavolo, sia per la ricerca del giocatore.
	  Mi gestisco lato servlet la differenza tra i due casi (cioè, se sono in Gestione tavolo, al Tavolo
	  in input setto l'id dell'utente loggato */
	public Set<Tavolo> findByExample(Tavolo example) {
		String query = "select t from Tavolo t left join fetch t.giocatori g join fetch t.creatore c where t.idTavolo = t.idTavolo ";			
		if (StringUtils.isNotEmpty(example.getDenominazione()))
			query += " and t.denominazione like '%" + example.getDenominazione() + "%' ";
		if (example.getDataCreazione()!=null)
			query += " and t.dataCreazione = '" + example.getDataCreazione() + "' ";
		if (example.getEsperienzaMinimaRichiesta() != null && example.getEsperienzaMinimaRichiesta()>= 0)
			query += " and t.esperienzaMinimaRichiesta >= " + example.getEsperienzaMinimaRichiesta();
		if (example.getPuntataMinima() != null && example.getPuntataMinima()> 0)
			query += " and t.puntataMinima >= " + example.getPuntataMinima();
		if (!example.getGiocatori().isEmpty()) { // non serve distinguere due casi come sopra
			for(Long idGiocatore:example.getGiocatori().stream().map(giocatore->giocatore.getIdUtente())
					.collect(Collectors.toSet())) {
				query += " and g.idUtente= " + idGiocatore;							
			}
		}
		if(example.getCreatore()!=null) {
			query += " and c.idUtente = "+example.getCreatore().getIdUtente();
		}

		return entityManager.createQuery(query, Tavolo.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Tavolo> trovaTuttiTramiteDenominazione(String denominazione) {
		return tavoloRepository.findAllByDenominazione(denominazione);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Tavolo> trovaTuttiTramiteCreatore(Utente creatore) {
		return tavoloRepository.findAllByCreatore(creatore);
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo trovaTramiteIdConCreatore(Long idTavolo) {
		return tavoloRepository.findByIdWithCreatore(idTavolo);
	}

	@Override
	@Transactional(readOnly = true)
	public Tavolo trovaTramiteIdConGiocatori(Long idTavolo) {
		return tavoloRepository.findByIdWithGiocatori(idTavolo);
	}

	@Override
	public Tavolo trovaTramiteIdConInformazioniComplete(Long idTavolo) {
		return tavoloRepository.findByIdWithCompleteInfo(idTavolo);
	}

}
