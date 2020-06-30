package kz.nu.carpet_cleaner.controller.controller.advice;

import kz.nu.carpet_cleaner.controller.exception.RestSecurityException;
import kz.nu.carpet_cleaner.controller.model.ApiError;
import kz.nu.carpet_cleaner.controller.exception.RestApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiAdvice {

  @ExceptionHandler(RestApiException.class)
  public ResponseEntity<ApiError> handleRemoteServiceException(RestApiException e) {
    return new ResponseEntity<>(ApiError.of(e.response), HttpStatus.valueOf(e.response.status));
  }

  @ExceptionHandler(RestSecurityException.class)
  public ResponseEntity<?> handleApiSecurityException(RestSecurityException e) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    return new ResponseEntity<>(e.message, headers, HttpStatus.UNAUTHORIZED);
  }

}
