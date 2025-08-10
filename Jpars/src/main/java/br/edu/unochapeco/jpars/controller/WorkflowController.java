package br.edu.unochapeco.jpars.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unochapeco.jpars.dto.WorkflowDTO;
import br.edu.unochapeco.jpars.service.WorkflowService;

@RestController
@RequestMapping("workflow")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;
	
	@GetMapping
	public ResponseEntity<List<WorkflowDTO>> getListaWorkflows() {
		
		List<WorkflowDTO> workflows = workflowService.findWorkflows();
		
		return ResponseEntity.ok(workflows);
	}
	
}
