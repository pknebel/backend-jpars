package br.edu.unochapeco.jpars.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.dto.EtapaValidacaoSentencaDTO;
import br.edu.unochapeco.jpars.modelo.ElementoPilha;
import br.edu.unochapeco.jpars.modelo.Sentenca;
import br.edu.unochapeco.jpars.modelo.TabelaSintatica;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaColumn;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaProducao;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaRow;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.repository.WorkflowRepository;

@Service
public class SentencaService {
	
	@Autowired
	private ValidacaoSentencaSingletonService sentencaSingletonService;
	
	@Autowired
	private WorkflowRepository workflowRepository;
	
	public EtapaValidacaoSentencaDTO iniciarValidacao(Integer idWorkflow, Integer idSentenca) {
		
		Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
		Sentenca sentenca = workflowRepository.findSentencaWorkflow(workflow, idSentenca);
		
		sentencaSingletonService.iniciarValidacao(workflow.getTabelaSintatica(), sentenca);
		return sentencaSingletonService.getEtapaValidacaoSentenca();
	}
	
	public EtapaValidacaoSentencaDTO validarProximoToken() {
		
		String pilha = sentencaSingletonService.getPilhaValues();
		String naoTerminal = sentencaSingletonService.getNaoTerminal();
		String tokenSentenca = sentencaSingletonService.getTokenSentenca();
		String elementoInicioPilha = sentencaSingletonService.getElementoInicioPilha();
		ElementoPilha ultimoElementoPilha = sentencaSingletonService.getUltimoElementoPilha();
		
		if(Objects.equals(tokenSentenca, elementoInicioPilha)) {
			 EtapaValidacaoSentencaDTO etapaValidacaoSentenca = sentencaSingletonService.getEtapaValidacaoSentenca();
			if(sentencaSingletonService.isErroSentenca()) {
				etapaValidacaoSentenca.setMensagemSucesso("Sentença validada com erros!");
			}else {
				etapaValidacaoSentenca.setMensagemSucesso("Sentença validada com sucesso!");
			}
			return etapaValidacaoSentenca;
		}
		
		if(ultimoElementoPilha.isTerminal()) {
			
			if(Objects.equals(ultimoElementoPilha.getElemento(), tokenSentenca)) {
				System.out.println("Casando token:'" + tokenSentenca + "' com a pilha " + pilha);
				sentencaSingletonService.avancarSentenca();
			}else {
				sentencaSingletonService.setErroSentenca(true);
				System.out.println("ERRO: Esperava '"  + tokenSentenca + "' encontrado '" + ultimoElementoPilha + "', desempilhando elemento '" + ultimoElementoPilha + "'");
			}
			sentencaSingletonService.desempilharElemento();
			return sentencaSingletonService.getEtapaValidacaoSentenca();
		}
		
		System.out.println("Validando token:'" + tokenSentenca + "' com não terminal: '" + naoTerminal + "' Pilha " + pilha);
		TabelaSintaticaColumn tabelaSintaticaColumn = getTabelaSintaticaColumn(tokenSentenca, naoTerminal);
		TabelaSintaticaProducao producao = tabelaSintaticaColumn.getProducao();
		
		if(tabelaSintaticaColumn.isPossuiProducao()) {
			
			if(ultimoElementoPilha.isNaoTerminal()) {
				System.out.println("Desempilhando nao terminal '" + ultimoElementoPilha + "'");
				sentencaSingletonService.desempilharElemento();
			}
			System.out.println("Empilhando producao " + producao);
			sentencaSingletonService.empilharTransicao(producao);
	
			if(producao.isTransicaoVazia() &&
			   sentencaSingletonService.isPilhaEstadoInicial() && 
			   sentencaSingletonService.isPossuiSimbolosPendentes()) {
				throw new RuntimeException("Sentenca inválida! Não foi possível realizar a validação de todos os tokens pois a pilha está vazia");
			}
			
		}else {
			
			if(tabelaSintaticaColumn.isSync()) {
				System.out.println("Sincronismo encontrado, desempilhando elemento '" + ultimoElementoPilha + "'");
				sentencaSingletonService.desempilharElemento();
				
				if(sentencaSingletonService.isPilhaEstadoInicial() && 
				   sentencaSingletonService.isPossuiSimbolosPendentes()) {
					throw new RuntimeException("Sentenca inválida! Não foi possível realizar a validação de todos os tokens pois a pilha está vazia");
				}
			}else {
				System.out.println("Producao nao encontrada na tabela, avancando para proximo token...");
				sentencaSingletonService.avancarSentenca();
			}
		}
		
		return sentencaSingletonService.getEtapaValidacaoSentenca();
	}

	private TabelaSintaticaColumn getTabelaSintaticaColumn(String tokenSentenca, String naoTerminal) {
		
		System.out.println("Consultando tabela com token '" + tokenSentenca + "' e nao terminal: '" + naoTerminal + "'");
		TabelaSintatica tabelaSintatica = sentencaSingletonService.getTabelaSintatica();
		
		for (TabelaSintaticaRow tabelaSintaticaRow : tabelaSintatica.getRows()) {
			for (TabelaSintaticaColumn tabelaSintaticaColumn : tabelaSintaticaRow.getColumns()) {
				
				String tokenTabela = tabelaSintaticaColumn.getTerminal();
				String naoTerminalTabela = tabelaSintaticaRow.getNaoTerminal();
				if(Objects.equals(naoTerminalTabela, naoTerminal) && Objects.equals(tokenTabela, tokenSentenca)) {
					return tabelaSintaticaColumn;
				}
			}
		}
		throw new RuntimeException("A sentença não é aceita pela Gramática");
	}
	
}
