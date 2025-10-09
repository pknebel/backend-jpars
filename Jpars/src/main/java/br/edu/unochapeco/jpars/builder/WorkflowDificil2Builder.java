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

public class WorkflowDificil2Builder {

    public Workflow build() {
		Workflow workflow = new Workflow(6, "Difícil");
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

		gramaticaProducao = new GramaticaProducao("E");
		gramaticaProducao.setTransicao("E + T");
		gramaticaProducao.setTransicao("T");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("T");
		gramaticaProducao.setTransicao("T * F");
		gramaticaProducao.setTransicao("F");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("F");
		gramaticaProducao.setTransicao("( E )");
		gramaticaProducao.setTransicao("id");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}

	public Gramatica getGramaticaSemRecursao() {

		GramaticaProducao gramaticaProducao;
		Gramatica gramatica = new Gramatica();

		gramaticaProducao = new GramaticaProducao("E");
		gramaticaProducao.setTransicao("T E'");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("E'");
		gramaticaProducao.setTransicao("+ T E'");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("T");
		gramaticaProducao.setTransicao("F T'");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		gramaticaProducao = new GramaticaProducao("T'");
		gramaticaProducao.setTransicao("* F T'");
		gramaticaProducao.setTransicao("&");
		gramatica.addGramaticaProducao(gramaticaProducao);

		gramaticaProducao = new GramaticaProducao("F");
		gramaticaProducao.setTransicao("( E )");
		gramaticaProducao.setTransicao("id");
		gramatica.addGramaticaProducao(gramaticaProducao);
		
		return gramatica;
	}
	
	public FirstFollow getFirstFollow() {
		
		FirstFollow firstFollow = new FirstFollow();
		firstFollow.addFirstFollowRow(new FirstFollowRow("E", "(, id", "$, )"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("E'", "+, &", "$, )"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("T", "(, id", "$, +, )"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("T'", "*, &", "$, +, )"));
		firstFollow.addFirstFollowRow(new FirstFollowRow("F", "(, id", "$, *, +, )"));
		
		return firstFollow;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		
		TabelaSintatica tabelaSintatica = new TabelaSintatica();
		TabelaSintaticaRow tabelaSintaticaRow;
		
		tabelaSintaticaRow = new TabelaSintaticaRow(1, "E");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id", new TabelaSintaticaProducao("E", "T", "E'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "+"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "*"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "(", new TabelaSintaticaProducao("E", "T", "E'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ")").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(2, "E'");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "+", new TabelaSintaticaProducao("E'", "+", "T", "E'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "*"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "("));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ")", new TabelaSintaticaProducao("E'", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$", new TabelaSintaticaProducao("E'", "&")));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(3, "T");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id", new TabelaSintaticaProducao("T", "F", "T'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "+").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "*"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "(", new TabelaSintaticaProducao("T", "F", "T'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ")").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		tabelaSintaticaRow = new TabelaSintaticaRow(4, "T'");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id"));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "+",new TabelaSintaticaProducao("T'", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "*",new TabelaSintaticaProducao("T'", "*", "F", "T'")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "("));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ")", new TabelaSintaticaProducao("T'", "&")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$", new TabelaSintaticaProducao("T'", "&")));
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);

		tabelaSintaticaRow = new TabelaSintaticaRow(4, "F");
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(1, "id",new TabelaSintaticaProducao("F", "id")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(2, "+").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(3, "*").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(4, "(",new TabelaSintaticaProducao("F", "(", "E", ")")));
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(5, ")").withSync());
		tabelaSintaticaRow.addColumn(new TabelaSintaticaColumn(6, "$").withSync());
		tabelaSintatica.addTabelaSintaticaRow(tabelaSintaticaRow);
		
		return tabelaSintatica;
	}
	
	public List<Sentenca> getSentencas() {
		return Arrays.asList(new Sentenca(1, "id", "+", "id", "*", "id"),
				             new Sentenca(2, "id", "+", "id"),
				             new Sentenca(3, "id", "+", "(", "id", "*", "id", ")"),
				             new Sentenca(4, "id", "+", "id", "*", "id", ")"));
		
	}
}