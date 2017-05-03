package com.lu;

import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lu.dao.GameRepository;
import com.lu.dao.MatchRepository;

@SpringBootApplication
public class RawideaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RawideaApplication.class);
	
    @Autowired
    DataSource dataSource;

    @Autowired
    GameRepository gameRepository;
    
    @Autowired
    MatchRepository matchRepository;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(RawideaApplication.class, args);
        System.out.println("List of beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        //Map<String, String> map=new HashMap<String, String>();
       
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.print(beanName);
            
            System.out.print("=");
            System.out.print(ctx.getBean(beanName).toString());
            System.out.println();
        }
	}
}
