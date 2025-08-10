package br.edu.unochapeco.jpars.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FirstFollowUtil {

	public static List<String> getTokens(String firstFollow) {
		
		if(Objects.isNull(firstFollow) || firstFollow.isEmpty()) {
			throw new RuntimeException("Digite todos os campos da tabela First Follow");
		}
		List<String> tokens = Arrays.asList(firstFollow.split(","));
		return StringUtil.removerEspacos(tokens);
	}
	
}
