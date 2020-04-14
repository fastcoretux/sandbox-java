package net.fastcoretux.sandbox.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author fastcoretux
 * @since 16.04.2018
 */
public abstract class AbstractJob extends QuartzJobBean {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected String name;

    // Invoked if a Job data map entry with that name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        log.info("{} [STARTED] Thread: {}", name, Thread.currentThread().getName());
        run(context);
        log.info("{} [DONE] Thread: {}", name, Thread.currentThread().getName());
    }

    protected abstract void run(JobExecutionContext context) throws JobExecutionException;

    protected static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
