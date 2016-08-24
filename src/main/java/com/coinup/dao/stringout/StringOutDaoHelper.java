package com.coinup.dao.stringout;

import java.util.List;
import java.util.Map;

public interface StringOutDaoHelper {

	<T> List<T> querySQLList(String stringOutQueryId, Map<String,Object> params);
	
	/**
	 * 
	 * Quando se passa a classe por parâmetro, a query deve ser um "select *"
	 * 
	 * @param stringOutQueryId
	 * @param params
	 * @param entityClass
	 * @return
	 */
	<T> List<T> querySQLList(String stringOutQueryId, Map<String,Object> params, Class<?> entityClass);
	
	<T> T querySQLSingleResult(String stringOutQueryId, Map<String,Object> params);
	
	/**
	 * 
	 * Quando se passa a classe por parâmetro, a query deve ser um "select *"
	 * 
	 * @param stringOutQueryId
	 * @param params
	 * @param entityClass
	 * @return
	 */
	<T> T querySQLSingleResult(String stringOutQueryId, Map<String,Object> params, Class<?> entityClass);
	
}
