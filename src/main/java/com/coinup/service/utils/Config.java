package com.coinup.service.utils;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.coinup.dao.stringout.JPAStringOutDaoHelper;

import me.dabpessoa.stringout.enums.StringOutType;

@Configuration
public class Config {

	@Autowired
	private ApplicationContext springContext;
	
	@Bean
	@Lazy(true)
	public JPAStringOutDaoHelper stringOutJPAHelper() {
		return new JPAStringOutDaoHelper((EntityManager)springContext.getBean("entityManager"), StringOutType.JSON);
	}
	
}
