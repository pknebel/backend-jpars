package br.edu.unochapeco.jpars.dto;

import java.util.List;

public class TabelaSintaticaRowDTO {

	private int index;
	private String naoTerminal;
	private List<TabelaSintaticaColumnDTO> columns;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getNaoTerminal() {
		return naoTerminal;
	}

	public void setNaoTerminal(String naoTerminal) {
		this.naoTerminal = naoTerminal;
	}

	public List<TabelaSintaticaColumnDTO> getColumns() {
		return columns;
	}

	public void setColumns(List<TabelaSintaticaColumnDTO> columns) {
		this.columns = columns;
	}

}
