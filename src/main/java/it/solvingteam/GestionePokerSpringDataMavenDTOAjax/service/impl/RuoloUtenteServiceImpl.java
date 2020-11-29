package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.impl;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository.RuoloUtenteRepository;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service.RuoloUtenteService;

@Component
public class RuoloUtenteServiceImpl implements RuoloUtenteService {

	@Autowired
	private RuoloUtenteRepository ruoloUtenteRepository;
		
	@Override
	@Transactional(readOnly = true)
	public Set<RuoloUtente> elenca() {
		ArrayList<RuoloUtente> temporaryList=(ArrayList<RuoloUtente>) ruoloUtenteRepository.findAll();
		return temporaryList.stream().collect(Collectors.toSet());
	}

	@Transactional(readOnly = true)
	@Override
	public RuoloUtente caricaSingoloRuoloUtente(Long idRuoloUtente) {
		return ruoloUtenteRepository.findById(idRuoloUtente).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(RuoloUtente ruoloUtenteInstance) {
		ruoloUtenteRepository.save(ruoloUtenteInstance);
	}
	
	@Transactional
	@Override
	public void inserisciNuovo(RuoloUtente ruoloUtenteInstance) {
		ruoloUtenteRepository.save(ruoloUtenteInstance);
	}

	@Transactional
	@Override
	public void rimuovi(RuoloUtente ruoloUtenteInstance) {
		ruoloUtenteRepository.delete(ruoloUtenteInstance);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<RuoloUtente> trovaTuttiTramiteUtenti_username(String username) {
		return ruoloUtenteRepository.findAllByUtenti_username(username);
	}

	@Transactional(readOnly = true)
	@Override
	public RuoloUtente trovaTramiteNomeRuoloConUtenti(NomeRuolo nomeRuolo) {
		return ruoloUtenteRepository.findByNomeRuoloWithUtenti(nomeRuolo);
	}

}
