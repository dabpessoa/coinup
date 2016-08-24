package com.coinup.framework.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericAbstractDao<T extends BaseEntity, I extends Serializable> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;
	private Class<I> keyClass;

	@SuppressWarnings("unchecked")
	public GenericAbstractDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.keyClass = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public void insert(T entity) {
		getEntityManager().persist(entity);
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public T update(T entity) {
		return getEntityManager().merge(entity);
	}

	public T findByKey(I key) {
		return getEntityManager().find(getEntityClass(), key);
	}

	public Long getRowCount() {
		return (Long) getEntityManager()
				.createQuery("select count(o) from " + getEntityClass().getSimpleName() + " as o").getSingleResult();
	}

	public boolean contains(T entity) {
		return getEntityManager().contains(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("Select t from " + getEntityClass().getSimpleName() + " t")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(int firstResult, int maxResults) {
		final Query query = getEntityManager()
				.createQuery("select o from " + getEntityClass().getSimpleName() + " as o");

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public Class<I> getKeyClass() {
		return keyClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
