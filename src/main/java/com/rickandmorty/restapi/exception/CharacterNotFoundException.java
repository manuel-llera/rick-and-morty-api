package com.rickandmorty.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Exception message => Character not found")
public class CharacterNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -9085488803788424530L;
	
  // TODO: implement correct behavior to use or delete Exception class
	
}