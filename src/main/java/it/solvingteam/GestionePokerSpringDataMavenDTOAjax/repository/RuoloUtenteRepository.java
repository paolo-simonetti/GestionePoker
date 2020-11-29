package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;

public interface RuoloUtenteRepository extends CrudRepository<RuoloUtente, Long>, QueryByExampleExecutor<RuoloUtente> {
	
	Set<RuoloUtente> findAllByUtenti_username(String username);
	
	@Query("from RuoloUtente r left join fetch r.utenti u where r.nomeRuolo like ?1")
	RuoloUtente findByNomeRuoloWithUtenti(NomeRuolo nomeRuolo);
	
	
}
