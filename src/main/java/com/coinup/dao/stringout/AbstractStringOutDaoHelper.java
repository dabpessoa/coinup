package com.coinup.dao.stringout;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.coinup.utils.MapUtils;
import com.coinup.utils.ReflectionUtils;

import me.dabpessoa.stringout.StringOut;
import me.dabpessoa.stringout.StringOutManager;
import me.dabpessoa.stringout.enums.StringOutType;

public abstract class AbstractStringOutDaoHelper implements StringOutDaoHelper {

	private StringOut stringOut;
	
	public AbstractStringOutDaoHelper(StringOutType stringOutType, String filePath, String encoding) {
		if (filePath == null && encoding == null) stringOut = StringOutManager.getInstance(stringOutType);
		else if (encoding == null) stringOut = StringOutManager.getInstance(stringOutType, filePath);
		else if (filePath == null) stringOut = StringOutManager.getInstanceWithEncoding(stringOutType, encoding);
	}
	
	public <T> List<T> convertObjectsByFieldsName(List<Object[]> resultsList, Class<T> clazz, String... fieldsNames) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		List<T> list = new ArrayList<T>();
		for (Object[] results : resultsList) {
			list.add(convertObjectsByFieldsName(results, clazz, fieldsNames));
		}
		return list;
	}
	
	public <T> T convertObjectsByFieldsName(Object[] resultObjectArray, Class<T> clazz, String... fieldsNames) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		T entity = clazz.newInstance();
		for (int i = 0 ; i < fieldsNames.length ; i++) {
			Field field = ReflectionUtils.findFieldByName(clazz, fieldsNames[i]);
			
			Object transformedFieldValue = basicFieldTypeTransform(resultObjectArray[i], field);
			
			ReflectionUtils.setFieldValue(entity, field, transformedFieldValue);
		}
		return entity;
	}
	
	public Map<String, String> transformToQueryMap(Map<String,Object> objectParams) {
		Map<String,String> stringParams = new HashMap<>();
		for (Entry<String,Object> element : objectParams.entrySet()) {
			Object value = element.getValue();
			String stringValue = null;
			if (value != null) stringValue = value.toString();
			stringParams.put(element.getKey(), stringValue);
		}
		return stringParams;
	}
	
	public void basicSQLParamsWrap(Map<String, Object> params) {
		for (String key : params.keySet()) {
			Object value = sqlValueWrap(params.get(key));
			if (value != null) params.put(key, value);
		}
	}
	
	public String sqlValueWrap(Object value) {
		return SQLObjectWrapper.wrap(value);
	}
	
	public Map<String, Object> createMap(Object... values) {
		return MapUtils.create(values);
	}
	
	public Map<String, Object> createWrappableMap(Object... values) {
		Map<String, Object> map = createMap(values);
		basicSQLParamsWrap(map);
		return map;
	}
	
	public Object basicFieldTypeTransform(Object value, Field field) {

		Type fieldType = field.getType();
		String fieldName = field.getName();
		
		if (value != null) {
		
			try {
				
				String myValue = value.toString();
				myValue = myValue.trim();
				
				if (Integer.class.equals(fieldType)) {
					value = new Double(myValue).intValue();
				} else if (String.class.equals(fieldType)) {
					value = myValue;
				} else if (BigDecimal.class.equals(fieldType)) {
					value = new BigDecimal(myValue);
				} else if (Long.class.equals(fieldType)) {
					value = Long.parseLong(myValue);
				} else if (Byte.class.equals(fieldType)) {
					value = Byte.parseByte(myValue);
				} else if (Short.class.equals(fieldType)) {
					value = Short.parseShort(myValue); 
				} else if (Double.class.equals(fieldType)) {
					value = Double.parseDouble(myValue);
				} else if (Float.class.equals(fieldType)) {
					value = Float.parseFloat(myValue);
				} else if (BigInteger.class.equals(fieldType)) {
					value = new BigInteger(myValue);
				}
				
			} catch (NumberFormatException e) {
				throw new RuntimeException("Não foi possível setar o valor: "+value+", no campo: "+fieldName+", do tipo: "+fieldType+", da classe: "+field.getDeclaringClass()+". Erro de conversão de tipo.", e);
			}
			
		}
		
		return value;

	}
	
	public StringOut getStringOut() {
		return stringOut;
	}
	
}
