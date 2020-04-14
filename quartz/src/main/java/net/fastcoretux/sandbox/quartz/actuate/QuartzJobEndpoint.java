package net.fastcoretux.sandbox.quartz.actuate;

import java.util.List;

import org.quartz.SchedulerException;

/**
 * @author fastcoretux
 * @since 17.04.2018
 */
public interface QuartzJobEndpoint {

    List<JobInfo> quartzJobs() throws SchedulerException;

}
