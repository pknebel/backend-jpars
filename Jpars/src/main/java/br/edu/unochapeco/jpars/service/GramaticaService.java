package br.edu.unochapeco.jpars.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.modelo.Gramatica;
import br.edu.unochapeco.jpars.modelo.GramaticaProducao;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.repository.WorkflowRepository;
import br.edu.unochapeco.jpars.util.GramaticaUtil;
import br.edu.unochapeco.jpars.util.StringUtil;

@Service
public class GramaticaService {
	
	@Autowired
	private WorkflowRepository workflowRepository;
	
	//valida se a gramatica que o usuario digitou esta correta com a gramatica sem recursao pre definida no codigo
    public void validarEntradaGramaticaRecursao(Integer idWorkflow, String estruturaGramaticaEntrada) {

    	Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
    	
    	Gramatica gramaticaSemRecursao = workflow.getGramaticaSemRecursao();
    	validarGramaticaEntrada(gramaticaSemRecursao, estruturaGramaticaEntrada);
	}

	//valida se a gramatica que o usuario digitou esta correta com a gramatica nao ambigua pre definida no codigo
    public void validarEntradaGramaticaFatorada(Integer idWorkflow, String estruturaGramaticaEntrada) {

    	Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
    	
    	Gramatica gramaticaFatorada = workflow.getGramaticaFatorada();
    	validarGramaticaEntrada(gramaticaFatorada, estruturaGramaticaEntrada);
	}
    
	private void validarGramaticaEntrada(Gramatica gramatica, String estruturaGramaticaEntrada) {
		
		if(Objects.isNull(gramatica)) {
    		throw new RuntimeException("Gramatica não encontrada no fluxo do processo.");
    	}
    	
    	if(Objects.isNull(estruturaGramaticaEntrada) || estruturaGramaticaEntrada.isEmpty()) {
    		throw new RuntimeException("Digite uma gramatica válida para processar.");
    	}
    	
    	List<String> producoesEntrada = GramaticaUtil.getProducoesGramatica(estruturaGramaticaEntrada);
    	List<GramaticaProducao> producoesGramatica = gramatica.getProducoes();
    	
    	for (int i = 0; i < producoesGramatica.size(); i++) {
			
    		int linha = (i + 1);
    		GramaticaProducao producaoGramatica = producoesGramatica.get(i);
    		String naoTerminal = producaoGramatica.getNaoTerminal();
    		List<String> transicoes = producaoGramatica.getTransicoes();
    		
    		validarQuantidadeProducao(producoesEntrada, i);
    		
    		String producaoEntrada = StringUtil.removerEspacos(producoesEntrada.get(i));
    		String naoTerminalEntrada = GramaticaUtil.getNaoTerminal(producaoEntrada);
    		List<String> transicoesEntrada = GramaticaUtil.getTransicoes(producaoEntrada);
    		
    		transicoes = StringUtil.removerEspacos(transicoes);
    		transicoesEntrada = StringUtil.removerEspacos(transicoesEntrada);
    		
    		if(!Objects.equals(naoTerminal, naoTerminalEntrada)) {
    			throw new RuntimeException("Não terminal " + naoTerminalEntrada + " não esperado na linha " + linha);
    		}
    		
    		for (String transicao : transicoes) {
				if(!transicoesEntrada.contains(transicao)) {
					throw new RuntimeException("A produção de " + naoTerminal + " está inválida!");
				}
			}
    		
       		for (String transicaoEntrada : transicoesEntrada) {
				if(!transicoes.contains(transicaoEntrada)) {
					throw new RuntimeException("A produção de " + naoTerminal + " está inválida!");
				}
			}
		}
	}

	private void validarQuantidadeProducao(List<String> producoesEntrada, int i) {
		if(i > producoesEntrada.size() - 1) {
			throw new RuntimeException("Gramatica incompleta, verifique a quantidade de produções!");
		}
	}
	
	public Gramatica findGramaticaLL1(Integer idWorkflow){
		Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
		
		return workflow.getGramaticaLL1();
	}
}
