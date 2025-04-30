import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    public static void main(String[] args) {
        // 1분에 한 번씩 Hello World, Hello Antock 을 print 하는 스케쥴러 (start, end 포함)

        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();

            sched.start();

            sched.getListenerManager().addJobListener(new JobListenerLogging2());

            JobDetail job1 = newJob(HelloJob.class)
                    .withIdentity("helloJob", "group1")
                    .build();
            JobDetail job2 = newJob(AntockJob.class)
                    .withIdentity("antockJob", "group1")
                    .build();

            Trigger trigger1 = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(10)
                    .withRepeatCount(5))
                    .build();
            Trigger trigger2 = newTrigger()
                    .withIdentity("myTrigger2", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .withRepeatCount(5))
                    .build();

            sched.scheduleJob(job1, trigger1);
            sched.scheduleJob(job2, trigger2);

        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
        }
    }
}