package net.fastcoretux.sandbox.quartz.actuate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

/**
 * @author fastcoretux
 * @since 16.04.2018
 */
@Endpoint(id = "qrtzjobs")
@Slf4j
public class QuartzJobEndpointImpl implements QuartzJobEndpoint {

    private final Scheduler scheduler;

    public QuartzJobEndpointImpl(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @ReadOperation
    @Override
    public List<JobInfo> quartzJobs() {
        try {
            final List<JobExecutionContext> jobs = scheduler.getCurrentlyExecutingJobs();
            return jobs.stream().map(QuartzJobEndpointImpl::getJobInfo).collect(Collectors.toList());
        } catch (SchedulerException e) {
            log.error("Can't obtain currently executing jobs", e);
        }

        return Collections.emptyList();
    }

    @WriteOperation
    public void perform(final Operation operation) throws SchedulerException {
        switch (operation) {
            case START:
                scheduler.resumeJobs(GroupMatcher.anyJobGroup());
                log.info("All jobs resumed");
                break;
            case STOP:
                scheduler.pauseJobs(GroupMatcher.anyJobGroup());
                log.info("All jobs paused");
                break;
            default:
                throw new IllegalArgumentException(String.format("Operation %s not supported", operation));
        }
    }

    // CLASS
    public enum Operation {
        START,
        STOP
    }


    // INTERNAL
    private static JobInfo getJobInfo(final JobExecutionContext ctx) {
        final JobInfo i = new JobInfo();
        i.setTriggerName(ctx.getTrigger().getKey().getName());
        i.setTriggerGroup(ctx.getTrigger().getKey().getGroup());
        i.setCalendarName(ctx.getTrigger().getCalendarName());
        i.setJobName(ctx.getJobDetail().getKey().getName());
        i.setJobGroup(ctx.getJobDetail().getKey().getGroup());
        i.setRefireCount(ctx.getRefireCount());
        i.setFireTime(localDateTime(ctx.getFireTime()));
        i.setScheduledFireTime(localDateTime(ctx.getScheduledFireTime()));
        i.setPreviousFireTime(localDateTime(ctx.getPreviousFireTime()));
        i.setNextFireTime(localDateTime(ctx.getNextFireTime()));
        i.setJobRunTime(ctx.getJobRunTime());
        i.setFireInstanceId(ctx.getFireInstanceId());
        try {
            i.setSchedulerName(ctx.getScheduler().getSchedulerName());
        } catch (SchedulerException e) {
            log.warn("Can't obtain scheduler name", e);
        }
        return i;
    }

    private static LocalDateTime localDateTime(final Date date) {
        if(date == null) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
