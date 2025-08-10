package br.edu.unochapeco.jpars.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Valid
public class FirstFollowDTO {
	
	@NotNull(message = "Informe o ID do Workflow!")
	private Integer idWorkflow;

	@NotEmpty(message = "Informe o First Follow")
	private List<FirstFollowRowDTO> rows;
	
	public void addFirstFollowRow(FirstFollowRowDTO firstFollowRowDTO) {
		if(Objects.isNull(rows)) {
			this.rows = new ArrayList<>();
		}
		this.rows.add(firstFollowRowDTO);
	}
	
	public List<FirstFollowRowDTO> getRows() {
		return rows;
	}

	public void setRows(List<FirstFollowRowDTO> rows) {
		this.rows = rows;
	}

	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
}
