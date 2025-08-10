package br.edu.unochapeco.jpars.modelo;

import java.util.Objects;

public class TabelaSintaticaColumn {

	private static final String SYNC_VALUE = "Sync";
	
	private int index;
	private String sync;
	private String terminal;
	private TabelaSintaticaProducao producao;

	public TabelaSintaticaColumn(int index, String terminal) {
		this.index = index;
		this.terminal = terminal;
		this.producao = new TabelaSintaticaProducao();
	}
	
	public TabelaSintaticaColumn(int index, String terminal, TabelaSintaticaProducao producao) {
		this.index = index;
		this.terminal = terminal;
		this.producao = producao;
	}

	public TabelaSintaticaColumn withSync() {
		this.sync = SYNC_VALUE;
		return this;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSync() {
		return Objects.toString(sync, "");
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public TabelaSintaticaProducao getProducao() {
		return producao;
	}

	public void setProducao(TabelaSintaticaProducao producao) {
		this.producao = producao;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public boolean isSync() {
		return Objects.equals(sync, SYNC_VALUE);
	}
	
	public boolean isPossuiProducao() {
		return !producao.toString().isEmpty();
	}
	
	public boolean isNaoPossuiProducao() {
		return producao.toString().isEmpty();
	}

}
