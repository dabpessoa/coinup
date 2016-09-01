package com.coinup.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.coinup.dao.CaixaDao;
import com.coinup.exceptions.CaixaAbertoNoPeriodoException;
import com.coinup.exceptions.InsertIdNotNullException;
import com.coinup.exceptions.NullObjectException;
import com.coinup.framework.service.GenericAbstractService;
import com.coinup.model.Caixa;
import com.coinup.model.CaixaPeriodicidade;

@Service
public class CaixaService extends GenericAbstractService<Caixa, Long, CaixaDao> {
	private static final long serialVersionUID = 1L;
	
	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Throwable.class)
	public void criarCaixa(Caixa caixa) {
		caixa.setSaldo(BigDecimal.ZERO);
		caixa.setDataCriacao(new Date());
		criarCaixaPrePreenchido(caixa);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Throwable.class)
	public void criarCaixaPrePreenchido(Caixa caixa) {
		if (caixa.getId() != null) {
			throw new InsertIdNotNullException();
		}
		verificaAbertura(caixa);
		getRepository().insert(caixa);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Throwable.class)
	public void fecharCaixa(Caixa caixa) {
		if (caixa == null || caixa.getId() == null) {
			throw new NullObjectException();
		}
		
		caixa.setDataFechamento(new Date());
		
		getRepository().update(caixa);
	}
	
	public void verificaAbertura(Caixa caixa) {
		CaixaPeriodicidade caixaPeriodicidade = caixa.getCaixaPeriodicidade();		
		boolean possuiCaixaAbertoParaPeriodo = getRepository().possuiCaixaAberto(caixaPeriodicidade);
		
		if (possuiCaixaAbertoParaPeriodo) {
			throw new CaixaAbertoNoPeriodoException();
		}
	}
	
	@Override
	public List<Caixa> find(Caixa entity) {
		return getRepository().findAll();
	}
	
}
