package net.fastcoretux.sandbox.quartz;

/**
 * @author fastcoretux
 * @since 16.04.2018
 */

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob extends AbstractJob {

    @Override
    protected void run(final JobExecutionContext context) throws JobExecutionException {
        wait(20000);
        log.info("Hello World!");
    }

}
