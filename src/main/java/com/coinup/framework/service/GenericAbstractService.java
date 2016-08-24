package com.coinup.framework.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coinup.framework.dao.BaseEntity;
import com.coinup.framework.dao.GenericAbstractDao;
import com.coinup.framework.util.GenericsUtils;
import com.coinup.utils.SpringUtils;

public abstract class GenericAbstractService<T extends BaseEntity, ID extends Serializable, R extends GenericAbstractDao<T, ID>> implements GenericService<T, ID> {
	private static final long serialVersionUID = 1L;

	protected R repository;
	
	@Override
	@SuppressWarnings("unchecked")
	public R getRepository(){
		if(repository == null){
			Class<R> daoClass = (Class<R>) GenericsUtils.discoverClass(this.getClass() , 2);
			repository = SpringUtils.getBean(daoClass);
		}
		return repository;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<T> findAll() {
		List<T> list = repository.findAll();
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> findAll(int firstItem, int maxItem) {
		List<T> list = repository.findAll(firstItem, maxItem);
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public T findByKey(ID id) {
		return repository.findByKey(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long getRowCount() {
		return repository.getRowCount();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Throwable.class)
	public void insert(T bean) {
		repository.insert(bean);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Throwable.class)
	public void update(T bean) {
		repository.update(bean);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Throwable.class)
	public void delete(T bean) {
		repository.delete(bean);
	}
	
}
