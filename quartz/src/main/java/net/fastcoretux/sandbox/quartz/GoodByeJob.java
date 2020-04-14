package net.fastcoretux.sandbox.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author fastcoretux
 * @since 16.04.2018
 */
public class GoodByeJob extends AbstractJob {

    @Override
    protected void run(final JobExecutionContext context) throws JobExecutionException {
        wait(10000);
        log.info("Hasta la vista, baby!");
    }

}
