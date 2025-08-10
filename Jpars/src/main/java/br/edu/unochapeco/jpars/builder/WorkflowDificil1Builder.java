package br.edu.unochapeco.jpars.builder;

import java.util.Arrays;
import java.util.List;

import br.edu.unochapeco.jpars.modelo.FirstFollow;
import br.edu.unochapeco.jpars.modelo.FirstFollowRow;
import br.edu.unochapeco.jpars.modelo.Gramatica;
import br.edu.unochapeco.jpars.modelo.GramaticaProducao;
import br.edu.unochapeco.jpars.modelo.Sentenca;
import br.edu.unochapeco.jpars.modelo.TabelaSintatica;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaColumn;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaProducao;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaRow;
import br.edu.unochapeco.jpars.modelo.Workflow;

public class WorkflowDificil1Builder {

	public Workflow build() {
		Workflow workflow = new Workflow(1, "Difícil");
		workflow.setGramatica(getGramatica());
		workflow.setSentencas(getSentencas());
		workflow.setFirstFollow(getFirstFollow());
		workflow.setTabelaSintatica(getTabelaSintatica());
		workflow.setGramaticaFatorada(getGramaticaFatorada());
		workflow.setGramaticaSemRecursao(getGramaticaSemRecursao());
		return workflow;
	}

	private Gramatica getGramatica() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("S i E t S");
		gramaticaProducao.setTransicao("S i E t S e S");
		gramaticaProducao.setTransicao("a");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("E");
		gramaticaProducao.setTransicao("b");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaSemRecursao() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("a A");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao("i E t S A");
		gramaticaProducao.setTransicao("i E t S e S A");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("E");
		gramaticaProducao.setTransicao("b");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaFatorada() {
		
		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("a A");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao("i E t S B");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("B");
		gramaticaProducao.setTransicao("e S");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("E");
		gramaticaProducao.setTransicao("b");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}
	
	public FirstFollow getFirstFollow() {
		
		FirstFollow firstFollow = new FirstFollow();
		firstFollow.addFirstFollowRow(new FirstFollowRow("S", "a", "$, e"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("A", "i, &", "$, e"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("B", "e, &", "$, e"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("E", "b", "t"));
		
		return firstFollow;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		
		TabelaSintatica tabelaSintatica = new TabelaSintatica();
		TabelaSintaticaRow tabelaSintaticaRow;
		
		tabelaSintaticaRow = new TabelaSintaticaRow(1, "S");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "a", new TabelaSintaticaProducao("S", "a", "A")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "i"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "e").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "b"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "t"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(2, "A");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "a"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "i", new TabelaSintaticaProducao("A", "i", "E", "t", "S", "B")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "e", new TabelaSintaticaProducao("A", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "b"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "t"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$", new TabelaSintaticaProducao("A", "&")));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(3, "B");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "a"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "i"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "e", new TabelaSintaticaProducao("B", "e", "S")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "b"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "t"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$", new TabelaSintaticaProducao("B", "&")));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(4, "E");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "a"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "i"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "e"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "b",new TabelaSintaticaProducao("E", "b")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "t"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$"));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		return tabelaSintatica;
	}
	
	public List<Sentenca> getSentencas() {
		return Arrays.asList(new Sentenca(1, "a", "i", "b", "t", "a", "e", "a"),
				             new Sentenca(2, "i", "b", "t", "a", "e", "a"),
				             new Sentenca(3, "e", "p", "s", "i", "l", "o", "n"),
				             new Sentenca(4, "a", "i", "b", "a", "t", "a", "e", "a"));
		
	}


}
