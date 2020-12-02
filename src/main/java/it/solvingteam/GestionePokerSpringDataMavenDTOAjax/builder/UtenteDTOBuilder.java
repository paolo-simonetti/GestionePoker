package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.builder;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.UtenteDTO;

public final class UtenteDTOBuilder {
	
	private Long idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String esperienzaAccumulata;
	private String creditoDisponibile;
	private String statoUtente;
	
	private UtenteDTOBuilder(Long idUtente) {
		super();
		this.idUtente = idUtente;
	}
	
	public static UtenteDTOBuilder nuovoBuilder(Long idUtente) {
		return new UtenteDTOBuilder(idUtente);
	}

	public UtenteDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	} 
	
	public UtenteDTOBuilder cognome(String cognome) {
		this.cognome = cognome;
		return this;
	} 
	
	public UtenteDTOBuilder username(String username) {
		this.username = username;
		return this;
	} 
	
	public UtenteDTOBuilder password(String password) {
		this.password = password;
		return this;
	} 
	
	public UtenteDTOBuilder dataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
		return this;
	} 
	
	public UtenteDTOBuilder esperienzaAccumulata(String esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
		return this;
	} 
	
	public UtenteDTOBuilder creditoDisponibile(String creditoDisponibile) {
		this.creditoDisponibile = creditoDisponibile;
		return this;
	} 
	
	public UtenteDTOBuilder statoUtente(String statoUtente) {
		this.statoUtente = statoUtente;
		return this;
	} 
	
	public UtenteDTO build() {
		return new UtenteDTO(idUtente,nome,cognome,username,password,dataRegistrazione,esperienzaAccumulata,creditoDisponibile,statoUtente);
	}
}
