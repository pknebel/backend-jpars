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

public class WorkflowFacil2Builder {

    public Workflow build() {
		Workflow workflow = new Workflow(2, "Fácil");
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
		gramaticaProducao.setTransicao("id A");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao(", id A");
        gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaSemRecursao() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("id A");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao(", id A");
        gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaFatorada() {
		
		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("id A");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("A");
		gramaticaProducao.setTransicao(", id A");
        gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}
	
	public FirstFollow getFirstFollow() {
		
		FirstFollow firstFollow = new FirstFollow();
		firstFollow.addFirstFollowRow(new FirstFollowRow("S", "id", "$"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("A", ", , &", "$"));
		
		return firstFollow;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		
		TabelaSintatica tabelaSintatica = new TabelaSintatica();
		TabelaSintaticaRow tabelaSintaticaRow;
		
		tabelaSintaticaRow = new TabelaSintaticaRow(1, "S");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id", new TabelaSintaticaProducao("S", "id", "A")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, ",").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(2, "A");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, ",", new TabelaSintaticaProducao("A", ",", "id", "A")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "$", new TabelaSintaticaProducao("A", "&")));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		return tabelaSintatica;
	}
	
	public List<Sentenca> getSentencas() {
		return Arrays.asList(new Sentenca(1, "id", ",", "id", ",", "id"),
				             new Sentenca(2, "id", "id", ",", "id"));
		
	}
}
