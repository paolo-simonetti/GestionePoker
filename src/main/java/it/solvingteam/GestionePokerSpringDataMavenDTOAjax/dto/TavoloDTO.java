package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;

public class TavoloDTO implements AbstractDTO<Tavolo> {	

	private Long idTavolo;
	private String denominazione;
	private String dataCreazione;
	private String esperienzaMinimaRichiesta;
	private String puntataMinima;
	private String usernameCreatore;
	private Set<String> usernameGiocatori = new TreeSet<>();
	
	
	
	public TavoloDTO(Long idTavolo, String denominazione, String dataCreazione, String esperienzaMinimaRichiesta,
			String puntataMinima, String usernameCreatore) {
		super();
		this.idTavolo = idTavolo;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.esperienzaMinimaRichiesta = esperienzaMinimaRichiesta;
		this.puntataMinima = puntataMinima;
		this.usernameCreatore = usernameCreatore;
	}
	
	public TavoloDTO() {}

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

	public Set<String> getUsernameGiocatori() {
		return usernameGiocatori;
	}

	@Override
	public Set<String> errors() {
		Set<String> result = new TreeSet<String>();
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non pu� essere vuoto");
		if(StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			result.add("Il campo Esperienza minima richiesta non pu� essere vuoto");
		} else {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Esperienza minima richiesta deve essere un numero");
			}
		}
		if(StringUtils.isBlank(this.puntataMinima)) {
			result.add("Il campo Puntata minima non pu� essere vuoto");
		} else {
			try {
				Integer.parseInt(puntataMinima);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Puntata minima deve essere un numero");
			}
		}
		if(StringUtils.isBlank(this.usernameCreatore))
			result.add("Il campo Creatore non pu� essere vuoto");
		return result;
	}

	@Override
	public String errorId(String idTavolo) {
		String result=null;
		if(StringUtils.isBlank(idTavolo)) {
			result="Il campo id non pu� essere vuoto";
		}  else {
			try {
				this.idTavolo=Long.parseLong(idTavolo);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result ="Id inserito non valido";
			}
		}
		
		return result;
	}

	@Override
	public Tavolo buildModelFromDTO() {
		Tavolo result = new Tavolo();
		result.setIdTavolo(idTavolo);
		result.setDenominazione(denominazione);
		result.setDataCreazione(LocalDate.parse(dataCreazione));
		result.setEsperienzaMinimaRichiesta(Integer.parseInt(esperienzaMinimaRichiesta));
		result.setPuntataMinima(Integer.parseInt(puntataMinima));
		return result;
	}

	@Override
	public void buildDTOFromModel(Tavolo tavoloInstance) {
		this.setIdTavolo(tavoloInstance.getIdTavolo());
		this.setDenominazione(tavoloInstance.getDenominazione());
		this.setDataCreazione(tavoloInstance.getDataCreazione().toString());
		this.setEsperienzaMinimaRichiesta(tavoloInstance.getEsperienzaMinimaRichiesta().toString());
		this.setPuntataMinima(tavoloInstance.getPuntataMinima().toString());
		this.setUsernameCreatore(tavoloInstance.getCreatore().getUsername());
		tavoloInstance.getGiocatori().stream().forEach(giocatore->this.getUsernameGiocatori().add(giocatore.getUsername()));
	}

}
