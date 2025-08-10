package br.edu.unochapeco.jpars.util;

import java.util.Arrays;
import java.util.List;

public class GramaticaUtil {

	public static List<String> getProducoesGramatica(String gramatica) {
		return Arrays.asList(gramatica.split("\n"));
	}
	
	public static String getNaoTerminal(String producao) {
		return producao.substring(0, 1);
	}
	
	public static List<String> getTransicoes(String producao) {
		String[] transicoes = producao.substring(2, producao.length()).split("\\|");
		return Arrays.asList(transicoes);
	}
	
}
