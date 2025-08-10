package br.edu.unochapeco.jpars.modelo;

import java.util.Arrays;
import java.util.List;

public class Sentenca {
	
	private int idSentenca;
	private List<String> simbolos;
	
	public Sentenca(int idSentenca, String... simbolos) {
		this.idSentenca = idSentenca;
		this.simbolos = Arrays.asList(simbolos);
	}

	public List<String> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}

	public int getIdSentenca() {
		return idSentenca;
	}

	public void setIdSentenca(int idSentenca) {
		this.idSentenca = idSentenca;
	}
	
	@Override
	public String toString() {
		return this.simbolos.toString();
	}
	
}
