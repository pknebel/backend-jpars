package br.edu.unochapeco.jpars.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Valid
public class TabelaSintaticaEntradaDTO {

	@NotNull(message = "ID do Workflow é obrigatório")
	private Integer idWorkflow;

	@NotEmpty(message = "É obrigatório informar a Tabela Sintática!")
	private List<TabelaSintaticaValueEntradaDTO> values;

	public void addValue(TabelaSintaticaValueEntradaDTO value) {
		if(Objects.isNull(values)) {
			this.values = new ArrayList<>();
		}
		this.values.add(value);
	}
	
	public List<TabelaSintaticaValueEntradaDTO> getValues() {
		return values;
	}

	public void setValues(List<TabelaSintaticaValueEntradaDTO> values) {
		this.values = values;
	}

	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
}
