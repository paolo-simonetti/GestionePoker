package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model;

import java.util.TreeMap;

public enum StatoUtente {
	CREATO ("creato"),
	ATTIVO ("attivo"),
	DISABILITATO("disabilitato");
	
	private String stringaStato;
	
	StatoUtente(String stringaStato) {
		this.stringaStato=stringaStato;
	}

	@Override
	public String toString() {
		return stringaStato;
	}

	public void setStringaStato(String stringaStato) {
		this.stringaStato = stringaStato;
	}
	
	public static TreeMap<String,StatoUtente> conversioneStatoUtente=new TreeMap<>();
	
	static {
		conversioneStatoUtente.put("creato",CREATO);
		conversioneStatoUtente.put("attivo",ATTIVO);
		conversioneStatoUtente.put("disabilitato",DISABILITATO);		
	}
	
}
