package com.moe.wadmin.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ScenarioUtilsBean extends BeanUtilsBean {

	static public void setScenario(List<?> beans, String scenario) {
		for (Object bean : beans) {
			((ScenarioModel) bean).setScenario(scenario);
		}
		;
	}

	public List<?> describes(List<?>beans) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<Object> result = new ArrayList<Object>();
		for (Object bean : beans) {
			Class<?> clazz = bean.getClass();
			if (bean == null || clazz.equals(String.class) ||   
	    	         clazz.equals(Integer.class)||   
	    	         clazz.equals(Byte.class) ||   
	    	         clazz.equals(Long.class) ||   
	    	         clazz.equals(Double.class) ||   
	    	         clazz.equals(Float.class) ||   
	    	         clazz.equals(Character.class) ||   
	    	         clazz.equals(Short.class) ||   
	    	         clazz.equals(BigDecimal.class) ||   
	    	         clazz.equals(BigInteger.class) ||   
	    	         clazz.equals(Boolean.class) ||   
	    	         clazz.equals(Date.class) ||   
//	    	         clazz.equals(DateTime.class) ||
	    	         clazz.isPrimitive() ){
				result.add(bean);	
			} else {
				@SuppressWarnings("unchecked")
				Map<String, Object>object =  (Map<String, Object>) this.describes(bean);
				result.add(object);
			}
		}
		return result;	
	}

	public Object describes(Object bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (bean == null) {
			return null;
		}

		Class<? extends Object> clazz = null;
		if (bean != null) {
			clazz = bean.getClass();
			if (bean == null || clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class)
					|| clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class)
					|| clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class)
					|| clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) ||
					// clazz.equals(DateTime.class) ||
					clazz.isPrimitive()) {
				return bean;
			}
		}

		List<String> fields;
		if (bean instanceof ScenarioModel) {
			fields = ((ScenarioModel) bean).fields();
		} else if (bean instanceof Map) {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> beanMap = (Map<String, Object>) bean;
			Set<String> keys = beanMap.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = beanMap.get(key);
				Object object = this.describes(value);
				result.put(key, object);
			}
			return (Map<String, Object>) result;
		} else if (bean instanceof Page || bean instanceof List) {
			return this.describes((List<?>) bean);
		} else {
			fields = ScenarioModel.getBeanAttributes(bean);
		}

		Map<String, Object> description = new HashMap<String, Object>();

		for (int i = 0; i < fields.size(); i++) {
			String name = fields.get(i);
			Object value = getPropertyUtils().getNestedProperty(bean, name);
			if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(getProperty(bean, name))) {
				Object in = this.describes(value);
				description.put(name, in);
			}
		}

		return (description);
	}

}
