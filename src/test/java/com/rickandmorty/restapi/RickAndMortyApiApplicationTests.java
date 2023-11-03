package com.rickandmorty.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.rickandmorty.restapi.controller.RickAndMortyController;
import com.rickandmorty.restapi.service.RickAndMortyService;

@WebMvcTest(RickAndMortyController.class)
class RickAndMortyApiControllerTests {
	
    private static final String END_POINT_WITHOUT_PARAMS = "/search-character-appearance/";
    private static final String END_POINT_WITH_PARAM = "/search-character-appearance/?name=";
    private static final String XXXXX_PARAM_NAME = "xxxxx";
 
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private RickAndMortyService rickAndMortyService;
    
    @MockBean
    WebClient webClient;
    
    @Test
    void whenIsNotSetPathParamNameVariableReturn400BadRequest() throws Exception {
        mockMvc.perform(get(END_POINT_WITHOUT_PARAMS).contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    
    @Test
    void whenPathParamNameIsEmptyReturn400BadRequest() throws Exception {
        mockMvc.perform(get(END_POINT_WITH_PARAM).contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    
//    @Test
//    void whenCharacterIsNotFoundExactlyReturn404IsNotFound() throws Exception {
//    	
//    	Mockito.when(rickAndMortyService.getCharacterInfo(XXXXX_PARAM_NAME)).thenThrow(WebClientResponseException.class);
//    	Mockito.when(webClient.get()).thenThrow(WebClientResponseException.class);
//    	
//        mockMvc.perform(get(END_POINT_WITH_PARAM + XXXXX_PARAM_NAME).contentType("application/json"))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//        
//    }
    
}
