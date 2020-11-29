package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.service;

import java.util.Set;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;

public interface UtenteService {
	
	public Set<Utente> elenca();
	
	public Utente caricaSingoloUtente(Long idUtente);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public Set<Utente> findByExample(Utente example);
	
	public Set<Utente> trovaTuttiTramiteRuoli(RuoloUtente ruoloUtente);
	
	public Set<Utente> trovaTuttiTramiteStatoUtente(StatoUtente statoUtente);
	
	public Set<Utente> trovaTuttiTramiteTavoloDiGioco(Tavolo tavoloDiGioco);
	
	public Utente trovaTramiteUsername(String username);
	
	public Set<Utente> trovaTuttiTramiteNomeECognome(String nome, String cognome);
	
	public Utente trovaTramiteTavoliCreati_IdTavolo(Long idTavolo);
	
	public Utente trovaTramiteIdConRuoli(Long idUtente);
	
	public Utente trovaTramiteIdConTavoloDiGioco(Long idUtente);
	
	public Utente trovaTramiteIdConTavoliCreati(Long idUtente);
	
	public Utente trovaTramiteIdConInformazioniComplete(Long idUtente);
	
}
