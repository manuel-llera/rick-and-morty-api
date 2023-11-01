package com.rickandmorty.restapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInfoDto {

	private ResponseInfo info;
	private List<Character> results;
	private String error;

}
