package com.rickandmorty.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.rickandmorty.restapi.domain.CharacterInfoResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

	@ExceptionHandler(WebClientResponseException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public CharacterInfoResponse missingServletRequestParameterExceptionHandlerMethod(WebClientResponseException wcrEx,
			WebRequest request) {

		HttpStatusCode cause = wcrEx.getStatusCode();
		if (cause.equals(HttpStatus.NOT_FOUND)) {
			return CharacterInfoResponse.builder().error("Character not found").build();
		}
		// default error message
		return CharacterInfoResponse.builder().error("Error processing response").build();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CharacterInfoResponse missingServletRequestParameterExceptionHandlerMethod(
			MissingServletRequestParameterException mSRPEx, WebRequest request) {

		return CharacterInfoResponse.builder().error("Bad request, required requet param 'name'").build();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CharacterInfoResponse constraintViolationExceptionHandlerMethod(ConstraintViolationException cVEx,
			WebRequest request) {

		return CharacterInfoResponse.builder().error("Bad request, please type a request param 'name' not empty")
				.build();
	}
}