package br.edu.unochapeco.jpars.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unochapeco.jpars.dto.SentencaDTO;
import br.edu.unochapeco.jpars.modelo.Sentenca;

@Service
public class SentencaConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<SentencaDTO> convertToDTO(List<Sentenca> sentencas) {
		return sentencas.stream().map(sentenca -> modelMapper.map(sentenca, SentencaDTO.class)).toList();
	}
}
