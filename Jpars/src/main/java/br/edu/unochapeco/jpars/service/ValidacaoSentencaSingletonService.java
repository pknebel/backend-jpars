package br.edu.unochapeco.jpars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

import br.edu.unochapeco.jpars.modelo.ElementoPilha;
import br.edu.unochapeco.jpars.modelo.EtapaValidacaoSentenca;
import br.edu.unochapeco.jpars.modelo.Sentenca;
import br.edu.unochapeco.jpars.modelo.TabelaSintatica;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaProducao;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaRow;

public class ValidacaoSentencaSingletonService {
	
	private static final String EPSILON = "&";
	private static final String ELEMENTO_INICIO_PILHA = "$";
	
	private int indexSentenca;
	private String naoTerminal;
	private boolean erroSentenca;
	private List<String> simbolosSentenca;
	private TabelaSintatica tabelaSintatica;
	private Stack<ElementoPilha> pilha = new Stack<>();
	
	public void iniciarValidacao(TabelaSintatica tabelaSintatica, Sentenca sentenca) {
		this.pilha.clear();
		this.indexSentenca = 0;
		this.erroSentenca = false;
		this.tabelaSintatica = tabelaSintatica;
		this.simbolosSentenca = getSimbolosValidacaoSentenca(sentenca);
		this.naoTerminal = tabelaSintatica.getRowByIndex(1).getNaoTerminal();
		this.pilha.push(new ElementoPilha(ELEMENTO_INICIO_PILHA, true));
		this.pilha.push(new ElementoPilha(naoTerminal, false));
	}
	
    public void empilharTransicao(TabelaSintaticaProducao producao) {
    	
		List<String> elementosTransicao = producao.getElementosTransicao();
		for (int i = elementosTransicao.size() - 1; i >= 0; i--) {
			String elemento = elementosTransicao.get(i);
			if(!Objects.equals(elemento, EPSILON)) {
				this.pilha.push(new ElementoPilha(elemento, isElementoTerminal(elemento)));
			}
		}
		atualizarEstadoNaoTerminal();
    }
    
    private boolean isElementoTerminal(String elemento) {
    	
    	boolean isTerminal = true;
    	for (TabelaSintaticaRow tabelaSintaticaRow : tabelaSintatica.getRows()) {
    		if(Objects.equals(tabelaSintaticaRow.getNaoTerminal(), elemento)) {
    			isTerminal = false;
    		}
		}
    	return isTerminal;
	}
    
	public EtapaValidacaoSentenca getEtapaValidacaoSentenca() {
		
		//retorna a lista de elementos da pilha em uma lista de string
		List<String> elementosPilha = pilha.stream().map(ElementoPilha::getElemento).collect(Collectors.toList());
		
		EtapaValidacaoSentenca etapaValidacaoSentenca = new EtapaValidacaoSentenca();
		etapaValidacaoSentenca.setPilha(elementosPilha);
		etapaValidacaoSentenca.setSentenca(simbolosSentenca);
		etapaValidacaoSentenca.setIndexSentenca(indexSentenca);
		return etapaValidacaoSentenca;
	}
	
	private ArrayList<String> getSimbolosValidacaoSentenca(Sentenca sentenca) {
		ArrayList<String> simbolos = new ArrayList<>(sentenca.getSimbolos());
		simbolos.add(ELEMENTO_INICIO_PILHA);
		return simbolos;
	}
	
	private void atualizarEstadoNaoTerminal() {
		ElementoPilha elementoPilha = this.pilha.lastElement();
    	if(elementoPilha.isNaoTerminal()) {
    		this.naoTerminal = elementoPilha.getElemento();
    	}
	}
	
    public void desempilharElemento() {
    	this.pilha.pop();
    	atualizarEstadoNaoTerminal();
	}
    
    public boolean isPilhaEstadoInicial() {
    	return Objects.equals(getUltimoElementoPilha().getElemento(), ELEMENTO_INICIO_PILHA);
	}
    
    public boolean isPossuiSimbolosPendentes() {
    	return this.simbolosSentenca.size() - 1 > indexSentenca;
	}
    
    public void avancarSentenca() {
    	this.indexSentenca++;
	}
    
    public ElementoPilha getUltimoElementoPilha() {
    	return this.pilha.lastElement();
	}
    
	public String getTokenSentenca() {
		return this.simbolosSentenca.get(indexSentenca);
	}
	
	public String getNaoTerminal() {
		return naoTerminal;
	}
	
	public TabelaSintatica getTabelaSintatica() {
		return tabelaSintatica;
	}
	
	public String getElementoInicioPilha() {
		return ELEMENTO_INICIO_PILHA;
	}
	
	public String getPilhaValues() {
		return this.pilha.toString();
	}

	public boolean isErroSentenca() {
		return erroSentenca;
	}

	public void setErroSentenca(boolean erroSentenca) {
		this.erroSentenca = erroSentenca;
	}
	
}
