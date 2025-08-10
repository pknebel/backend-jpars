package br.edu.unochapeco.jpars.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.dto.TabelaSintaticaDTO;
import br.edu.unochapeco.jpars.modelo.TabelaSintatica;

@Service
public class TabelaSintaticaConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TabelaSintaticaDTO convertToDTO(TabelaSintatica tabelaSintatica) {
		return modelMapper.map(tabelaSintatica, TabelaSintaticaDTO.class);
	}
	
}
