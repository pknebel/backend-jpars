package br.edu.unochapeco.jpars.modelo;

import java.util.List;
import java.util.Objects;

public class Workflow {
	
	private int idWorkflow;
	
	private String nivel;
	
	// essa gramatica é a que sera slecionada na primeira tela
	private Gramatica gramatica;

	// essa e a gramatica sem recursao alimentada caso existir;
	private Gramatica gramaticaSemRecursao;

	// essa e a gramatica nao ambigua alimentada caso existir;
	private Gramatica gramaticaFatorada;

	// first follow alimentado pelo sistema;
	private FirstFollow firstFollow;

	// deixar a tabela sintatica alimentada com a recuperacao de erros, criar dois
	// metodos que validam um que considera erros e outro nao
	private TabelaSintatica tabelaSintatica;

	// opcoes de sentencas pre definidas para validacao
	private List<Sentenca> sentencas;
	
	public Workflow(int idWorkflow, String nivel) {
		this.idWorkflow = idWorkflow;
		this.nivel = nivel;
	}

	public int getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(int idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Gramatica getGramatica() {
		return gramatica;
	}

	public void setGramatica(Gramatica gramatica) {
		this.gramatica = gramatica;
	}

	public Gramatica getGramaticaSemRecursao() {
		return gramaticaSemRecursao;
	}

	public void setGramaticaSemRecursao(Gramatica gramaticaSemRecursao) {
		this.gramaticaSemRecursao = gramaticaSemRecursao;
	}


	public Gramatica getGramaticaFatorada() {
		return gramaticaFatorada;
	}

	public void setGramaticaFatorada(Gramatica gramaticaFatorada) {
		this.gramaticaFatorada = gramaticaFatorada;
	}

	public FirstFollow getFirstFollow() {
		return firstFollow;
	}

	public void setFirstFollow(FirstFollow firstFollow) {
		this.firstFollow = firstFollow;
	}

	public TabelaSintatica getTabelaSintatica() {
		return tabelaSintatica;
	}

	public void setTabelaSintatica(TabelaSintatica tabelaSintatica) {
		this.tabelaSintatica = tabelaSintatica;
	}

	public List<Sentenca> getSentencas() {
		return sentencas;
	}

	public void setSentencas(List<Sentenca> sentencas) {
		this.sentencas = sentencas;
	}
	
	public Gramatica getGramaticaLL1() {
		
		if(Objects.nonNull(gramaticaFatorada)) {
			return gramaticaFatorada;
		}
		
		if(Objects.nonNull(gramaticaSemRecursao)) {
			return gramaticaSemRecursao;
		}
		
		return gramatica;
	}
}
