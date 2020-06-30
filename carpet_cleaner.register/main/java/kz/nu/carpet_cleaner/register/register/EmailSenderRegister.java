package kz.nu.carpet_cleaner.register.register;

import java.util.List;
import kz.greetgo.email.Attachment;
import kz.greetgo.email.Email;

public interface EmailSenderRegister {

  Email prepareEmail(String from, String to, String subject, String body);

  Email prepareEmailWithAttachments(String from, String to,
                                    String subject, String body, List<Attachment> attachments);

  Email prepareEmailWithCopies(String from, String to, String subject, String body, List<String> copies);

  Email prepareEmailWithCopiesAndAttachments(String from, String to,
                                             String subject, String body, List<String> copies,
                                             List<Attachment> attachments);

  void sendEmailFromSystem(String to, String subject, String body);

  void sendEmail(Email email);

  void sendAllExistingEmails();

  void cleanOldSentEmails();

}
