package br.edu.unochapeco.jpars.modelo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TabelaSintaticaProducao {

	private String naoTerminal;
	private List<String> elementosTransicao;

	public TabelaSintaticaProducao() {
		this.naoTerminal = "";
		this.elementosTransicao = Collections.emptyList();
	}
	
	public TabelaSintaticaProducao(String naoTerminal, String... elementosTransicao) {
		this.naoTerminal = naoTerminal;
		this.elementosTransicao = Arrays.asList(elementosTransicao);
	}

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

	public boolean isEmpty() {
		return toString().isEmpty();
	}
	
	@Override
	public String toString() {
		if(Objects.equals(naoTerminal, "")) return "";
		
		StringBuilder producao = new StringBuilder();
		producao.append(naoTerminal).append("=");
		
		for (String transicao : elementosTransicao) {
			producao.append(transicao);
		}
		return producao.toString();
	}
	
	public boolean isTransicaoVazia() {
		if(elementosTransicao.size() == 1) {
			return this.elementosTransicao.get(0).equals("&");
		}
		return false;
	}
	
}
