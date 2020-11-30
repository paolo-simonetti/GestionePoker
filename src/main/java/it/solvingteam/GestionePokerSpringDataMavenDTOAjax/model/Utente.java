package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table(name="utente")
public class Utente implements Comparable<Utente> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	

	private LocalDate dataRegistrazione;
	
	private Integer esperienzaAccumulata;
	private Integer creditoDisponibile;
	
	@Enumerated(EnumType.STRING)
	private StatoUtente statoUtente=StatoUtente.CREATO;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<RuoloUtente> ruoli=new TreeSet<>();	
	
	/* Abbiamo scelto di fare cancellazione fisica del tavolo, quindi questo atttributo tiene conto solo
	 * dei tavoli non ancora cancellati */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creatore", orphanRemoval = false)  
	private Set<Tavolo> tavoliCreati=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_id", nullable = true, updatable=true) // un utente non deve per forza giocare
	private Tavolo tavoloDiGioco;
	
	public Utente() {}
	
	public Utente(String nome, String cognome, String username, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = LocalDate.now(Clock.systemUTC());
		this.esperienzaAccumulata=0;
		this.creditoDisponibile=0;
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
	
	public LocalDate getDataRegistrazione() {
		return dataRegistrazione;
	}
	
	public void setDataRegistrazione(LocalDate dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	
	public StatoUtente getStatoUtente() {
		return statoUtente;
	}
	
	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}
	
	public Set<RuoloUtente> getRuoli() {
		return ruoli;
	}
	
	public boolean addToRuoli(RuoloUtente ruoloUtente) {
		// se l'utente non ha ruoli, aggiungo subito ruoloUtente
		if(ruoli.size()==0) {
			this.ruoli.add(ruoloUtente);
			return true;
		} else {
		// Se sono qui, l'utente ha già dei ruoli, quindi controllo che non possieda già quello in input	
			if(!ruoli.contains(ruoloUtente)) {
				this.ruoli.add(ruoloUtente);
				return true;
			} else {
				return false;
			}
		}
	}
	
	public Integer getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}
	
	public void setEsperienzaAccumulata(Integer esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}
	
	public Integer getCreditoDisponibile() {
		return creditoDisponibile;
	}
	
	public void setCreditoDisponibile(Integer creditoDisponibile) {
		this.creditoDisponibile = creditoDisponibile;
	}
	
	
	public Set<Tavolo> getTavoliCreati() {
		return tavoliCreati;
	}
	
	public boolean addToTavoliCreati(Tavolo tavolo) {
		if (!tavoliCreati.contains(tavolo)) {
			this.tavoliCreati.add(tavolo);
			return true;
		} else {
			return false;
		}
	}
	

	public Tavolo getTavoloDiGioco() {
		return tavoloDiGioco;
	}

	public void setTavoloDiGioco(Tavolo tavoloDiGioco) {
		this.tavoloDiGioco = tavoloDiGioco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", dataRegistrazione=" + dataRegistrazione + ", statoUtente=" + statoUtente
				+ "]";
	}

	@Override
	public int compareTo(Utente utenteInstance) {
		return username.compareTo(utenteInstance.getUsername());
	}
	
	
	
	
	
	
	
	
	
}
