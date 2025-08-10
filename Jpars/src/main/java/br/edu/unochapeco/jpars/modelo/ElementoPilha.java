package br.edu.unochapeco.jpars.modelo;

public class ElementoPilha {

	private String elemento;
	private boolean terminal;

	public ElementoPilha(String elemento, boolean terminal) {
		this.elemento = elemento;
		this.terminal = terminal;
	}

	public String getElemento() {
		return elemento;
	}

	public boolean isTerminal() {
		return terminal;
	}
	
	public boolean isNaoTerminal() {
		return !terminal;
	}
	
	@Override
	public String toString() {
		return elemento;
	}

}
