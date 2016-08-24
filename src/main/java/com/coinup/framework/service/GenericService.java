package com.coinup.framework.service;

import java.io.Serializable;
import java.util.List;

import com.coinup.framework.dao.BaseEntity;
import com.coinup.framework.dao.GenericAbstractDao;

public interface GenericService<T extends BaseEntity, ID extends Serializable> extends Serializable {
	
	void insert(T bean);
	void update(T bean);
	void delete(T bean);
	
	Long getRowCount();	
	
	T findByKey(ID id);
	
	List<T> findAll();
	List<T> findAll(int firstItem, int maxItem);

	GenericAbstractDao<T, ID> getRepository();
	
}