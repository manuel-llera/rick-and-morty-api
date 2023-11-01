package com.rickandmorty.restapi.dto;

import lombok.Data;

@Data
public class ResponseInfo {

	private int count;
	private int pages;
    private String next;
    private String prev;
    
}
