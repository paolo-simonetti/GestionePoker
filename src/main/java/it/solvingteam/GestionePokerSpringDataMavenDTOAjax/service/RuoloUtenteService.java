package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service;

import java.util.Set;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;

public interface RuoloUtenteService {
	
public Set<RuoloUtente> elenca();
	
	public RuoloUtente caricaSingoloRuoloUtente(Long idRuoloUtente);

	public void aggiorna(RuoloUtente ruoloUtenteInstance);

	public void inserisciNuovo(RuoloUtente ruoloUtenteInstance);

	public void rimuovi(RuoloUtente ruoloUtenteInstance);

	public Set<RuoloUtente> trovaTuttiTramiteUtenti_username(String username);

	public RuoloUtente trovaTramiteNomeRuoloConUtenti(NomeRuolo nomeRuolo);
	
}
