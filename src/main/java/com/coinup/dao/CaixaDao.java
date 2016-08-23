package com.coinup.dao;

import org.springframework.stereotype.Repository;

import com.coinup.framework.dao.GenericAbstractDao;
import com.coinup.model.Caixa;
import com.coinup.utils.SpringUtils;

@Repository
public class CaixaDao extends GenericAbstractDao<Caixa, Long> {

	public static void main(String[] args) {
		
		CaixaDao dao = SpringUtils.getBean(CaixaDao.class);
		System.out.println(dao);
		
		System.out.println(dao.findAll());
		
	}
	
}
