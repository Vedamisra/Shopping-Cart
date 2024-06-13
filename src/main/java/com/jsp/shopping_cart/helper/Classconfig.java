package com.jsp.shopping_cart.helper;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@ComponentScan(basePackages = "com")
public class Classconfig {

	@Bean
	public EntityManagerFactory getEmf() {
		return Persistence.createEntityManagerFactory("dev");
	}
	
	@Bean
	public InternalResourceViewResolver getIrr() {
		InternalResourceViewResolver ir = new InternalResourceViewResolver();
		
		ir.setSuffix(".jsp");
		ir.setPrefix("/");
		
		return ir;
	}
}


