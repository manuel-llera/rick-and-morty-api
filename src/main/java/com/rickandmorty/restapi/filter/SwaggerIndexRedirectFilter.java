package com.rickandmorty.restapi.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SwaggerIndexRedirectFilter implements Filter {
	
	private static final String SWAGGER_INDEX_URL = "/swagger-ui/index.html";
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        // root path will be redirect to swagger-ui index.html page
        if (request.getRequestURI().equals("") || request.getRequestURI().equals("/")){
            response.sendRedirect(SWAGGER_INDEX_URL);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
    
}
