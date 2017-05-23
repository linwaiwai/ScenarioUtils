package com.moe.wadmin.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.moe.wadmin.pojo.RestResponse;
import com.moe.wadmin.util.ScenarioModel;
import com.moe.wadmin.util.ScenarioUtilsBean;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	 @Bean
	 public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter () {
	     return new MappingJackson2HttpMessageConverter () {
	    	 protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
    				throws IOException, HttpMessageNotWritableException {
	    		 if (object instanceof RestResponse){
	    			 RestResponse<?> response  = (RestResponse<?>)object ;
	    			 try {
	    				 ScenarioUtilsBean.setInstance(new ScenarioUtilsBean());
	    				 if (response.getData() instanceof List){
	    					 List<Map<String, Object>> result;
	    					 result = ( (ScenarioUtilsBean) ScenarioUtilsBean.getInstance()).describes((List)response.getData());
	    					 super.writeInternal(new RestResponse(result,response.getCode(),response.getMessage()), type, outputMessage);
	    				 } else {
	    					 Map<String, Object> map = null;
	    					 map =(Map<String, Object>) ( (ScenarioUtilsBean) ScenarioUtilsBean.getInstance()).describes(response.getData());	
	    					 super.writeInternal(new RestResponse<Map<String, Object>>(map,response.getCode(),response.getMessage()), type, outputMessage);
	    				 }
	    			 } catch (IllegalAccessException e) {
	    				 e.printStackTrace();
	    				 super.writeInternal(object, type, outputMessage);
	    			 } catch (InvocationTargetException e) {
	    				 e.printStackTrace();
	    				 super.writeInternal(object, type, outputMessage);
	    			 } catch (NoSuchMethodException e) {
	    				 e.printStackTrace();
	    				 super.writeInternal(object, type, outputMessage);
	    			 }
	    		 } else {
	    			 super.writeInternal(object, type, outputMessage);
	    		 }
	         }
	     };
	 }
	// 添加自定义转换器
	 @Override
	 public void configureMessageConverters (List<HttpMessageConverter<?>> converters) {
	     converters.add (mappingJackson2HttpMessageConverter ());
	     super.configureMessageConverters (converters);
	 }
}
