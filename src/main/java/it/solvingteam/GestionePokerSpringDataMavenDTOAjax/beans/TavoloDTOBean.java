package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.beans;

import java.io.Serializable;

public class TavoloDTOBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTavolo;
	private String denominazione;
	private String dataCreazione;
	private String esperienzaMinimaRichiesta;
	private String puntataMinima;
	private String usernameCreatore;
	
	public TavoloDTOBean() {}
	
	public Long getIdTavolo() {
		return idTavolo;
	}
	
	public void setIdTavolo(Long idTavolo) {
		this.idTavolo = idTavolo;
	}
	
	public String getDenominazione() {
		return denominazione;
	}
	
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	
	public String getDataCreazione() {
		return dataCreazione;
	}
	
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	public String getEsperienzaMinimaRichiesta() {
		return esperienzaMinimaRichiesta;
	}
	
	public void setEsperienzaMinimaRichiesta(String esperienzaMinimaRichiesta) {
		this.esperienzaMinimaRichiesta = esperienzaMinimaRichiesta;
	}
	
	public String getPuntataMinima() {
		return puntataMinima;
	}
	
	public void setPuntataMinima(String puntataMinima) {
		this.puntataMinima = puntataMinima;
	}
	
	public String getUsernameCreatore() {
		return usernameCreatore;
	}
	
	public void setUsernameCreatore(String usernameCreatore) {
		this.usernameCreatore = usernameCreatore;
	}
	
	
	
}
