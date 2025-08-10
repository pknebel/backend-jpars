package br.edu.unochapeco.jpars.modelo;

import java.util.List;

public class EtapaValidacaoSentenca {

	private List<String> pilha;
	private List<String> sentenca;
	private int indexSentenca;

	public List<String> getPilha() {
		return pilha;
	}

	public void setPilha(List<String> pilha) {
		this.pilha = pilha;
	}

	public List<String> getSentenca() {
		return sentenca;
	}

	public void setSentenca(List<String> sentenca) {
		this.sentenca = sentenca;
	}

	public int getIndexSentenca() {
		return indexSentenca;
	}

	public void setIndexSentenca(int indexSentenca) {
		this.indexSentenca = indexSentenca;
	}


}
