package br.edu.unochapeco.jpars.dto;

import java.util.List;

public class TabelaSintaticaProducaoDTO {

	private String naoTerminal;
	private List<String> elementosTransicao;

	public String getNaoTerminal() {
		return naoTerminal;
	}

	public void setNaoTerminal(String naoTerminal) {
		this.naoTerminal = naoTerminal;
	}

	public List<String> getElementosTransicao() {
		return elementosTransicao;
	}

	public void setElementosTransicao(List<String> elementosTransicao) {
		this.elementosTransicao = elementosTransicao;
	}

}
