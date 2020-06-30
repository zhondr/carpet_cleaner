package kz.nu.carpet_cleaner.register.hotconfig;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("SMTP server options for sending emails")
public interface EmailSenderConfig {

  @Description("Mailing folder (relative to ~ / service_bus.d /)")
  @DefaultStrValue("email/toSend")
  String toSendDir();

  @Description("Sent mail folder (relative to ~ / service_bus.d /)")
  @DefaultStrValue("email/sent")
  String sentDir();

  @Description("Number of days to keep sent emails")
  @DefaultIntValue(5)
  int mailStoreDay();

  @Description("Mail Sender")
  @DefaultStrValue("AIX")
  String from();

  @Description("SMTP Server: Server Address")
  @DefaultStrValue("smtp.office365.com")
  String serverHost();

  @Description("SMTP server: Port")
  @DefaultIntValue(587)
  int serverPort();

  @Description("SMTP Server: Username")
  @DefaultStrValue("reports@aix.kz")
  String serverUsername();

  @Description("SMTP Server: User Password")
  @DefaultStrValue("secret")
  String serverPassword();

  @Description("SMTP server: Mail sending protocol")
  @DefaultStrValue("smtp")
  String transportProtocol();

  @Description("SMTP Server: Authentication")
  @DefaultBoolValue(false)
  boolean smtpAuth();

  @Description("SMTP server: Connect using SSL")
  @DefaultBoolValue(true)
  boolean smtpSSLEnable();

  @Description("SMTP server: Connect using TLS")
  @DefaultBoolValue(true)
  boolean smtpTLSEnable();

  @Description("SMTP Server: Debug Mode")
  @DefaultBoolValue(false)
  boolean debug();

}