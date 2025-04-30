import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

public class JobListenerLogging2 extends JobListenerSupport {
    @Override
    public String getName() {
        return "JobListenerLogging2";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("Job Started");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("Job Ended");
    }
}
