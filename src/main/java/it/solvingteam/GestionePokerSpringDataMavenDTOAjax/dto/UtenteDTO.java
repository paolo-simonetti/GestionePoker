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

//TODO: scrivere una validazione a parte per le ricerche (LocalDate.parse(), Integer.parseInt())	
	private Long idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String esperienzaAccumulata;
	private String creditoDisponibile;
	private String statoUtente;
	
	
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
	
	@Override
	public Set<String> errors() {
		/* non invoco questo metodo nelle pagine dedicate alle funzioni di ricerca
		   perché lì la validazione non serve (credo?) */
		Set<String> result = new TreeSet<>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		/* per ora non mi serve altro. TODO: tornare qui e aggiungere le altre validazioni,
		 * se capita di doverne fare */
		return result;
	}

	public Set<String> errorLogin() {
		// Nella pagina di Login, inserisco solo utente e password, quindi li devo validare
		Set<String> result=new TreeSet<>();
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
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
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("L'esperienza accumulata è un numero intero!");
			}
		}
		
		if (!StringUtils.isBlank(this.creditoDisponibile)) {
			try {
				Integer.parseInt(this.creditoDisponibile);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Il credito disponibile è un numero intero!");
			}
		}
		
		return result;
	}

	
	@Override
	public String errorId(String idUtente) {
		String result=null;
		if(StringUtils.isBlank(idUtente)) {
			result="Il campo id non può essere vuoto";
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


	@Override
	public Utente buildModelFromDTO() {
		Utente result = new Utente();
		result.setIdUtente(idUtente);
		result.setNome(nome);
		result.setCognome(cognome);
		result.setUsername(username);
		result.setPassword(password);
		result.setDataRegistrazione(LocalDate.parse(dataRegistrazione));
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
	}

}
