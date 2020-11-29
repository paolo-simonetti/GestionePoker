package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tavolo")
public class Tavolo implements Comparable<Tavolo> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTavolo;
	private String denominazione;
	private LocalDate dataCreazione;
	private Integer esperienzaMinimaRichiesta;
	private Integer puntataMinima;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavoloDiGioco", orphanRemoval = true)
	private Set<Utente> giocatori=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatore_id", nullable = false)
	private Utente creatore;	
	
	public Tavolo() {} // mi serve quando uso il DTO nelle ricerche 
	
	public Tavolo(String denominazione, Integer esperienzaMinimaRichiesta, Utente creatore) {
		super();
		this.denominazione = denominazione;
		this.dataCreazione = LocalDate.now();
		this.esperienzaMinimaRichiesta = esperienzaMinimaRichiesta;
		this.creatore = creatore;
	}

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
	
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}
	
	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	public Integer getEsperienzaMinimaRichiesta() {
		return esperienzaMinimaRichiesta;
	}
	
	public void setEsperienzaMinimaRichiesta(Integer esperienzaMinimaRichiesta) {
		this.esperienzaMinimaRichiesta=esperienzaMinimaRichiesta;
	}
	
	public Integer getPuntataMinima() {
		return puntataMinima;
	}
	
	public void setPuntataMinima(Integer puntataMinima) {
		this.puntataMinima = puntataMinima;
	}
	
	public Utente getCreatore() {
		return creatore;
	}
	
	public void setCreatore(Utente creatore) {
		this.creatore=creatore;
	}
	
	public Set<Utente> getGiocatori() {
		return giocatori;
	}
	
	public boolean addToGiocatori(Utente giocatore) {
		if (!giocatori.contains(giocatore)) {
			this.giocatori.add(giocatore);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creatore == null) ? 0 : creatore.hashCode());
		result = prime * result + ((dataCreazione == null) ? 0 : dataCreazione.hashCode());
		result = prime * result + ((denominazione == null) ? 0 : denominazione.hashCode());
		result = prime * result + ((esperienzaMinimaRichiesta == null) ? 0 : esperienzaMinimaRichiesta.hashCode());
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
		Tavolo other = (Tavolo) obj;
		if (creatore == null) {
			if (other.creatore != null)
				return false;
		} else if (!creatore.equals(other.creatore))
			return false;
		if (dataCreazione == null) {
			if (other.dataCreazione != null)
				return false;
		} else if (!dataCreazione.equals(other.dataCreazione))
			return false;
		if (denominazione == null) {
			if (other.denominazione != null)
				return false;
		} else if (!denominazione.equals(other.denominazione))
			return false;
		if (esperienzaMinimaRichiesta == null) {
			if (other.esperienzaMinimaRichiesta != null)
				return false;
		} else if (!esperienzaMinimaRichiesta.equals(other.esperienzaMinimaRichiesta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tavolo [idTavolo=" + idTavolo + ", denominazione=" + denominazione + ", dataCreazione=" + dataCreazione
				+ ", esperienzaMinimaRichiesta=" + esperienzaMinimaRichiesta + ", creatore=" + creatore + "]";
	}

	@Override
	public int compareTo(Tavolo tavoloInstance) {
		/* Prima voglio elencare i tavoli che richiedono meno esperienza; a parità di esperienza,
		 voglio prima quelli in cui la puntata minima è più bassa. A parità di questa, li metto 
		 in ordine alfabetico */
		int confrontoTraEsperienza=esperienzaMinimaRichiesta
				.compareTo(tavoloInstance.getEsperienzaMinimaRichiesta());
		if(confrontoTraEsperienza!=0) {
			return confrontoTraEsperienza;			
		} else {
			int confrontoTraPuntata=puntataMinima.compareTo(tavoloInstance.getPuntataMinima());
			if(confrontoTraPuntata!=0) {
				return confrontoTraPuntata;
			} else {
				int confrontoTraDenominazione=denominazione.compareTo(tavoloInstance.getDenominazione());
				return confrontoTraDenominazione;
			}
		}
	}

	
	
	
	
}
