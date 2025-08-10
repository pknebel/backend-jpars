package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Gramatica {

	private List<GramaticaProducao> producoes;
	
	public void addGramaticaProducao(GramaticaProducao gramaticaProducao) {
		if(Objects.isNull(this.producoes)) {
			this.producoes = new ArrayList<>();
		}
		this.producoes.add(gramaticaProducao);
	}
	
	public List<GramaticaProducao> getProducoes() {
		return producoes;
	}

	public void setProducoes(List<GramaticaProducao> producoes) {
		this.producoes = producoes;
	}

	@Override
	public String toString() {
		StringBuilder estrutura = new StringBuilder();
		Iterator<GramaticaProducao> producaoIterator = producoes.iterator();
		
		while (producaoIterator.hasNext()) {
			
			GramaticaProducao gramaticaProducao = producaoIterator.next();
			estrutura.append(gramaticaProducao);
			
			if(producaoIterator.hasNext()) {
				estrutura.append("\n");
			}
		}
		
		return estrutura.toString();
	}
	
}
