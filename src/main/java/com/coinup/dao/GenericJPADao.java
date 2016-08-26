package com.coinup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.coinup.dao.stringout.JPAStringOutDaoHelper;
import com.coinup.model.Caixa;
import com.coinup.utils.SpringUtils;

/**
 * Classe utilitária para não precisar sempre ficar criando DAO's para as operações básicas.
 * 
 * @author diego.pessoa
 *
 */
public class GenericJPADao {
	
	/**
	 * Método que utiliza a biblioteca 'Stringout' para auxilizar nas centralização das 
	 * consultas em um único arquivo.
	 * @return
	 */
	public static JPAStringOutDaoHelper getStringOutDaoHelper() {
		return SpringUtils.getBean("stringOutJPAHelper");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> findAll(Class<T> entityClass) {
		return getEntityManager().createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
	}
	
	public static <T> void insert(T entity) {
		getEntityManager().persist(entity);
	}

	public static <T> void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public static <T> T update(T entity) {
		return getEntityManager().merge(entity);
	}

	public static <T> T findByKey(Long key, Class<T> entityClass) {
		return getEntityManager().find(entityClass, key);
	}

	public static <T> Long getRowCount(Class<T> entityClass) {
		return (Long) getEntityManager()
				.createQuery("select count(o) from " + entityClass.getSimpleName() + " as o").getSingleResult();
	}

	public static <T> boolean contains(T entity) {
		return getEntityManager().contains(entity);
	}
	
	public static EntityManager getEntityManager() {
		return SpringUtils.getBean("entityManager");
	}
	
	public static void main(String[] args) {
		System.out.println(GenericJPADao.findAll(Caixa.class));
	}
	
}
