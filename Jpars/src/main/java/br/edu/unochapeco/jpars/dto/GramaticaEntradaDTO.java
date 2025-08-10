package br.edu.unochapeco.jpars.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public class GramaticaEntradaDTO {
	
	@NotNull(message = "O ID do WorkFlow é obrigatório!")
	private Integer idWorkflow;
	
	@NotBlank(message = "Informe a gramática de entrada!")
	private String gramaticaEntrada;

	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public String getGramaticaEntrada() {
		return gramaticaEntrada;
	}

	public void setGramaticaEntrada(String gramaticaEntrada) {
		this.gramaticaEntrada = gramaticaEntrada;
	}

}
