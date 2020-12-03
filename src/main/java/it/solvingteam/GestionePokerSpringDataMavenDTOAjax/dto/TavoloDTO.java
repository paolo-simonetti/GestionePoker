package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;

public class TavoloDTO implements AbstractDTO<Tavolo>, Comparable<TavoloDTO> {	

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

	public void setUsernameGiocatori(Set<String> usernameGiocatori) {
		this.usernameGiocatori = usernameGiocatori;
	}

	@Override
	public Set<String> errors() throws NumberFormatException {
		Set<String> result = new TreeSet<String>();
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non può essere vuoto");
		if(StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			result.add("Il campo Esperienza minima richiesta è obbligatorio e deve essere un numero");
		} else {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Esperienza minima richiesta deve essere un numero. Hai provato a cambiarne il tipo di input?");
				throw e;
			}
		}
		if(StringUtils.isBlank(this.puntataMinima)) {
			result.add("Il campo Puntata minima è obbligatorio e deve essere un numero");
		} else {
			try {
				Integer.parseInt(puntataMinima);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Puntata minima richiesta deve essere un numero. Hai provato a cambiarne il tipo di input?");
				throw e;
			}
		}
		if(StringUtils.isBlank(this.usernameCreatore))
			result.add("Il campo Creatore non può essere vuoto");
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
	
	public Set<String> errorRicerca() {
		Set<String> result=new TreeSet<>();
		if(!StringUtils.isBlank(this.dataCreazione)) {
			try {
				LocalDate.parse(this.dataCreazione);
			} catch(DateTimeParseException e) {
				e.printStackTrace();
				result.add("Data inserita non valida!");
			}
		}
		
		if (!StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("L'esperienza minimaRichiesta è un numero intero!");
			}
		}
		
		if (!StringUtils.isBlank(this.puntataMinima)) {
			try {
				Integer.parseInt(this.puntataMinima);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("La puntata minima è un numero intero!");
			}
		}
		
		return result;
	}

	@Override
	public Tavolo buildModelFromDTO() {
		Tavolo result = new Tavolo();
		result.setIdTavolo(idTavolo);
		result.setDenominazione(denominazione);
		if(!StringUtils.isBlank(dataCreazione)) {			
			result.setDataCreazione(LocalDate.parse(dataCreazione));
		} 
		if(!StringUtils.isBlank(esperienzaMinimaRichiesta)) {
			result.setEsperienzaMinimaRichiesta(Integer.parseInt(esperienzaMinimaRichiesta));			
		}
		if(!StringUtils.isBlank(esperienzaMinimaRichiesta)) {
			result.setPuntataMinima(Integer.parseInt(puntataMinima));			
		}
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

	@Override
	public int compareTo(TavoloDTO tavoloDTO) {
		return denominazione.compareTo(tavoloDTO.getDenominazione());
	}

}
