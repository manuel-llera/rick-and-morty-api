package com.rickandmorty.restapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class Character {
	
	private int id;
	private String name;
	private String status;
	private String species;
	private String type;
	private String gender;
	private List<String> episode;
	private String url;
	private String created;
	
}
