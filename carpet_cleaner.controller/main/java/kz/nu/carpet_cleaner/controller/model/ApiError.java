package kz.nu.carpet_cleaner.controller.model;

import java.util.Date;
import lombok.var;

public class ApiError {

  public String url;
  public int status;
  public Date timestamp = new Date();
  public String body;


  public static ApiError of(Response<String> response) {
    var apiError = new ApiError();
    apiError.body = response.body.replaceAll("\n", "").replaceAll("\t", "");
    apiError.status = response.status;
    apiError.url = response.url;
    return apiError;
  }

}
