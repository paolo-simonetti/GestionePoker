package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.builder;



import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;

public final class TavoloDTOBuilder {
	private String denominazione;
	private String dataCreazione;
	private String esperienzaMinimaRichiesta;
	private String puntataMinima;
	private String usernameCreatore;
	private Long idTavolo;
	
	private TavoloDTOBuilder(String denominazione) {
		super();
		this.denominazione = denominazione;
	}
	
	public static TavoloDTOBuilder nuovoBuilder(String denominazione) {
		return new TavoloDTOBuilder(denominazione);
	}
		
	public TavoloDTOBuilder dataCreazione(String dataCreazione) {
		this.dataCreazione=dataCreazione;
		return this;
	}
	
	public TavoloDTOBuilder esperienzaMinimaRichiesta(String esperienzaMinimaRichiesta) {
		this.esperienzaMinimaRichiesta=esperienzaMinimaRichiesta;
		return this;
	}
	
	public TavoloDTOBuilder puntataMinima(String puntataMinima) {
		this.puntataMinima=puntataMinima;
		return this;
	}
	
	public TavoloDTOBuilder usernameCreatore(String usernameCreatore) {
		this.usernameCreatore=usernameCreatore;
		return this;
	}

	public TavoloDTOBuilder idTavolo(Long idTavolo) {
		this.idTavolo=idTavolo;
		return this;
	}

	public TavoloDTO build() {
		return new TavoloDTO(idTavolo,denominazione,dataCreazione,esperienzaMinimaRichiesta,puntataMinima,usernameCreatore);
	}
	
}
