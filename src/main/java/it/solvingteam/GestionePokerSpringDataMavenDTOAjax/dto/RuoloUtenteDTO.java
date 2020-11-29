package it.solvingteam.GestionePokerSpringDataMavenDTOAjax.dto;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.NomeRuolo;
import it.solvingteam.GestionePokerSpringDataMavenDTOAjax.model.RuoloUtente;

public class RuoloUtenteDTO implements AbstractDTO<RuoloUtente> {

	private Long idRuolo;
	private String descrizioneRuolo;
	//Non ho messo un campo NomeRuolo, come nel model, perché nel DTO viaggiano stringhe e il 
	//toString del nomeRuolo è la descrizione stessa, per come ho costruito il model
	
	public Long getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(Long idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getDescrizioneRuolo() {
		return descrizioneRuolo;
	}

	public void setDescrizioneRuolo(String descrizioneRuolo) {
		this.descrizioneRuolo = descrizioneRuolo;
	}

	@Override
	public Set<String> errors() {
		Set<String> result = new TreeSet<String>();
		if(StringUtils.isBlank(this.descrizioneRuolo)) {
			result.add("Il campo descrizione non può essere vuoto");
		} else if(NomeRuolo.conversioneNomeRuolo.get(descrizioneRuolo)==null) {
			result.add("Ruolo non presente");
		}
		return result;
	}

	@Override
	public String errorId(String idRuolo) {
		String result=null;
		if(StringUtils.isBlank(idRuolo)) {
			result="Il campo id non può essere vuoto";
		}  else {
			try {
				this.idRuolo=Long.parseLong(idRuolo);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result ="Id inserito non valido";
			}
		}
		
		return result;
	}

	@Override
	public RuoloUtente buildModelFromDTO() {
		RuoloUtente result=new RuoloUtente(descrizioneRuolo);
		return result;
	}

	@Override
	public void buildDTOFromModel(RuoloUtente ruoloInstance) {
		this.setIdRuolo(ruoloInstance.getIdRuolo());
		this.setDescrizioneRuolo(ruoloInstance.getDescrizioneRuolo());
	}

}
