package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabelaSintaticaEntrada {

	private List<TabelaSintaticaValueEntrada> values;

	public void addValue(TabelaSintaticaValueEntrada value) {
		if(Objects.isNull(values)) {
			this.values = new ArrayList<>();
		}
		this.values.add(value);
	}
	
	public List<TabelaSintaticaValueEntrada> getValues() {
		return values;
	}

	public void setValues(List<TabelaSintaticaValueEntrada> values) {
		this.values = values;
	}

}
