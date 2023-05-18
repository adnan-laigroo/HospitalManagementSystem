package com.magic.project.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAndValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult br = ex.getBindingResult();
		List<ObjectError> errors = br.getAllErrors();
		List<String> errorMessages = new ArrayList<>();
		for (ObjectError error : errors) {
			if (error instanceof FieldError) {
				FieldError fieldError = (FieldError) error;
				String errorMessage = fieldError.getDefaultMessage();
				errorMessages.add(errorMessage);
			}
		}
		if (errorMessages.isEmpty()) {
			errorMessages.add("Invalid request payload");
		}
		ResponseError response = new ResponseError("Validation Failed", errorMessages);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex) {
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add(ex.getMessage());
		ResponseError response = new ResponseError("Patient Not Found", errorMessages);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<Object> handleDoctorNotFoundException(DoctorNotFoundException ex) {
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add(ex.getMessage());
		ResponseError response = new ResponseError("Doctor Not Found", errorMessages);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(TimeInfoException.class)
	public ResponseEntity<Object> handleTimeInfoException(TimeInfoException ex) {
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add(ex.getMessage());
		ResponseError response = new ResponseError("Time Info Exception", errorMessages);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
