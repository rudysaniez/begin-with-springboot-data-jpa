package com.me.work.api.jpa.bo.controllers;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.me.work.api.jpa.exception.AlreadyExistException;
import com.me.work.character.api.model.HttpErrorInfo;

@RestControllerAdvice
public class MainControllerAdvice {

	/**
	 * @param ex
	 * @return {@link HttpErrorInfo}
	 */
	@ExceptionHandler(value=AlreadyExistException.class)
	@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody HttpErrorInfo alreadyExist(AlreadyExistException ex) {
		return buildHttpErrorInfo(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
	}
	
	/**
	 * @param httpStatus
	 * @param message
	 * @return {@link HttpErrorInfo}
	 */
	private HttpErrorInfo buildHttpErrorInfo(HttpStatus httpStatus, String message) {
		
		HttpErrorInfo out = new HttpErrorInfo();
		out.setHttpStatus(httpStatus.name());
		out.setMessage(message);
		out.setTimestamp(OffsetDateTime.now());
		return out;
	}
}
