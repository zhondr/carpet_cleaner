package kz.nu.carpet_cleaner.register.scheduler;

import kz.greetgo.scheduling.FromConfig;
import kz.greetgo.scheduling.Scheduled;
import kz.nu.carpet_cleaner.register.configurations.beans.all.AllConfigFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotConfigResetScheduler extends AbstractSchedulerTask{

  private final AllConfigFactory allConfigFactory;

  @FromConfig("reset hot configs")
  @Scheduled("repeat every 30 sec")
  public void execute() {
    allConfigFactory.resetAll();
  }

}
