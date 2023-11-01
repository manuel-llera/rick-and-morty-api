package com.rickandmorty.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.rickandmorty.restapi.filter.SwaggerIndexRedirectFilter;

@SpringBootApplication
public class RickAndMortyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApiApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<SwaggerIndexRedirectFilter> filterRegistrationBean() {
		FilterRegistrationBean<SwaggerIndexRedirectFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		SwaggerIndexRedirectFilter swaggerIndexRedirect = new SwaggerIndexRedirectFilter();
		filterRegistrationBean.setFilter(swaggerIndexRedirect);
		return filterRegistrationBean;
	}
}
