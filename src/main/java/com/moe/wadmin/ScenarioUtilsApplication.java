package com.moe.wadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 
 * 
 * @author xxx
 * @version 1.0.0 2017年5月23日 下午6:38:58
 */
@SpringBootApplication
public class ScenarioUtilsApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(ScenarioUtilsApplication.class, args);
		
		cac.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
	        @Override
	        public void onApplicationEvent(ContextClosedEvent event) {
	            
	        }
	    });

	}
}
