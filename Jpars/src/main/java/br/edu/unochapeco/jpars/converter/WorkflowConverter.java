package br.edu.unochapeco.jpars.converter;

import java.util.ArrayList;
import java.util.List;

import br.edu.unochapeco.jpars.dto.WorkflowDTO;
import br.edu.unochapeco.jpars.modelo.Workflow;

public class WorkflowConverter {

	public List<WorkflowDTO> convertToDTO(List<Workflow> workflows) {
		
		List<WorkflowDTO> workflowsDTO = new ArrayList<WorkflowDTO>();
		
		for (Workflow workflow : workflows) {
			
			WorkflowDTO dto = new WorkflowDTO();
			dto.setIdWorkflow(workflow.getIdWorkflow());
			dto.setNivel(workflow.getNivel());
			dto.setGramatica(workflow.getGramatica().toString());
			
			workflowsDTO.add(dto);
			
		}
		return workflowsDTO;
	}
}
