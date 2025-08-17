package br.edu.unochapeco.jpars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unochapeco.jpars.converter.TabelaSintaticaConverter;
import br.edu.unochapeco.jpars.dto.MessageDTO;
import br.edu.unochapeco.jpars.dto.TabelaSintaticaDTO;
import br.edu.unochapeco.jpars.dto.TabelaSintaticaEntradaDTO;
import br.edu.unochapeco.jpars.modelo.TabelaSintatica;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.repository.WorkflowRepository;
import br.edu.unochapeco.jpars.service.TabelaSintaticaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("tabela-sintatica")
public class TabelaSintaticaController {

	@Autowired
	private TabelaSintaticaService tabelaSintaticaService;
	
	@Autowired
	private WorkflowRepository workflowRepository;
	
	@Autowired
	private TabelaSintaticaConverter tabelaSintaticaConverter;
	
	@PostMapping("validar")
	public ResponseEntity<MessageDTO> validarTabelaSintatica(@Valid @NotNull(message = "Tabela sintática é obrigatória") @RequestBody TabelaSintaticaEntradaDTO tabelaSintaticaEntradaDTO) {
		
		tabelaSintaticaService.validarTabelaSintatica(tabelaSintaticaEntradaDTO);
		
		return ResponseEntity.ok(new MessageDTO("Tabela validada com sucesso!"));
	}
	
	@PostMapping("validar-sync")
	public ResponseEntity<MessageDTO> validarTabelaSintaticaSync(@Valid @NotNull(message = "Tabela sintática é obrigatória") @RequestBody TabelaSintaticaEntradaDTO tabelaSintaticaEntradaDTO) {
		
		tabelaSintaticaService.validarTabelaSintaticaSync(tabelaSintaticaEntradaDTO);
		
		return ResponseEntity.ok(new MessageDTO("Tabela com recuperação de erros validada com sucesso!"));
	}
	
	@GetMapping("{idWorkflow}")
	private ResponseEntity<TabelaSintaticaDTO> findTabelaSintatica(@Valid @NotNull(message = "ID do Workflow é obrigatório") @PathVariable("idWorkflow") Integer idWorkflow) {
		
		Workflow workflow = workflowRepository.findWorkflow(idWorkflow);
		
		TabelaSintatica tabelaSintatica = workflow.getTabelaSintatica();
		TabelaSintaticaDTO tabelaSintaticaDTO = tabelaSintaticaConverter.convertToDTO(tabelaSintatica);
		
		return ResponseEntity.ok(tabelaSintaticaDTO);
	}
	
}
