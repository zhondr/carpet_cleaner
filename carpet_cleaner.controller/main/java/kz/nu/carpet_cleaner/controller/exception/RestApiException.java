package kz.nu.carpet_cleaner.controller.exception;

import kz.nu.carpet_cleaner.controller.model.Response;

public class RestApiException extends RuntimeException {


  public final Response response;

  public RestApiException(Response response) {
    this.response = response;
  }

}
