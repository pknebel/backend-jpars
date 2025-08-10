package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabelaSintaticaRow {

	private int index;
	private String naoTerminal;
	private List<TabelaSintaticaColumn> columns;

	public TabelaSintaticaRow(int index, String naoTerminal) {
		this.index = index;
		this.naoTerminal = naoTerminal;
	}
	
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

	public List<TabelaSintaticaColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TabelaSintaticaColumn> columns) {
		this.columns = columns;
	}
	
	public void addColumn(TabelaSintaticaColumn tabelaSintaticaColumn) {
		if(Objects.isNull(this.columns)) {
			this.columns = new ArrayList<>();
		}
		this.columns.add(tabelaSintaticaColumn);
	}

}
