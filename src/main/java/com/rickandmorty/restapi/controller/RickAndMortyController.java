package com.rickandmorty.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rickandmorty.restapi.domain.CharacterInfoResponse;
import com.rickandmorty.restapi.service.RickAndMortyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@Tag(name = "API REST exercise implementation", description = "API REST to search RickAndMorty character info")
@RequestMapping("")
public class RickAndMortyController {

	@Autowired
	private RickAndMortyService rickAndMortyService;
	
	@Operation(
		      summary = "Search character appearance",
		      description = "Get Api Method in order to get info about RickAndMorty character where name match exactly")
	@GetMapping("/search-character-appearance/")
	public ResponseEntity<CharacterInfoResponse> getRickAndMortyCharacterInfo(
									@NotBlank(message = "Param name can not be empty")
									@RequestParam(name="name", required = true)
									String characterName) {
		
		log.info("\n\n");
		log.info("Starting => RickAndMortyController.getRickAndMortyCharacterInfo => params: name = \"" + characterName + "\"");
		
		CharacterInfoResponse response = rickAndMortyService.getCharacterInfo(characterName);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
