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

            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(30)
                    .repeatForever())
                    .build();

            System.out.println("job started");

            sched.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}