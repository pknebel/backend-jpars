package br.edu.unochapeco.jpars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unochapeco.jpars.converter.FirstFollowConverter;
import br.edu.unochapeco.jpars.dto.FirstFollowDTO;
import br.edu.unochapeco.jpars.dto.MessageDTO;
import br.edu.unochapeco.jpars.modelo.FirstFollow;
import br.edu.unochapeco.jpars.service.FirstFollowService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("first-follow")
public class FirstFollowController {

	@Autowired
	private FirstFollowService firstFollowService;

	@PostMapping("validar-conjuntos")
	public ResponseEntity<MessageDTO> validarFirstFollow(@Valid @NotNull(message = "Informe os conjuntos!") @RequestBody FirstFollowDTO firstFollowDTO){
		
		FirstFollowConverter converter = new FirstFollowConverter();
		
		FirstFollow firstFollow = converter.convertToModel(firstFollowDTO);
		
		Integer idWorkflow = firstFollowDTO.getIdWorkflow();
		
		firstFollowService.validarFirstFollow(idWorkflow, firstFollow);
		
		return ResponseEntity.ok(new MessageDTO("Conjuntos First e Follow validados com sucesso!"));
		
	}
	
}