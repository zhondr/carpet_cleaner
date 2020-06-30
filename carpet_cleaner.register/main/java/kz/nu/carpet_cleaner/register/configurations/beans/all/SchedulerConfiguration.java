package kz.nu.carpet_cleaner.register.configurations.beans.all;

import java.nio.file.Paths;
import java.util.List;
import java.util.TimeZone;
import kz.greetgo.scheduling.collector.SchedulerConfigStore;
import kz.greetgo.scheduling.collector.SchedulerConfigStoreInFile;
import kz.greetgo.scheduling.collector.TaskCollector;
import kz.greetgo.scheduling.scheduler.Scheduler;
import kz.greetgo.scheduling.scheduler.SchedulerBuilder;
import kz.nu.carpet_cleaner.controller.AppFolderPath;
import kz.nu.carpet_cleaner.controller.logging.LOG;
import kz.nu.carpet_cleaner.register.scheduler.AbstractSchedulerTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfiguration {

  private final LOG logger = LOG.forClass(getClass());
  private final List<AbstractSchedulerTask> schedulers;

  private Scheduler scheduler;

  public void startup() {
    logger.info(()-> "ZONE :" + TimeZone.getDefault().toString());
    if (scheduler != null) {
      return;
    }

    SchedulerConfigStore schedulerConfigStore = new SchedulerConfigStoreInFile(
        Paths.get(AppFolderPath.schedulerConfDir()));

    TaskCollector taskCollector = TaskCollector.newTaskCollector()
        .setSchedulerConfigStore(schedulerConfigStore)
        .setConfigExtension(".cfg.txt")
        .setConfigErrorsExtension(".cfg.error.txt");

    for (AbstractSchedulerTask schedulerTask : schedulers) {
      taskCollector.addController(schedulerTask);
    }

    scheduler = SchedulerBuilder.newSchedulerBuilder()
        .setPingDelayMillis(200)
        .setDefaultExecutionPoolSize(100)
        .setExecutionPoolSize("schedulers", 1000)
        .setThrowCatcher((logger::errorThrowable))
        .addTasks(taskCollector.getTasks())
        .build();

    scheduler.startup();
  }

}
