package kz.nu.carpet_cleaner.controller.model;

public class RestSecurityMessage {

  public final String reason;
  public final String url;


  private RestSecurityMessage(String reason, String url) {
    this.reason = reason;
    this.url = url;
  }

  public static RestSecurityMessage of(String reason, String url) {
    return new RestSecurityMessage(reason, url);
  }

}
