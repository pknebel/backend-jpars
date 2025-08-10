package br.edu.unochapeco.jpars.modelo;

public class TabelaSintaticaValueEntrada {

	private int rowIndex;
	private int columnIndex;
	private String value;

	public TabelaSintaticaValueEntrada() {
	}
	
	public TabelaSintaticaValueEntrada(int rowIndex, int columnIndex, String value) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = value;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
