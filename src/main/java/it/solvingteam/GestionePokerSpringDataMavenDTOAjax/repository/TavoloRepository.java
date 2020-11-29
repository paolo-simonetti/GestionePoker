package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, QueryByExampleExecutor<Tavolo> {
	
	Set<Tavolo> findAllByDenominazione(String denominazione);
	
	// per trovare solo i tavoli vuoti, faccio un service apposito con stream
	Set<Tavolo> findAllByCreatore(Utente creatore); 
	
	@Query("from Tavolo t join fetch t.creatore where t.idTavolo = ?1")
	Tavolo findByIdWithCreatore(Long idTavolo);
	
	@Query("from Tavolo t left join fetch t.giocatori g where t.idTavolo = ?1")
	Tavolo findByIdWithGiocatori(Long idTavolo);
	
}
