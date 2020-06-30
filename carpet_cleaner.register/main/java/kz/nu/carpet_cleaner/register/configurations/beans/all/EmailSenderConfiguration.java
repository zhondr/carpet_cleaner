package kz.nu.carpet_cleaner.register.configurations.beans.all;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Properties;
import kz.greetgo.email.Attachment;
import kz.greetgo.email.EmailSaver;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.greetgo.email.from_spring.javamail.JavaMailSenderImpl;
import kz.greetgo.email.from_spring.javamail.MimeMessageHelper;
import kz.nu.carpet_cleaner.register.hotconfig.EmailSenderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.internet.MimeUtility;

import static kz.nu.carpet_cleaner.controller.AppFolderPath.appDir;

@Configuration
@RequiredArgsConstructor
public class EmailSenderConfiguration {

  private final EmailSenderConfig config;

  @Bean
  public EmailSender createEmailSender() {
    return new EmailSaver("aix", appDir() + "/" + config.toSendDir());
  }

  @Bean
  public EmailSenderController createEmailSenderController() {
    File toSendDir = appDir().resolve(config.toSendDir()).toFile();
    File sentDir = appDir().resolve(config.sentDir()).toFile();

    return new EmailSenderController(getEmailSender(), toSendDir, sentDir);
  }

  private EmailSender getEmailSender() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost(config.serverHost());
    sender.setPort(config.serverPort());
    sender.setUsername(config.serverUsername());
    sender.setPassword(config.serverPassword());

    Properties properties = new Properties();
    properties.put("mail.transport.protocol", config.transportProtocol());
    properties.put("mail.smtp.auth", Boolean.toString(config.smtpAuth()));
    properties.put("mail.smtp.ssl.enable", Boolean.toString(config.smtpSSLEnable()));
    properties.put("mail.smtp.ssl.trust", config.serverHost());
    properties.put("mail.smtp.starttls.enable", Boolean.toString(config.smtpTLSEnable()));
    properties.put("mail.debug", Boolean.toString(config.debug()));
    sender.setJavaMailProperties(properties);

    return email -> {
      try {
        sender.send(mimeMessage -> {
          MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
          message.setFrom(email.getFrom());
          message.setTo(email.getTo());
          message.setSubject(email.getSubject());
          message.setText(email.getBody(), true);

          for (Attachment attachment : email.getAttachments()) {
            message
                .addAttachment(MimeUtility.encodeText(attachment.name), () -> new ByteArrayInputStream(attachment.data),
                               "application/octet-stream");
          }
          for (String copy : email.getCopies()) {
            message.addCc(copy);
          }
        });
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

}
