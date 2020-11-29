package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.util.Set;

public interface AbstractDTO<MODEL> {
	public Set<String> errors();
	public String errorId(String id);
	public MODEL buildModelFromDTO ();
	public void buildDTOFromModel (MODEL modelInstance);
}
