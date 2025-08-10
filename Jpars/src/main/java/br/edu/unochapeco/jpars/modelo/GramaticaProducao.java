package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GramaticaProducao {

	private String naoTerminal;
	private List<String> transicoes;
	
	public GramaticaProducao(String naoTerminal) {
		this.naoTerminal = naoTerminal;
	}

	public String getNaoTerminal() {
		return naoTerminal;
	}

	public void setNaoTerminal(String naoTerminal) {
		this.naoTerminal = naoTerminal;
	}
	
	public void setTransicao(String transicao) {
		if(Objects.isNull(this.transicoes)) {
			this.transicoes = new ArrayList<>();
		}
		this.transicoes.add(transicao);
	}

	public List<String> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<String> transicoes) {
		this.transicoes = transicoes;
	}

	@Override
	public String toString() {
		
		StringBuilder producao = new StringBuilder();
		producao.append(getNaoTerminal()).append(" = ");
		Iterator<String> transicaoIterator = getTransicoes().iterator();
		
		while (transicaoIterator.hasNext()) {
			producao.append(transicaoIterator.next());
			
			if(transicaoIterator.hasNext()) {
				producao.append(" | ");
			}
		}
		return producao.toString();
	}
	
}
