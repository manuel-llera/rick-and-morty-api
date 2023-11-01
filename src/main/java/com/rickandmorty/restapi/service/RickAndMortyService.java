package com.rickandmorty.restapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.rickandmorty.restapi.domain.CharacterInfoResponse;
import com.rickandmorty.restapi.dto.Character;
import com.rickandmorty.restapi.dto.CharacterInfoDto;
import com.rickandmorty.restapi.dto.EpisodeInfoDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RickAndMortyService {

	private static final String CHARACTER_INFO_URL = "https://rickandmortyapi.com/api/character/?name={characterName}&page={pageNumber}";
	private static final String NOT_MATCHES_FOUND = "Not matches found";
	private static final Locale LOCALE_US = new Locale("en", "US");

	private final WebClient webClient;

	public RickAndMortyService(WebClient webClient) {
		this.webClient = webClient;
	}

	/**
	 * Method in order to get info about some character of RichAndMorty using public ApiRest
	 * 
	 * @param characterNameToConsult character to get info
	 * @return 
	 * 		CharacterInfoResponse Character response info format required
	 */
	public CharacterInfoResponse getCharacterInfo(final String characterNameToConsult) {

		log.debug("Starting => RickAndMortyService.getCharacterInfo");
		
		// HashSet Collection to avoid duplicated episode names
		Set<String> episodes = new HashSet<>();
		String firstApppearance = null;
		CharacterInfoDto characterInfoDto = null;
		
		int pageNumber = 0;

		do {
			characterInfoDto = webClient.get()
					.uri(getCharacterInfoUrl(), getCharacterInfoParams(characterNameToConsult, ++pageNumber)).retrieve()
					.toEntity(CharacterInfoDto.class).block().getBody();

			for (Character characterInfoToProcess : characterInfoDto.getResults()) {
				firstApppearance = processCharacterInfo(characterNameToConsult, episodes, firstApppearance, characterInfoToProcess);
			}
			
		} while (characterInfoDto.getInfo().getNext() != null);

		return composeResponse(characterNameToConsult, episodes, firstApppearance);
	}

	private String processCharacterInfo(final String characterNameToConsult, Set<String> episodes, String firstApppearance, Character characterInfoToProcess) {
		
		SimpleDateFormat readApiDateFormat = new SimpleDateFormat("MMMM dd, yyyy", LOCALE_US);
		SimpleDateFormat responseDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// process only info with perfect match name
		if (characterNameToConsult.equals(characterInfoToProcess.getName())) {
			log.debug("OK Match in response => adding info");

			for (String episode : characterInfoToProcess.getEpisode()) {
				ResponseEntity<EpisodeInfoDto> episodeInfoResponse = webClient.get().uri(getEpisodeUrl(episode))
						.retrieve().toEntity(EpisodeInfoDto.class).block();

				EpisodeInfoDto episodeInfoDto = episodeInfoResponse.getBody();
				episodes.add(episodeInfoDto.getName());

				if (firstApppearance == null) {
					try {
						Date firstApppearanceDate = readApiDateFormat.parse(episodeInfoDto.getAirDate());
						firstApppearance = responseDateFormat.format(firstApppearanceDate);
					} catch (ParseException pEx) {
						log.error("Exception formating episodeInfoDto airDate" + pEx);
					}
				}

			} // End-for episodes

		} else {
			log.debug("NOT Match => skipping character info with name = \"" + characterInfoToProcess.getName() + "\"");
		}
		return firstApppearance;
	}

	private CharacterInfoResponse composeResponse(final String characterNameToConsult, Set<String> episodes,
			String firstApppearance) {
		String errorMessage = null;

		if (episodes.isEmpty()) {
			episodes = null;
			errorMessage = NOT_MATCHES_FOUND;
		}

		return CharacterInfoResponse.builder().name(characterNameToConsult).episodes(episodes)
				.firstApppearance(firstApppearance).error(errorMessage).build();
	}

	private String getCharacterInfoUrl() {
		return UriComponentsBuilder.fromHttpUrl(CHARACTER_INFO_URL).encode().toUriString();
	}

	private String getEpisodeUrl(final String episode) {
		return UriComponentsBuilder.fromHttpUrl(episode).encode().toUriString();
	}

	private Map<String, String> getCharacterInfoParams(String characterName, int pageNumber) {
		log.debug("pageNumber " + pageNumber);

		Map<String, String> params = new HashMap<>();
		params.put("characterName", characterName);
		params.put("pageNumber", String.valueOf(pageNumber));
		return params;
	}

}