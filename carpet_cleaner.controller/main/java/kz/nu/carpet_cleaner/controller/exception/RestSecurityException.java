package kz.nu.carpet_cleaner.controller.exception;

import kz.nu.carpet_cleaner.controller.model.RestSecurityMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestSecurityException extends RuntimeException {

  public final RestSecurityMessage message;

}
