package br.edu.unochapeco.jpars.dto;

public class TabelaSintaticaColumnDTO {

	private int index;
	private String sync;
	private String terminal;
	private TabelaSintaticaProducaoDTO producao;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public TabelaSintaticaProducaoDTO getProducao() {
		return producao;
	}

	public void setProducao(TabelaSintaticaProducaoDTO producao) {
		this.producao = producao;
	}

}
