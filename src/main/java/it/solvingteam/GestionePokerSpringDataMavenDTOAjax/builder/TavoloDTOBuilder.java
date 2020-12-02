package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.builder;



import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto.TavoloDTO;

public final class TavoloDTOBuilder {
	private Long idTavolo;
	private String denominazione;
	private String dataCreazione;
	private String esperienzaMinimaRichiesta;
	private String puntataMinima;
	private String usernameCreatore;
	
	private TavoloDTOBuilder(Long idTavolo) {
		super();
		this.idTavolo = idTavolo;
	}
	
	public static TavoloDTOBuilder nuovoBuilder(Long idTavolo) {
		return new TavoloDTOBuilder(idTavolo);
	}
	
	public TavoloDTOBuilder denominazione(String denominazione) {
		this.denominazione=denominazione;
		return this;
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
	
	public TavoloDTO build() {
		return new TavoloDTO(idTavolo,denominazione,dataCreazione,esperienzaMinimaRichiesta,puntataMinima,usernameCreatore);
	}
	
}
