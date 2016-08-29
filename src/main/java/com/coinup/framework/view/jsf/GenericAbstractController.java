package com.coinup.framework.view.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.coinup.framework.dao.BaseEntity;
import com.coinup.framework.service.GenericAbstractService;
import com.coinup.framework.util.GenericsUtils;
import com.coinup.utils.SpringUtils;

public class GenericAbstractController<Entity extends BaseEntity, Key extends Serializable, Service extends GenericAbstractService<Entity, Key, ?>> implements GenericController {

	private Logger logger;
	
	protected Entity entity;
	protected Entity searchEntity;
	protected Service service;
	
	protected Class<Entity> entityClass;
	protected Class<Key> keyClass;
	protected Class<Service> serviceClass;
	
	protected List<Entity> searchList;
	
	public GenericAbstractController() {}
	
	public void find() {
		searchList = getService().find(getSearchEntity());
	}
	
	public Entity getEntity() {
		if(entity == null){
			try {
				entity = getEntityClass().newInstance();
				entity.init();
			} catch (InstantiationException | IllegalAccessException e) {
				getLogger().log(Level.SEVERE, "Erro ao instanciar a entidade  " + getEntityClass(), e);
			} 
		}
		return entity;
	}
	
	public Entity getSearchEntity() {
		if(searchEntity == null){
			try {
				searchEntity = getEntityClass().newInstance();
				searchEntity.init();
			} catch (InstantiationException | IllegalAccessException e) {
				getLogger().log(Level.SEVERE, "Erro ao instanciar a entidade  " + getEntityClass(), e);
			} 
		}
		return searchEntity;
	}
	
	public List<Entity> getSearchList() {
		if (searchList == null) {
			searchList = new ArrayList<Entity>();
		}
		return searchList;
	}
	
	public Service getService() {
		if(service == null){
			service = SpringUtils.getBean(getServiceClass());
		}
		return service;
	}
	
	@SuppressWarnings("unchecked")
	protected Class<Entity> getEntityClass(){
		if(entityClass == null){
			entityClass = (Class<Entity>) GenericsUtils.discoverClass( this.getClass() , 0);
		}
		return entityClass;
	}
	
	@SuppressWarnings("unchecked")
	public Class<Key> getKeyClass() {
		if (keyClass == null) {
			keyClass = (Class<Key>) GenericsUtils.discoverClass(this.getClass(), 1);
		}
		return keyClass;
	}
	
	@SuppressWarnings("unchecked")
	public Class<Service> getServiceClass() {
		if( serviceClass == null ){
			serviceClass = (Class<Service>) GenericsUtils.discoverClass( getClass() , 2) ;
		}
		return serviceClass;
	}
	
	public String getFormPageLocation() {
		return getCrudAnnotation().formPageLocation();
	}
	
	public String getListPageLocation() {
		return getCrudAnnotation().listPageLocation();
	}
	
	private Crud getCrudAnnotation() {
		return this.getClass().getAnnotation(Crud.class);
	}
	
	public Logger getLogger() {
		if(logger == null){
			logger = Logger.getLogger(getClass().getName());
		}
		return logger;
	}
	
}
