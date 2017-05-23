package com.moe.wadmin;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.moe.wadmin.controller.ProjectController;
import com.moe.wadmin.pojo.Project;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


/**
 * 
 * 
 * @author xxx
 * @version 1.0.0 2017年5月23日 下午6:38:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class ScenarioUtilsApplicationTest {
	
	@Autowired
	public ProjectController projectController;
	@Autowired
    private TestRestTemplate restTemplate ;
	
	@Test
	public void test(){
		String project = restTemplate.getForObject("/detail", String.class);
		String detailResult = "{\"data\":{\"name\":\"hello\",\"id\":1},\"code\":1,\"message\":\"ok\"}";
        assertEquals(detailResult,project );
        
        String project1 = restTemplate.getForObject("/list", String.class);
		String detailResult1 = "{\"data\":{\"id\":1},\"code\":1,\"message\":\"ok\"}";
        assertEquals(detailResult1,project1 );
	}
	
}
