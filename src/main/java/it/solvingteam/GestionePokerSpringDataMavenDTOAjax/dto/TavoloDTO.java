package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;

public class TavoloDTO implements AbstractDTO<Tavolo> {
	//TODO: scrivere una validazione a parte per le ricerche (LocalDate.parse(), Integer.parseInt())	

	private Long idTavolo;
	private String denominazione;
	private String dataCreazione;
	private String esperienzaMinimaRichiesta;
	private String puntataMinima;
	
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

	@Override
	public Set<String> errors() {
		Set<String> result = new TreeSet<String>();
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non può essere vuoto");
		if(StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			result.add("Il campo Esperienza minima richiesta non può essere vuoto");
		} else {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Esperienza minima richiesta deve essere un numero");
			}
		}
		if(StringUtils.isBlank(this.puntataMinima)) {
			result.add("Il campo Puntata minima non può essere vuoto");
		} else {
			try {
				Integer.parseInt(puntataMinima);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Puntata minima deve essere un numero");
			}
		}
		return result;
	}

	@Override
	public String errorId(String idTavolo) {
		String result=null;
		if(StringUtils.isBlank(idTavolo)) {
			result="Il campo id non può essere vuoto";
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
	}

}
