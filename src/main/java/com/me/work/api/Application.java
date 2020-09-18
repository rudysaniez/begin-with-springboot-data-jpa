package com.me.work.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.me.work.api.jpa.mapper.CharacterMapper;
import com.me.work.api.jpa.mapper.CharacterMapperImpl;
import com.me.work.api.jpa.mapper.PageMetadataMapper;
import com.me.work.api.jpa.mapper.PageMetadataMapperImpl;
import com.me.work.api.jpa.mapper.SpellMapper;
import com.me.work.api.jpa.mapper.SpellMapperImpl;

import lombok.Getter;
import lombok.Setter;

@EnableCaching
@EnableJpaRepositories
@ComponentScan(basePackages="com.me.work.api")
@EnableConfigurationProperties(Application.ApiPaginationInformation.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CharacterMapper characterMapper() {
		return new CharacterMapperImpl();
	}
	
	@Bean
	public SpellMapper spellMapper() {
		return new SpellMapperImpl();
	}
	
	@Bean
	public PageMetadataMapper pageMetadataMapper() {
		return new PageMetadataMapperImpl();
	}
	
	/**
	 * @author rudysaniez @since 0.0.1
	 */
	@Getter @Setter
	@ConfigurationProperties(prefix="api.pagination")
	public static class ApiPaginationInformation {
		
		private int defaultPageNumber;
		private int defaultPageSize;
	}
}