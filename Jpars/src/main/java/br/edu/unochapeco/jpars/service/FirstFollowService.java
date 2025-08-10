package br.edu.unochapeco.jpars.service;

import java.util.List;
import java.util.Objects;

import br.edu.unochapeco.jpars.modelo.FirstFollow;
import br.edu.unochapeco.jpars.modelo.FirstFollowRow;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.util.FirstFollowUtil;

public class FirstFollowService {

	//validar a tabela de entrada que o usuario digitou com a tabela que esta definida no workflow
	public void validarFirstFollow(Workflow workflow, FirstFollow firstFollowEntrada) {
		
		FirstFollow firstFollow = workflow.getFirstFollow();
		
		for (FirstFollowRow firstFollowRow : firstFollow.getRows()) {
			
			String naoTerminal = firstFollowRow.getNaoTerminal();
			FirstFollowRow firstFollowRowEntrada = getFirstFollowRowEntrada(naoTerminal, firstFollowEntrada);
			
			List<String> tokensFirst = FirstFollowUtil.getTokens(firstFollowRow.getFirst());
			List<String> tokensFollow = FirstFollowUtil.getTokens(firstFollowRow.getFollow());
			
			List<String> tokensFirstEntrada = FirstFollowUtil.getTokens(firstFollowRowEntrada.getFirst());
			List<String> tokensFollowEntrada = FirstFollowUtil.getTokens(firstFollowRowEntrada.getFollow());
			
			validarTokensFirstFollow("First", naoTerminal, tokensFirst, tokensFirstEntrada);		
			validarTokensFirstFollow("Follow", naoTerminal, tokensFollow, tokensFollowEntrada);		
		}
		
	}

	private void validarTokensFirstFollow(String tipoConjunto, String naoTerminal, List<String> tokens, List<String> tokensEntrada) {
		
		for (String token : tokens) {
			
			if(!tokensEntrada.contains(token)) {
				throw new RuntimeException("O conjunto " + tipoConjunto + " do terminal " + naoTerminal + " está incorreto!");
			}
		}
		
		for (String token : tokensEntrada) {
			
			if(!tokens.contains(token)) {
				throw new RuntimeException("O conjunto " + tipoConjunto + " do terminal " + naoTerminal + " está incorreto!");
			}
		}
	}
	
	
	private FirstFollowRow getFirstFollowRowEntrada(String naoTerminal, FirstFollow firstFollowEntrada) {
		
		for (FirstFollowRow firstFollowRow : firstFollowEntrada.getRows()) {
			if(Objects.equals(naoTerminal, firstFollowRow.getNaoTerminal())) {
				return firstFollowRow;
			}
		}
		
		throw new RuntimeException("Não terminal " + naoTerminal + " não encotrado na tabela");
	}
	
	
}
