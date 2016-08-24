package com.coinup.dao.stringout;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import me.dabpessoa.stringout.enums.StringOutType;

public class JPAStringOutDaoHelper extends AbstractStringOutDaoHelper {

	private EntityManager entityManager;
	
	public JPAStringOutDaoHelper(EntityManager entityManager, StringOutType stringOutType) {
		super(stringOutType, null, null);
		this.entityManager = entityManager;
	}
	
	public JPAStringOutDaoHelper(EntityManager entityManager, StringOutType stringOutType, String filePath, String encoding) {
		super(stringOutType, filePath, encoding);
		this.entityManager = entityManager;
	}
	
	public <T> List<T> queryHQLList(String queryId, Map<String,Object> params) {
		return queryAny(queryId, params, null, true, true);
	}
	
	public <T> T queryHQLSingleResult(String queryId, Map<String,Object> params) {
		return queryAny(queryId, params, null, true, false);
	}	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> querySQLList(String stringOutQueryId, Map<String,Object> params, Class<?> entityClass, String... fieldsNames ) {
		List<Object[]> resultsList = querySQLList(stringOutQueryId, params);
		try {
			return (List<T>) convertObjectsByFieldsName(resultsList, entityClass, fieldsNames);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Erro ao tentar consultar entidade via native SQL.");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T querySQLSingleResult(String stringOutQueryId, Map<String,Object> params, Class<?> entityClass, String... fieldsNames ) {
		Object[] resultObjectArray = querySQLSingleResult(stringOutQueryId, params);
		try {
			return (T) convertObjectsByFieldsName(resultObjectArray, entityClass, fieldsNames);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Erro ao tentar consultar entidade via native SQL.");
	}
	
	@Override
	public <T> List<T> querySQLList(String stringOutQueryId, Map<String,Object> params) {
		return queryAny(stringOutQueryId, params, null, false, true);
	}
	
	@Override
	public <T> List<T> querySQLList(String stringOutQueryId, Map<String, Object> params, Class<?> entityClass) {
		return queryAny(stringOutQueryId, params, entityClass, false, true);
	}
	
	@Override
	public <T> T querySQLSingleResult(String stringOutQueryId, Map<String,Object> params) {
		return queryAny(stringOutQueryId, params, null, false, false);
	}
	
	@Override
	public <T> T querySQLSingleResult(String stringOutQueryId, Map<String, Object> params, Class<?> entityClass) {
		return queryAny(stringOutQueryId, params, entityClass, false, false);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T queryAny(String queryId, Map<String, Object> replacements, Class<?> entityClass, boolean isJPQL, boolean isResultList) {
		String queryString;
		try {
			basicSQLParamsWrap(replacements);
			queryString = getStringOut().find(queryId, transformToQueryMap(replacements));
		} catch (Throwable e1) {
			throw new RuntimeException(e1);
		}
		Query q = null;
		if (isJPQL) {
			q = entityManager.createQuery(queryString);
		} else {
			if (entityClass != null) q = entityManager.createNativeQuery(queryString, entityClass);
			else q = entityManager.createNativeQuery(queryString);
		}
		try {
			if (isResultList) {
				return (T) q.getResultList();
			} else {
				return (T) q.getSingleResult();
			}
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
