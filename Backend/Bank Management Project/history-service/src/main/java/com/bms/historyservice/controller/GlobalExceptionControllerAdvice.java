package com.bms.historyservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bms.historyservice.exceptions.BadHistoryException;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

	public GlobalExceptionControllerAdvice() {
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "Not Found");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadHistoryException.class)
	public ResponseEntity<?> handleBadProfileException(BadHistoryException exp) {
		Map<String, String> map = new HashMap<>();
		map.put("message", exp.getMessage());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUnhandledExceptions(Exception exp) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "Something went wrong.");
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}