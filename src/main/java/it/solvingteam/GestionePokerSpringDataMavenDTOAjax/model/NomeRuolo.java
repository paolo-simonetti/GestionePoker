package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model;

import java.util.Map;
import java.util.TreeMap;

public enum NomeRuolo {
	ROLE_ADMIN("admin"),
	ROLE_SPECIAL_PLAYER("specialPlayer"),
	ROLE_PLAYER("player");
	
	private String stringaNomeRuolo;
	
	NomeRuolo(String stringaNomeRuolo) {
		this.stringaNomeRuolo=stringaNomeRuolo;
	}
	
	@Override
	public String toString() {
		return this.stringaNomeRuolo;
	}
	
	public void setStringaNomeRuolo(String stringaNomeRuolo) {
		this.stringaNomeRuolo=stringaNomeRuolo;
	}
	
	public static Map<String,NomeRuolo> conversioneNomeRuolo=new TreeMap<>();
	
	static {
		conversioneNomeRuolo.put("admin",ROLE_ADMIN);
		conversioneNomeRuolo.put("specialPlayer",ROLE_SPECIAL_PLAYER);
		conversioneNomeRuolo.put("player",ROLE_PLAYER);	
	}
	
}
