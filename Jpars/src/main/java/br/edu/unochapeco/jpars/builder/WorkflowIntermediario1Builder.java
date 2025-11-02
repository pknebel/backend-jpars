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

public class WorkflowIntermediario1Builder {

    public Workflow build() {
		Workflow workflow = new Workflow(3, "Intermediário");
		workflow.setGramatica(getGramatica());
		workflow.setSentencas(getSentencas());
		workflow.setFirstFollow(getFirstFollow());
		workflow.setTabelaSintatica(getTabelaSintatica());
		workflow.setGramaticaSemRecursao(getGramaticaSemRecursao());
		return workflow;
	}

	private Gramatica getGramatica() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("T L ;");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("T");
		gramaticaProducao.setTransicao("int");
		gramaticaProducao.setTransicao("float");
		gramaticaProducao.setTransicao("boolean");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("L");
		gramaticaProducao.setTransicao("L, id");
		gramaticaProducao.setTransicao("id");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaSemRecursao() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("S");
		gramaticaProducao.setTransicao("T L ;");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("T");
		gramaticaProducao.setTransicao("int");
		gramaticaProducao.setTransicao("float");
		gramaticaProducao.setTransicao("boolean");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("L");
		gramaticaProducao.setTransicao("id U");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("U");
		gramaticaProducao.setTransicao(", id U");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public FirstFollow getFirstFollow() {
		
		FirstFollow firstFollow = new FirstFollow();
		firstFollow.addFirstFollowRow(new FirstFollowRow("S", "int, float, boolean", "$"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("T", "int, float, boolean", "$, id"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("L", "id", "$, ;"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("U", ", &", "$, ;"));
		
		return firstFollow;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		
		TabelaSintatica tabelaSintatica = new TabelaSintatica();
		TabelaSintaticaRow tabelaSintaticaRow;
		
		tabelaSintaticaRow = new TabelaSintaticaRow(1, "S");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "int", new TabelaSintaticaProducao("S", "T", "L", ";")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "float", new TabelaSintaticaProducao("S", "T", "L", ";")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "boolean", new TabelaSintaticaProducao("S", "T", "L", ";")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ","));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, ";"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(2, "T");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "int", new TabelaSintaticaProducao("T", "int")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "float", new TabelaSintaticaProducao("T", "float")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "boolean", new TabelaSintaticaProducao("T", "boolean")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "id").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ","));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, ";"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(3, "L");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "int", new TabelaSintaticaProducao("L", "id", "L'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "float"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "boolean"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ","));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, ";").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(4, "U");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "int", new TabelaSintaticaProducao("U", ",", "id", "L'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "float"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "boolean"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ","));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, ";", new TabelaSintaticaProducao("U", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(7, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		return tabelaSintatica;
	}
	
	public List<Sentenca> getSentencas() {
		return Arrays.asList(new Sentenca(1, "int", "id", ",", "id", ";"),
				             new Sentenca(2, "boolean", "id", ",", ";"),
				             new Sentenca(3, "float", ";"));
		
	}
}
