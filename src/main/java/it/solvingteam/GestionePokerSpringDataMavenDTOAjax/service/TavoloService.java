package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service;

import java.util.Set;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;

public interface TavoloService {

public Set<Tavolo> elenca();
	
	public Tavolo caricaSingoloTavolo(Long idTavolo);

	public void aggiorna(Tavolo tavoloInstance);

	public void inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Tavolo tavoloInstance);

	public Set<Tavolo> findByExample(Tavolo example);
	
	public Set<Tavolo> trovaTuttiTramiteDenominazione(String denominazione);
	
	public Set<Tavolo> trovaTuttiTramiteCreatore(Utente creatore);
	
	public Tavolo trovaTramiteIdConCreatore(Long idTavolo);
	
	public Tavolo trovaTramiteIdConGiocatori(Long idTavolo);
	
}
