package br.edu.unochapeco.jpars.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.builder.WorkflowDificil1Builder;
import br.edu.unochapeco.jpars.builder.WorkflowDificil2Builder;
import br.edu.unochapeco.jpars.builder.WorkflowFacil1Builder;
import br.edu.unochapeco.jpars.builder.WorkflowFacil2Builder;
import br.edu.unochapeco.jpars.builder.WorkflowIntermediario1Builder;
import br.edu.unochapeco.jpars.builder.WorkflowIntermediario2Builder;
import br.edu.unochapeco.jpars.modelo.Sentenca;
import br.edu.unochapeco.jpars.modelo.Workflow;

@Service
public class WorkflowRepository {

	private List<Workflow> workflows = new ArrayList<>();
	
	public WorkflowRepository() {
		workflows.add(new WorkflowFacil1Builder().build());
		workflows.add(new WorkflowFacil2Builder().build());
		workflows.add(new WorkflowIntermediario1Builder().build());
		workflows.add(new WorkflowIntermediario2Builder().build());
		workflows.add(new WorkflowDificil1Builder().build());
		workflows.add(new WorkflowDificil2Builder().build());
	}

	public List<Workflow> findWorkflows() {
		return workflows;
	}
	
	public Workflow findWorkflow(int idWorkflow) {
		for (Workflow workflow : workflows) {
			if(Objects.equals(workflow.getIdWorkflow(), idWorkflow)) {
				return workflow;
			}
		}
		throw new RuntimeException("Workflow não encontrado com id " + idWorkflow);
	}
	
	public Sentenca findSentencaWorkflow(Workflow workflow, int idSentenca) {
		for (Sentenca sentenca : workflow.getSentencas()) {
			if(Objects.equals(sentenca.getIdSentenca(), idSentenca)) {
				return sentenca;
			}
		}
		throw new RuntimeException("Sentença não encontrado com id " + idSentenca);
	}
	
	
	
}
