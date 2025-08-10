package br.edu.unochapeco.jpars.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.unochapeco.jpars.dto.WorkflowDTO;
import br.edu.unochapeco.jpars.modelo.Workflow;

public class WorkflowConverter {

	public List<WorkflowDTO> convertToDTO(List<Workflow> workflows) {
		
		List<WorkflowDTO> workflowsDTO = new ArrayList<WorkflowDTO>();
		
		for (Workflow workflow : workflows) {
			
			WorkflowDTO workflowDTO = new WorkflowDTO();
			workflowDTO.setNivel(workflow.getNivel());
			workflowDTO.setIdWorkflow(workflow.getIdWorkflow());
			workflowDTO.setGramatica(workflow.getGramatica().toString());
			workflowDTO.setPossuiRecursao(Objects.nonNull(workflow.getGramaticaSemRecursao()));
			workflowDTO.setPossuiFatoracao(Objects.nonNull(workflow.getGramaticaFatorada()));
			workflowsDTO.add(workflowDTO);
			
		}
		return workflowsDTO;
	}
}
