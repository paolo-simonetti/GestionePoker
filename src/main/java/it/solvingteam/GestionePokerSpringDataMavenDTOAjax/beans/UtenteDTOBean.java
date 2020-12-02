package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.beans;

import java.io.Serializable;

public class UtenteDTOBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String esperienzaAccumulata;
	private String creditoDisponibile;
	private String statoUtente;
	
	private UtenteDTOBean() {
		super();
	}

	public Long getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDataRegistrazione() {
		return dataRegistrazione;
	}
	
	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	
	public String getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}
	
	public void setEsperienzaAccumulata(String esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}
	
	public String getCreditoDisponibile() {
		return creditoDisponibile;
	}
	
	public void setCreditoDisponibile(String creditoDisponibile) {
		this.creditoDisponibile = creditoDisponibile;
	}
	
	public String getStatoUtente() {
		return statoUtente;
	}
	
	public void setStatoUtente(String statoUtente) {
		this.statoUtente = statoUtente;
	}
	
	
	
}
