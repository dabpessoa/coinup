package com.coinup.service;

import org.springframework.stereotype.Service;

import com.coinup.dao.CaixaDao;
import com.coinup.exceptions.CaixaAbertoNoPeriodoException;
import com.coinup.framework.service.GenericAbstractService;
import com.coinup.model.Caixa;
import com.coinup.model.CaixaPeriodicidade;

@Service
public class CaixaService extends GenericAbstractService<Caixa, Long, CaixaDao> {
	private static final long serialVersionUID = 1L;
	
	public void abrirCaixa(Caixa caixa) {
		
		verificaAbertura(caixa);
		
		
	}
	
	public void fecharCaixa(Caixa caixa) {
		
	}
	
	public void verificaAbertura(Caixa caixa) {
		CaixaPeriodicidade caixaPeriodicidade = caixa.getCaixaPeriodicidade();		
		boolean possuiCaixaAbertoParaPeriodo = getRepository().possuiCaixaAberto(caixaPeriodicidade);
		
		if (possuiCaixaAbertoParaPeriodo) {
			throw new CaixaAbertoNoPeriodoException();
		}
	}
	
}
