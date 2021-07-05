package com.vdf.assessment.garage.handlers;

import com.vdf.assessment.garage.exceptions.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHelper {

  @ExceptionHandler(value = { ResourceNotFoundException.class})
  public ResponseEntity<Object> handleInvalidInputException(ResourceNotFoundException ex) {
    log.error(ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = { UnsupportedOperationException.class})
  public ResponseEntity<Object> handleInvalidInputException(UnsupportedOperationException ex) {
    log.error(ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<String> handleConflict(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value ={MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    log.error(ex.getMessage());
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return  ResponseEntity.badRequest().body(errors.values());
  }

  @ExceptionHandler(value = { HttpMessageNotReadableException.class })
  public ResponseEntity<Object> handleException(HttpMessageNotReadableException ex) {
    log.error(ex.getMessage(),ex);
    return  ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Object> handleException(Exception ex) {
    log.error(ex.getMessage(),ex);
    return  ResponseEntity.internalServerError().body("An unexpected error has occurred");
  }



}