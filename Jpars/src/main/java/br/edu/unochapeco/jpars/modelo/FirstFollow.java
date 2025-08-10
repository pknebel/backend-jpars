package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FirstFollow {

	private List<FirstFollowRow> rows;
	
	public void addFirstFollowRow(FirstFollowRow firstFollowRow) {
		if(Objects.isNull(rows)) {
			this.rows = new ArrayList<>();
		}
		this.rows.add(firstFollowRow);
	}
	
	public List<FirstFollowRow> getRows() {
		return rows;
	}

	public void setRows(List<FirstFollowRow> rows) {
		this.rows = rows;
	}

}
