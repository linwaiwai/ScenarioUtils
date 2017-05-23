package com.moe.wadmin.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MethodUtils;


public class ScenarioModel {
	protected static final String DEFAULT_SCENARIO = "default";
	private String _scenario = ScenarioModel.DEFAULT_SCENARIO;
	static public List<String> getBeanAttributes(Object bean){
		List<String> fields = new ArrayList<String>();
		
		if (bean instanceof DynaBean) {
            DynaProperty[] descriptors =
                ((DynaBean) bean).getDynaClass().getDynaProperties();
            for (int i = 0; i < descriptors.length; i++) {
            	
                String name = descriptors[i].getName();
                fields.add(name);
            }
        } else {
            PropertyDescriptor[] descriptors =
            		BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptors(bean);
            Class<?> clazz = bean.getClass();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if ((MethodUtils.getAccessibleMethod(clazz, descriptors[i].getReadMethod()))!=null) {
                	if (!name.equals("class"))
                		fields.add(name); 
                }
            }
        }
		return fields;
	}
	
	public HashMap<String, List<String>> scenarios(){
		HashMap<String, List<String>> scenarios  = new HashMap<String, List<String>>();
		scenarios.put(ScenarioModel.DEFAULT_SCENARIO, ScenarioModel.getBeanAttributes(this));
		return scenarios;
	}
	
	public String getScenario(){
		return this._scenario;
	}
	
	public void setScenario(String scenario){
		this._scenario = scenario;
	}
	
	public  List<String> fields() {
		String scenario = this.getScenario();
		HashMap<String, List<String>> scenarios = this.scenarios();
		if (!scenarios.containsKey(scenario)){
			return new ArrayList<String>();
		}
		
		return scenarios.get(scenario);	
	}
}







