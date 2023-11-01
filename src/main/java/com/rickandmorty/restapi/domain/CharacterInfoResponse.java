package com.rickandmorty.restapi.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class CharacterInfoResponse {

	private String name;
	private Set<String> episodes;
	private String error;
	
	@JsonProperty("first_apppearance")
	private String firstApppearance;

}
