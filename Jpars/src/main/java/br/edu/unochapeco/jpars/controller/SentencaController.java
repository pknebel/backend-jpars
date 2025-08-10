package br.edu.unochapeco.jpars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unochapeco.jpars.converter.SentencaConverter;
import br.edu.unochapeco.jpars.dto.EtapaValidacaoSentencaDTO;
import br.edu.unochapeco.jpars.dto.SentencaDTO;
import br.edu.unochapeco.jpars.modelo.Sentenca;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.repository.WorkflowRepository;
import br.edu.unochapeco.jpars.service.SentencaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("sentenca")
public class SentencaController {
	
	@Autowired
	private SentencaService sentencaService;
	
	@Autowired
	private WorkflowRepository workflowRepository;
	
	@Autowired
	private SentencaConverter sentencaConverter;

	@GetMapping("iniciar-validacao/{idWorkflow}/{idSentenca}")
	public ResponseEntity<EtapaValidacaoSentencaDTO> iniciarValidacao(@Valid @NotNull(message = "ID do Workflow obrigatorio") @PathVariable("idWorkflow") Integer idWorkflow, 
			                                                          @Valid @NotNull(message = "ID da Sentenca obrigatorio")  @PathVariable("idSentenca")  Integer idSentenca) {
	
		EtapaValidacaoSentencaDTO etapaValidacaoSentencaDTO = sentencaService.iniciarValidacao(idWorkflow, idSentenca);
		return ResponseEntity.ok(etapaValidacaoSentencaDTO);
	}
	
	@GetMapping("validar")
	public ResponseEntity<EtapaValidacaoSentencaDTO> validarProximoToken() {
		EtapaValidacaoSentencaDTO etapaValidacaoSentencaDTO = sentencaService.validarProximoToken();
		return ResponseEntity.ok(etapaValidacaoSentencaDTO);
	}
	
	@GetMapping("{idWorkflow}")
	private ResponseEntity<List<SentencaDTO>> findSentenca(@PathVariable("idWorkflow") Integer idWorkflow) {
		Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
		List<Sentenca> sentencas = workflow.getSentencas();
		List<SentencaDTO> sentencasDTO = sentencaConverter.convertToDTO(sentencas);
		return ResponseEntity.ok(sentencasDTO);
	}
}
