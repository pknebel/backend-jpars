package br.edu.unochapeco.jpars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.converter.WorkflowConverter;
import br.edu.unochapeco.jpars.dto.WorkflowDTO;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.repository.WorkflowRepository;

@Service
public class WorkflowService {

	@Autowired
	private WorkflowRepository workflowRepository;
	
	public List<WorkflowDTO> findWorkflows() {
		
		List<Workflow> workflows = workflowRepository.findWorkflows();
		
		WorkflowConverter converter = new WorkflowConverter();
		
		return converter.convertToDTO(workflows);
		
	}
	
}
