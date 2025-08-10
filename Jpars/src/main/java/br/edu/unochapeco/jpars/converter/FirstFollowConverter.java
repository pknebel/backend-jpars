package br.edu.unochapeco.jpars.converter;

import java.util.List;

import br.edu.unochapeco.jpars.dto.FirstFollowDTO;
import br.edu.unochapeco.jpars.dto.FirstFollowRowDTO;
import br.edu.unochapeco.jpars.modelo.FirstFollow;
import br.edu.unochapeco.jpars.modelo.FirstFollowRow;

public class FirstFollowConverter {

	public FirstFollow convertToModel(FirstFollowDTO firstFollowDTO) {
	
		FirstFollow firstFollow = new FirstFollow();
		
		List<FirstFollowRowDTO> rows = firstFollowDTO.getRows();
		
		for (FirstFollowRowDTO firstFollowRowDTO : rows) {
			
			FirstFollowRow firstFollowRow = new FirstFollowRow();
			
			firstFollowRow.setNaoTerminal(firstFollowRowDTO.getNaoTerminal());
			firstFollowRow.setFirst(firstFollowRowDTO.getFirst());
			firstFollowRow.setFollow(firstFollowRowDTO.getFollow());

			firstFollow.addFirstFollowRow(firstFollowRow);
		}
		return firstFollow;
	}
	
	public FirstFollowDTO convertToDTO(FirstFollow firstFollow) {
		
		FirstFollowDTO firstFollowDTO = new FirstFollowDTO();
		
		List<FirstFollowRow> rows = firstFollow.getRows();
		
		for (FirstFollowRow firstFollowRow : rows) {
			
			FirstFollowRowDTO firstFollowRowDTO = new FirstFollowRowDTO();
			
			firstFollowRowDTO.setNaoTerminal(firstFollowRow.getNaoTerminal());
			firstFollowRowDTO.setFirst(firstFollowRow.getFirst());
			firstFollowRowDTO.setFollow(firstFollowRow.getFollow());

			firstFollowDTO.addFirstFollowRow(firstFollowRowDTO);
		}
		return firstFollowDTO;
	}
}
