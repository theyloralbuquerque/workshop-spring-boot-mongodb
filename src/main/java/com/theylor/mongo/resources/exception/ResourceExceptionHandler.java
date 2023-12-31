package com.theylor.mongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.theylor.mongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Define que essa classe é responsável por tratar possíveis erros nas minhas requisições.
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) // Indica que esse método será invocado quando houver uma ObjectNotFoundException.
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND; // Código 404.
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", 
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
