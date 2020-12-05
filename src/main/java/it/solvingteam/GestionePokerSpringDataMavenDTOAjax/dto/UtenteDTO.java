package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.StatoUtente;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.Utente;

public class UtenteDTO implements AbstractDTO<Utente> {
// Usare il design factory per i costruttori
	
	private Long idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String esperienzaAccumulata;
	private String creditoDisponibile;
	private String statoUtente;
	
	public UtenteDTO() {}

	public UtenteDTO(Long idUtente, String nome, String cognome, String username, String password,
			String dataRegistrazione, String esperienzaAccumulata, String creditoDisponibile, String statoUtente) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.esperienzaAccumulata = esperienzaAccumulata;
		this.creditoDisponibile = creditoDisponibile;
		this.statoUtente = statoUtente;
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
	
	public Set<String> errorAggiorna() {
		Set<String> result = new TreeSet<>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non pu� essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non pu� essere vuoto");
		if(StringUtils.isBlank(this.username)) {
			result.add("Il campo username non pu� essere vuoto");
		} else if(this.username.trim().split("\\s+").length>1) {
			result.add("Nel campo username non possono essere inseriti spazi");
		} else if(this.username.trim().split("-").length>1) {
			result.add("Nel campo username non possono essere inseriti trattini '-'");
		}
		if(!StringUtils.isBlank(this.dataRegistrazione)) {
			try {
				LocalDate.parse(this.dataRegistrazione);
			} catch(DateTimeParseException e) {
				e.printStackTrace();
				result.add("Data inserita non valida!");
			}
		}
		
		if (!StringUtils.isBlank(this.esperienzaAccumulata)) {
			try {
				Integer.parseInt(this.esperienzaAccumulata);
				if(Integer.parseInt(this.esperienzaAccumulata)<0) {
					result.add("L'esperienza accumulata non pu� essere negativa");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("L'esperienza accumulata � un numero intero!");
			}
		}
		
		if (!StringUtils.isBlank(this.creditoDisponibile)) {
			try {
				Integer.parseInt(this.creditoDisponibile);
				if(Integer.parseInt(this.creditoDisponibile)<0) {
					result.add("Il credito disponibile non pu� essere negativo");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il credito disponibile � un numero intero!");
			}
		}
		return result;
	}
	
	@Override
	public Set<String> errors() {
		/* non invoco questo metodo nelle pagine dedicate alle funzioni di ricerca
		   perch� l� la validazione non serve (credo?) */
		Set<String> result = new TreeSet<>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non pu� essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non pu� essere vuoto");
		if(StringUtils.isBlank(this.username)) {
			result.add("Il campo username non pu� essere vuoto");			
		} else if(this.username.trim().split("\\s+").length>1) {
			result.add("Nel campo username non possono essere inseriti spazi");
		} else if(this.username.trim().split("-").length>1) {
			result.add("Nel campo username non possono essere inseriti trattini '-'");
		}
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non pu� essere vuoto");
		return result;
	}

	public Set<String> errorLogin() {
		// Nella pagina di Login, inserisco solo utente e password, quindi li devo validare
		Set<String> result=new TreeSet<>();
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non pu� essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non pu� essere vuoto");
		return result;
	}
	
	public Set<String> errorRicerca() {
		Set<String> result=new TreeSet<>();
		if(!StringUtils.isBlank(this.dataRegistrazione)) {
			try {
				LocalDate.parse(this.dataRegistrazione);
			} catch(DateTimeParseException e) {
				e.printStackTrace();
				result.add("Data inserita non valida!");
			}
		}
		
		if (!StringUtils.isBlank(this.esperienzaAccumulata)) {
			try {
				Integer.parseInt(this.esperienzaAccumulata);
				if(Integer.parseInt(this.esperienzaAccumulata)<0) {
					result.add("L'esperienza accumulata non pu� essere negativa");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("L'esperienza accumulata � un numero intero!");
			}
		}
		
		if (!StringUtils.isBlank(this.creditoDisponibile)) {
			try {
				Integer.parseInt(this.creditoDisponibile);
				if(Integer.parseInt(this.creditoDisponibile)<0) {
					result.add("Il credito disponibile non pu� essere negativo");
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il credito disponibile � un numero intero!");
			}
		}
		
		return result;
	}

	
	@Override
	public String errorId(String idUtente) {
		String result=null;
		if(StringUtils.isBlank(idUtente)) {
			result="Il campo id non pu� essere vuoto";
		}  else {
			try {
				this.idUtente=Long.parseLong(idUtente);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result ="Id inserito non valido";
			}
		}
		
		return result;
	}
	
	public String errorCompraCredito(String creditoDesiderato) {
		String result=null;
		if(StringUtils.isBlank(creditoDesiderato)) {
			result="Inserisci un numero positivo valido";
		}  else {
			try {
				Integer.parseInt(creditoDesiderato);
				if(Integer.parseInt(creditoDesiderato)<=0) {
					result="Il credito desiderato deve essere un numero positivo";
				}
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result ="Credito inserito non valido";
			}
		}
		
		return result;	}

	public String generaRisultatoRicercaPerGet(Set<Utente> risultatoRicercaUtenti) {
		String result="";
		for (Utente utente: risultatoRicercaUtenti) {
			result+=("risultatoRicercaUtentePerGet="+utente.getIdUtente()+"&");
		}
		return result;
	}
	
	public String generaRisultatoRicercaPerPost(Set<Utente> risultatoRicercaUtenti) {
		String result="";
		for (Utente utente: risultatoRicercaUtenti) {
			result+=(utente.getIdUtente()+", ");
		}
		return result;
	}
	
	
	@Override
	public Utente buildModelFromDTO() {
		Utente result = new Utente();
		result.setIdUtente(idUtente);
		result.setNome(nome);
		result.setCognome(cognome);
		result.setUsername(username);
		result.setPassword(password);
		if(!StringUtils.isBlank(dataRegistrazione)) {
			result.setDataRegistrazione(LocalDate.parse(dataRegistrazione));			
		}
		if(!StringUtils.isBlank(esperienzaAccumulata)) {
			result.setEsperienzaAccumulata(Integer.parseInt(esperienzaAccumulata));
		} 
		if(!StringUtils.isBlank(creditoDisponibile)) {
			result.setCreditoDisponibile(Integer.parseInt(creditoDisponibile));
		}
		if(!StringUtils.isBlank(statoUtente)) {
			result.setStatoUtente(StatoUtente.conversioneStatoUtente.get(statoUtente));
		}
		return result;
	}

	@Override
	public void buildDTOFromModel(Utente utenteInstance) {
		this.setIdUtente(utenteInstance.getIdUtente());
		this.setNome(utenteInstance.getNome());
		this.setCognome(utenteInstance.getCognome());
		this.setUsername(utenteInstance.getUsername());
		this.setPassword(utenteInstance.getPassword());
		this.setStatoUtente(utenteInstance.getStatoUtente().toString());
		if (utenteInstance.getDataRegistrazione()!=null) {
			this.setDataRegistrazione(utenteInstance.getDataRegistrazione().toString());
		}
	}

}
