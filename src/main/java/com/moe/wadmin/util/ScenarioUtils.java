package com.moe.wadmin.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

public class ScenarioUtils extends BeanUtils{
	public static Map<String, Object> describes(Object bean)
	            throws IllegalAccessException, InvocationTargetException,
	            NoSuchMethodException {
		if (ScenarioUtilsBean.getInstance() instanceof BeanUtilsBean){
			ScenarioUtilsBean.setInstance(new ScenarioUtilsBean());	
		}
		return (Map<String, Object>) ((ScenarioUtilsBean)ScenarioUtilsBean.getInstance()).describes(bean);
	}

	
}