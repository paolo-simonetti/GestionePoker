package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ruolo_utente")
public class RuoloUtente implements Comparable<RuoloUtente> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRuolo;
	
	@Enumerated(EnumType.STRING)
	private NomeRuolo nomeRuolo;
	
	private String descrizioneRuolo;
	
	@ManyToMany(mappedBy="ruoli")
	private Set<Utente> utenti=new HashSet<>();
	
	public RuoloUtente() {} // mi serve, perché altrimenti si blocca nel restituire il risultato delle query
	
	public RuoloUtente(String descrizioneRuolo) {
		super();
		this.descrizioneRuolo = descrizioneRuolo;
		this.nomeRuolo=NomeRuolo.conversioneNomeRuolo.get(descrizioneRuolo);
	}
	
	public Long getIdRuolo() {
		return idRuolo;
	}
	
	public void setIdRuolo(Long idRuolo) {
		this.idRuolo = idRuolo;
	}
	
	public NomeRuolo getNomeRuolo() {
		return nomeRuolo;
	}
	
	public void setNomeRuolo(NomeRuolo nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	
	public String getDescrizioneRuolo() {
		return descrizioneRuolo;
	}
	
	public void setDescrizioneRuolo(String descrizioneRuolo) {
		this.descrizioneRuolo = descrizioneRuolo;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}
	
	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	public boolean addToUtenti(Utente utente) {
		if (!utenti.contains(utente)) {
			utenti.add(utente);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizioneRuolo == null) ? 0 : descrizioneRuolo.hashCode());
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
		RuoloUtente other = (RuoloUtente) obj;
		if (descrizioneRuolo == null) {
			if (other.descrizioneRuolo != null)
				return false;
		} else if (!descrizioneRuolo.equals(other.descrizioneRuolo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descrizioneRuolo;
	}

	@Override
	public int compareTo(RuoloUtente ruoloUtente) {
		return descrizioneRuolo.compareTo(ruoloUtente.getDescrizioneRuolo());
	}
	
	
	
	
	
}
