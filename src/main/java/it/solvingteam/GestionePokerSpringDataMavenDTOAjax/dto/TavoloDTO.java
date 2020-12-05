package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Tavolo;

public class TavoloDTO implements AbstractDTO<Tavolo>, Comparable<TavoloDTO>, Serializable {	

	private static final long serialVersionUID = 4473145098440374271L;
	
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
				if(Integer.parseInt(this.esperienzaMinimaRichiesta)<0) {
					result.add("L'esperienza minima richiesta non può essere negativa");
				}
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
				if(Integer.parseInt(this.puntataMinima)<0) {
					result.add("La puntata minima non può essere negativa");
				}
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
	
	public Set<String> errorAggiorna(String idTavolo) {
		Set<String> result=new TreeSet<>();
		if(StringUtils.isBlank(idTavolo)) {
			result.add("Il campo id non può essere vuoto");
		}  else {
			try {
				this.idTavolo=Long.parseLong(idTavolo);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Id inserito non valido");
			}
		}
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non può essere vuoto");
		if(StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			result.add("Il campo Esperienza minima richiesta è obbligatorio e deve essere un numero");
		} else {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
				if(Integer.parseInt(this.esperienzaMinimaRichiesta)<0) {
					result.add("L'esperienza minima richiesta non può essere negativa");
				}
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
				if(Integer.parseInt(this.puntataMinima)<0) {
					result.add("La puntata minima non può essere negativa");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il campo Puntata minima richiesta deve essere un numero. Hai provato a cambiarne il tipo di input?");
				throw e;
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
			
			//Devo mettere questi controlli sugli spazi, per non far scoppiare il palleggio dei parametri di ricerca in get
			if(dataCreazione.trim().split("\\s+").length>1) {
				result.add("Nella data di creazione non possono essere presenti spazi");
			}
		}
		
		if (!StringUtils.isBlank(this.esperienzaMinimaRichiesta)) {
			try {
				Integer.parseInt(this.esperienzaMinimaRichiesta);
				if(Integer.parseInt(this.esperienzaMinimaRichiesta)<0) {
					result.add("L'esperienza minima richiesta non può essere negativa");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("L'esperienza minimaRichiesta è un numero intero!");
			}

			if(esperienzaMinimaRichiesta.trim().split("\\s+").length>1) {
				result.add("Nell'esperienza minima richiesta non possono essere presenti spazi");
			}

		}
		
		if (!StringUtils.isBlank(this.puntataMinima)) {
			try {
				Integer.parseInt(this.puntataMinima);
				if(Integer.parseInt(this.puntataMinima)<0) {
					result.add("La puntata minima non può essere negativa");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("La puntata minima è un numero intero!");
			}

			if(puntataMinima.trim().split("\\s+").length>1) {
				result.add("Nella puntata minima non possono essere presenti spazi");
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
		if(!StringUtils.isBlank(puntataMinima)) {
			result.setPuntataMinima(Integer.parseInt(puntataMinima));			
		}
		return result;
	}

	@Override
	public void buildDTOFromModel(Tavolo tavoloInstance) {
		this.setIdTavolo(tavoloInstance.getIdTavolo());
		this.setDenominazione(tavoloInstance.getDenominazione());
		if(tavoloInstance.getDataCreazione()!=null) {
			this.setDataCreazione(tavoloInstance.getDataCreazione().toString());			
		}
		
		if (tavoloInstance.getEsperienzaMinimaRichiesta()!=null) {
			this.setEsperienzaMinimaRichiesta(tavoloInstance.getEsperienzaMinimaRichiesta().toString());
		}
		
		if(tavoloInstance.getPuntataMinima()!=null) {
			this.setPuntataMinima(tavoloInstance.getPuntataMinima().toString());			
		}
		
		if(tavoloInstance.getCreatore()!=null) {
			this.setUsernameCreatore(tavoloInstance.getCreatore().getUsername());
		}
		
		if(tavoloInstance.getGiocatori().size()>0) {
			tavoloInstance.getGiocatori().stream().forEach(giocatore->this.getUsernameGiocatori().add(giocatore.getUsername()));	
		}
		
	}

	@Override
	public int compareTo(TavoloDTO tavoloDTO) {
		return denominazione.compareTo(tavoloDTO.getDenominazione());
	}

	@Override
	public String toString() {
		// Mi serve per palleggiare i parametri di ricerca nei metodi get
		String stringaDenominazione=null;
		if(!StringUtils.isBlank(denominazione)) {
			String[] paroleComponentiDenominazione=denominazione.trim().split("\\s+");
			if(paroleComponentiDenominazione.length>1) {
				stringaDenominazione=Stream.of(paroleComponentiDenominazione).reduce((parola1,parola2)->parola1+"-"+parola2).get();			
			} else if(paroleComponentiDenominazione.length==1) {
				stringaDenominazione=paroleComponentiDenominazione[0];
			}			
		}
		// Preparo lo stesso tipo di stringa per i giocatori
		String stringaGiocatori=null;
		if(usernameGiocatori.size()>1) {
			stringaGiocatori=usernameGiocatori.stream().reduce((user1,user2)->user1+"-"+user2).get();			
		} else if (usernameGiocatori.size()==1) {
			stringaGiocatori=usernameGiocatori.stream().findFirst().orElse(null);
		}
		return  "?"+(idTavolo != null ? "idTavolo=" + idTavolo +"&" : "")
				+ (!StringUtils.isBlank(denominazione) ? "denominazione=" + stringaDenominazione+"&" : "")
				+ (!StringUtils.isBlank(dataCreazione) ? "dataCreazione=" + dataCreazione+"&" : "")
				+ (!StringUtils.isBlank(esperienzaMinimaRichiesta) ? "esperienzaMinimaRichiesta=" + esperienzaMinimaRichiesta+"&": "")
				+ (!StringUtils.isBlank(puntataMinima) ? "puntataMinima=" + puntataMinima+"&" : "")
				+ (!StringUtils.isBlank(usernameCreatore) ? "usernameCreatore=" + usernameCreatore +"&" : "")
				+ (usernameGiocatori.size()>0 ? "usernameGiocatori=" + stringaGiocatori +"&": "") ;
	}
	
	public void ricostruisciCriteriDiRicercaDaDTO(HttpServletRequest request) {
		if(request.getParameter("denominazione")!=null) {
			this.denominazione=request.getParameter("denominazione").replaceAll("-"," ");					
		}
		this.dataCreazione=request.getParameter("dataCreazione");
		this.puntataMinima=request.getParameter("puntataMinima");
		this.esperienzaMinimaRichiesta=request.getParameter("esperienzaMinimaRichiesta");
		this.usernameCreatore=request.getParameter("usernameCreatore");
		
		// Preparo il criterio riguardante gli username dei giocatori, che potrebbe essere composto di più parole
		if(request.getParameter("usernameGiocatori")!=null) {
			Stream.of(request.getParameter("usernameGiocatori").split("-")).forEach(usernameGiocatore->this.usernameGiocatori.add(usernameGiocatore));
		}
	
	}
		
}
