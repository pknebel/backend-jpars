package br.edu.unochapeco.jpars.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabelaSintatica {

	private List<TabelaSintaticaRow> rows;

	public void addTabelaSintaticaRow(TabelaSintaticaRow tabelaSintaticaRow) {
		if(Objects.isNull(this.rows)) {
			this.rows = new ArrayList<>();
		}
		this.rows.add(tabelaSintaticaRow);
	}
	
	public List<TabelaSintaticaRow> getRows() {
		return rows;
	}

	public void setRows(List<TabelaSintaticaRow> rows) {
		this.rows = rows;
	}
	
	public TabelaSintaticaRow getRowByIndex(int index) {
		for (TabelaSintaticaRow tabelaSintaticaRow : rows) {
			if(Objects.equals(tabelaSintaticaRow.getIndex(), index)) {
				return tabelaSintaticaRow;
			}
		}
		throw new RuntimeException("Linha " + index + " não encontrada na tabela sintatica");
	}

}
