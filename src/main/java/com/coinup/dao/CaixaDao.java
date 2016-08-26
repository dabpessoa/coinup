package com.coinup.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.coinup.dao.stringout.JPAStringOutDaoHelper;
import com.coinup.framework.dao.GenericAbstractDao;
import com.coinup.model.Caixa;
import com.coinup.model.CaixaPeriodicidade;
import com.coinup.utils.MapUtils;

@Repository
public class CaixaDao extends GenericAbstractDao<Caixa, Long> {
	
	@Resource(name="stringOutJPAHelper")
	private JPAStringOutDaoHelper daoHelper;
	
	public boolean possuiCaixaAberto(CaixaPeriodicidade caixaPeriodicidade) {
		Map<String, Object> params = MapUtils
				.create("idPeriodicidade", caixaPeriodicidade.getId());
		return daoHelper.queryHQLSingleResult("caixaAbertoNoPeriodo", params);
	}
	
}
