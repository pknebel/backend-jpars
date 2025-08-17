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

import br.edu.unochapeco.jpars.dto.GramaticaEntradaDTO;
import br.edu.unochapeco.jpars.dto.MessageDTO;
import br.edu.unochapeco.jpars.modelo.Gramatica;
import br.edu.unochapeco.jpars.service.GramaticaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("gramatica")
public class GramaticaController {
	
	@Autowired
	private GramaticaService gramaticaService;
	
	@PostMapping("validar-recursao")
	public ResponseEntity<MessageDTO> validarEntradaGramaticaRecursao(@Valid @NotNull(message = "Informe os dados de entrada!") @RequestBody GramaticaEntradaDTO gramaticaEntradaDTO) {
		
		Integer idWorkflow = gramaticaEntradaDTO.getIdWorkflow();
		String gramaticaEntrada = gramaticaEntradaDTO.getGramaticaEntrada();
		
		gramaticaService.validarEntradaGramaticaRecursao(idWorkflow, gramaticaEntrada);
		
		return ResponseEntity.ok(new MessageDTO("Gramática validada com sucesso"));
	}
	
	@PostMapping("validar-fatoracao")
	public ResponseEntity<MessageDTO> validarEntradaGramaticaFatorada(@Valid @NotNull(message = "Informe os dados de entrada!") @RequestBody GramaticaEntradaDTO gramaticaEntradaDTO) {
		
		Integer idWorkflow = gramaticaEntradaDTO.getIdWorkflow();
		String gramaticaEntrada = gramaticaEntradaDTO.getGramaticaEntrada();
		
		gramaticaService.validarEntradaGramaticaFatorada(idWorkflow, gramaticaEntrada);
		
		return ResponseEntity.ok(new MessageDTO("Gramática validada com sucesso"));
	}
	
	@GetMapping("ll1/{idWorkflow}")
	public ResponseEntity<String> findGramaticaLL1(@Valid @NotNull(message = "ID do Workflow é obrigatório") @PathVariable("idWorkflow") Integer idWorkflow){
		
		Gramatica gramaticaLL1 = gramaticaService.findGramaticaLL1(idWorkflow);
		
		return ResponseEntity.ok(gramaticaLL1.toString());
	}
}
