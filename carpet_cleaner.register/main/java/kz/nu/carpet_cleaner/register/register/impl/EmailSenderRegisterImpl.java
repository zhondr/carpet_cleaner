package kz.nu.carpet_cleaner.register.register.impl;

import java.util.List;
import kz.greetgo.email.Attachment;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import kz.nu.carpet_cleaner.controller.logging.LOG;
import kz.nu.carpet_cleaner.register.hotconfig.EmailSenderConfig;
import kz.nu.carpet_cleaner.register.register.EmailSenderRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderRegisterImpl implements EmailSenderRegister {

  private final EmailSender emailSender;
  private final EmailSenderController emailSenderController;
  private final EmailSenderConfig emailSenderConfig;

  @Override
  public Email prepareEmail(String from, String to, String subject, String body) {
    return initEmail(from, to, subject, body);
  }

  @Override
  public Email prepareEmailWithAttachments(String from, String to,
                                           String subject, String body,
                                           List<Attachment> attachments) {

    Email email = initEmail(from, to, subject, body);

    if (attachments != null && !attachments.isEmpty()) {
      email.getAttachments().addAll(attachments);
    }

    return email;
  }

  @Override
  public Email prepareEmailWithCopies(String from, String to, String subject, String body, List<String> copies) {
    Email email = initEmail(from, to, subject, body);

    if (copies != null && !copies.isEmpty()) {
      email.getCopies().addAll(copies);
    }

    return email;
  }

  @Override
  public Email prepareEmailWithCopiesAndAttachments(String from, String to,
                                                    String subject, String body, List<String> copies,
                                                    List<Attachment> attachments) {
    Email email = initEmail(from, to, subject, body);

    if (copies != null && !copies.isEmpty()) {
      email.getCopies().addAll(copies);
    }

    if (attachments != null && !attachments.isEmpty()) {
      email.getAttachments().addAll(attachments);
    }

    return email;
  }

  @Override
  public void sendEmailFromSystem(String to, String subject, String body) {
    Email email = initEmail(emailSenderConfig.from(), to, subject, body);
    sendEmail(email);
  }

  @Override
  public void sendEmail(Email email) {
    long start = System.currentTimeMillis();

    getLogger().trace(() -> "START SAVE EMAIL: " + email);

    emailSender.send(email);

    getLogger().trace(() -> "CLEAN SAVE EMAIL : time=" + (System.currentTimeMillis() - start));
  }

  @Override
  public void sendAllExistingEmails() {
    long start = System.currentTimeMillis();

    getLogger().trace(() -> "START SEND EMAIL");

    emailSenderController.sendAllExistingEmails();

    getLogger().trace(() -> "CLEAN SEND EMAIL : time=" + (System.currentTimeMillis() - start));
  }

  @Override
  public void cleanOldSentEmails() {
    long start = System.currentTimeMillis();

    emailSenderController.cleanOldSentFiles(emailSenderConfig.mailStoreDay());

    getLogger().info(() -> "CLEAN OLD EMAILS : time=" + (System.currentTimeMillis() - start));
  }

  protected LOG getLogger() {
    return LOG.byName("EMAIL");
  }

  private Email initEmail(String from, String to, String subject, String body) {
    Email email = new Email();
    email.setFrom(from);
    email.setTo(to);
    email.setSubject(subject);
    email.setBody(body);
    return email;
  }

}
