package kz.nu.carpet_cleaner.register.scheduler;

import kz.greetgo.scheduling.FromConfig;
import kz.greetgo.scheduling.Scheduled;
import kz.nu.carpet_cleaner.register.register.EmailSenderRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("prod")
public class EmailSenderScheduler extends AbstractSchedulerTask {

  private final EmailSenderRegister emailSenderRegister;

  @Scheduled("repeat every 10 second")
  @FromConfig("Sending emails")
  public void sendAllExistingEmails() {
    emailSenderRegister.sendAllExistingEmails();
  }

  @Scheduled("00:00")
  @FromConfig("Delete sent emails")
  public void cleanOldSentEmails() {
    emailSenderRegister.cleanOldSentEmails();
  }

}
