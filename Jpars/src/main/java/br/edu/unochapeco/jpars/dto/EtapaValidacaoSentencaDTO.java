package br.edu.unochapeco.jpars.dto;

import java.util.List;

public class EtapaValidacaoSentencaDTO {
	
	private int indexSentenca;
	private List<String> pilha;
	private List<String> sentenca;
	private String mensagemSucesso;
	
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

	public String getMensagemSucesso() {
		return mensagemSucesso;
	}

	public void setMensagemSucesso(String mensagemSucesso) {
		this.mensagemSucesso = mensagemSucesso;
	}

}
