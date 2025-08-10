package br.edu.unochapeco.jpars.service;

import java.util.Objects;

import br.edu.unochapeco.jpars.modelo.TabelaSintatica;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaColumn;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaEntrada;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaRow;
import br.edu.unochapeco.jpars.modelo.TabelaSintaticaValueEntrada;
import br.edu.unochapeco.jpars.modelo.Workflow;
import br.edu.unochapeco.jpars.util.StringUtil;

public class TabelaSintaticaService {

	public void validarTabelaSintatica(Workflow workflow, TabelaSintaticaEntrada tabelaSintaticaEntrada) {
		
		TabelaSintatica tabelaSintatica = workflow.getTabelaSintatica();
		for (TabelaSintaticaRow tabelaSintaticaRow : tabelaSintatica.getRows()) {
			
			for (TabelaSintaticaColumn tabelaSintaticaColumn : tabelaSintaticaRow.getColumns()) {
				
				String terminal = tabelaSintaticaColumn.getTerminal();
				String naoTerminal = tabelaSintaticaRow.getNaoTerminal();
				
				int rowIndex = tabelaSintaticaRow.getIndex();
				int columnIndex = tabelaSintaticaColumn.getIndex();
				String producao = tabelaSintaticaColumn.getProducao().toString();
				
				String producaoEntrada = getTabelaSintaticaValueEntrada(tabelaSintaticaEntrada, rowIndex, columnIndex);
				
				//A producao nao e igual e nao e um Sync entao eu valido.
				if(!Objects.equals(producao, producaoEntrada) && !tabelaSintaticaColumn.isSync()) {
					throw new RuntimeException("A produção do não terminal " + naoTerminal + " está incorreto para o token " + terminal);
				}
			}
		}
	}
	
	public void validarTabelaSintaticaSync(Workflow workflow, TabelaSintaticaEntrada tabelaSintaticaEntrada) {
		
		TabelaSintatica tabelaSintatica = workflow.getTabelaSintatica();
		for (TabelaSintaticaRow tabelaSintaticaRow : tabelaSintatica.getRows()) {
			
			for (TabelaSintaticaColumn tabelaSintaticaColumn : tabelaSintaticaRow.getColumns()) {
			
				//So valido os campos Sync de entrada se a existir nao existir
				if(tabelaSintaticaColumn.isNaoPossuiProducao()) {
					
					int rowIndex = tabelaSintaticaRow.getIndex();
					int columnIndex = tabelaSintaticaColumn.getIndex();
					String sync = tabelaSintaticaColumn.getSync();
					String syncEntrada = getTabelaSintaticaValueEntrada(tabelaSintaticaEntrada, rowIndex, columnIndex);
					
					if(!Objects.equals(sync, syncEntrada)) {
						String terminal = tabelaSintaticaColumn.getTerminal();
						String naoTerminal = tabelaSintaticaRow.getNaoTerminal();
						throw new RuntimeException("Sincronismo inválido para o não terminal " + naoTerminal + " e token " + terminal);
					}
				}
			}
		}
		
	}
	
	private String getTabelaSintaticaValueEntrada(TabelaSintaticaEntrada tabelaSintaticaEntrada, int rowIndex, int columnIndex) {
		
		for (TabelaSintaticaValueEntrada tabelaSintaticaValueEntrada : tabelaSintaticaEntrada.getValues()) {
			
			if(tabelaSintaticaValueEntrada.getRowIndex() == rowIndex && 
			   tabelaSintaticaValueEntrada.getColumnIndex() == columnIndex) {
				String value = Objects.toString(tabelaSintaticaValueEntrada.getValue(), "");
				return StringUtil.removerEspacos(value);
			}
		}
		
		throw new RuntimeException("Não foi encontrado a linha " + rowIndex + " e coluna " + columnIndex + " na tabela sintática");
	}
	
}
