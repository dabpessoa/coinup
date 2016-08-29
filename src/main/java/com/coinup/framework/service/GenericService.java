package com.coinup.framework.service;

import java.io.Serializable;
import java.util.List;

import com.coinup.framework.dao.BaseEntity;
import com.coinup.framework.dao.GenericAbstractDao;

public interface GenericService<Entity extends BaseEntity, key extends Serializable> extends Serializable {
	
	void insert(Entity entity);
	void update(Entity entity);
	void delete(Entity entity);
	
	Long getRowCount();	
	
	Entity findByKey(key id);
	
	List<Entity> findAll();
	List<Entity> findAll(int firstItem, int maxItem);
	List<Entity> find(Entity entity);

	GenericAbstractDao<Entity, key> getRepository();
	
}