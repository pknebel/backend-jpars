package br.edu.unochapeco.jpars.dto;

import java.util.List;

public class SentencaDTO {
	
	private int idSentenca;
	private List<String> simbolos;

	public int getIdSentenca() {
		return idSentenca;
	}

	public void setIdSentenca(int idSentenca) {
		this.idSentenca = idSentenca;
	}

	public List<String> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}
	
	public String getSimbolosLiteral() {
		return this.simbolos.toString().replace("[", "").replace("]", "").replace(",", "");
	}

}
