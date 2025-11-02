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

public class WorkflowIntermediario2Builder {

    public Workflow build() {
		Workflow workflow = new Workflow(4, "Intermediário");
		workflow.setGramatica(getGramatica());
		workflow.setSentencas(getSentencas());
		workflow.setFirstFollow(getFirstFollow());
		workflow.setTabelaSintatica(getTabelaSintatica());
		workflow.setGramaticaFatorada(getGramaticaFatorada());
		return workflow;
	}

	private Gramatica getGramatica() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("A class id;");
		gramaticaProducao.setTransicao("A class { }");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao("public");
		gramaticaProducao.setTransicao("private");
		gramaticaProducao.setTransicao("protected");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);

		return gramatica;
	}

	public Gramatica getGramaticaFatorada() {
		
		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("A class id B");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao("public");
		gramaticaProducao.setTransicao("private");
		gramaticaProducao.setTransicao("protected");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("B");
		gramaticaProducao.setTransicao("{ }");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);

		return gramatica;
	}
	
	public FirstFollow getFirstFollow() {
		
		FirstFollow firstFollow = new FirstFollow();
		firstFollow.addFirstFollowRow(new FirstFollowRow("S", "public, private, protected, class", "$"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("A", "public, private, protected, &", "$, class"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("B", "{, &", "$"));
		
		return firstFollow;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		
		TabelaSintatica tabelaSintatica = new TabelaSintatica();
		TabelaSintaticaRow tabelaSintaticaRow;
		
		tabelaSintaticaRow = new TabelaSintaticaRow(1, "S");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "public", new TabelaSintaticaProducao("S", "A", "class", "id", "B")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "private", new TabelaSintaticaProducao("S", "A", "class", "id", "B")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "protected", new TabelaSintaticaProducao("S", "A", "class", "id", "B")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "class", new TabelaSintaticaProducao("S", "A", "class", "id", "B")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "{"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "}"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(8, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(2, "A");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "public", new TabelaSintaticaProducao("A", "public")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "private", new TabelaSintaticaProducao("A", "private")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "protected", new TabelaSintaticaProducao("A", "protected")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "class", new TabelaSintaticaProducao("A", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "{"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "}"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(8, "$"));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(3, "B");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "public"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "private"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "protected"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "class"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "{", new TabelaSintaticaProducao("B", "{", "}")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "}", new TabelaSintaticaProducao("B", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(8, "$"));
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