package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {
	
	Set<Utente> findAllByRuoli(RuoloUtente ruoloUtente);
	Set<Utente> findAllByStatoUtente(StatoUtente statoUtente);
	Set<Utente> findAllByTavoloDiGioco(Tavolo tavoloDiGioco);
	Utente findByUsername(String username);
	Set<Utente> findAllByNomeAndCognome(String nome, String cognome);
	Utente findByTavoliCreati_IdTavolo(Long idTavolo);
	
	@Query("from Utente u join fetch u.ruoli where u.idUtente = ?1")
	Utente findByIdWithRuoli(Long idUtente);
	
	@Query("from Utente u join fetch u.tavoloDiGioco where u.idUtente = ?1")
	Utente findByIdWithTavoloDiGioco(Long idUtente);
	
	@Query("from Utente u join fetch u.tavoliCreati where u.idUtente = ?1")
	Utente findByIdWithTavoliCreati(Long idUtente);
	
	@Query("from Utente u join fetch u.tavoliCreati join fetch u.tavoloDiGioco join fetch u.ruoli where idUtente=?1")
	Utente findByIdWithCompleteInfo(Long idUtente);
}
