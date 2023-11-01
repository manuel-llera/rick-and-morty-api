package com.rickandmorty.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.rickandmorty.restapi.controller.RickAndMortyController;
import com.rickandmorty.restapi.service.RickAndMortyService;

@SpringBootTest
@ContextConfiguration(classes=RickAndMortyApiApplication.class)
@WebMvcTest(RickAndMortyController.class)
class RickAndMortyApiApplicationTests {
	
    private static final String END_POINT_WITHOUT_PARAMS = "/search-character-appearance/";
 
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private RickAndMortyService rickAndMortyService;
 
//    @Test
//    void testWhenIsNotSetPathParamNameVariableReturn400BadRequest() throws Exception {
//    	
//        mockMvc.perform(get(END_POINT_WITHOUT_PARAMS).contentType("application/json"))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//        
//        
//    }
 
}
