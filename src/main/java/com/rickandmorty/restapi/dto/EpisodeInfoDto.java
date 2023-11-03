package com.rickandmorty.restapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EpisodeInfoDto {

	private int id;
	private String name;
	
	@JsonProperty("air_date")
	private String airDate;
	
	private String episode;
	private List<String> characters;
	private String url;
	private String created;
	
}
