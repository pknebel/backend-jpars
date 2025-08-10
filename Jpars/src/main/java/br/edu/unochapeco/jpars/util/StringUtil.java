package br.edu.unochapeco.jpars.util;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

	public static String removerEspacos(String value) {
		return value.trim().replaceAll("\\s+","");
	}

	public static List<String> removerEspacos(List<String> values) {
		return values.stream().map(value -> value.replaceAll("\\s+", "")).collect(Collectors.toList());
	}
	
}
